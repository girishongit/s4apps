package com.company.customerprofile.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper; 
import com.company.customerprofile.entities.CustomerProfile;

public class CustomerProfileMapper implements RowMapper<CustomerProfile>
{
    @Override
    public CustomerProfile mapRow(ResultSet row, int rowNum) throws SQLException 
    {
        CustomerProfile cp = new CustomerProfile();
        cp.setCustomerId(1);
        cp.setCountrycode(2);
        cp.setBusinessArea(3);
        cp.setProfile(4);	//row.getString("city")
        cp.setSubProfile(5);
        cp.setValue("6");
        cp.setValueType("Date");
        return cp;
    }
}
