package com.cg.pbs.customerprofile.DAO;

import java.util.HashMap;
import java.util.List;

import com.cg.pbs.customerprofile.models.Brand;
import com.cg.pbs.customerprofile.models.CustomerProfile;
import com.cg.pbs.customerprofile.models.Profile;
import com.cg.pbs.customerprofile.models.ProfileHierarchyTemplate;
import com.cg.pbs.customerprofile.models.ProfileText;
import com.cg.pbs.customerprofile.models.ProfileView;
import com.cg.pbs.customerprofile.models.Tables;

public interface CustomerProfileDAO {
	
	//All function dealing with general information
	public List<Tables> getAvailableTables(String schema);
	public HashMap<String, String> updateTableData(String country);
	public List<CustomerProfile> getCustomerList();
	public CustomerProfile getCustomerProfileDetails(int customerid);
	
	//All function for dealing with Customer Information
	public HashMap<String, String> insertCustomerProfile(CustomerProfile cp);
	//public HashMap<String, String> updateCustomerProfile(CustomerProfile cp); // We dont require this, Update can be done be delete and insert
	public HashMap<String, String> deleteCustomerProfile(CustomerProfile cp);
	public ProfileHierarchyTemplate getCustomerProfile(int client, String countrycode, String sorg, int delvch, int div, int customerid);
	
	
	//All function for dealing with Metadata
	public HashMap<String, String> insertProfile(List<Profile> profile);
	public HashMap<String, String> deleteProfile(int level, String value);
	
	public HashMap<String, String> insertProfileText(List<ProfileText> profiletext);
	public HashMap<String, String> insertBrands(List<Brand> brand);
	public List<Brand> getBrandsList();	
	public List<ProfileView> getProfileTemplateList();
	
}
