package lk.ijse.dao.custom.impl;

import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.entity.Car;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean update(User entity) {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean delete(User id) {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
            return true;
        }
    }

    @Override
    public User get(String s) {
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, s);
            transaction.commit();
            return user;
        }
    }

    @Override
    public List<User> getAll() {
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> query= criteriaBuilder.createQuery(User.class);
            query.from(User.class);
            List<User>list = session.createQuery(query).getResultList();
            transaction.commit();
            return list;
        }
    }

    @Override
    public String getNewId() {
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            String newId = "U000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select  UserId from user order by UserId desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        }
    }
}
