package com.ltsznh.common.utils;

import org.apache.poi.ss.formula.functions.T;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bean2Map {
	public <T, K, V> Map<String, Object> bean2Map(T bean, Map<String, Object> map) throws Exception, IllegalAccessException {
		if (bean == null) {
			return null;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!key.equals("class")) {
					Method getter = property.getReadMethod();// Java中提供了用来访问某个属性的
					// getter/setter方法
					Object value;

					value = getter.invoke(bean);
					map.put(key, value);
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	public <T, K, V> Map<String, Object> bean2Map(T bean) throws Exception, IllegalAccessException {
		Map<String, Object> map = new HashMap<String, Object>();
		return bean2Map(bean, map);
	}

	public List<Map<String, Object>> beans2MapList(List<?> beansList) throws Exception {
		if (beansList == null)
			return  null;
		try {
			List<Map<String, Object>> mapList = new ArrayList<>();
			for (int i = 0; i < beansList.size(); i++) {
				mapList.add(bean2Map(beansList.get(i)));
			}
			return mapList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}