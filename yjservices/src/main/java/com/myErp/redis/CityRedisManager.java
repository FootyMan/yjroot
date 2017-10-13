package com.myErp.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.aspectj.weaver.ast.Var;

import com.myErp.manager.bean.Province;
import com.myErp.utils.CommonMethod;
import com.myErp.utils.ResultModel;
import com.myErp.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * 城市缓存
 * 
 * @author HCY
 *
 */
public class CityRedisManager {

	/**
	 * 存储
	 * 
	 * @param provinces
	 */
	public ResultModel SetCity(List<Province> provinces) {

		ResultModel result = new ResultModel();
		Jedis jedis = RedisUtil.getJedis();
		try {
			// 这是key 格式:(city|id,value)
			// List<String> keys = new ArrayList<String>();
			for (Province x : provinces) {
				// 存储
				 byte[] key = SerializeUtil.serialize("city|" +x.getProvinceId());
				 byte[] value = SerializeUtil.serialize(x);
				jedis.set(key, value);
			}

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
			// TODO: handle exception
		} finally {
			RedisUtil.returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取所有
	 * 
	 * @return
	 */
	public List<Province> GetCityAll() {

		List<Province> provinces = new ArrayList<Province>();
		Jedis jedis = RedisUtil.getJedis();
		try {
			// 这是key 格式:(city|id,value)

			Set<byte[]> b = jedis.keys(SerializeUtil.serialize("city|*"));
			for (byte[] bs : b) {
				String key = String.valueOf(SerializeUtil.unserialize(bs));
				System.out.println(key);
				Province model = new Province();
				byte[] objbyte = jedis.get(SerializeUtil.serialize(key));
				model = (Province) SerializeUtil.unserialize(objbyte);
				System.out.println(model.getName());
				provinces.add(model);
			}
		} finally {
			RedisUtil.returnResource(jedis);
		}
		return provinces;
	}
	/**
	 * 此种类型可以使用通配符*匹配多个字符 上面byte不行 一个*号一个字符
	 */
//	public List<Province> GetCityAll1() throws Exception {
//
//		List<Province> provinces = new ArrayList<Province>();
//		Jedis jedis = RedisUtil.getJedis();
//		try {
//			// 这是key 格式:(city|id,value)
//
//			Set<String> s = jedis.keys("city|*");
//			Iterator<String> it = s.iterator();
//			while (it.hasNext()) {
//				String key = (String) it.next();
//				;
//				System.out.println(key);
//				Province model = CommonMethod.ConvertJsonToObj(jedis.get(key), Province.class);
//				System.out.println(model.getName());
//				provinces.add(model);
//			}
//		} finally {
//			RedisUtil.returnResource(jedis);
//		}
//		return provinces;
//	}

	/**
	 * 获取单个
	 * 
	 * @return
	 */
	public Province GetCitySingle(int cityId) {

		Province provinces = null;
		Jedis jedis = RedisUtil.getJedis();
		try {
			byte[] objbyte = jedis.get(SerializeUtil.serialize("city|" + cityId));
			if (objbyte != null) {
				provinces = (Province) SerializeUtil.unserialize(objbyte);
			}
		} finally {
			RedisUtil.returnResource(jedis);
		}
		return provinces;
	}

	/**
	 * 删除所有
	 */
	public void RemoveCityAll() {
		Jedis jedis = RedisUtil.getJedis();
		try {
			// 这是key 格式:(city|id,value)
			Set<byte[]> b = jedis.keys(SerializeUtil.serialize("city|*"));
			for (byte[] bs : b) {
				jedis.del(bs);

			}
		} finally {
			RedisUtil.returnResource(jedis);
		}
	}

	/**
	 * 删除单个
	 * 
	 * @param cityId
	 */
	public void RemoveCityById(int cityId) {
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.del(SerializeUtil.serialize("city|" + cityId));

		} finally {
			RedisUtil.returnResource(jedis);
		}
	}
}
