package com.qicms.core.common;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

/**
 * 缓存对象还原
 */
public class ObjRestore {

	/**
	 * 将序列化串还原为对象
	 */
	public static Object restore(String payload) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(payload);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}
}
