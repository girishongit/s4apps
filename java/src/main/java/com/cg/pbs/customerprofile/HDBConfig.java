package com.cg.pbs.customerprofile;

/**
 *
 * @author GBANGALO
 */ 
import javax.sql.DataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class HDBConfig {

	@Bean
	public DataSource getDataSource() {
    	
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	String DB_USERNAME = "";
    	String DB_PASSWORD = "";
    	String DB_HOST = "";
    	String DB_PORT = "";
	
    	try {
    	    JSONObject obj = new JSONObject(System.getenv("VCAP_SERVICES"));
    	    JSONArray arr = obj.getJSONArray("hanatrial");
    	    DB_USERNAME = arr.getJSONObject(0).getJSONObject("credentials").getString("user");
    	    DB_PASSWORD = arr.getJSONObject(0).getJSONObject("credentials").getString("password");
    	    DB_HOST = arr.getJSONObject(0).getJSONObject("credentials").getString("host").split(",")[0];
    	    DB_PORT = arr.getJSONObject(0).getJSONObject("credentials").getString("port");
    	    String DB_READ_CONNECTION_URL = "jdbc:sap://" + DB_HOST + ":" + DB_PORT;
	
    	    
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
