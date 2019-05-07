package com.cg.pbs.customerprofile.controller;

/**
 *
 * @author GBANGALO
 */
 
//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.client.RestTemplate;

//import java.util.HashMap;
//import java.lang.Object;
//import java.net.*;
//import java.util.List;
import java.util.Random;

import com.cg.pbs.customerprofile.DAOImpl.CustomerProfileDAOImpl;
import com.cg.pbs.customerprofile.entities.CustomerProfile;
import com.cg.pbs.customerprofile.entities.CustomerProfileHierarchy;
import com.cg.pbs.customerprofile.entities.Tables;

@RestController
@RequestMapping("/api")
public class CustomerProfileController {
	
	@Autowired
    CustomerProfileDAOImpl customerprofiledaoimpl;
    
	@GetMapping("/tablelist")
    public ResponseEntity<List<Tables>> getAvailableTables()
    {
        List<Tables> tablelist = customerprofiledaoimpl.getAvailableTables();
        return new ResponseEntity<List<Tables>>(tablelist, HttpStatus.OK);
    }
	
	@GetMapping("/data")
    public ResponseEntity<List<CustomerProfileHierarchy>> getAllData()
    {
        List<CustomerProfileHierarchy> dataList = customerprofiledaoimpl.getCustomerProfiles();
        return new ResponseEntity<List<CustomerProfileHierarchy>>(dataList, HttpStatus.OK);
    }
    
    @PostMapping("/customerprofile")
    public ResponseEntity<String> insertCP(@RequestBody List<CustomerProfile> cp)
    {
        String result = customerprofiledaoimpl.insertCustomerProfile(cp);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    
    @PutMapping("/customerprofile")
    public ResponseEntity<String> updateCP(@RequestBody CustomerProfile cp)
    {
        String result = customerprofiledaoimpl.updateCustomerProfile(cp);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
    
    @DeleteMapping("/customerprofile")
    public ResponseEntity<String> deleteCP(@RequestBody CustomerProfile cp)
    {
        String result = customerprofiledaoimpl.deleteCustomerProfile(cp);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
	
    /*@GetMapping("/data")
    public ResponseEntity<List<VendorDetails>> getAllData()
    {
        List<VendorDetails> dataList = vendorDAOImpl.getAllData();
        return new ResponseEntity<List<VendorDetails>>(dataList, HttpStatus.OK);
    }*/

   
    /*
    @PutMapping("/data/{fileid}/{recordid}")
    public ResponseEntity<VendorDetails> updateRecord(@RequestBody VendorDetails data, @PathVariable("fileid") Integer fileid, @PathVariable("recordid") Integer recordid)
    {
        vendorDAOImpl.updateRecord(fileid,recordid,data);
        return new ResponseEntity<VendorDetails>(data, HttpStatus.ACCEPTED);
    }
	
    @PostMapping("/data")
    public ResponseEntity<Void> addVendor(@RequestBody VendorDetails vendor)
    {
        vendorDAOImpl.addData(vendor);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable("id") Integer id)
    {
        vendorDAOImpl.deleteFile(id);
        return new ResponseEntity<String>("File Deleted Successfully (FileID = "+id+").",HttpStatus.OK);
    }
    
    //@CrossOrigin(origins = "http://localhost:8080")
    @CrossOrigin
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
    	 String data = csvReader.readCsvDatatoHDB(file);
    	 return data;
    }*/
    
    
    @GetMapping("/")
    public ResponseEntity<String> getLandingPage()
    {
    	String[] quotes = new String[]
    	{
			"Without requirements or design, programming is the art of adding bugs to an empty text file.",
			"Projects we have completed demonstrate what we know - future projects decide what we will learn.",
			"The most important thing is to keep the most important thing as the most important thing.",
			"There are 10 people in the world, those who can read BINARY and those who cannot.",
			"Before software can be reusable it first has to be usable.",
			"It is not enough to do your best: you must KNOW what to do, and THEN do your best.",
			"The significant problems we face cannot be solved by the same level of thinking that created them.",
			"It's hard enough to find an error in your code when you're looking for it;<br>its even harder when you've ASSUMED your code is ERROR-FREE.",
			"The perfect project plan is POSSIBLE if one first documents a list of ALL the UNKNOWNS.",
			"Ref*ctoring: Refactoring an otherwise well built code, adding changes so that nobody but yourself can maintain it.",
			"Figure out the data structures, and the code will follow",
			"The Primary purpose of modelling is to provide an opportunity to thing before you act",
			"Make it correct, make it clear, make it concise, make it fast; In that order.",
			"Code never lies, Comments sometimes do.",
			"Continuous improvement is better than delayed perfection.",
			"To SQL or NO SQL, that is the query.",
			"The Scrum idea of a separated Scrum Master is good for Scrum, but not appropriate for most projects.<br> Good development requires not just talkers but doers.",
			"Sometimes it pays to stay in bed on Monday, rather than spending the rest of the week debugging Monday's code.",
			"Walking on water and developing software from a specification are easy if both are frozen.",
			"Programming today is a race between software engineers striving to build bigger and better idiot-proof programs,<br> and the Universe trying to produce bigger and better idiots. So far, the Universe is winning."
			}
			;
		
		Random rand = new Random();
		int  index = rand.nextInt(19);
			
    	StringBuilder html = new StringBuilder();
	    html	
	    	.append("<html")
			.append("<body>")
			.append("<style>")
			.append("body {")
	        .append("font: 12px/20px \"Lucida Grande\", Tahoma, Verdana, sans-serif;")
	        .append("font-size: large;")
	        .append("color: #404040;")
	        .append("background: hsl(180, 8%, 87%);")
	        .append("line-height: 150%;")
	    	.append("}")
	    	.append("p { font-size: x-small; }")
			.append(".v-center-table {")
			.append("display: table;")
			.append("width: 100%;")
			.append("height: 100%;")
			.append("text-align: center;")
			.append("}")
			.append(".v-center-table div {")
			.append("display: table-cell;")
			.append("vertical-align: middle;")
			.append("}")
			.append("</style>")
			.append("<div class='v-center-table'>")
			.append("<div>")
			.append(quotes[index])
			.append("<br>")
			.append("<a href=redirect>Know me more...</a>")
			.append("<p>By the way enter approprite api endpoint to redirect</p>")
			.append("</div>")
			.append("</div>")
			.append("<html>")
			;
 
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(org.springframework.http.MediaType.TEXT_HTML);
		return new ResponseEntity<String>(html.toString(), httpHeaders, HttpStatus.OK);
    }
    
    @GetMapping("/redirect")
    public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
	    URI eurl = new URI("https://www.linkedin.com/in/girish-bn");
	    HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setLocation(eurl);
	    return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
    
}
