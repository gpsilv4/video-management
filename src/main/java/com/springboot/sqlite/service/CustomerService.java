package com.springboot.sqlite.service;


import com.springboot.sqlite.domain.Customer;
import com.springboot.sqlite.domain.enumeration.Country;
import com.springboot.sqlite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final Map<String, Country > country;

    @Autowired
    public CustomerService(final CustomerRepository customerRepository, final Map<String, Country> country) {
        this.customerRepository = customerRepository;
        this.country = new HashMap<>();
    }

    public List<Customer> allCustomer() {
        final List<Customer> teamListOptional = customerRepository.findAll();
        if (!teamListOptional.isEmpty()) {
            List<Customer> listCustomer = new ArrayList<>();
            for (Customer customer: teamListOptional) {
                customer.setCountry(validateNumber(customer.getPhone()));
                customer.setStatus(validateNumber(customer.getPhone()).equals("")? "Not Valid": "Valid");
                listCustomer.add(customer);
            }
            return listCustomer;
        }
        throw new NullPointerException("Customer List is Empty!");
    }

    public String validateNumber(final String phoneNumber) {

        country.put("\\(237\\)\\ ?[2368]\\d{7,8}$", Country.CAMEROON);
        country.put("\\(251\\)\\ ?[1-59]\\d{8}$", Country.ETHIOPIA);
        country.put("\\(212\\)\\ ?[5-9]\\d{8}$", Country.MOROCCO);
        country.put("\\(258\\)\\ ?[28]\\d{7,8}$", Country.MOZAMBIQUE);
        country.put("\\(256\\)\\ ?\\d{9}$", Country.UGANDA);

        String phoneNumberPrefix = phoneNumber.split("\\)")[0];

        List<Country> countryFound = country
                .entrySet()
                .parallelStream()
                .filter(country -> country.getKey().startsWith("\\" + phoneNumberPrefix))
                .filter(country -> Pattern.compile(country.getKey()).matcher(phoneNumber).matches())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        return countryFound.isEmpty() ? "" : countryFound.get(0).name();

    }


    public List<Customer> findAllByCountry(final String country){
        List<Customer> listFilterCustomer = new ArrayList<>();
        for (Customer customer : allCustomer()) {
            if (customer.getCountry().equals(country)){
                listFilterCustomer.add(customer);
            }
        }
        if (listFilterCustomer.isEmpty()){
            throw new NullPointerException("Customer by country not exist!!");
        }
        return listFilterCustomer;
    }

    public List<Customer> findAllByStatus(final String status){
        List<Customer> listFilterStatus = new ArrayList<>();
        for (Customer customer : allCustomer()) {
            if (!customer.getCountry().equals("") && status.equals("Valid")){
                listFilterStatus.add(customer);
            } else if (customer.getCountry().equals("") && status.equals("Not Valid")){
                listFilterStatus.add(customer);
            }
        }
        if (listFilterStatus.isEmpty()){
            throw new NullPointerException("Customer by Status not exist!!");
        }
        return listFilterStatus;
    }

    public List<Customer> findAllByCountryAndStatus(final String country, final String status){
        List<Customer> listCustomer = new ArrayList<>();
        for (Customer customer : allCustomer()) {
            if (customer.getCountry().equals(country) && status.equals("Valid")){
                listCustomer.add(customer);
            }
        }
        if (listCustomer.isEmpty()){
            throw new NullPointerException("Customer by Country and Status not exist!!");
        }
        return listCustomer;
    }
}
