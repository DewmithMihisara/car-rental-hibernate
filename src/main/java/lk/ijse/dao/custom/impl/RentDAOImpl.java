package lk.ijse.dao.custom.impl;

import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.dao.custom.RentDAO;
import lk.ijse.entity.Rent;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        return null;
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
        try {
            session.save(rent);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
