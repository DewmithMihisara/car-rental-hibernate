package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Category;
import lk.ijse.entity.Customer;
import lk.ijse.entity.embedded.CustomerAddress;
import lk.ijse.entity.embedded.CustomerFullName;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customerDAO.getAll()) {
            customerDtos.add(new CustomerDto(
                    customer.getId(),
                    customer.getUserName(),
                    customer.getEmail(),
                    customer.getFullName().getFirstName(),
                    customer.getFullName().getLastName(),
                    customer.getAddress().getStreet(),
                    customer.getAddress().getCity(),
                    customer.getAddress().getDistrict(),
                    customer.getPostalCode(),
                    customer.getMobiles()
            ));
        }
        return customerDtos;
    }

    @Override
    public boolean saveCustomer(CustomerDto customerDto) {
        return customerDAO.save(new Customer(
                customerDto.getId(),
                customerDto.getUserName(),
                customerDto.getEmail(),
                new CustomerFullName(customerDto.getFirstName(), customerDto.getLastName()),
                new CustomerAddress(customerDto.getStreet(), customerDto.getCity(), customerDto.getDistrict()),
                customerDto.getPostalCode(),
                customerDto.getMobile()
        ));
    }

    @Override
    public String getNextId() {
        String id = customerDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("Cu", "")) + 1;
        return String.format("Cu%03d", newId);    }

    @Override
    public CustomerDto getCustomer(String text) {
        Customer customer = customerDAO.getItem(text);
        if (customer != null) {
            return new CustomerDto(
                    customer.getId(),
                    customer.getUserName(),
                    customer.getEmail(),
                    customer.getFullName().getFirstName(),
                    customer.getFullName().getLastName(),
                    customer.getAddress().getStreet(),
                    customer.getAddress().getCity(),
                    customer.getAddress().getDistrict(),
                    customer.getPostalCode(),
                    customer.getMobiles()
            );
        }
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerDto customerDto) {
        return customerDAO.update(new Customer(
                customerDto.getId(),
                customerDto.getUserName(),
                customerDto.getEmail(),
                new CustomerFullName(customerDto.getFirstName(), customerDto.getLastName()),
                new CustomerAddress(customerDto.getStreet(), customerDto.getCity(), customerDto.getDistrict()),
                customerDto.getPostalCode(),
                customerDto.getMobile()
        ));
    }

    @Override
    public boolean deleteCustomer(CustomerDto customerDto) {
        return customerDAO.delete(new Customer(
                customerDto.getId(),
                customerDto.getUserName(),
                customerDto.getEmail(),
                new CustomerFullName(customerDto.getFirstName(), customerDto.getLastName()),
                new CustomerAddress(customerDto.getStreet(), customerDto.getCity(), customerDto.getDistrict()),
                customerDto.getPostalCode(),
                customerDto.getMobile()
        ));
    }

    @Override
    public List<String> getIds() {
        List<String>idList=new ArrayList<>();
        for(Customer customer: customerDAO.getAll()){
            idList.add(customer.getId());
        }
        return idList;
    }
}
