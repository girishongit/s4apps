package com.cg.pbs.customerprofile.DAO;

/**
 * @author: GBANGALO
 **/
 
import java.util.List;


import com.cg.pbs.customerprofile.entities.Tables;
import com.cg.pbs.customerprofile.entities.CustomerProfileHierarchy;
import com.cg.pbs.customerprofile.entities.CustomerProfile;

public interface CustomerProfileDAO {
	
	public List<Tables> getAvailableTables();
	public List<CustomerProfileHierarchy> getCustomerProfiles();
	public String insertCustomerProfile(List<CustomerProfile> cp);
	public String updateCustomerProfile(CustomerProfile cp);
	public String deleteCustomerProfile(CustomerProfile cp);
	
}