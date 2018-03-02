package com.liqun.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.aop.DbLoggable;
import com.liqun.entity.MenuItem;
import com.liqun.entity.SysOrganization;
import com.liqun.service.OrgManagementServiceImpl;

@Controller
@RequestMapping("/api")
public class OrgManagementApiController {
	@Autowired
	private OrgManagementServiceImpl orgManagementServiceImpl;
	@RequestMapping(value = "getOrgTree", method = RequestMethod.POST)
	@ResponseBody
	public Object getOrgTree() {
		List<MenuItem> list =orgManagementServiceImpl.readAllPrivileges(2);
		return list;
	}
	@RequestMapping(value = "getOrgInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getOrgInfo(int id) {
		SysOrganization sysOrganization=orgManagementServiceImpl.getOrgInfo(id);
		return sysOrganization;
	}
	/**
	 * 保存组织机构信息
	 * @param sysOrganization
	 */
	@RequestMapping(value = "saveOrgInfo", method = RequestMethod.POST)
	@ResponseBody
	//@DbLoggable(describe="操作组织架构表：新增组织）")
	public Object saveOrgInfo(SysOrganization sysOrganization) {
		System.out.println(sysOrganization);
		return orgManagementServiceImpl.saveOrgInfo(sysOrganization);
	}
	/**
	 * 删除选中的组织 以及 所属所有组织
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteOrgInfo", method = RequestMethod.POST)
	@ResponseBody
	//@DbLoggable(describe="操作组织架构表：删除组织）")
	public Object deleteOrgInfo(@RequestParam(value="id[]") Integer[] id) {
		orgManagementServiceImpl.deleteOrgInfo(id);
		return orgManagementServiceImpl.deleteOrgInfo(id);
	}
}
