package com.test.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class TestMyBatis {

	// static SqlSessionFactory sqlSessionFactory = null;
	// static {
	// sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	// }
	public static void main(String[] args) {
		String A = new String("java");
		StringBuffer B = new StringBuffer("java");
		A = replace(A);
		Integer a = 100;
		Integer b = 100;
		System.out.println(a == b);
		Integer i = 1000;
		Integer j = 1000;
		System.out.println(i == j);
		appstr(B);
		System.out.println(A + B);

		ReadProperties();
		//ThreadLocal
		// EmployeeServiceImpl empservece=new EmployeeServiceImpl();
		// Employee emp=new Employee();
		// emp.setRoleId(0);
		// emp.setQuitDate(new Date());
		// emp.setEntryDate(new Date());
		// emp.setEmpWx("empWx");
		// emp.setEmpStatus("empStatus");
		// emp.setEmpQq("empQq");
		// emp.setEmpPwd("empPwd");
		// emp.setEmpPhone("empPhone");
		// emp.setEmpNo("empNo");
		// emp.setEmpName("empName");
		// emp.setEmpLogin("empLogin");
		// emp.setEmpGender("empGender");
		// emp.setEmpEmail("empEmail");
		// emp.setDeptCode("deptCode");
		// emp.setCityCode("cityCode");
		// emp.setAddDate(new Date());
		// empservece.insertEmployee(emp);
		// getUser();
	}

	public static String replace(String str) {
		str.replace('j', '1');
		return str;
	}

	public static void appstr(StringBuffer str) {
		str.append("c");
	}

	public static void getUser() {
		// SqlSession sqlSession = sqlSessionFactory.openSession();
		// try {
		// UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		// user user = userMapper.getUser("寮犱笁");
		// System.out.println("name: " + user.getUsername() + "|age: "
		// + user.getSex());
		// } finally {
		// sqlSession.close();
		// }
	}

	public static void ReadProperties() {

		Properties prop = new Properties();
		try {
			//褰撳墠绾跨▼璺緞
			String path = Thread.currentThread().getContextClassLoader().getResource  ("config.properties").getPath();
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			prop.load(in); /// 鍔犺浇灞炴�у垪琛�
			System.out.println("-------------------"+prop.getProperty("img.ftp.host"));
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				System.out.println(key + ":" + prop.getProperty(key));
			}
			in.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
