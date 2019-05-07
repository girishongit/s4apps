package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.cg.pbs.customerprofile.models.Tables;

/**
 * 
 * @author gbangalo
 *
 */

public class TablesMapper implements RowMapper<Tables>{
	
	 @Override
	    public Tables mapRow(ResultSet row, int rowNum) throws SQLException 
	    {
	        Tables table = new Tables();
	        table.setTableName(row.getString("TABLE_NAME"));
	        table.setRecordCount(row.getInt("RECORD_COUNT"));
	        table.setSchemaName(row.getString("SCHEMA_NAME"));
	        return table;
	    }

}
