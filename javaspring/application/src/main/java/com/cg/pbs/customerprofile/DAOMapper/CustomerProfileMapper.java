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

public class CustomerProfileMapper implements RowMapper<CustomerProfile> {
	
	CustomerProfile cp;
	
	 public CustomerProfileMapper(CustomerProfile cp) {
		this.cp = cp;
	}

	@Override
	    public CustomerProfile mapRow(ResultSet row, int rowNum) throws SQLException 
	    {
		 	cp.setClient(row.getInt("CLIENT"));
		 	cp.setSorg(row.getString("SORG"));
		 	cp.setDelvchl(row.getInt("DELVCHL"));
		 	cp.setDiv(row.getInt("DIV"));
		 	cp.setCountrycode(row.getString("COUNTRY_CODE"));
		 	cp.setCustomerid(row.getInt("CUSTOMERID"));
		 	
		 	cp.addValues(new SubProfileValues(row.getInt("SUBPROFILE"), row.getString("VALUE")));	 
		 	
		 	cp.setValidfrom(row.getDate("VALID_FROM"));
		 	cp.setValidto(row.getDate("VALID_TO"));
		 	cp.setModifiedby(row.getString("MODIFIED_AT"));
		 	cp.setModifiedat(row.getString("MODIFIED_BY"));		 	
	     return cp;
	    }


}
