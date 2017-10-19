package com.service.utils;

import java.io.OutputStream;
import java.io.PrintWriter;

import java.security.MessageDigest;
import java.text.DecimalFormat;

import java.util.Iterator;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
public class CommonMethod {

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 把对象装换成JSON
	 * 
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String ConvertObjToJson(Object obj) throws JsonProcessingException {
		// 把NULL去掉
		mapper.setSerializationInclusion(Include.NON_NULL);
		String json = mapper.writeValueAsString(obj);
		return json;
	}

	public static void response(String data, HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			response.setHeader("content-type", "text/html;charset=UTF-8");
			pw = response.getWriter();
			pw.write(data);
			pw.flush();
		} catch (Exception e) {
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * 把JSON 转换成对象
	 * 
	 * @param objJSON
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static <T> T ConvertJsonToObj(String objJSON, Class<T> obj) throws IOException {
		T t = mapper.readValue(objJSON, obj);
		/**
		 * 验签 暂时不用 因有些操作不是接口、
		 */
		/*
		 * boolean isSign = Boolean.parseBoolean(GetConfigValue("isSign")); if
		 * (isSign) {
		 * 
		 * @SuppressWarnings("unchecked") BaseRequestModel<Object> baseObj =
		 * (BaseRequestModel<Object>) t; if (baseObj != null) { String sign =
		 * "20160323" + baseObj.getTimeStamp() + baseObj.getRandomCode(); //
		 * byte[] data = sign.getBytes(); String md5sign =
		 * bytesToLowerCaseString(md5(sign)); if
		 * (!baseObj.getSign().equals(md5sign)) { return null; } } }
		 */

		return t;
	}

	/**
	 * 输出JSON内容到客户端
	 * 
	 * @param response
	 * @param jsonContent
	 * @throws Exception
	 */
	public static void ResponseJsonToClient(HttpServletResponse response, String jsonContent) throws Exception {
		OutputStream outputStream = response.getOutputStream();// 获取OutputStream输出流
		response.setHeader("content-type", "text/html;charset=UTF-8");// 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
		/**
		 * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
		 * 如果是中文的操作系统环境，默认就是查找查GB2312的码表， 将字符转换成字节数组的过程就是将中文字符转换成GB2312的码表上对应的数字
		 * 比如： "中"在GB2312的码表上对应的数字是98 "国"在GB2312的码表上对应的数字是99
		 */
		/**
		 * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，如果是中文操作系统，那么就使用GB2312的码表
		 */
		byte[] dataByteArr = jsonContent.getBytes("UTF-8");// 将字符转换成字节数组，指定以UTF-8编码进行转换
		outputStream.write(dataByteArr);// 使用OutputStream流向客户端输出字节数组
	}

	/**
	 * 读取请求数据里的正文内容
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String ReadInputStreamFromClientRequest(HttpServletRequest request) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}

	public static String WriteLog(HttpServletRequest request, String key) {
		String value = request.getServletContext().getInitParameter(key);
		return value;
	}

	/**
	 * 读取web.xml 参数 context-param 值
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String ReadWebConfigValue(HttpServletRequest request, String key) {
		String value = request.getServletContext().getInitParameter(key);
		return value;
	}

	/**
	 * 读取XML文件键值
	 * 
	 * @param key
	 * @return
	 */
	public static String GetConfigValue(String key) {

		Properties props = new Properties();
		String value = "";
		// xml的相对路径
		String xmlpath = "key-value.xml"; // 此XML放到类文件夹下，类的相对路径

		ClassLoader classLoader = CommonMethod.class.getClassLoader(); // 这里XmlReadLearn是当前类
		InputStream in = classLoader.getResourceAsStream(xmlpath);

		try {
			props.loadFromXML(in);
			value = props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * MD5鎽樿
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] md5(String strOrgin) {
		try {
			byte[] data = strOrgin.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(data);
			return md5.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转大写
	 * 
	 * @param data
	 * @param charCase
	 * @return
	 */
	public static String bytesToUpperCaseString(byte[] data) {
		StringBuilder result = new StringBuilder("");
		if (data == null || data.length <= 0) {
			return null;
		}
		for (int i = 0; i < data.length; i++) {
			int v = data[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				result.append(0);
			}
			result.append(hv);
		}
		return result.toString().toUpperCase();

	}

	/**
	 * 小写
	 * 
	 * @param data
	 * @return
	 */
	public static String bytesToLowerCaseString(byte[] data) {
		StringBuilder result = new StringBuilder("");
		if (data == null || data.length <= 0) {
			return null;
		}
		for (int i = 0; i < data.length; i++) {
			int v = data[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				result.append(0);
			}
			result.append(hv);
		}
		return result.toString().toLowerCase();

	}

//	public static void SendShortMessage(String phoneNumber, String msgContent) {
//
//		try {
//			PhoneServiceLocator service = new PhoneServiceLocator();
//			java.net.URL url = new java.net.URL("http://service.luxuriesclub.com/PhoneService.asmx?WSDL");
//			PhoneServiceSoapStub stub = new PhoneServiceSoapStub(url, service);
//			String x2 = stub.XSCreateBlueMessage(phoneNumber, msgContent);
//			System.out.println(x2);
//
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block20
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block23
//			e.printStackTrace();
//		}
//	}

	/**
	 * 使用ImageReader获取图片宽高比
	 * 
	 * @param src
	 *            源图片路径
	 */
	public static String getImageSizeByImageReader(String src) {

		String dd = "0.00";
		File file = new File(src);
		try {
			Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = (ImageReader) readers.next();
			ImageInputStream iis = ImageIO.createImageInputStream(file);
			reader.setInput(iis, true);
			System.out.println("width:" + reader.getWidth(0));
			System.out.println("height:" + reader.getHeight(0));

			float scale = reader.getWidth(0) / reader.getHeight(0);
			DecimalFormat fnum = new DecimalFormat("##0.00");
			dd = fnum.format(scale);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return dd;
	}
}
