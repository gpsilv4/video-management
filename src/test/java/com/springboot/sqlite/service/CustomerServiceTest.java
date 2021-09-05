package com.springboot.sqlite.service;

import com.springboot.sqlite.domain.Customer;
import com.springboot.sqlite.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;



@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    //Class where you have the methods I'm testing.
    @InjectMocks
    CustomerService customerService;

    //All services external to the class I'm testing.
    @Mock
    CustomerRepository customerRepository;


    //find all customer
    @Test
    public void findAll_thrownAnException(){
        //Given
        List<Customer> allCustomerGiven = new ArrayList<>();
        allCustomerGiven.isEmpty();

        //When
        Mockito.when(customerRepository.findAll()).thenReturn(allCustomerGiven);
        Assert.assertThrows(NullPointerException.class, () -> customerService.allCustomer());
    }

}