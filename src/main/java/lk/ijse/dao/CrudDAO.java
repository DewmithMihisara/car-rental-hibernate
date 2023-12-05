package lk.ijse.dao;

import java.util.List;

public interface CrudDAO <T, ID> extends SuperDAO{
    public boolean save(T entity);

    public boolean update(T entity);

    public boolean delete(T id);

    public T get(ID id);

    public List<T> getAll();
}
