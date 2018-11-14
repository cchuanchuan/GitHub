package top.study.spring.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import top.study.spring.dao.UserDao;
import top.study.spring.model.User;

public class UserServiceImpl implements UserService {
	public UserServiceImpl(){
		System.out.println("UserServiceImpl的构造方法被调用了");
	}

	private UserDao userdao;

	public void setUserdao(UserDao userdao) {
		System.out.println("调用UserServiceImpl的setUserdao方法");
		this.userdao = userdao;
	}

	public void createService(User user) {
		System.out.println("调用UserServiceImpl的createUser方法");
	}
	
	public static void main(String []args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
		UserService userservice = ctx.getBean(UserService.class);
		
		User user = new User();
		user.setUsername("chuan");
		userservice.createService(user);
		System.out.println("第二种getBean方法:");
		
	}
		

}
