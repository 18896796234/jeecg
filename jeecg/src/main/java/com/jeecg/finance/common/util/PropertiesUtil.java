package com.jeecg.finance.common.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	private static final String SKEY = "kmeritkr";
	private static final Charset CHARSET = Charset.forName("gb2312");
	private static Map<String, String> ctxPropertiesMap; // 用于存放perperties中的数据
	private List<String> decryptProperties; // 用于存放需要解密的key

	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		ctxPropertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			if (decryptProperties != null && decryptProperties.contains(keyStr)) {
				try {
					value = DesUtil.decrypt(value, CHARSET, SKEY);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 解密
				props.setProperty(keyStr, value); // 设置解密后的明文数据
			}
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	/**
	 * @param decryptPropertiesMap
	 *            the decryptPropertiesMap to set
	 */
	public void setDecryptProperties(List<String> decryptProperties) {
		this.decryptProperties = decryptProperties;
	}

	/**
	 * Get a value based on key , if key does not exist , null is returned
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		try {
			return ctxPropertiesMap.get(key);
		} catch (MissingResourceException e) {
			return null;
		}
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return Integer.parseInt(ctxPropertiesMap.get(key));
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		String value = ctxPropertiesMap.get(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * 根据key获取值
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = ctxPropertiesMap.get(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return new Boolean(value);
	}

	public static String decrypt(String value) {
		try {
			return DesUtil.decrypt(value, CHARSET, SKEY);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String encrypt = DesUtil.encrypt("KRMDEV", CHARSET, SKEY);
		System.out.println(encrypt);
		try {
			System.out.println(DesUtil.decrypt(encrypt, CHARSET, SKEY));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
