package com.liqun.service;

import java.util.Collection;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassportService {

	@Autowired DefaultWebSecurityManager securityManager;
	
	public void clearCache(Long principal) {

		Collection<Realm> realmCollection = securityManager.getRealms();
		for (Realm realm : realmCollection) {
			if (realm instanceof AuthorizingRealm) {
				SimplePrincipalCollection spc = new SimplePrincipalCollection();
				spc.add(principal, realm.getName());

				AuthorizingRealm authRealm = (AuthorizingRealm) realm;
				authRealm.getAuthenticationCache().remove(spc);
				authRealm.getAuthorizationCache().remove(spc);
			}
		}
	}
}
