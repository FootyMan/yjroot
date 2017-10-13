package com.myErp.redis;

import redis.clients.jedis.Jedis;

public class RedisManager {

	/**
	 * 取键值
	 * 
	 * @param key
	 * @return
	 */
	public String getCache(String key) {

		Jedis jedis = RedisUtil.getJedis();
		try {
			String value = jedis.get(key);
			return value;
		} finally {
			RedisUtil.returnResource(jedis);
		}

	}

	/**
	 * 设置键值
	 * 
	 * @param key
	 * @param value
	 */
	public void setCache(String key, String value) {
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.set(key, value);
		} finally {
			RedisUtil.returnResource(jedis);
		}

	}

	/**
	 * 删除KEY
	 * 
	 * @param key
	 * @param value
	 */
	public void delKey(String key) {
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.del(key);
		} finally {
			RedisUtil.returnResource(jedis);
		}

	}
}
