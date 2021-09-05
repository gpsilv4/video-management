package com.springboot.sqlite.web.rest;

import com.springboot.sqlite.service.CustomerService;
import com.springboot.sqlite.service.mapper.CustomerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.sqlite.model.CustomerDTO;

import java.util.List;

@RestController
public class CustomerController {
    public static final String CUSTOMER = "/customers";
    public static final String VALIDATENUMBER = "/validateNumber";
    public static final String FINDCOUNTRY = "/findcountry";
    public static final String FINDSTATUS = "/findstatus";
    public static final String FINDCOUNTRYANDSTATUS = "/findcountryandstatus";

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }


    /**
     * Controller to get all a teams
     *
     * @return List Customer
     */
    @GetMapping(value = CUSTOMER,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<CustomerDTO>> allCustomer() {
        return ResponseEntity.ok(
                customerMapper.mapCustomerListTOCustomerListDTO(
                        customerService.allCustomer()));
    }


    /**
     * Controller to get validate number
     *
     * @return List Validate Number
     */
    @GetMapping(value = VALIDATENUMBER,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<String> validateNumber() {
        customerService.validateNumber("(251) 985558200000");
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = FINDCOUNTRY,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<CustomerDTO>> findAllByCountry(@RequestParam final String country) {
        return ResponseEntity.ok(
                customerMapper.mapCustomerListTOCustomerListDTO(customerService.findAllByCountry(country)));
    }

    @GetMapping(value = FINDSTATUS,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<CustomerDTO>> findAllByStatus(@RequestParam final String status) {
        return ResponseEntity.ok(
                customerMapper.mapCustomerListTOCustomerListDTO(customerService.findAllByStatus(status)));
    }

    @GetMapping(value = FINDCOUNTRYANDSTATUS,
            produces = {"application/json"},
            consumes = {"application/json"})
    public ResponseEntity<List<CustomerDTO>> findAllByCountryAndStatus(@RequestParam final String country,
                                                                       @RequestParam final String status) {
        return ResponseEntity.ok(
                customerMapper.mapCustomerListTOCustomerListDTO(customerService
                        .findAllByCountryAndStatus(country, status)));
    }

}
