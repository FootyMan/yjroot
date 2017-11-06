package com.erp.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.erp.model.PositionModel;
import com.erp.utils.PositionUtils;
import com.google.gson.Gson;
import com.service.utils.HttpClientUtil;

public class TestMyBatis {

	// static SqlSessionFactory sqlSessionFactory = null;
	// static {
	// sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	// }
	public static void main(String[] args) {

		List<String> strs=new ArrayList<String>();
		strs.add("1");strs.add("2");strs.add("3");strs.add("4");
		
		Gson gson=new Gson();
		String str=gson.toJson(strs);
	 
		System.out.println(str);
		String phone=game(10);
		System.out.println(phone);
//		CacheData cacheData = new CacheData();
//		File file=new File("D:\\huasheCacheData_4");
//		CacheData object = (CacheData)getCacheObj(file);
	//	cacheData.setCache(object);
		PositionModel resultStr = PositionUtils.GetpositionByAddress("北京");
		System.out.println(resultStr.getLat());
		// String A = new String("java");
		// StringBuffer B = new StringBuffer("java");
		// A = replace(A);
		// Integer a = 100;
		// Integer b = 100;
		// System.out.println(a == b);
		// Integer i = 1000;
		// Integer j = 1000;
		// System.out.println(i == j);
		// appstr(B);
		// System.out.println(A + B);
		//
		// ReadProperties();
		// ThreadLocal
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
	
	public static String game(int count) {
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num) + ""), "");
		}
		return sb.toString();
	}

	

	public static void ReadProperties() {

		Properties prop = new Properties();
		try {
			// 褰撳墠绾跨▼璺緞
			String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			prop.load(in); /// 鍔犺浇灞炴�у垪琛�
			System.out.println("-------------------" + prop.getProperty("img.ftp.host"));
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
