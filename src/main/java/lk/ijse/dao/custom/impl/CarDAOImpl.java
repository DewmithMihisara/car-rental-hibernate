package lk.ijse.dao.custom.impl;

import lk.ijse.configaration.SessionFactoryConfig;
import lk.ijse.dao.SuperDAO;
import lk.ijse.dao.custom.CarDAO;
import lk.ijse.entity.Car;
import lk.ijse.entity.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class CarDAOImpl implements CarDAO {
    @Override
    public boolean save(Car entity){
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean update(Car entity){
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }
    }

    @Override
    public boolean delete(Car id){
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
            return true;
        }
    }

    @Override
    public Car get(String s){
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            Car car = session.get(Car.class, s);
            transaction.commit();
            return car;
        }
    }

    @Override
    public List<Car> getAll(){
        try(Session session = SessionFactoryConfig.getInstance().getSession()){
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car>query= criteriaBuilder.createQuery(Car.class);
            query.from(Car.class);
            List<Car>list = session.createQuery(query).getResultList();
            transaction.commit();
            return list;
        }
    }

    @Override
    public String getNewId() {
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            String newId = "C000";
            Transaction transaction = session.beginTransaction();
            List list = session.createNativeQuery("select  CarId from car order by CarId desc limit 1").list();
            if (!list.isEmpty()) newId = (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        }
    }

    @Override
    public boolean updateAsRent(Session session, Car car) {
        try {
            session.update(car);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Car getCarByNum(String carNumber) {
        try (Session session=SessionFactoryConfig.getInstance().getSession()){
            String hql = "FROM Car car WHERE car.number = :carNumber";

            Car car = session.createQuery(hql, Car.class).
                    setParameter("carNumber", carNumber).uniqueResult();
            return car;
        }

    }

    @Override
    public Car getCarByNum(String carNumber, Session session) {
        Car car= session.get(Car.class,carNumber);
        return car;
    }
}
