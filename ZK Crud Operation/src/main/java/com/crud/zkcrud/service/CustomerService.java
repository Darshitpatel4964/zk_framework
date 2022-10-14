package com.crud.zkcrud.service;

import java.util.List;
import com.crud.zkcrud.modelbean.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public List<Customer> search(Long id);

	public int deleteCustomer(Customer customer);

	public List<Customer> save(Customer customer);
	
	public String checkEmailAddress(String email); 
	
	public String checkMobileNumber(String mobile); 
	
}