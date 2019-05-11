package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.pbs.customerprofile.models.Brand;

/**
 * 
 * @author gbangalo
 *
 */
public class BrandMapper implements RowMapper<Brand> {

	@Override
	public Brand mapRow(ResultSet row, int rowNum) throws SQLException {
		
		Brand brand = new Brand();
		brand.setId(row.getInt("ID"));
		brand.setName(row.getString("BRAND_NAME"));
		return brand;
	}

}
