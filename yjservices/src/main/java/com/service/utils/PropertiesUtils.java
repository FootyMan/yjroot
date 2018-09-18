package com.service.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesUtils {

	public static String ReadProperties(String fileName, String key) {

		Properties prop = new Properties();
		try {
			// 当前线程路径
			String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
			File file = new File(path);
			if (file.exists()) {
				InputStream in = new BufferedInputStream(new FileInputStream(path));
				prop.load(in); /// 加载属性列表
				return prop.getProperty(key);
			}
			else
			{
				//spring boot专用 其实都可以这样 、待测试
				PropertiesConfiguration conf = new PropertiesConfiguration(fileName);
				System.out.println("哈哈哈哈哈哈哈"+conf.getProperty(key));
				return conf.getProperty(key).toString();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return key;
	}
}
