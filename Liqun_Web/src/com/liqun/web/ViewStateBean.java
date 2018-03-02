package com.liqun.web;

public class ViewStateBean {
	
	private String stateName;
	
	/**
	 * 本状态是否等于或属于某状态。例如，myspace.home属于myspace<br>
	 * 测试见：{@link com.liqun.web.ViewStateBeanTest#testIncludes()
	 * 
	 * @param parent
	 * @return
	 */
	public boolean includes(String parent) {
		return stateName.length() == parent.length() ?
				stateName.equals(parent) :
				stateName.startsWith(parent) && stateName.charAt(parent.length())=='.';
	}
	
	public ViewStateBean(String stateName) {
		this.stateName = stateName;
	}
	
}
