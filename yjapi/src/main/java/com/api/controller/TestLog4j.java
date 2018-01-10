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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.Literal;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.response.AlipayResponse;
import com.api.response.BaseResponse;
import com.api.response.IapReceipt;
import com.api.response.IapReceiptResult;
import com.api.utils.CommonConfig;
import com.api.utils.DES;
import com.api.utils.EncryUtil;
import com.api.utils.Hex;
import com.api.utils.PageUtils;
import com.api.wxpay.sdk.WXPay;
import com.api.wxpay.sdk.WXPayConfigImpl;
import com.api.wxpay.sdk.WXPayUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mysql.fabric.xmlrpc.base.Data;
import com.service.api.impl.InvitationCodeServiceImpl;
import com.service.bean.InvitationCode;
import com.service.bean.Province;
import com.service.bean.UserFinancial;
import com.service.easemob.CheckSumBuilder;
import com.service.easemob.EaseMobBusiness;
import com.service.easemob.NeteaseBusiness;
import com.service.easemob.NeteaseModel;
import com.service.enums.LableType;
import com.service.enums.OrderState;
import com.service.redis.CityRedisManager;
import com.service.utils.CommonMethod;
import com.service.utils.DateStyle;
import com.service.utils.DateUtil;
import com.service.utils.Md5Util;
import com.service.utils.StringUtils;
import com.service.utils.SystemConfig;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestLog4j {

	@Autowired
	private static InvitationCodeServiceImpl invitationCodeServiceImpl;
	private static Logger logger = Logger.getLogger(TestLog4j.class);

	public static void main(String[] args) throws Exception {

		System.out.println(Md5Util.stringByMD5("1"));
		NeteaseModel isFlag=NeteaseBusiness.UnblockaccId("helloworld1");
		System.out.println(isFlag);
		// CreateaccId();
		/**
		 * 加密 解密
		 * 
		 * BASE64Encoder encoder = new BASE64Encoder(); String str = "123";
		 * String encStr = encoder.encode(str.getBytes());
		 * System.out.println("64加密：" + encStr);
		 * 
		 * // 解密 BASE64Decoder decoder = new BASE64Decoder(); byte[] by =
		 * decoder.decodeBuffer(encStr); System.out.println("64解密" + new
		 * String(by, "utf-8"));
		 * 
		 * //Hex hex = new Hex(); String message = "123"; String t="";
		 * System.out.println("加密后：" + (t = EncryUtil.encrypt(message)));
		 * System.out.println("解密后：" + EncryUtil.decrypt(t));
		 */
		// String jsonStr = "{\"receipt\": {\"original_purchase_date_pst\":
		// \"2016-04-28 03:18:49 America/Los_Angeles\",\"purchase_date_ms\":
		// \"1461838729285\",\"unique_identifier\":
		// \"d4e721ec67ef2feca7fbdbd25a45cfb37e10ea7b\",\"original_transaction_id\":
		// \"1000000208620470\",\"bvrs\": \"1.1\",\"transaction_id\":
		// \"1000000208620470\",\"quantity\":
		// \"1\",\"unique_vendor_identifier\":
		// \"8E19EEC4-33D7-4536-B62E-112BAC68EECD\",\"item_id\":
		// \"1108798151\",\"product_id\": \"1244\",\"purchase_date\":
		// \"2016-04-28 10:18:49 Etc/GMT\",\"original_purchase_date\":
		// \"2016-04-28 10:18:49 Etc/GMT\",\"purchase_date_pst\": \"2016-04-28
		// 03:18:49 America/Los_Angeles\",\"bid\":
		// \"com.doctorHys\",\"original_purchase_date_ms\":
		// \"1461838729285\"},\"status\": 0}";
		// Gson gson = new Gson();
		// IapReceiptResult result = gson.fromJson(jsonStr,
		// IapReceiptResult.class);
		//
		// if (result.getStatus() == 0) {
		// System.out.println(result.getStatus());
		// IapReceipt receipt = result.getReceipt();
		// System.out.println(receipt.getTransaction_id());
		// }
		// String encod= new BASE64Encoder().encode(buf);;
		// System.out.println("64解密："+encod);
		// double reward = 1 * SystemConfig.percentage;
		// System.out.println(reward);
		//
		// List<String> strings=new ArrayList<String>();
		// strings.add("1");
		// strings.add("2");
		// List<String> str=new ArrayList<String>();
		// str.add("1");
		// str.add("2");
		// str.add("3");
		// str.add("4");
		// for (String x : str) {
		// if (strings.contains(x)) {
		// str.remove(x);
		// }
		// }
		// for (String c : str) {
		// System.out.println(c);
		// }

		// Province province = new Province();
		// for (int i = 0; i < 2; i++) {
		// province.setProvinceId(i);
		// province.setParentId(i);
		// province.setName("北京");
		// provinces.add(province);
		// // 添加
		// redis.SetCity(provinces);
		// }

		// String orderNumber = StringUtils.GetOrderNumber(1, 1);
		// AlipayPayManager manager = new AlipayPayManager();
		// String orderString = manager.GetOrderString(orderNumber, "月度会员",
		// "0.01");
		// System.out.println(orderString);

		// 获取token
		// String toket = EaseMobBusiness.QueryToken();
		// 创建用户
		// String st = EaseMobBusiness.AccountCreate("1qa");
		// // 删除用户
		// st = EaseMobBusiness.AccountDel("1qa");
		//
		// Map map = (Map) JSON.parse(st);
		// if (map != null && !map.containsKey("error")) {
		//
		// }
		//
		// System.out.println(map.get("uri"));
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

		// String reqStr =
		// DES.decrypt("fiQJ0FtQYu6C1JBQtCVez/90iBJ98+8j89UKsGOk0bQ=");
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

	private static void redis() {
		CityRedisManager redis = new CityRedisManager();
		List<Province> provinces = new ArrayList<Province>();
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

	public static void CreateaccId() throws Exception, Exception {

		String url = "https://api.netease.im/nimserver/user/create.action";
		HttpPost httpPost = new HttpPost(url);
		String appKey = "dfe5724d830c73e04fea28d7ffbde313";
		String appSecret = "8822033395ae";
		String nonce = "12345";
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);// 参考
																					// 计算CheckSum的java代码

		// 设置请求的header
		httpPost.addHeader("AppKey", appKey);
		httpPost.addHeader("Nonce", nonce);
		httpPost.addHeader("CurTime", curTime);
		httpPost.addHeader("CheckSum", checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

		// 设置请求的参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("accid", "helloworld"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 执行请求
		// HttpResponse response = httpClient.execute(httpPost);
		CloseableHttpResponse response = httpclient.execute(httpPost);

		// 打印执行结果
		System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
	}

}
