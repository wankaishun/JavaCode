package com.liqun.entity;

import java.io.Serializable;
import java.util.Date;

import com.liqun.util.ExcelVOAttribute;

public class CustomerDto implements Serializable  {
	 @ExcelVOAttribute(name = "客户编号", column = "A")    
	private Integer khbh;
	 @ExcelVOAttribute(name = "客户名称", column = "B")
		private String khmc;
	 @ExcelVOAttribute(name = "客户税号", column = "C")
		private String khsh;
	 @ExcelVOAttribute(name = "地址电话", column = "D")
		private String khdzdh;
	 @ExcelVOAttribute(name = "银行账号", column = "E")
		private String yhzh;
	 @ExcelVOAttribute(name = "客户状态", column = "F")
		private String khzt;
	 @ExcelVOAttribute(name = "最后更新时间", column = "G")
		private Date zhgxsj;

		public Integer getKhbh() {
			return khbh;
		}

		public void setKhbh(Integer khbh) {
			this.khbh = khbh;
		}

		public String getKhmc() {
			return khmc;
		}

		public void setKhmc(String khmc) {
			this.khmc = khmc== null ? null : khmc.trim();
		}

		public String getKhsh() {
			return khsh;
		}

		public void setKhsh(String khsh) {
			this.khsh = khsh== null ? null : khsh.trim();
		}

		public String getKhdzdh() {
			return khdzdh;
		}

		public void setKhdzdh(String khdzdh) {
			this.khdzdh = khdzdh== null ? null : khdzdh.trim();
		}

		public String getYhzh() {
			return yhzh;
		}

		public void setYhzh(String yhzh) {
			this.yhzh = yhzh== null ? null : yhzh.trim();
		}

		public String getKhzt() {
			return khzt;
		}

		public void setKhzt(String khzt) {
			this.khzt = khzt== null ? null : khzt.trim();
		}

		public Date getZhgxsj() {
			return zhgxsj;
		}

		public void setZhgxsj(Date zhgxsj) {
			this.zhgxsj = zhgxsj;
		}

	}

	

