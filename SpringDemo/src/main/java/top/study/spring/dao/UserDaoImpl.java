package top.study.spring.dao;

import top.study.spring.model.User;

public class UserDaoImpl implements UserDao {
	
	public UserDaoImpl() {
		System.out.println("UserDaoImpl的构造方法被调用了");
	}
	
	@Override
	public void createUser(User user) {
		System.out.println("调用了UserDaoImpl的createUser方法");
	}

}
