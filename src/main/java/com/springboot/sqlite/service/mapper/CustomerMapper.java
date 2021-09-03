package com.springboot.sqlite.service.mapper;



import com.springboot.sqlite.domain.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer mapCustomerDTOTOCustomer (com.springboot.sqlite.model.CustomerDTO customerDTO);

    com.springboot.sqlite.model.CustomerDTO mapCustomerTOCustomerDTO (Customer customer);

    List<Customer> mapCustomerListDTOTOCustomerList (List<com.springboot.sqlite.model.CustomerDTO> customerListDTO);

    List<com.springboot.sqlite.model.CustomerDTO> mapCustomerListTOCustomerListDTO (List<Customer> customerList);


}
