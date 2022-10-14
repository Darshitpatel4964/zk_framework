package com.crud.zkcrud.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.crud.zkcrud.constant.Constant;
import com.crud.zkcrud.mapper.CustomerMapper;
import com.crud.zkcrud.modelbean.Customer;
import com.crud.zkcrud.util.DbSession;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private SqlSession session = null;
    
    List < Customer > selectQuery = null;

    @Override
    public List < Customer > findAll() {
        try {
            session = DbSession.getSqlSessionFactory();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            selectQuery = mapper.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
		return selectQuery;
    }

    @Override
    public List < Customer > search(Long id) {
        try {
            session = DbSession.getSqlSessionFactory();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            selectQuery = mapper.getCustomerById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return selectQuery;
    }

    @Override
    public int deleteCustomer(Customer customer) {
        int query = 0;
		try {
            session = DbSession.getSqlSessionFactory();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            query = mapper.deleteCustomer(customer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
		return query;
    }

    @Override
    public List < Customer > save(Customer customer) {
        try {
            session = DbSession.getSqlSessionFactory();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            mapper.saveCustomer(customer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
		return selectQuery;
    }

    @Override
    public List < Customer > update(Customer customer) {
        try {
            session = DbSession.getSqlSessionFactory();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            mapper.updateCustomer(customer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
		return selectQuery;
    }
   
    @Override
    public String checkEmailAddress(String email) {
        List < Customer > customer = null;
        try {
            session = DbSession.getSqlSessionFactory();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            customer = mapper.checkEmailAddress(email);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (customer != null && !customer.isEmpty()) ? Constant.DUPLICATE_VALUE : Constant.UNIQUE_VALUE;
    }
    
    @Override
    public String checkMobileNumber(String mobile) {
        List < Customer > customer = null;
        try {
            session = DbSession.getSqlSessionFactory();
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            customer = mapper.checkMobileNumber(mobile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (customer != null && !customer.isEmpty()) ? Constant.DUPLICATE_VALUE : Constant.UNIQUE_VALUE;
    }

}