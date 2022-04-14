package Repository;

import Util.SessionFactorySingleton;
import org.hibernate.SessionFactory;


public interface OperationByNationalCode<T>{
    SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    default void DeleteByNationalCode(Class<T>clazz,String nationalcode){
        var session = sessionFactory.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriadelete=criteriaBuilder.createCriteriaDelete(clazz);
        var root =criteriadelete.from(clazz);
        criteriadelete.where(criteriaBuilder.equal(root.get("nationalcode"),nationalcode));
        session.beginTransaction();
        session.createQuery(criteriadelete).executeUpdate();
        session.getTransaction().commit();


    }
    default T findByNationalCode(Class<T>clazz,String nationalcode){
        var session = sessionFactory.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaFind=criteriaBuilder.createQuery(clazz);
        var root=criteriaFind.from(clazz);
        var queryFind=criteriaFind.where(criteriaBuilder.equal(root.get("nationalcode"),nationalcode));
        session.createQuery(criteriaFind);
        return session.createQuery(queryFind).getSingleResult();

    }

}


