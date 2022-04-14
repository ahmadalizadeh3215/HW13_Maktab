package Repository;

import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface UserInterface<T>extends BaseRepository<T> {
    SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    default T login(Class<T> clazz, Integer nationalCode, String password) {
        var session = sessionFactory.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        var queryResult = query.select(root)
                .where(
                        criteriaBuilder.equal(root.get("nationalcode"), nationalCode),
                        criteriaBuilder.equal(root.get("password"), password)

                );
        return session.createQuery(queryResult).getSingleResult();


    }
}
