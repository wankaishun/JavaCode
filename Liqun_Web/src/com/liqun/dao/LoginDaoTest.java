package com.liqun.dao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liqun.dao.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class LoginDaoTest {
	@Autowired
	LoginDao loginDao;
	
	@Test
	public void  findUserByUserName() {
		loginDao.findUserByUserName("admin");
	}
	@Test
	public void findUserById() {
		loginDao.findUserById(1);
	}
	@Test
	public void findRoleByRoleId() {
		loginDao.findRoleByRoleId(1);
	}
	@Test
	public void findPermissionByRoleId() {
		loginDao.findPermissionByRoleId(1);
	}
}
