package com.liqun.entity;

import java.io.Serializable;

public class BizProductCategoryBrandVW implements Serializable{
	   /***private***/
		private Integer id;
	    private String productBrandNo;
	    private String productBrandName;
	    private String canInstall;
	    private String canRepair;
	    /***public***/
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getProductBrandNo() {
			return productBrandNo;
		}
		public void setProductBrandNo(String productBrandNo) {
			this.productBrandNo = productBrandNo;
		}
		public String getProductBrandName() {
			return productBrandName;
		}
		public void setProductBrandName(String productBrandName) {
			this.productBrandName = productBrandName;
		}
		public String getCanInstall() {
			return canInstall;
		}
		public void setCanInstall(String canInstall) {
			this.canInstall = canInstall;
		}
		public String getCanRepair() {
			return canRepair;
		}
		public void setCanRepair(String canRepair) {
			this.canRepair = canRepair;
		}
		@Override
		public String toString() {
			return "BizProductCategoryBrandVW [id=" + id + ", productBrandNo="
					+ productBrandNo + ", productBrandName=" + productBrandName
					+ ", canInstall=" + canInstall + ", canRepair=" + canRepair
					+ "]";
		}
		public BizProductCategoryBrandVW(Integer id, String productBrandNo,
				String productBrandName, String canInstall, String canRepair) {
			super();
			this.id = id;
			this.productBrandNo = productBrandNo;
			this.productBrandName = productBrandName;
			this.canInstall = canInstall;
			this.canRepair = canRepair;
		}
		public BizProductCategoryBrandVW() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	    
}
