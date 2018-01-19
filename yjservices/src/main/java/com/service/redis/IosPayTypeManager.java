package com.service.redis;

import com.service.utils.StringUtils;

import redis.clients.jedis.Jedis;

public class IosPayTypeManager {

	/**
	 * 设置是否走内购 非0为真
	 * 
	 * @param isIap
	 * @return
	 */
	public String SetPayType(int isIap) {
		Jedis jedis = RedisUtil.getJedis();
		if (jedis != null) {
			try {
				jedis.set("isIap", String.valueOf(isIap));
			} catch (Exception e) {
				return "错误" + e.getMessage();
				// TODO: handle exception
			} finally {
				RedisUtil.returnResource(jedis);

			}
			return "成功";
		}
		return "Jedis空";
	}

	/**
	 * 设置是否走内购 非0为真
	 * 
	 * @param isIap
	 * @return
	 */
	public int GetPayType() {
		int isIap = 1;
		Jedis jedis = RedisUtil.getJedis();
		if (jedis != null) {
			try {
				String strIap = jedis.get("isIap");
				if (!StringUtils.isEmpty(strIap)) {
					isIap = Integer.parseInt(strIap);
				}
			} catch (Exception e) {
				return isIap;
			} finally {
				RedisUtil.returnResource(jedis);
			}
		}
		return isIap;
	}
}
