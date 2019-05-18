package com.cg.pbs.customerprofile.DAOImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.pbs.customerprofile.DAO.CustomerProfileDAO;
import com.cg.pbs.customerprofile.DAOMapper.BrandMapper;
import com.cg.pbs.customerprofile.DAOMapper.CustomerProfileHierarchyMapper;
import com.cg.pbs.customerprofile.DAOMapper.CustomerProfileListMapper;
import com.cg.pbs.customerprofile.DAOMapper.CustomerProfileMapper;
import com.cg.pbs.customerprofile.DAOMapper.ProfileHierarchyTemplateMapper;
import com.cg.pbs.customerprofile.DAOMapper.ProfileViewMapper;
import com.cg.pbs.customerprofile.DAOMapper.TablesMapper;
import com.cg.pbs.customerprofile.models.Brand;
import com.cg.pbs.customerprofile.models.CustomerProfile;
import com.cg.pbs.customerprofile.models.Profile;
import com.cg.pbs.customerprofile.models.ProfileHierarchyTemplate;
import com.cg.pbs.customerprofile.models.ProfileText;
import com.cg.pbs.customerprofile.models.ProfileView;
import com.cg.pbs.customerprofile.models.Tables;

/**
 * 
 * @author gbangalo
 *
 */

@Repository
public class CustomerProfileDAOImpl implements CustomerProfileDAO{


	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CustomerProfileDAOImpl( DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);;
	}
	
	private String escapeQuotes(String data)
	{
		return "\""+data+"\"";
	}
	
	private String sqlString(Object data)
	{
		return "'"+data.toString()+"'";
	}
	
	@Override
	public List<Tables> getAvailableTables(String schema) {
		
		String sql = "select SCHEMA_NAME, TABLE_NAME, RECORD_COUNT from M_TABLES";
		if(!schema.equals(""))
			sql = sql.concat(" WHERE SCHEMA_NAME = '"+schema+"';");
        List<Tables> tables = jdbcTemplate.query(sql, new TablesMapper());
        return tables;
        
	}
	
	@Override
	public List<CustomerProfile> getCustomerList() {


		String result = "";
        StringBuilder sql = new StringBuilder();
		sql
			.append("SELECT DISTINCT ")
			.append(escapeQuotes("CLIENT"))			.append(", ")
			.append(escapeQuotes("SORG"))			.append(", ")
			.append(escapeQuotes("DELVCHL"))		.append(", ")
			.append(escapeQuotes("DIV"))			.append(", ")
			.append(escapeQuotes("COUNTRY_CODE"))	.append(", ")
			.append(escapeQuotes("CUSTOMERID"))
			.append(" FROM CUSTOMER.")
			.append(escapeQuotes("customerprofile.db::Customer.CustomerProfile"))
			;
			
		List<CustomerProfile> cp = jdbcTemplate.query(sql.toString(), new CustomerProfileListMapper());
		return cp;
	}

	@Override
	public CustomerProfile getCustomerProfileDetails(int customerid ) {

		CustomerProfile cp = new CustomerProfile();
		
		String result = "";
        StringBuilder sql = new StringBuilder();
		sql
			.append("SELECT ")
			.append(escapeQuotes("CLIENT"))			.append(", ")
			.append(escapeQuotes("SORG"))			.append(", ")
			.append(escapeQuotes("DELVCHL"))		.append(", ")
			.append(escapeQuotes("DIV"))			.append(", ")
			.append(escapeQuotes("COUNTRY_CODE"))	.append(", ")
			.append(escapeQuotes("CUSTOMERID"))		.append(", ")
			.append(escapeQuotes("SUBPROFILE"))		.append(", ")
			//.append(escapeQuotes("BRAND_ID"))		.append(", ")
			.append(escapeQuotes("VALUE"))			.append(", ")
			.append(escapeQuotes("VALID_FROM"))		.append(", ")
			.append(escapeQuotes("VALID_TO"))		.append(", ")
			.append(escapeQuotes("MODIFIED_AT"))	.append(", ")
			.append(escapeQuotes("MODIFIED_BY"))
			.append(" FROM CUSTOMER.")
			.append(escapeQuotes("customerprofile.db::Customer.CustomerProfile"))
			.append(" WHERE ")
			.append(escapeQuotes("CUSTOMERID"))		.append(" =  ") .append(customerid)
			;
			
		jdbcTemplate.query(sql.toString(), new CustomerProfileMapper(cp));
		return cp;
	}

	@Override
	public HashMap<String, String> insertCustomerProfile(CustomerProfile cp) {

		HashMap<String, String> result = new HashMap<String, String>();
        StringBuilder sql = new StringBuilder();
		sql
			.append("INSERT INTO CUSTOMER. ")
			.append(escapeQuotes("customerprofile.db::Customer.CustomerProfile"))
			.append(" ( ")
			.append(escapeQuotes("CLIENT"))			.append(", ")
			.append(escapeQuotes("SORG"))			.append(", ")
			.append(escapeQuotes("DELVCHL"))		.append(", ")
			.append(escapeQuotes("DIV"))			.append(", ")
			.append(escapeQuotes("COUNTRY_CODE"))	.append(", ")
			.append(escapeQuotes("CUSTOMERID"))		.append(", ")
			.append(escapeQuotes("SUBPROFILE"))		.append(", ")
			//.append(escapeQuotes("BRAND_ID"))		.append(", ")
			.append(escapeQuotes("VALUE"))			.append(", ")
			.append(escapeQuotes("VALID_FROM"))		.append(", ")
			.append(escapeQuotes("VALID_TO"))		.append(", ")
			.append(escapeQuotes("MODIFIED_AT"))	.append(", ")
			.append(escapeQuotes("MODIFIED_BY"))
			.append(" ) VALUES ")
			.append("( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_USER )")
			;
			
		try
		{
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, cp.getClient());
					ps.setString(2, cp.getSorg());
					ps.setInt(3, cp.getDelvchl());
					ps.setInt(4, cp.getDiv());
					ps.setString(5, cp.getCountrycode());
					ps.setInt(6, cp.getCustomerid());
					
					ps.setInt(7, cp.getValues().get(i).getSubprofile());
					//ps.setInt(8, cp.getValues().get(i).getBrandid());
					ps.setString(8, cp.getValues().get(i).getValue());
					
					ps.setDate(9, cp.getValidfrom());
					ps.setDate(10, cp.getValidto());
				}
				
				@Override
				public int getBatchSize() {
					return cp.getValues().size();
				}
			});
			
			result.put("status", "Saved Successfully"); 
			result.put("code", "200");
		}
		catch(Exception e)
		{
			result.put("status", "Error");
			result.put("error", e.toString());
			result.put("code", "500");
		}
		
		return result;
		
	}
	
	@Override
	public HashMap<String, String> deleteCustomerProfile(CustomerProfile cp) {
		
		HashMap<String, String> result = new HashMap<String, String>();
        StringBuilder sql = new StringBuilder();
		sql
			.append("DELETE FROM CUSTOMER. ")
			.append(escapeQuotes("customerprofile.db::Customer.CustomerProfile"))
			.append(" WHERE ")
			.append(escapeQuotes("CLIENT"))			.append(" = ")	.append("?") 			.append(" AND ")
			.append(escapeQuotes("SORG"))			.append(" = ")	.append("?")			.append(" AND ")
			.append(escapeQuotes("DELVCHL"))		.append(" = ")	.append("?")			.append(" AND ")
			.append(escapeQuotes("DIV"))			.append(" = ")	.append("?")			.append(" AND ")
			.append(escapeQuotes("COUNTRY_CODE"))	.append(" = ")	.append("?")			.append(" AND ")
			.append(escapeQuotes("CUSTOMERID"))		.append(" = ")	.append("?")
			;
		
		try
		{
			jdbcTemplate.update(sql.toString(), new Object[] {cp.getClient(), cp.getSorg(), cp.getDelvchl(), cp.getDiv(), cp.getCountrycode(), cp.getCustomerid() });
			result.put("status", "Deleted Successfully");
			result.put("code", "200");
		}
		catch(Exception e)
		{
			result.put("status","Error");
			result.put("error",e.toString());
			result.put("code", "500");
		}
	        				
		return result;
		
	}

	@Override
	public HashMap<String, String> insertProfile(List<Profile> profile) {
		
		HashMap<String, String> result = new HashMap<String, String>();
        StringBuilder sql = new StringBuilder();
		sql
			.append("INSERT INTO CUSTOMER. ")
			.append(escapeQuotes("customerprofile.db::Customer.Profile"))
			.append(" ( ")
			.append(escapeQuotes("CLIENT"))				.append(", ")
			.append(escapeQuotes("SORG"))				.append(", ")
			.append(escapeQuotes("DELVCHL"))			.append(", ")
			.append(escapeQuotes("DIV"))				.append(", ")
			.append(escapeQuotes("COUNTRY_CODE"))		.append(", ")
			.append(escapeQuotes("BUSINESS_AREA"))		.append(", ")
			.append(escapeQuotes("PROFILE"))			.append(", ")
			.append(escapeQuotes("SUBPROFILE"))			.append(", ")
			.append(escapeQuotes("SUBPROFILE_DATATYPE")).append(", ")
			.append(escapeQuotes("MODIFIED_AT"))		.append(", ")
			.append(escapeQuotes("MODIFIED_BY"))
			.append(" ) VALUES ")
			.append("( ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_USER )")
			;
			
		try
		{
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, profile.get(i).getClient());
					ps.setString(2, profile.get(i).getSorg());
					ps.setInt(3, profile.get(i).getDelvchl());
					ps.setInt(4, profile.get(i).getDiv());
					ps.setString(5, profile.get(i).getCountrycode());
					ps.setInt(6, profile.get(i).getBusinessarea());
					ps.setInt(7, profile.get(i).getProfile());
					ps.setInt(8, profile.get(i).getSubprofile());
					ps.setString(9, profile.get(i).getSubprofiletype());
				}
				
				@Override
				public int getBatchSize() {
					return profile.size();
				}
			});
			
			result.put("status", "Saved Successfully"); 
			result.put("code", "200");
		}
		catch(Exception e)
		{
			result.put("status", "Error");
			result.put("error", e.toString());
			result.put("code", "500");
		}
		
		return result;
	}

	@Override
	public HashMap<String, String> insertProfileText(List<ProfileText> profiletext) {
		HashMap<String, String> result = new HashMap<String, String>();
        StringBuilder sql = new StringBuilder();
		sql
			.append("INSERT INTO CUSTOMER. ")
			.append(escapeQuotes("customerprofile.db::Customer.ProfileText"))
			.append(" ( ")
			.append(escapeQuotes("CLIENT"))			.append(", ")
			.append(escapeQuotes("ID"))				.append(", ")
			.append(escapeQuotes("LANGUAGE"))		.append(", ")
			.append(escapeQuotes("TYPE"))			.append(", ")
			.append(escapeQuotes("TEXT"))			.append(", ")
			.append(escapeQuotes("MODIFIED_AT"))	.append(", ")
			.append(escapeQuotes("MODIFIED_BY"))
			.append(" ) VALUES ")
			.append("( ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_USER )")
			;
			
		try
		{
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, profiletext.get(i).getClient());
					ps.setInt(2, profiletext.get(i).getId());
					ps.setString(3, profiletext.get(i).getLanguage());
					ps.setString(4, profiletext.get(i).getType());
					ps.setString(5, profiletext.get(i).getText());
				}
				
				@Override
				public int getBatchSize() {
					return profiletext.size();
				}
			});
			
			result.put("status", "Saved Successfully"); 
			result.put("code", "200");
		}
		catch(Exception e)
		{
			result.put("status", "Error");
			result.put("error", e.toString());
			result.put("code", "500");
		}
		
		return result;
	}
	
	private ProfileHierarchyTemplate getProfileTemplate(int client, String countrycode, String sorg, int delvch, int div) {
		
		ProfileHierarchyTemplate template = new ProfileHierarchyTemplate();
		String result = "";
        StringBuilder sql = new StringBuilder();
		sql
			.append("SELECT ")
			.append(escapeQuotes("CLIENT"))				.append(", ")
			.append(escapeQuotes("SORG"))				.append(", ")
			.append(escapeQuotes("DELVCHL"))			.append(", ")
			.append(escapeQuotes("DIV"))				.append(", ")
			.append(escapeQuotes("COUNTRY_CODE"))		.append(", ")
			.append(escapeQuotes("BUSINESS_AREA"))		.append(", ")
			.append(escapeQuotes("BUSINESS_AREA_TEXT"))	.append(", ")
			.append(escapeQuotes("PROFILE"))			.append(", ")
			.append(escapeQuotes("PROFILE_TEXT"))		.append(", ")
			.append(escapeQuotes("SUBPROFILE"))			.append(", ")
			.append(escapeQuotes("SUBPROFILE_TEXT"))	.append(", ")
			.append(escapeQuotes("SUBPROFILE_DATATYPE"))
			.append(" FROM CUSTOMER.")
			.append(escapeQuotes("customerprofile.db::Customer.vProfile"))
			.append(" WHERE ")
			.append(escapeQuotes("CLIENT"))			.append(" = ")	.append(client) 			.append(" AND ")
			.append(escapeQuotes("SORG"))			.append(" = ")	.append(sqlString(sorg))	.append(" AND ")
			.append(escapeQuotes("DELVCHL"))		.append(" = ")	.append(delvch)				.append(" AND ")
			.append(escapeQuotes("DIV"))			.append(" = ")	.append(div)				.append(" AND ")
			.append(escapeQuotes("COUNTRY_CODE"))	.append(" = ")	.append(sqlString(countrycode))
			;
			
		jdbcTemplate.query(sql.toString(), new ProfileHierarchyTemplateMapper(template));
		return template;
		
	}

	@Override
	public ProfileHierarchyTemplate getCustomerProfile(int client, String countrycode, String sorg, int delvch, int div, int customerid) {
		
		ProfileHierarchyTemplate hierarchy = getProfileTemplate(client, countrycode, sorg, delvch, div);
		
		StringBuilder sql = new StringBuilder();
		sql
			.append("SELECT ")
			.append(escapeQuotes("CUSTOMERID"))			.append(", ")
			.append(escapeQuotes("SUBPROFILE"))			.append(", ")
			//.append(escapeQuotes("BRAND_ID"))			.append(", ")
			//.append(escapeQuotes("BRAND_NAME"))			.append(", ")
			.append(escapeQuotes("VALUE"))
			.append(" FROM CUSTOMER.")
			.append(escapeQuotes("customerprofile.db::Customer.CustomerProfile"))
			//.append(" LEFT JOIN CUSTOMER.")
			//.append(escapeQuotes("customerprofile.db::Customer.Brands"))
			//.append(" ON BRAND_ID = ID ")
			.append(" WHERE ")
			.append(escapeQuotes("CUSTOMERID"))		.append(" = ")	.append(customerid) 		.append(" AND ")
			.append(escapeQuotes("CLIENT"))			.append(" = ")	.append(client) 			.append(" AND ")
			.append(escapeQuotes("SORG"))			.append(" = ")	.append(sqlString(sorg))	.append(" AND ")
			.append(escapeQuotes("DELVCHL"))		.append(" = ")	.append(delvch)				.append(" AND ")
			.append(escapeQuotes("DIV"))			.append(" = ")	.append(div)				.append(" AND ")
			.append(escapeQuotes("COUNTRY_CODE"))	.append(" = ")	.append(sqlString(countrycode))
			;
		
		jdbcTemplate.query(sql.toString(), new CustomerProfileHierarchyMapper(hierarchy));
		return hierarchy;
	}

	@Override
	public HashMap<String, String> insertBrands(List<Brand> brand) {
		
		HashMap<String, String> result = new HashMap<String, String>();
        StringBuilder sql = new StringBuilder();
		sql
			.append("INSERT INTO CUSTOMER. ")
			.append(escapeQuotes("customerprofile.db::Customer.Brands"))
			.append(" ( ")
			.append(escapeQuotes("ID"))			.append(", ")
			.append(escapeQuotes("BRAND_NAME"))
			.append(" ) VALUES ")
			.append("( ?, ? )")
			;
			
		try
		{
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, brand.get(i).getId());
					ps.setString(2, brand.get(i).getName());
				}
				
				@Override
				public int getBatchSize() {
					return brand.size();
				}
			});
			
			result.put("status", "Saved Successfully"); 
			result.put("code", "200");
		}
		catch(Exception e)
		{
			result.put("status", "Error");
			result.put("error", e.toString());
			result.put("code", "500");
		}
		
		return result;
	}

	@Override
	public List<Brand> getBrandsList() {
		
        StringBuilder sql = new StringBuilder();
		sql
			.append("SELECT ")
			.append(escapeQuotes("ID"))				.append(", ")
			.append(escapeQuotes("BRAND_NAME"))	
			.append(" FROM CUSTOMER.")
			.append(escapeQuotes("customerprofile.db::Customer.Brands"))
			;
			
		List<Brand> brands = jdbcTemplate.query(sql.toString(), new BrandMapper());
		return brands;
	}

	public List<ProfileView> getProfileTemplateList() {
		
		String result = "";
        StringBuilder sql = new StringBuilder();
		sql
			.append("SELECT ")
			.append(escapeQuotes("CLIENT"))				.append(", ")
			.append(escapeQuotes("SORG"))				.append(", ")
			.append(escapeQuotes("DELVCHL"))			.append(", ")
			.append(escapeQuotes("DIV"))				.append(", ")
			.append(escapeQuotes("COUNTRY_CODE"))		.append(", ")
			.append(escapeQuotes("BUSINESS_AREA"))		.append(", ")
			.append(escapeQuotes("BUSINESS_AREA_TEXT"))	.append(", ")
			.append(escapeQuotes("PROFILE"))			.append(", ")
			.append(escapeQuotes("PROFILE_TEXT"))		.append(", ")
			.append(escapeQuotes("SUBPROFILE"))			.append(", ")
			.append(escapeQuotes("SUBPROFILE_TEXT"))	.append(", ")
			.append(escapeQuotes("SUBPROFILE_DATATYPE"))
			.append(" FROM CUSTOMER.")
			.append(escapeQuotes("customerprofile.db::Customer.vProfile"))
			;
			
		List<ProfileView> data = jdbcTemplate.query(sql.toString(), new ProfileViewMapper());
		return data;
		
	}

	public HashMap<String, String> deleteProfile(int level, String value) {
		
		HashMap<String, String> result = new HashMap<String, String>();
        StringBuilder sql_profile = new StringBuilder();
        StringBuilder sql_profiletext = new StringBuilder();
        
        String WHERE =  level == 1 ? "CLIENT" :
        				level == 2 ? "COUNTRY_CODE":
        				level == 3 ? "SORG":
        				level == 4 ? "DELVCHL":
        				level == 5 ? "DIV":
        				level == 6 ? "BUSINESS_AREA":	
        				level == 7 ? "PROFILE":
        				level == 8 ? "SUBPROFILE": "";
        
        sql_profile
			.append("DELETE FROM CUSTOMER.")
			.append(escapeQuotes("customerprofile.db::Customer.Profile"))
			.append(" WHERE ")
			.append(WHERE).append(" = ").append(sqlString(value))
			;
        
        sql_profiletext
		.append("DELETE FROM CUSTOMER.")
		.append(escapeQuotes("customerprofile.db::Customer.ProfileText"))
		.append(" WHERE ")
		.append("( TYPE = ").append(sqlString("BUSINESSAREA"))	.append("and ID NOT IN (SELECT DISTINCT BUSINESS_AREA FROM  CUSTOMER.")	.append(escapeQuotes("customerprofile.db::Customer.Profile")).append(")) OR ")
		.append("( TYPE = ").append(sqlString("PROFILE"))		.append("and ID NOT IN (SELECT DISTINCT PROFILE FROM  CUSTOMER.")		.append(escapeQuotes("customerprofile.db::Customer.Profile")).append(")) OR ")
		.append("( TYPE = ").append(sqlString("SUBPROFILE"))	.append("and ID NOT IN (SELECT DISTINCT SUBPROFILE FROM  CUSTOMER.")		.append(escapeQuotes("customerprofile.db::Customer.Profile")).append(")) OR ")
		.append("( TYPE = ").append(sqlString("DIV"))			.append("and ID NOT IN (SELECT DISTINCT DIV FROM  CUSTOMER.")			.append(escapeQuotes("customerprofile.db::Customer.Profile")).append(")) OR ")
		.append("( TYPE = ").append(sqlString("DELVCHL"))		.append("and ID NOT IN (SELECT DISTINCT DELVCHL FROM  CUSTOMER.")		.append(escapeQuotes("customerprofile.db::Customer.Profile")).append(")) OR ")
		.append("( TYPE = ").append(sqlString("COUNTRYCODE"))	.append("and ID NOT IN (SELECT DISTINCT COUNTRY_CODE FROM  CUSTOMER.")	.append(escapeQuotes("customerprofile.db::Customer.Profile")).append("))")
		
		;
		
		try {
			jdbcTemplate.update(sql_profile.toString());
			jdbcTemplate.update(sql_profiletext.toString());
			result.put("status", "Deleted Successfully");
			result.put("code", "200");
		}
		catch(Exception e)
		{
			result.put("status", "Error");
			result.put("error", e.toString());
			result.put("code", "500");
		}
		
		return result;
		
	}
	
	
}
