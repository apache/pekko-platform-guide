package shopping.cart;

import akka.japi.function.Function;
import akka.projection.jdbc.JdbcSession;
import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class HibernateJdbcSession extends DefaultTransactionDefinition implements JdbcSession {

  private final JpaTransactionManager transactionManager;
  private final TransactionStatus transactionStatus;

  public HibernateJdbcSession(JpaTransactionManager transactionManager) {
    this.transactionManager = transactionManager;
    this.transactionStatus = transactionManager.getTransaction(this);
  }

  public EntityManager entityManager() {
    return EntityManagerFactoryUtils.getTransactionalEntityManager(transactionManager.getEntityManagerFactory());
  }

  @Override
  public <Result> Result withConnection(Function<Connection, Result> func) {
    EntityManager entityManager = entityManager();
    Session hibernateSession = ((Session) entityManager.getDelegate());
    return hibernateSession.doReturningWork(
        new ReturningWork<Result>() {
          @Override
          public Result execute(Connection connection) throws SQLException {
            try {
              Result result = func.apply(connection);

              // FIXME tested that this will rollback above offset storage
//              if (true)
//                throw new RuntimeException("Simulated exc in doReturningWork");

              return result;
            } catch (SQLException e) {
              throw e;
            } catch (Exception e) {
              throw new SQLException(e);
            }
          }
        });
  }

  @Override
  public void commit() {
    transactionManager.commit(transactionStatus);
  }

  @Override
  public void rollback() {
    transactionManager.rollback(transactionStatus);
  }

  @Override
  public void close() {
    entityManager().close();
  }
}
