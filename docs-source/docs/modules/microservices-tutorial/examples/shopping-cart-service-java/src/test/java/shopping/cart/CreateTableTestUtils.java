package shopping.cart;

import static java.util.concurrent.TimeUnit.SECONDS;

import akka.actor.typed.ActorSystem;
import akka.persistence.jdbc.testkit.javadsl.SchemaUtils;
import akka.projection.jdbc.javadsl.JdbcProjection;
import java.util.Objects;
import javax.persistence.EntityManager;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import shopping.cart.repository.HibernateJdbcSession;

public class CreateTableTestUtils {

  public static void createTables(JpaTransactionManager transactionManager, ActorSystem<?> system)
      throws Exception {

    // create schemas
    // ok to block here, main test thread

    SchemaUtils.dropIfExists(system).toCompletableFuture().get(30, SECONDS);
    ;
    SchemaUtils.createIfNotExists(system).toCompletableFuture().get(30, SECONDS);

    JdbcProjection.dropOffsetTableIfExists(
            () -> new HibernateJdbcSession(transactionManager), system)
        .toCompletableFuture()
        .get(30, SECONDS);
    ;
    JdbcProjection.createOffsetTableIfNotExists(
            () -> new HibernateJdbcSession(transactionManager), system)
        .toCompletableFuture()
        .get(30, SECONDS);
    ;

    // NOTE: item_popularity is created by auto-created hibernate in tests
    deleteItemPopularity(transactionManager);

    LoggerFactory.getLogger(CreateTableTestUtils.class).info("Tables created");
  }

  private static void deleteItemPopularity(JpaTransactionManager transactionManager) {
    TransactionStatus transaction = transactionManager.getTransaction(null);
    EntityManager entityManager =
        EntityManagerFactoryUtils.getTransactionalEntityManager(
            Objects.requireNonNull(transactionManager.getEntityManagerFactory()));
    entityManager.createQuery("delete from ItemPopularity").executeUpdate();
    entityManager.close();
    transactionManager.commit(transaction);
  }
}
