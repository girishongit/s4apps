package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cg.pbs.customerprofile.models.CustomerProfile;
import com.cg.pbs.customerprofile.models.SubProfileValues;

/**
 * 
 * @author gbangalo
 *
 */

public class CustomerProfileListMapper implements RowMapper<CustomerProfile> {
	
	
	
	@Override
	    public CustomerProfile mapRow(ResultSet row, int rowNum) throws SQLException 
	    {
			CustomerProfile cp = new CustomerProfile();
		 	cp.setClient(row.getInt("CLIENT"));
		 	cp.setSorg(row.getString("SORG"));
		 	cp.setDelvchl(row.getInt("DELVCHL"));
		 	cp.setDiv(row.getInt("DIV"));
		 	cp.setCountrycode(row.getString("COUNTRY_CODE"));
		 	cp.setCustomerid(row.getInt("CUSTOMERID"));	 	
		 	return cp;
	    }


}
