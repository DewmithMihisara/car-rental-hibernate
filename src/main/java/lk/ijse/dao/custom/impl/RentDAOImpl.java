package lk.ijse.dao.custom.impl;

import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.dao.custom.RentDAO;
import lk.ijse.entity.Car;
import lk.ijse.entity.Rent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class RentDAOImpl implements RentDAO {
    @Override
    public boolean save(Rent entity) {
        return false;
    }

    @Override
    public boolean update(Rent entity) {
        return false;
    }

    @Override
    public boolean delete(Rent id) {
        return false;
    }

    @Override
    public Rent get(String s) {
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            Rent rent = session.get(Rent.class, s);
            transaction.commit();
            return rent;
        }
    }

    @Override
    public List<Rent> getAll() {
        return null;
    }

    @Override
    public String getNewId() {
        try (Session session= SessionFactoryConfig.getInstance().getSession()){
            String newId = "R000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select  RentId from rent order by RentId desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        }
    }

    @Override
    public boolean addRent(Rent rent, Session session) {
        Serializable save=session.save(rent);
        return save!=null;
    }

    @Override
    public List<Rent> getAllActive() {
        try (Session session= SessionFactoryConfig.getInstance().getSession()){
            String hql = "FROM Rent";
            Query query = session.createQuery(hql);
            List<Rent> rentEntities = query.list();
            return rentEntities;
        }
    }

    @Override
    public boolean updateRent(Session session, Rent rent) {
        session.update(rent);
        return true;
    }

    @Override
    public Rent get(String id, Session session) {
        Rent rent = session.get(Rent.class,id);
        return rent;
    }
}
