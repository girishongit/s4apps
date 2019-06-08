package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;

import com.cg.pbs.customerprofile.models.Brand;
import com.cg.pbs.customerprofile.models.Tables;

/**
 * 
 * @author gbangalo
 *
 */
public class ProfileKeyMapper implements RowMapper<HashMap<String, Object>> {

	@Override
	public HashMap<String, Object> mapRow(ResultSet row, int rowNum) throws SQLException {
		
		HashMap<String, Object> record = new HashMap<String, Object>();
			record.put("client", row.getInt("CLIENT"));
			record.put("countryCode", row.getString("COUNTRY_CODE"));
		return record;
	}

}
