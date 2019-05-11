package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.pbs.customerprofile.models.ProfileView;

public class ProfileViewMapper implements RowMapper<ProfileView> {

	@Override
	public ProfileView mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProfileView pv = new ProfileView();
		
		pv.setClient(rs.getInt("CLIENT"));
		pv.setSorg(rs.getString("SORG"));
		pv.setDelvch(rs.getInt("DELVCHL"));
		pv.setDiv(rs.getInt("DIV"));
		pv.setCountrycode(rs.getInt("COUNTRY_CODE"));
		pv.setBusinessarea(rs.getInt("BUSINESS_AREA"));
		pv.setBusinessareatext(rs.getString("BUSINESS_AREA_TEXT"));
		pv.setProfile(rs.getInt("PROFILE"));
		pv.setProfiletext(rs.getString("PROFILE_TEXT"));
		pv.setSubprofile(rs.getInt("SUBPROFILE"));
		pv.setSubprofiletext(rs.getString("SUBPROFILE_TEXT"));
		pv.setSubprofiledatatype(rs.getString("SUBPROFILE_DATATYPE"));
		
		return pv;
		
	}

}
