package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.pbs.customerprofile.entities.CustomerProfileHierarchy;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author: GBANGALO
 **/

public class CustomerProfileHierarchyMapper implements RowMapper<CustomerProfileHierarchy>
{
    @Override
    public CustomerProfileHierarchy mapRow(ResultSet row, int rowNum) throws SQLException 
    {
        CustomerProfileHierarchy cp = new CustomerProfileHierarchy();
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
