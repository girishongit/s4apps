package com.cg.pbs.customerprofile.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.cg.pbs.customerprofile.Config;
import com.cg.pbs.customerprofile.DAOImpl.CustomerProfileDAOImpl;
import com.cg.pbs.customerprofile.models.HelloWorldResponse;
import com.cg.pbs.customerprofile.models.Tables;

@RestController
@RequestMapping( "/hello" )
public class HelloWorldController
{
    private static final Logger logger = CloudLoggerFactory.getLogger(HelloWorldController.class);
    
    @Autowired
    private Config config;
    
    @Autowired
    CustomerProfileDAOImpl customerprofiledaoimpl;

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<HelloWorldResponse> getHello( @RequestParam( defaultValue = "world" ) final String name )
    {
        logger.info("I am running!");
        return ResponseEntity.ok(new HelloWorldResponse(name));
    }
    
    @GetMapping("/vcap")
    public ResponseEntity<String> getVCAP()
    {
    	Object x = System.getenv("VCAP_SERVICES");
        return new ResponseEntity<String>( x == null ? config.getHdbuser() : x.toString(), HttpStatus.OK); //VCAP_SERVICES
    }
    
    @GetMapping("/tablelist")
    public ResponseEntity<List<Tables>> getAvailableTables()
    {
        List<Tables> tablelist = customerprofiledaoimpl.getAvailableTables();
        return new ResponseEntity<List<Tables>>(tablelist, HttpStatus.OK);
    }
    
}
