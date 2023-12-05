package lk.ijse.dao.custom.impl;

import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.dao.SuperDAO;
import lk.ijse.dao.custom.CategoryDAO;
import lk.ijse.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    @Override
    public boolean save(Category entity){
        try(Session session= SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            Serializable save = session.save(entity);
            transaction.commit();
            return save != null;
        }
    }

    @Override
    public boolean update(Category entity){
        try(Session session= SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean delete(Category id) {
        try(Session session= SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
            return true;
        }
    }

    @Override
    public Category get(String s) {
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            Category category = session.get(Category.class, s);
            transaction.commit();
            return category;
        }
    }

    @Override
    public List<Category> getAll(){
        try(Session session= SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Category>query=criteriaBuilder.createQuery(Category.class);
            query.from(Category.class);
            List<Category>list=session.createQuery(query).getResultList();
            transaction.commit();
            return list;
        }
    }

    @Override
    public String getNewId() {
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            String newId = "Ca000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select  CatId from carcategory order by CatId desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        }
    }
}
