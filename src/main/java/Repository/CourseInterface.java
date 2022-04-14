package Repository;

import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;

public interface CourseInterface<T> {
    SessionFactory sessionFactory= SessionFactorySingleton.getInstance();

    default T findByCourseName(Class<T>clazz,String name){
        var session = sessionFactory.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaFind=criteriaBuilder.createQuery(clazz);
        var root=criteriaFind.from(clazz);
        var queryFind=criteriaFind.where(criteriaBuilder.equal(root.get("name"),name));
        session.createQuery(criteriaFind);
        return session.createQuery(queryFind).getSingleResult();

    }
}
