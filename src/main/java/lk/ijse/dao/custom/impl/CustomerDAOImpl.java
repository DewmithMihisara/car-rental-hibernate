package lk.ijse.dao.custom.impl;

import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Serializable save = session.save(entity);
            transaction.commit();
            return save != null;
        }
    }

    @Override
    public boolean update(Customer entity) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean delete(Customer id) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, id.getId());
            session.delete(customer);
            transaction.commit();
            return true;
        }
    }

    @Override
    public Customer get(String s) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Customer> query = criteriaBuilder.createQuery(Customer.class);
            query.from(Customer.class);
            List<Customer> list = session.createQuery(query).getResultList();
            transaction.commit();
            return list;
        }
    }

    @Override
    public String getNextId() {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            String newId = "Cu000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select CustId from customer order by CustId desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        }
    }

    @Override
    public Customer getItem(String text) {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Customer customer = session.get(Customer.class, text);
            transaction.commit();
            return customer;
        }
    }

    @Override
    public boolean updateAsRent(Session session, Customer customer) {
        try{
            session.update(customer);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
