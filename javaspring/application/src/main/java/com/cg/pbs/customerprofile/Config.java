package com.cg.pbs.customerprofile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class Config {
	
	  private String hdbuser;
	  private String hdbpassword;
	  private String hdbhost;
	  private String hdbport;
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
	public String getHdbhost() {
		return hdbhost;
	}
	public void setHdbhost(String hdbhost) {
		this.hdbhost = hdbhost;
	}
	public String getHdbport() {
		return hdbport;
	}
	public void setHdbport(String hdbport) {
		this.hdbport = hdbport;
	}
	  


}
