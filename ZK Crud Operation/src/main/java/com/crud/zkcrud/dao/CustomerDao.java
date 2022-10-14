package com.crud.zkcrud.dao;

import java.util.List;
import com.crud.zkcrud.modelbean.Customer;

public interface CustomerDao {

	public List<Customer> findAll();

	public List<Customer> search(Long id);

	public int deleteCustomer(Customer customer);

	public List<Customer> save(Customer customer);

	public List<Customer> update(Customer customer);
	
	public String checkEmailAddress(String email);
	
	public String checkMobileNumber(String mobile);

}