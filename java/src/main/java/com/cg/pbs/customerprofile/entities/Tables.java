package com.cg.pbs.customerprofile.entities;

public class Tables {

	private String tableName;
	private Integer recordCount;
	private String schemaName;
	
	 public Tables()
    {
        super();
    }

	public Tables(String tableName, int recordCount, String schemaName)
    {
        super();
        this.tableName 		= tableName;
        this.recordCount	= recordCount;
        this.schemaName		= schemaName;
    }
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	/**
	 * @return the schemaName
	 */
	public String getSchemaName() {
		return schemaName;
	}
	/**
	 * @param schemaName the schemaName to set
	 */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	/**
	 * @return the recordCount
	 */
	public Integer getRecordCount() {
		return recordCount;
	}
	/**
	 * @param recordCount the recordCount to set
	 */
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	

}
