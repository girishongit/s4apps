package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cg.pbs.customerprofile.models.CustomerProfile;
import com.cg.pbs.customerprofile.models.ProfileHierarchyTemplate;
import com.cg.pbs.customerprofile.models.SubProfileValues;

/**
 * 
 * @author gbangalo
 *
 */

public class CustomerProfileHierarchyMapper implements RowMapper<ProfileHierarchyTemplate> {
	
	ProfileHierarchyTemplate hierarchy = new ProfileHierarchyTemplate();
	
	 public CustomerProfileHierarchyMapper(ProfileHierarchyTemplate hierarchy) {
		this.hierarchy = hierarchy;
	}

	@Override
	    public ProfileHierarchyTemplate mapRow(ResultSet row, int rowNum) throws SQLException 
	    {
			hierarchy.updateSubProfile(row.getInt("CUSTOMERID"), row.getInt("SUBPROFILE"), row.getString("VALUE"));
	     return hierarchy;
	    }


}
