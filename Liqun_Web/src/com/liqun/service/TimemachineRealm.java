package com.liqun.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import com.liqun.entity.SysPermission;
import com.liqun.entity.SysRole;
import com.liqun.entity.SysUser;
import com.liqun.web.SuccessfulAuthenticatedToken;

public class TimemachineRealm extends AuthorizingRealm {
	
	@Autowired LoginService loginService;
	
	public TimemachineRealm() {
		
		this.setCredentialsMatcher(new BcryptPasswordMatcher());
		this.setAuthenticationTokenClass(AuthenticationToken.class);
	}
	
	private static class BcryptPasswordMatcher implements CredentialsMatcher {

		@Override
		public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
			
			if (token instanceof SuccessfulAuthenticatedToken) {
				
				return true;
				
			} else if (token instanceof UsernamePasswordToken) {
				
				UsernamePasswordToken userToken = (UsernamePasswordToken) token;
				
				//本系统采用BCrypt加密用户的密码
				String password = new String(userToken.getPassword());
				String hashed = (String) info.getCredentials();
//				System.out.println("checking bcrypt password token......password=" + password);
				
				return BCrypt.checkpw(password, hashed);
				
			}
			
			throw new AuthenticationException("未支持对该类型认证信息中的凭据进行核验 :" + token.getClass());
		}

	}
	

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		System.out.println(getName() + " -> doGetAuthenticationInfo, token=" + token);
		
		if (token instanceof SuccessfulAuthenticatedToken) {
			return ((SuccessfulAuthenticatedToken)token).getInfo();
		}
		
		//根据token查询得到用户数据，并生成authcInfo
		//Document member;
		SysUser user = new SysUser();
		AuthenticationInfo authcInfo;
		
		if (token instanceof UsernamePasswordToken) {
			
			//处理用户名密码的认证
			UsernamePasswordToken upToken = (UsernamePasswordToken) token;
			String username = upToken.getUsername();
			user = loginService.findUserByUserName(username);
			if(user == null )
				throw new UnknownAccountException("用户不存在!");
			if("0".equals(user.getFlag())) {
				throw new LockedAccountException("账号被禁用");
			}
			
			SysUser sysUser = new  SysUser();
			sysUser.setAccount(user.getAccount());
			sysUser.setId(user.getId());
			sysUser.setEmployeeNo(user.getEmployeeNo());
			sysUser.setMobile(user.getMobile());
			sysUser.setRealName(user.getRealName());
			sysUser.setRoleId(user.getRoleId());
			sysUser.setFlag(user.getFlag());
			
			//member = memberService.findMemberByUsername(username);
			
			
			Integer principal = user.getId();
			
			String credentials = user.getPassword();
			
			authcInfo = new SimpleAuthenticationInfo(sysUser, credentials, getName());
			
		}else {
			throw new UnsupportedTokenException("未受支持的认证令牌 类型：" + token.getClass());
		}
		
		
		
		
		return authcInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		System.out.println(getName() + " -> doGetAuthorizationInfo, principals="+principals);
		
		try {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			
			SysUser principal = (SysUser)principals.getPrimaryPrincipal();

			SysUser user = loginService.findUserById(principal.getId());
			
			//Document member = memberService.findMemberById(uid);
			//获取用户的RoleId
			Integer roleId = user.getRoleId();
			
			//根据roleId 查询角色信息
			SysRole role = new SysRole();
			
			role = loginService.findRoleByRoleId(roleId);

			//查询permission信息

			List<SysPermission> list = new ArrayList<>();
			
			list = loginService.findPermissionByRoleId(roleId);

			System.out.println("list="+list);
			
			Set<String> permissions = new HashSet<>();
			
			for (SysPermission syspermission : list) {
				String permissionCode = syspermission.getPermissionCode();
				permissions.add(permissionCode);
			}
			//permissions.add();
			
			
			info.setStringPermissions(permissions);
			
			return info;
		
		} catch (Exception e) {
			
			System.out.println("查询授权时出现问题，已返回空授权. ex="+e.getClass() + " - "+e.getMessage());
			
			return new SimpleAuthorizationInfo();
			
		}
		
	}
	
	@Override
	public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		
		return super.getAuthorizationInfo(principals);
		
	}
	
	@Override
	protected void clearCache(PrincipalCollection principals) {
		
		super.clearCache(principals);
		
	}

}