package top.study.spring.dao;

import top.study.spring.model.User;

public class UserDaoImpl implements UserDao {
	
	public UserDaoImpl() {
		System.out.println("UserDaoImpl�Ĺ��췽����������");
	}
	
	@Override
	public void createUser(User user) {
		System.out.println("������UserDaoImpl��createUser����");
	}

}
