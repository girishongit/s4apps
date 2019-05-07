package com.cg.pbs.customerprofile.DAOMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.pbs.customerprofile.entities.Tables;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author: GBANGALO
 **/

public class TableMapper implements RowMapper<Tables>
{
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
