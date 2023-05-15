package com.sela.sela.service;

import java.util.List;

import com.sela.sela.dto.Customerdto;
import com.sela.sela.model.Customer;

public interface Customerservice {
    public List<Customer> index();
    
    public Customer register(Customer customer);

    public Customer show(Long id);

    public Customer update(Long id, Customer customer);

    public Customer delete(Long id);

    public Customerdto login(Customer body) throws Exception;
}
