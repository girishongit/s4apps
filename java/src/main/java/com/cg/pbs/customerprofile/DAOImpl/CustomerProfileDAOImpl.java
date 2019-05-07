package com.cg.pbs.customerprofile.DAOImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.cg.pbs.customerprofile.DAO.CustomerProfileDAO;
import com.cg.pbs.customerprofile.DAOMapper.CustomerProfileHierarchyMapper;
import com.cg.pbs.customerprofile.DAOMapper.TableMapper;
import com.cg.pbs.customerprofile.entities.CustomerProfile;
import com.cg.pbs.customerprofile.entities.CustomerProfileHierarchy;
import com.cg.pbs.customerprofile.entities.Tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author: GBANGALO
 **/

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
	
    @Override
    public List<Tables> getAvailableTables()
    {
        String sql = "select SCHEMA_NAME, TABLE_NAME, RECORD_COUNT from M_TABLES";
        List<Tables> tables = jdbcTemplate.query(sql, new TableMapper());
        return tables;
    }

    @Override
    public List<CustomerProfileHierarchy> getCustomerProfiles()
    {
        String sql = "select * from \"CUSTOMER\".\"customerprofile.db::Customer.CustomerProfile\"";
        List<CustomerProfileHierarchy> customerprofile = jdbcTemplate.query(sql, new CustomerProfileHierarchyMapper());
        return customerprofile;
    }
    
    @Override
    public String insertCustomerProfile(List<CustomerProfile> cp)
    {
    	String result = "";
        StringBuilder sql = new StringBuilder();
		sql
			.append("INSERT INTO CUSTOMER. ")
			.append(escapeQuotes("customerprofile.db::Customer.CustomerProfile"))
			.append(" ( ")
			.append(escapeQuotes("CUSTOMERID"))		.append(", ")
			.append(escapeQuotes("COUNTRY_CODE"))	.append(", ")
			.append(escapeQuotes("SUBPROFILE"))		.append(", ")
			.append(escapeQuotes("VALUE"))			.append(", ")
			.append(escapeQuotes("MODIFIED_AT"))	.append(", ")
			.append(escapeQuotes("MODIFIED_BY"))
			.append(" ) VALUES ")
			.append("( ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_USER )")
			;
			
		try
		{
			jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setInt(1, cp.get(i).getCustomerId());
					ps.setInt(2, cp.get(i).getCountryCode());
					ps.setInt(3, cp.get(i).getSubProfile());
					ps.setString(4, cp.get(i).getValue());
				}
				
				@Override
				public int getBatchSize() {
					return cp.size();
				}
			});
			
			result = "Inserted Successfully...!";
		}
		catch(Exception e)
		{
			result = e.toString();
		}
		
		return result;
		
    }

	public String updateCustomerProfile(CustomerProfile cp) {
		
		String result = "";
        StringBuilder sql = new StringBuilder();
		sql
			.append("UPDATE CUSTOMER. ")
			.append(escapeQuotes("customerprofile.db::Customer.CustomerProfile"))
			.append(" SET ")
			.append(escapeQuotes("VALUE"))			.append(" = ") .append(" ?, ")
			.append(escapeQuotes("MODIFIED_AT"))	.append(" = ") .append(" CURRENT_TIMESTAMP, ")
			.append(escapeQuotes("MODIFIED_BY"))	.append(" = ") .append(" CURRENT_USER ")
			.append(" WHERE ")
			.append(escapeQuotes("CUSTOMERID"))		.append(" = ") .append(" ? ")	.append(" AND ")
			.append(escapeQuotes("SUBPROFILE"))		.append(" = ") .append(" ? ")
			;
		
		try
		{
			jdbcTemplate.update(sql.toString(), cp.getValue(), cp.getCustomerId() , cp.getSubProfile() );
			result = "Updated Successfully";
		}
		catch(Exception e)
		{
			result = e.toString();
		}
	        				
		return result;
	}

	@Override
	public String deleteCustomerProfile(CustomerProfile cp) {
		
		String sql = "DELETE from \"CUSTOMER\".\"customerprofile.db::Customer.CustomerProfile\"";
		jdbcTemplate.update(sql.toString());
		return "Deleted Suvccessfully";
	}

}
