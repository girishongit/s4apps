package com.cg.pbs.customerprofile.models;

/**
 * 
 * @author gbangalo
 *
 */

public class Tables {
	private String tableName;
	private Integer recordCount;
	private String schemaName;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Integer getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
}
