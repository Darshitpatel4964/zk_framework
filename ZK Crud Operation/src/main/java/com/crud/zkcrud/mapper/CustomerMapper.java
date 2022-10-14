package com.crud.zkcrud.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.crud.zkcrud.modelbean.Customer;

@Mapper
public interface CustomerMapper {

	public void saveCustomer(Customer customer);

	public List<Customer> getAll();

	public int deleteCustomer(Customer customer);

	public List<Customer> getCustomerById(Long id);

	public void updateCustomer(Customer customer);
	
	public List<Customer> checkEmailAddress(@Param("email") String email);
	
	public List<Customer> checkMobileNumber(@Param("mobile") String mobile);

}