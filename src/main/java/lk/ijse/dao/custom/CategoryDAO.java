package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SuperDAO;
import lk.ijse.entity.Category;

public interface CategoryDAO extends CrudDAO<Category,String> {
    String getNewId();
}
