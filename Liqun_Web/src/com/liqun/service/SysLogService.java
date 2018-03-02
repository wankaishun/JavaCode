package com.liqun.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.liqun.dao.SysLogDao;
import com.liqun.entity.SysLog;
import com.liqun.entity.SysUser;

@Service
public class SysLogService {
	
	@Autowired SysLogDao sysLogDao;

	public void insertLog(Integer permissionId, String content) {

		// 获取session中的信息
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		String ipAddress = request.getRemoteAddr();

		SysLog sysLog = new SysLog();
		sysLog.setUserId(sysUser.getId());
		sysLog.setIpAddress(ipAddress);
		sysLog.setOperationTime(new Date());
		sysLog.setCreateDate(new Date());
		sysLog.setCreateUser(sysUser.getRealName());

		sysLog.setContent(content);
		sysLog.setPermissionId(permissionId);

		sysLogDao.insert(sysLog);
	}

	public void insertLog(SysLog log) {
		sysLogDao.insert(log);
	}
}
