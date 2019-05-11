package com.cg.pbs.customerprofile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class LocalConfig {
	
	  private String hdbuser;
	  private String hdbpassword;
	  private String hdburl;
	  
	public String getHdbuser() {
		return hdbuser;
	}
	public void setHdbuser(String hdbuser) {
		this.hdbuser = hdbuser;
	}
	public String getHdbpassword() {
		return hdbpassword;
	}
	public void setHdbpassword(String hdbpassword) {
		this.hdbpassword = hdbpassword;
	}
	public String getHdburl() {
		return hdburl;
	}
	public void setHdburl(String hdburl) {
		this.hdburl = hdburl;
	}
	  

	  


}
