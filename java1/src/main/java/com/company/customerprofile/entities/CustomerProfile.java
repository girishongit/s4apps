package com.company.customerprofile.entities;

public class CustomerProfile {
	private int customerId;
    private int countrycode;
    private int businessArea;
    private int profile;
    private int subProfile;
    private String value;
    private String valueType;
  
    public CustomerProfile()
    {
        super();
    }

	public CustomerProfile(int customerId, int countrycode, int businessArea, int profile, int subProfile, String value, String valueType)
    {
        super();
        this.customerId 	= customerId;
        this.businessArea	= businessArea;
        this.profile		= profile;
        this.subProfile 	= subProfile;
        this.countrycode	= countrycode;
        this.value			= value;
        this.valueType		= valueType;
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
	 * @return the countrycode
	 */
	public int getCountrycode() {
		return countrycode;
	}

	/**
	 * @param countrycode the countrycode to set
	 */
	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}

	/**
	 * @return the businessArea
	 */
	public int getBusinessArea() {
		return businessArea;
	}

	/**
	 * @param businessArea the businessArea to set
	 */
	public void setBusinessArea(int businessArea) {
		this.businessArea = businessArea;
	}

	/**
	 * @return the profile
	 */
	public int getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(int profile) {
		this.profile = profile;
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

	/**
	 * @return the valueType
	 */
	public String getValueType() {
		return valueType;
	}

	/**
	 * @param valueType the valueType to set
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

   
}
