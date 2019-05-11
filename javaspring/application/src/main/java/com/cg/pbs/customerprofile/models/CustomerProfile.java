package com.cg.pbs.customerprofile.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gbangalo
 *
 */
public class CustomerProfile {
	
	private int client;
	private String sorg;
	private int delvchl;
	private int div;
	private int countrycode;
	private int customerid;
	private Date validfrom;
	private Date validto;
	private String modifiedby;
	private String modifiedat;
	private List<SubProfileValues> values = new ArrayList<SubProfileValues>();
	
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public String getSorg() {
		return sorg;
	}
	public void setSorg(String sorg) {
		this.sorg = sorg;
	}
	public int getDelvchl() {
		return delvchl;
	}
	public void setDelvchl(int delvchl) {
		this.delvchl = delvchl;
	}
	public int getDiv() {
		return div;
	}
	public void setDiv(int div) {
		this.div = div;
	}
	public int getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public Date getValidfrom() {
		return validfrom;
	}
	public void setValidfrom(Date validfrom) {
		this.validfrom = validfrom;
	}
	public Date getValidto() {
		return validto;
	}
	public void setValidto(Date validto) {
		this.validto = validto;
	}
	public String getModifiedby() {
		return modifiedby;
	}
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}
	public String getModifiedat() {
		return modifiedat;
	}
	public void setModifiedat(String modifiedat) {
		this.modifiedat = modifiedat;
	}
	public List<SubProfileValues> getValues() {
		return values;
	}
	public void addValues(SubProfileValues values) {
		this.values.add(values);
	}
	public void setValues(List<SubProfileValues> values) {
		this.values = values ;
	}
	
}
