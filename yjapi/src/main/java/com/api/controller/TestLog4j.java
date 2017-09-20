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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Predicate;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.jstl.Literal;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SystemPropertyUtils;

import com.api.wxpay.sdk.WXPay;
import com.api.wxpay.sdk.WXPayConfigImpl;
import com.api.wxpay.sdk.WXPayUtil;
import com.myErp.enums.ProductLevel;
import com.myErp.impl.InvitationCodeServiceImpl;
import com.myErp.manager.bean.InvitationCode;
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

		File f1 = new File("D:\\TestWork\\test");//需要转码的GBK格式文件夹
		File f2 = new File("D:\\TestWork\\test1");//转码成UTF-8的目标文件夹
		try {
			copyFile(f1, f2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		ProductLevel product = ProductLevel.getProductById(1);
//		double reward = product.getPrice() * SystemConfig.percentage;
//		System.out.println(reward);
//		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(dateFormat.format(DateUtil.addDay(new Date(), 30)));
		//wxOrderPay();
//		for (int i = 0; i < 100000; i++) {
//			String code = StringUtils.getRandomNum(999999, 111111);
//			InvitationCode invitation = invitationCodeServiceImpl.selectCode(code);
//			if (invitation!=null && invitation.getCode()!="") {
//				InvitationCode entity=new InvitationCode();
//				entity.setCode(code);
//				entity.setIsValid(1);
//				invitationCodeServiceImpl.insertCode(entity);
//			}
//		}
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
		System.out.println(Md5Util.stringByMD5("123456"));
		// System.out.println(DateUtil.getYearTwo(new Date()));
	}
	public static void wxOrderPay() throws Exception
	{
		WXPayConfigImpl config = WXPayConfigImpl.getInstance();
		WXPay wxpay = new WXPay(config);
		String orderNumber = "20170916002";// 订单号
		int price = 60;// 金额
		String notify_url = "wxNotify.html";// 支付回调地址
		String nonce_str=WXPayUtil.generateNonceStr();
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("appid", WXPayConfigImpl.getInstance().getAppID());
		parameters.put("mch_id", WXPayConfigImpl.getInstance().getMchID());
		parameters.put("nonce_str", nonce_str);
		parameters.put("body", "欲见包年费");
		parameters.put("out_trade_no", orderNumber); // 订单id
		parameters.put("fee_type", "CNY");
		parameters.put("total_fee", String.valueOf(price));
		parameters.put("spbill_create_ip", "8.8.8.8");
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", "APP");
		Map<String, String> r = wxpay.unifiedOrder(parameters);
		// {nonce_str=i1hdur2MgVzhCkiS,
		// appid=wx81039e1a7a12c6a3,
		// sign=0E4B582ABB994AB0E4638A7FADE79F615C42950283588BB4FB67D111CE07B8FD,
		// trade_type=APP, return_msg=OK, result_code=SUCCESS,
		// mch_id=1269287301, return_code=SUCCESS,
		// prepay_id=wx2017090617090760f390c9d30815941123}
		if (r.get("result_code").equals("SUCCESS") && r.get("return_code").equals("SUCCESS")) {
			
			long timeStamp=WXPayUtil.getCurrentTimestamp();
			HashMap<String, String> resultMap = new HashMap<String, String>();
			resultMap.put("appid", WXPayConfigImpl.getInstance().getAppID());
			resultMap.put("prepayid", r.get("prepay_id").toString());
			resultMap.put("package", "Sign=WXPay");
			resultMap.put("partnerid", WXPayConfigImpl.getInstance().getMchID());
			resultMap.put("noncestr", nonce_str);
			resultMap.put("timestamp", String.valueOf(timeStamp));
			String sign=WXPayUtil.generateSignature(resultMap,WXPayConfigImpl.getInstance().getKey());
			resultMap.put("sign", sign);
			//resultMap.put("trade_type", r.get("trade_type"));
			System.out.println(resultMap);
		}
	}
	
	private static void copyFile(File f1, File f2) throws FileNotFoundException, IOException {
		if(f1.isDirectory()) {
			f2.mkdir();
			File[] fs = f1 .listFiles();
			for (File subF : fs) {
				//递归遍历目录
				copyFile(subF, new File(f2, subF.getName()));
			}
		} else if (f1.isFile() && f1.getName().endsWith(".java")) {
			//对java文件进行转码
			parse2UTF_8(f1, f2);
		} else {
			//对非java文件直接复制
			copyData(f1, f2);
		}
	}
	//复制文件
		private static void copyData(File f1, File f2) throws FileNotFoundException, IOException{
			
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2, false);
			
			byte[] bytes = new byte[1024];
			int temp = 0;
			while (-1 != (temp = fis.read(bytes))) {
				fos.write(bytes, 0, temp);
			}
			
			fos.flush();
			
			if(null != fis) {
				fis.close();
			}
			if(null != fos) {
				fos.close();
			}
		}
		
		/*
		 * 该方法引用 杰克思勒(Jacksile)的文章：Eclipse修改编码后乱码解决
		 * 	之后作了小修改
		 * 原码地址 https://www.cnblogs.com/tufujie/p/5137564.html
		 */
		private static void parse2UTF_8(File file, File destFile) throws IOException {
	        StringBuffer msg = new StringBuffer();
	        // 读写对象
	        PrintWriter ps = new PrintWriter(new OutputStreamWriter(new FileOutputStream(destFile.getAbsolutePath(), false), "utf8"));
	        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "gbk"));

	        // 读写动作
	        String line = br.readLine();
	        while (line != null) {
	            msg.append(line).append("\r\n");
	            line = br.readLine();
	        }
	        ps.write(msg.toString());
	        br.close();
	        ps.flush();
	        ps.close();
	    }

}
