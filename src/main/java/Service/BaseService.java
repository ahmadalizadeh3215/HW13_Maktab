package Service;


import ExceptionManagment.RecordDoesNotExist;
import Repository.BaseRepository;

import java.util.List;

public abstract class BaseService<E, R extends BaseRepository<E>> {

    private R r;

    public BaseService(R r) {
        this.r = r;
    }

    public void save(E e) {
        r.save(e);
    }

    public void update(E e) {
        r.update(e);
    }

    public void delete(E e) {r.delete(e);}

    public E findById(Class<E> clazz,Integer id) {
        return r.findById( clazz,id);
    }

    public List<E> findAll(Class<E> clazz) {
        return r.findAll(clazz);
    }

    public void deleteById(Class<E> clazz, Integer nationalcode){
        if (r.deleteById(clazz,nationalcode)==0)
            throw new RecordDoesNotExist();

    }
}
