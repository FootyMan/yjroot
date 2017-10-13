package com.api.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.KeyStore.PrivateKeyEntry;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.Literal;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;

import com.alibaba.fastjson.JSON;
import com.api.alipay.sdk.AlipayPayManager;
import com.api.response.AlipayResponse;
import com.api.response.baseResponse;
import com.api.utils.DES;
import com.api.utils.PageUtils;
import com.api.wxpay.sdk.WXPay;
import com.api.wxpay.sdk.WXPayConfigImpl;
import com.api.wxpay.sdk.WXPayUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myErp.easemob.EaseMobBusiness;
import com.myErp.enums.ProductLevel;
import com.myErp.impl.InvitationCodeServiceImpl;
import com.myErp.manager.bean.InvitationCode;
import com.myErp.manager.bean.Province;
import com.myErp.manager.bean.UserFinancial;
import com.myErp.redis.CityRedisManager;
import com.myErp.utils.CommonMethod;
import com.myErp.utils.DateStyle;
import com.myErp.utils.DateUtil;
import com.myErp.utils.Md5Util;
import com.myErp.utils.StringUtils;
import com.myErp.utils.SystemConfig;
import com.mysql.fabric.xmlrpc.base.Data;

public class TestLog4j {

	@Autowired
	private static InvitationCodeServiceImpl invitationCodeServiceImpl;
	private static Logger logger = Logger.getLogger(TestLog4j.class);

	public static void main(String[] args) throws Exception {

		CityRedisManager redis = new CityRedisManager();
		List<Province> provinces = new ArrayList<Province>();

//		Province province = new Province();
//		for (int i = 0; i < 2; i++) {
//			province.setProvinceId(i);
//			province.setParentId(i);
//			province.setName("北京");
//			provinces.add(province);
//			// 添加
//			redis.SetCity(provinces);
//		}
		// 获取
		List<Province> listp = redis.GetCityAll();
		for (Province x : listp) {
			System.out.println(x.getProvinceId());
			System.out.println(x.getParentId());
			System.out.println(x.getName());
		}
		// 获取单个
		Province sign = redis.GetCitySingle(1);
		System.out.println(sign.getName());
		// 删除单个
		redis.RemoveCityById(1);
		// 再获取
		listp = redis.GetCityAll();
		System.out.println(listp.size());

		// 删除所有
		redis.RemoveCityAll();
		// 再获取
		listp = redis.GetCityAll();
		System.out.println(listp.size());

		String orderNumber = StringUtils.GetOrderNumber(1, 1);
		AlipayPayManager manager = new AlipayPayManager();
		String orderString = manager.GetOrderString(orderNumber, "月度会员", "0.01");
		System.out.println(orderString);

		// // 获取token
		// String toket = EaseMobBusiness.QueryToken();
		// // 创建用户
		// EaseMobBusiness.AccountCreate(userName);
		// // 删除用户
		// EaseMobBusiness.AccountDel(userName);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date date = sdf.parse("2017-09-26 10:42:11");
		// Calendar c1 = Calendar.getInstance();
		// Calendar c2 = Calendar.getInstance();
		// c2.setTime(date);
		// if (c1.get(Calendar.DATE) == c2.get(Calendar.DATE)) {
		// System.out.println(DateUtil.getHH_MM(c2.getTime()) + "来访");
		//
		// } else{
		// System.out.println(DateUtil.getDate(c2.getTime()) + "来访");
		//
		// } {
		// System.out.println("c1:" + c1.get(Calendar.DATE));
		// System.out.println("c2:" + c2.get(Calendar.DATE));
		// }

		// testDate dd = new testDate();
		// if (dd.getTestDate() == null) {
		// System.out.println("null");
		// } else {
		// System.out.println("not null");
		// }

		// String dString="11111111";
		// dString=DES.encrypt(dString);
		// System.out.println(dString);

		String reqStr = DES.decrypt("fiQJ0FtQYu6C1JBQtCVez/90iBJ98+8j89UKsGOk0bQ=");
		// toket = EaseMobBusiness.QueryToken();
		// baseResponse response=new baseResponse();
		// System.out.println(reqStr);
		// File f1 = new File("D:\\TestWork\\test");//需要转码的GBK格式文件夹
		// File f2 = new File("D:\\TestWork\\test1");//转码成UTF-8的目标文件夹
		// try {
		// copyFile(f1, f2);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// ProductLevel product = ProductLevel.getProductById(1);
		// double reward = product.getPrice() * SystemConfig.percentage;
		// System.out.println(reward);
		// SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		// System.out.println(dateFormat.format(DateUtil.addDay(new Date(),
		// 30)));
		// wxOrderPay();
		// for (int i = 0; i < 100000; i++) {
		// String code = StringUtils.getRandomNum(999999, 111111);
		// InvitationCode invitation =
		// invitationCodeServiceImpl.selectCode(code);
		// if (invitation!=null && invitation.getCode()!="") {
		// InvitationCode entity=new InvitationCode();
		// entity.setCode(code);
		// entity.setIsValid(1);
		// invitationCodeServiceImpl.insertCode(entity);
		// }
		// }
		// List<String> strOrder = new ArrayList<String>();
		// for (int i = 0; i < 10000; i++) {
		// String orderNumber =StringUtils.getRandomNum(999999999, 111111111);
		// if (!strOrder.contains(orderNumber)) {
		// strOrder.add(orderNumber);
		// System.out.println(orderNumber);
		// } else {
		// System.out.println("数据重复" + orderNumber);
		// }
		// }
		// System.out.println("执行完毕----------------------------------------------------");

		// for (int i = 0; i < 50; i++) {
		// System.out.println(StringUtils.GetOrderNumber(1, 1));
		// }
		// System.out.println(Md5Util.stringByMD5("123456"));
		// System.out.println(DateUtil.getYearTwo(new Date()));
	}

	public static void TestJson() {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.writeValue(out, value);
		// CommonMethod.ConvertJsonToObj(objJSON, obj)
	}

	public static class testDate {
		private Date testDate;

		public Date getTestDate() {
			return testDate;
		}

		public void setTestDate(Date testDate) {
			this.testDate = testDate;
		}
	}

}
