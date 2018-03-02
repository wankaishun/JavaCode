package com.liqun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.liqun.entity.SysRole;
import com.liqun.entity.SysUser;
@Repository
public interface UserDao {

	/*int insert(User user);

	int update(User user);

	int delete(Long id);

	List<User> selectAll();

	long countAll();

	User findByName(String name);

	List<User> findAll(@Param("p") PageRequest pageRequest);

	default void save(User user) {
		if (null != user.getId()) update(user); else insert(user);
	}

	User findOneById(long friend_id);*/
	/**
	 * 根据uid查询用户信息
	 * @param uid
	 * @return
	 */
	SysUser findUserById(Integer uid);
	
	/**
	 * 根据username查询用户信息
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);
	
	
	
}
