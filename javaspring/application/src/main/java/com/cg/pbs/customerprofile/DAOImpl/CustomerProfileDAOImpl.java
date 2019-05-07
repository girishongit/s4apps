package com.cg.pbs.customerprofile.DAOImpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.pbs.customerprofile.DAO.CustomerProfileDAO;
import com.cg.pbs.customerprofile.DAOMapper.TablesMapper;
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
	
	@Override
	public List<Tables> getAvailableTables() {
		
		String sql = "select SCHEMA_NAME, TABLE_NAME, RECORD_COUNT from M_TABLES";
        List<Tables> tables = jdbcTemplate.query(sql, new TablesMapper());
        return tables;
        
	}

}
