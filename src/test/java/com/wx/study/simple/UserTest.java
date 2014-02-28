package com.wx.study.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
 * @Description 
 * @date 2014年2月28日
 * @author WangXin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"}) 
public class UserTest {
	@Autowired
	SimpleUserRepository repository;
	User user = null;
	@Before
	public void init() {
		user = new User();
		user.setUsername("shawn");
		user.setFirstname("firstname");
		user.setLastname("lastname");
	}
	@Test
	public void test() {
		repository.save(user);
		assertEquals(user, repository.findOne(user.getId()));
	}
	@Test
	public void findSavedUserByLastname() throws Exception {
		repository.save(user);
		List<User> users = repository.findByLastname("lastname");
		assertNotNull(users);
		assertTrue(users.contains(user));
	}
	@Test
	public void findByFirstnameOrLastname() throws Exception {
		repository.save(user);
		List<User> users = repository.findByFirstnameOrLastname("lastname");
		assertTrue(users.contains(user));
	}
}

