package com.cg.pbs.customerprofile.entities;

public class CustomerProfile {

	private int customerId;
	private int countryCode;
	private int subProfile;
	private String value;
	
	 public CustomerProfile()
    {
        super();
    }

	public CustomerProfile(int customerId, int countrycode, int subProfile, String value)
    {
        super();
        this.customerId 	= customerId;
        this.subProfile 	= subProfile;
        this.countryCode	= countrycode;
        this.value			= value;
    }
	
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the countryCode
	 */
	public int getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * @return the subProfile
	 */
	public int getSubProfile() {
		return subProfile;
	}
	/**
	 * @param subProfile the subProfile to set
	 */
	public void setSubProfile(int subProfile) {
		this.subProfile = subProfile;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
