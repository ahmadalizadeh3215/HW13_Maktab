package Repository;

import Util.SessionFactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public interface BaseRepository<T>{
    SessionFactory sessionFactory= SessionFactorySingleton.getInstance();

    default T save(T t){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(t);
        session.getTransaction().commit();
        return t;
    }

    default T findById(Class<T> clazz, Integer id) {
        Session session = sessionFactory.openSession();
        return session.find(clazz, id);
    }

    default void delete(T t){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
       session.delete(t);
        session.getTransaction().commit();
    }

    default List<T> findAll(Class<T> clazz){
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> query=criteriaBuilder.createQuery(clazz);
        Root<T> root= query.from(clazz);
        return session.createQuery(query.select(root)).getResultList();
    }

    default void update(T t){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
    }
    default T findByUserName(Class<T>clazz,String username){
        var session = sessionFactory.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriaFind=criteriaBuilder.createQuery(clazz);
        var root=criteriaFind.from(clazz);
        var queryFind=criteriaFind.where(criteriaBuilder.equal(root.get("username"),username));
        session.createQuery(criteriaFind);
        return session.createQuery(queryFind).getSingleResult();

    }
    default Integer deleteById(Class<T>clazz,Integer nationalcode){
        var session = sessionFactory.openSession();
        var criteriaBuilder = session.getCriteriaBuilder();
        var criteriadelete=criteriaBuilder.createCriteriaDelete(clazz);
        var root =criteriadelete.from(clazz);
        criteriadelete.where(criteriaBuilder.equal(root.get("nationalcode"),nationalcode));
        session.beginTransaction();
        session.createQuery(criteriadelete).executeUpdate();
        session.getTransaction().commit();
       return 0;



    }

}
