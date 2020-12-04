// tag::handler[]
package shopping.cart;

import akka.projection.eventsourced.EventEnvelope;
import akka.projection.jdbc.javadsl.JdbcHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class ItemPopularityProjectionHandler
    extends JdbcHandler<EventEnvelope<ShoppingCart.Event>, HibernateJdbcSession> { // <1>
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final String tag;
  private final ItemPopularityRepository repo;

  public ItemPopularityProjectionHandler(String tag, ItemPopularityRepository repo) {
    this.tag = tag;
    this.repo = repo;
  }

  @Override
  public void process(
      HibernateJdbcSession session, EventEnvelope<ShoppingCart.Event> envelope) { // <2>
    ShoppingCart.Event event = envelope.event();

    if (event instanceof ShoppingCart.ItemAdded) { // <3>
      ShoppingCart.ItemAdded added = (ShoppingCart.ItemAdded) event;
      String itemId = added.itemId;

      // FIXME testing entityManager operations (within same transaction)
      EntityManager entityManager = session.entityManager();
      CriteriaBuilder builder = entityManager.getCriteriaBuilder();
      CriteriaQuery<ItemPopularity> criteria = builder.createQuery(ItemPopularity.class);
      Root<ItemPopularity> root = criteria.from(ItemPopularity.class);
      criteria.select(root).where(builder.equal(root.get("itemId"), itemId + "_a"));
      try {
        ItemPopularity result = entityManager.createQuery(criteria).getSingleResult();
        logger.info("Existing {}: {}", result.getItemId(), result.getCount());
          ItemPopularity updatedItemPop = result.changeCount(added.quantity);
          entityManager.merge(updatedItemPop);
      } catch (NoResultException e) {
        logger.info("New {}", itemId + "_a");
        ItemPopularity newItemPop = new ItemPopularity(itemId + "_a", 0, added.quantity);
        entityManager.persist(newItemPop);
      }

      ItemPopularity existingItemPop =
          repo.findById(itemId).orElseGet(() -> new ItemPopularity(itemId, 0, 0));
      ItemPopularity updatedItemPop = existingItemPop.changeCount(added.quantity);
      repo.save(updatedItemPop);
      logger.info(
          "ItemPopularityProjectionHandler({}) item popularity for '{}': [{}]",
          this.tag,
          itemId,
          updatedItemPop.getCount());

      // FIXME tested that this will rollback both above operations and the offset storage
//      throw new RuntimeException("Simulated exc in projection handler");
      // end::handler[]
    } else if (event instanceof ShoppingCart.ItemQuantityAdjusted) {
      ShoppingCart.ItemQuantityAdjusted adjusted = (ShoppingCart.ItemQuantityAdjusted) event;
      String itemId = adjusted.itemId;
      ItemPopularity existingItemPop =
          repo.findById(itemId).orElseGet(() -> new ItemPopularity(itemId, 0, 0));
      ItemPopularity updatedItemPop =
          existingItemPop.changeCount(adjusted.newQuantity - adjusted.oldQuantity);
      repo.save(updatedItemPop);
    } else if (event instanceof ShoppingCart.ItemRemoved) {
      ShoppingCart.ItemRemoved removed = (ShoppingCart.ItemRemoved) event;
      String itemId = removed.itemId;
      ItemPopularity existingItemPop =
          repo.findById(itemId).orElseGet(() -> new ItemPopularity(itemId, 0, 0));
      ItemPopularity updatedItemPop = existingItemPop.changeCount(-removed.oldQuantity);
      repo.save(updatedItemPop);
      // tag::handler[]
    } else {
      // skip all other events, such as `CheckedOut`
    }
  }
}
// end::handler[]
