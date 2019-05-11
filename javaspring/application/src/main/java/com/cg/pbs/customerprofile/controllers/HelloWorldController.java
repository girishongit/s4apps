package com.cg.pbs.customerprofile.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.cg.pbs.customerprofile.LocalConfig;
import com.cg.pbs.customerprofile.DAOImpl.CustomerProfileDAOImpl;
import com.cg.pbs.customerprofile.models.HelloWorldResponse;
import com.cg.pbs.customerprofile.models.Tables;
import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;

@RestController
@RequestMapping( "/test" )
public class HelloWorldController
{
    private static final Logger logger = CloudLoggerFactory.getLogger(HelloWorldController.class);
    
    @Autowired
    private LocalConfig config;
    
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
    public ResponseEntity<List<Tables>> getAvailableTables(@RequestParam( defaultValue = "" ) final String schema)
    {
        List<Tables> tablelist = customerprofiledaoimpl.getAvailableTables(schema);
        return new ResponseEntity<List<Tables>>(tablelist, HttpStatus.OK);
    }
    
   /* @GetMapping("/token")
	public Map<String, String> message(@AuthenticationPrincipal Token token) {
		Map<String, String> result = new HashMap<>();
		result.put("grant type", token.getGrantType());
		result.put("client id", token.getClientId());
		result.put("subaccount id", token.getSubaccountId());
		result.put("logon name", token.getLogonName());
		result.put("family name", token.getFamilyName());
		result.put("given name", token.getGivenName());
		result.put("email", token.getEmail());
		result.put("authorities", String.valueOf(token.getAuthorities()));
		result.put("scopes", String.valueOf(token.getScopes()));

		return result;
	}*/
    
}
