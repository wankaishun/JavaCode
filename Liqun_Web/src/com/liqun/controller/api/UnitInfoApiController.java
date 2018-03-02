package com.liqun.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.entity.MManage;
import com.liqun.entity.SysOrganization;
import com.liqun.service.UnitInfoService;

@Controller
@RequestMapping("/api")
public class UnitInfoApiController {
	@Autowired
	private UnitInfoService unitInfoService;
	@RequestMapping(value = "getUinitInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object getUinitInfo() {
		return unitInfoService.getUnitInfo();
	}
	/**
	 * 保存组织机构信息
	 * @param sysOrganization
	 */
	@RequestMapping(value = "saveUnitInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUnitInfo(MManage mManage) {
		return unitInfoService.saveMManage(mManage);
	}
}
