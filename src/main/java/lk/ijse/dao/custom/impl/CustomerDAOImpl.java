package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;

import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) {
        return false;
    }

    @Override
    public boolean update(Customer entity) {
        return false;
    }

    @Override
    public boolean delete(Customer id) {
        return false;
    }

    @Override
    public Customer get(String s) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
