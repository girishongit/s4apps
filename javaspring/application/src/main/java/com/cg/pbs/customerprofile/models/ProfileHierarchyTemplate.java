package com.cg.pbs.customerprofile.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProfileHierarchyTemplate {
	
	private int client;
	private String sorg;
	private int delvch;
	private int div;
	private int countrycode;
	private int customerid = -1;
	private List<BusinessArea> businessArea = new ArrayList<>();
	
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


	public int getDelvch() {
		return delvch;
	}
	
	public void setDelvch(int delvch) {
		this.delvch = delvch;
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


	public List<BusinessArea> getBusinessArea() {
		return businessArea;
	}
	
	public int getCustomerid() {
		return customerid;
	}


	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}


	public void setHierarchy(int businessareaid, String baName,  int profileid, String prName, int subprofileid, String sprName, String subprofileDatatype) {
		
		int index = -1;
		
		for(int i = 0; i < this.businessArea.size(); i++) 
		{
			if(businessArea.get(i).equals(businessareaid))
			{
				index = i;
				break;
			}
		}
			

		if(index < 0)
			businessArea.add(new BusinessArea(businessareaid, baName, profileid, prName, subprofileid, sprName, subprofileDatatype));
		else
			businessArea.get(index).addProfile(profileid, prName, subprofileid, sprName, subprofileDatatype);
	}
	
	
	public void updateSubProfile(int customerid, int subprofileid, String value)
	{
		this.setCustomerid(customerid);
		for (BusinessArea ba: this.businessArea)
		{
			ba.updateSubProfile(subprofileid, value);
		}
	}



	private class BusinessArea{
		
		private int id;
		private String name = "";
		private List<Profile> profile = new ArrayList<Profile>();
		
		
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Profile> getProfile() {
			return profile;
		}

		public void setProfile(List<Profile> profile) {
			this.profile = profile;
		}

		BusinessArea(int businessareaid, String baName, int profileid, String prName, int subprofileid, String sprName, String subprofileDatatype)
		{
			this.id = businessareaid;
			this.name = baName;
			this.profile.add(new Profile(profileid, prName, subprofileid, sprName, subprofileDatatype));
		}
		
		public void addProfile(int profileid, String prName, int subprofileid, String sprName, String subprofileDatatype)
		{
			int index = -1;
			
			for(int i =0; i< this.profile.size(); i++)
			{
				if(this.profile.get(i).equals(profileid))
				{
					index = i;
					break;
				}
			}
			
			if(index < 0)
				this.profile.add(new Profile(profileid, prName, subprofileid, sprName, subprofileDatatype));
			else
				this.profile.get(index).addSubProfile(subprofileid, sprName, subprofileDatatype);
		}
		
		private void updateSubProfile(int id, String value)
		{
			for (Profile p: this.profile)
			{
				p.updateSubProfile(id, value);
			}
		}
		
		public boolean equals(int id)
		{
			return this.id == id;
		}
	}
	
	
	private class Profile{
		private int id;
		private String name = "";
		private List<SubProfile> subProfile = new ArrayList<SubProfile>();
		
		
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<SubProfile> getSubProfile() {
			return subProfile;
		}

		public void setSubProfile(List<SubProfile> subProfile) {
			this.subProfile = subProfile;
		}
		
		
		Profile(int profileid, String prName, int subprofileid, String sprName, String subprofileDatatype)
		{
			this.id = profileid;
			this.name = prName;
			this.subProfile.add(new SubProfile(subprofileid, sprName, subprofileDatatype));
		}
		
		void addSubProfile(int subprofileid, String sprName, String subprofileDatatype)
		{
			this.subProfile.add(new SubProfile(subprofileid, sprName, subprofileDatatype));
		}
		
		public boolean equals(int id)
		{
			return this.id == id;
		}
		
		private void updateSubProfile(int id, String value)
		{
			for (SubProfile sp: this.subProfile)
			{
				sp.updateSubProfile(id, value);
			}
		}
		
	}
	
	private class SubProfile
	{
		private int id;
		private String dataType;
		private String name = "";
		private String value = "";

		SubProfile(int subprofileid, String sprName, String subprofileDatatype){
			this.id = subprofileid;
			this.name = sprName;
			this.dataType = subprofileDatatype;
		}

		private void updateSubProfile(int id, String value)
		{
			if(this.id == id)
			{
				this.value = value;
			}
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		
		public String getDataType() {
			return dataType;
		}

		public void setDataType(String dataType) {
			this.dataType = dataType;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		
	}	

}
