package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.cg.pbs.customerprofile.models.ProfileHierarchyTemplate;

/**
 * 
 * @author gbangalo
 *
 */

public class ProfileHierarchyTemplateMapper implements RowMapper<ProfileHierarchyTemplate> {
	
	ProfileHierarchyTemplate hierarchy;
	
	 public ProfileHierarchyTemplateMapper(ProfileHierarchyTemplate hierarchy) {
		this.hierarchy = hierarchy;
	}

	@Override
	    public ProfileHierarchyTemplate mapRow(ResultSet row, int rowNum) throws SQLException 
	    {
			hierarchy.setClient(row.getInt("CLIENT"));
			hierarchy.setSorg(row.getString("SORG"));
			hierarchy.setDelvch(row.getInt("DELVCHL"));
			hierarchy.setDiv(row.getInt("DIV"));
			hierarchy.setCountrycode(row.getString("COUNTRY_CODE"));
			hierarchy.setHierarchy( row.getInt("BUSINESS_AREA"), row.getString("BUSINESS_AREA_TEXT"), 
									row.getInt("PROFILE"), row.getString("PROFILE_TEXT"),
									row.getInt("SUBPROFILE"), row.getString("SUBPROFILE_TEXT"), 
									row.getString("SUBPROFILE_DATATYPE")); 	
			return hierarchy;
	    }


}
