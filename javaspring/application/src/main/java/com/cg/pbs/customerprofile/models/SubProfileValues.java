package com.cg.pbs.customerprofile.models;

/**
 * @author gbangalo
 *
 */
public class SubProfileValues {

		//private int brandid;
		private int subprofile;
		private String value;
		
		public SubProfileValues() {
			super();
		}
		
		public SubProfileValues( int subprofile, String value) {
			//this.brandid = brandid;
			this.subprofile = subprofile;
			this.value = value;
		}
		/*public int getBrandid() {
			return brandid;
		}
		public void setBrandid(int brandid) {
			this.brandid = brandid;
		}*/
		public int getSubprofile() {
			return subprofile;
		}
		public void setSubprofile(int subprofile) {
			this.subprofile = subprofile;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
		
}
