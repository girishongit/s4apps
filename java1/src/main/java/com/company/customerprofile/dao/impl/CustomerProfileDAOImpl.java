package com.company.customerprofile.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import com.company.customerprofile.dao.CustomerProfileDAO;
import com.company.customerprofile.dao.mapper.CustomerProfileMapper;
import com.company.customerprofile.entities.CustomerProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerProfileDAOImpl implements CustomerProfileDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CustomerProfileDAOImpl( DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);;
	}

    @Override
    public List<CustomerProfile> getCustomerProfiles()
    {
        String sql = "select * from customerprofile.db::Customer.CustomerProfile";
        List<CustomerProfile> customerprofile = jdbcTemplate.query(sql, new CustomerProfileMapper());
        return customerprofile;
    }
}
