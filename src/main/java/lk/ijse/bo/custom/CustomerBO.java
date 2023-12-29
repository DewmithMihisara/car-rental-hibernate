package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CategoryDto;
import lk.ijse.dto.CustomerDto;

import java.util.List;

public interface CustomerBO extends SuperBO {
    List<CustomerDto> getAllCustomers();

    boolean saveCustomer(CustomerDto customerDto);

    String getNextId();

    CustomerDto getCustomer(String text);

    boolean updateCustomer(CustomerDto customerDto);

    boolean deleteCustomer(CustomerDto customerDto);

    List<String> getIds();
}
