namespace customerprofile.db;
context Customer {

    Entity Profile{
    	KEY CLIENT				: Integer;
    	KEY SORG				: String(5);
    	KEY DELVCHL				: Integer;
    	KEY DIV					: Integer;
    	KEY COUNTRY_CODE		: Integer;
    	KEY BUSINESS_AREA		: Integer;
    	KEY PROFILE				: Integer;
    	KEY SUBPROFILE			: Integer;
	    	SUBPROFILE_DATATYPE	: String(10);
    		MODIFIED_AT			: UTCTimestamp default NOW();
    		MODIFIED_BY			: String(150);
    }; 
    
    Entity ProfileText{
    	KEY	CLIENT				: Integer;
    	KEY ID					: Integer;
    	KEY LANGUAGE			: String(3) 	default 'EN';
    	KEY TYPE				: String(10);
    		TEXT				: String(100);
    		MODIFIED_AT			: UTCTimestamp	default NOW();
    		MODIFIED_BY			: String(150);
    }; 
    
    Entity Brands{
        KEY ID					: Integer;
    	    BRAND_NAME			: String(150)		default 'Delault Brand';
    }; 
    
    Entity CustomerProfile{
    	KEY CLIENT				: Integer;
    	KEY SORG				: String(5)		default 'DEFLT';
    	KEY DELVCHL				: Integer		default 0;
    	KEY DIV					: Integer		default 0;
    	KEY COUNTRY_CODE		: Integer;
    	KEY CUSTOMERID			: Integer;
    	KEY SUBPROFILE			: Integer;
    	KEY	BRAND_ID			: Integer;
    		VALUE				: String(150);
    		VALID_FROM			: LocalDate;
    		VALID_TO			: LocalDate;
    		MODIFIED_AT			: UTCTimestamp	default NOW();
    		MODIFIED_BY			: String(150);
    };

    
};