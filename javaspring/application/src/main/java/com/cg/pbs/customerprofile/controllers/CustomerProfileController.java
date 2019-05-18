package com.cg.pbs.customerprofile.controllers;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.cg.pbs.customerprofile.DAOImpl.CustomerProfileDAOImpl;
import com.cg.pbs.customerprofile.models.Brand;
import com.cg.pbs.customerprofile.models.CustomerProfile;
import com.cg.pbs.customerprofile.models.Profile;
import com.cg.pbs.customerprofile.models.ProfileHierarchyTemplate;
import com.cg.pbs.customerprofile.models.ProfileText;
import com.cg.pbs.customerprofile.models.ProfileView;


/**
 * 
 * @author gbangalo
 *
 */

@RestController
@RequestMapping( "/api" )
public class CustomerProfileController
{
    private static final Logger logger = CloudLoggerFactory.getLogger(CustomerProfileController.class);
    
    @Autowired
    CustomerProfileDAOImpl customerprofiledaoimpl;

    @GetMapping("/getToken")
    public ResponseEntity<String> getToken( )
    {
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }
    
    @GetMapping("/customerlist")
    public ResponseEntity<List<CustomerProfile>> getCustomerList( )
    {
        List<CustomerProfile> dataList = customerprofiledaoimpl.getCustomerList();
        return new ResponseEntity<List<CustomerProfile>>(dataList, HttpStatus.OK);
    }
    
    @GetMapping("/customerprofiledetails")
    public ResponseEntity<CustomerProfile> getAllData(  @RequestParam final int customerid )
    {
        CustomerProfile dataList = customerprofiledaoimpl.getCustomerProfileDetails(customerid);
        return new ResponseEntity<CustomerProfile>(dataList, HttpStatus.OK);
    }
    
    @GetMapping("/customerprofile/{client}/{countrycode}/{sorg}/{delvch}/{div}")
    public ResponseEntity<ProfileHierarchyTemplate> getProfileTemplate( @PathVariable int client,@PathVariable String countrycode, @PathVariable String sorg, @PathVariable int delvch, @PathVariable int div, @RequestParam final int customerid )
    {
        ProfileHierarchyTemplate template = customerprofiledaoimpl.getCustomerProfile(client, countrycode, sorg, delvch, div, customerid);
        return new ResponseEntity<ProfileHierarchyTemplate>(template, HttpStatus.OK);
    }
    
    @PostMapping(path = "/customerprofile")		//consumes = MediaType.APPLICATION_JSON_VALUE
    public ResponseEntity<HashMap<String, String>> insertCustomerProfile(@RequestBody CustomerProfile cp)
    {
    	HashMap<String, String> result = customerprofiledaoimpl.insertCustomerProfile(cp);
        return new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
    }
    
    @PutMapping(path = "/customerprofile")
    public ResponseEntity<HashMap<String, String>> upsertCustomerProfile(@RequestBody CustomerProfile cp)
    {
    	HashMap<String, String> result = customerprofiledaoimpl.deleteCustomerProfile(cp);
        if(result.get("code") == "200")
        	result = customerprofiledaoimpl.insertCustomerProfile(cp);
        
        if(result.get("code") == "200")
        	result.put("status", "Updated Successfully");
        
        return new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
        
    }
    
    @DeleteMapping(path = "/customerprofile")
    public ResponseEntity<HashMap<String, String>> deleteCustomerProfile(@RequestBody CustomerProfile cp)
    {
    	HashMap<String, String> result = customerprofiledaoimpl.deleteCustomerProfile(cp);
        return new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
    }
    
    @PostMapping("/profile")
    public ResponseEntity<HashMap<String, String>> insertProfile(@RequestBody List<Profile> profile)
    {
    	HashMap<String, String> result = customerprofiledaoimpl.insertProfile(profile);
        return new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
    }
    
    @DeleteMapping("/profile/{level}/{value}")
    public ResponseEntity<HashMap<String, String>> deleteProfile(@PathVariable int level,@PathVariable String value)
    {
    	HashMap<String, String> result = customerprofiledaoimpl.deleteProfile(level, value);
        return new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
    }
    
    @PostMapping("/profiletext")
    public ResponseEntity<HashMap<String, String>> insertProfileText(@RequestBody List<ProfileText> profiletext)
    {
    	HashMap<String, String> result = customerprofiledaoimpl.insertProfileText(profiletext);
        return new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
    }
    
    /*@PostMapping("/brands")
    public ResponseEntity<HashMap<String, String>> insertbrands(@RequestBody List<Brand> brands)
    {
    	HashMap<String, String> result = customerprofiledaoimpl.insertBrands(brands);
        return new ResponseEntity<HashMap<String, String>>(result, HttpStatus.OK);
    }
    
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> insertbrands()
    {
    	List<Brand> brands = customerprofiledaoimpl.getBrandsList();
        return new ResponseEntity<List<Brand>>(brands, HttpStatus.OK);
    }*/
    
    @GetMapping("/profilelist")
    public ResponseEntity<List<ProfileView>> getProfileTemplateList(  )
    {
    	List<ProfileView> dataList = customerprofiledaoimpl.getProfileTemplateList();
        return new ResponseEntity<List<ProfileView>>(dataList, HttpStatus.OK);
    }
    
}
