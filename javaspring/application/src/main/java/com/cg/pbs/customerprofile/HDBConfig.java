package com.cg.pbs.customerprofile;

/**
*
* @author GBANGALO
*/ 
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class HDBConfig {

	@Autowired
    private Config localconfig;
	
	@Bean
	public DataSource getDataSource() {
   	
   	DriverManagerDataSource dataSource = new DriverManagerDataSource();
   	String DB_USERNAME = "";
   	String DB_PASSWORD = "";
   	String DB_HOST = "";
   	String DB_PORT = "";
   	String DB_READ_CONNECTION_URL = "";
   	String DB_SCHEMA = "";
	
   	try {
   		if(System.getenv("VCAP_SERVICES") != null)
   		{
   	    	JSONObject obj = new JSONObject(System.getenv("VCAP_SERVICES"));
   	    	JSONArray arr = obj.getJSONArray("hanatrial");
   	    	DB_USERNAME = arr.getJSONObject(0).getJSONObject("credentials").getString("user");
   	    	DB_PASSWORD = arr.getJSONObject(0).getJSONObject("credentials").getString("password");
   	    	DB_HOST = arr.getJSONObject(0).getJSONObject("credentials").getString("host").split(",")[0];
   	    	DB_PORT = arr.getJSONObject(0).getJSONObject("credentials").getString("port");
   	    	DB_READ_CONNECTION_URL = "jdbc:sap://" + DB_HOST + ":" + DB_PORT;
   		}
   		else
   		{
   			DB_USERNAME = localconfig.getHdbuser();
   			DB_PASSWORD = localconfig.getHdbpassword();
   			DB_HOST		= localconfig.getHdbhost();
   			DB_PORT		= localconfig.getHdbport();
   			DB_SCHEMA	= localconfig.getHdbschema();
   			DB_READ_CONNECTION_URL = "jdbc:sap://" + DB_HOST + ":" + DB_PORT+"/?currentschema="+DB_SCHEMA;
   		}
   	    	
	 
				dataSource.setDriverClassName("com.sap.db.jdbc.Driver");
				dataSource.setUrl(DB_READ_CONNECTION_URL);
				dataSource.setUsername(DB_USERNAME);
				dataSource.setPassword(DB_PASSWORD);
   	    
   	} catch (Exception e) {
   	    System.out.println(e);
   	}

   return dataSource;
 }
 
}
