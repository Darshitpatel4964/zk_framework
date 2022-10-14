package com.crud.zkcrud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.zkcrud.dao.CustomerDao;
import com.crud.zkcrud.modelbean.Customer;

@Service("customerService")
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public List<Customer> search(Long id) {
		return customerDao.search(id);
	}

	@Override
	public int deleteCustomer(Customer customer) {
		return customerDao.deleteCustomer(customer);
	}

	@Override
	public List<Customer> save(Customer customer) {
		if (customer.getId() == 0) {
			return customerDao.save(customer);
		} else {
			return customerDao.update(customer);
		}
	}

	@Override
	public String checkEmailAddress(String email) {
		return customerDao.checkEmailAddress(email);
	}
	
	@Override
	public String checkMobileNumber(String mobile) {
		return customerDao.checkMobileNumber(mobile);
	}

}