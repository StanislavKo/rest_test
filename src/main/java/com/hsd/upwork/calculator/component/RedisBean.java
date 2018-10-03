package com.hsd.upwork.calculator.component;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
// singleton - default
public class RedisBean {

	private static final Logger logger = LoggerFactory.getLogger(RedisBean.class);

	@Value("${redis.host}")
	private String host;

	@Value("${redis.password}")
	private String password;

	private Jedis jedis;
	private boolean toReconnect = true;

	@PostConstruct
	public void init() {
		logger.info("host={}", host);
		reconnect();
	}

	@PreDestroy
	public void cleanUp() throws Exception {
		try {
			jedis.shutdown();
		} catch (Exception e) {
			logger.error("Redis error", e);
		}
	}

	/**
	 * Retrieve value from Redis or null.
	 * On exception try to reconnect once.
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		try {
			return jedis.get(key);
		} catch (Exception e) {
			reconnect();
			return jedis.get(key);
		}
	}

	/**
	 * Set value in Redis and return value
	 * On exception try to reconnect once.
	 * @param key
	 * @param value
	 * @return
	 */
	public String setValue(String key, String value) {
		try {
			jedis.set(key, value);
			jedis.expire(key, 7*24*60*60);
		} catch (Exception e) {
			reconnect();
			jedis.set(key, value);
			jedis.expire(key, 7*24*60*60);
		}
		return value;
	}

	private void reconnect() {
		toReconnect = true;
		reconnectImpl();
	}

	private synchronized void reconnectImpl() {
		if (toReconnect) {
			jedis = new Jedis(host, 6379);
			jedis.connect();
			jedis.auth(password);
			toReconnect = false;
		}
	}

}
