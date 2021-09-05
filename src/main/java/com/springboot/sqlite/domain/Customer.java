package com.springboot.sqlite.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    private String status;

    private String country;

    public Customer(int id, String name, String phone, String status, String country) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.country = country;
    }

    public Customer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Builder Customer for name
     * @param name name to build.
     * @return team witch name.
     */
    public Customer name(final String name){
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Builder Customer for name
     * @param phone name to build.
     * @return team witch phone.
     */
    public Customer phone(final String phone){
        this.phone = phone;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Builder Customer for name
     * @param status name to build.
     * @return Customer witch status.
     */
    public Customer status(final String status){
        this.status = status;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Builder Customer for name
     * @param country name to build.
     * @return Customer witch country.
     */
    public Customer country(final String country){
        this.status = country;
        return this;
    }
}
