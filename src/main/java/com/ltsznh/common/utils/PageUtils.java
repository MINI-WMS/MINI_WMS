package com.ltsznh.common.utils;

import com.google.gson.Gson;
import com.ltsznh.modules.sys.entity.SysConfigEntity;

import java.io.Serializable;
import java.util.*;

/**
 * 分页工具类
 *
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年11月4日 下午12:59:00
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	//总记录数
	private int totalCount;
	//每页记录数
	private int pageSize;
	//总页数
	private int totalPage;
	//当前页数
	private int currPage;
	//列表数据
	private List<?> list;


	/**
	 * 分页
	 *
	 * @param list       列表数据
	 * @param totalCount 总记录数
	 * @param pageSize   每页记录数
	 * @param currPage   当前页数
	 */
	public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	//列宽列表
	private HashMap<String, Integer> widthMap = null;

	public void setColumnWidth(List<SysConfigEntity> configList) {
		if (configList == null || configList.size() <= 0) return;
		List<Map<String, Object>> mapList = null;
		try {
		  mapList = new Bean2Map().beans2MapList(configList);
		}catch (Exception e){}

		if (mapList == null) return;

		if (widthMap == null) widthMap = new HashMap<>();//初始化列宽对应关系

		int lastWidth = 0;
		int newWidth = 0;

		String columnName;
		String columnValue;
		int strLength;

		for (int i = 0; i < mapList.size(); i++) {
			HashMap<String, Object> item = (HashMap<String, Object>) mapList.get(i);
			Iterator iterator = item.entrySet().iterator();
			while (iterator.hasNext()) {
				try {
					Map.Entry entry = (java.util.Map.Entry) iterator.next();
					columnName = entry.getKey().toString();
					if (entry.getValue().getClass().toString().toLowerCase().endsWith("date")) {
						columnValue = DateUtils.formatToDateTime((Date) entry.getValue());
					} else {
						columnValue = entry.getValue().toString();
					}


					if (widthMap.containsKey(columnName)) lastWidth = widthMap.get(columnName);

					strLength = columnValue.getBytes("GBK").length > 30 ? 30 : columnValue.getBytes("GBK").length;
					newWidth = strLength * 12;
					if (newWidth > lastWidth) {
						widthMap.remove(columnName);
						widthMap.put(columnName, newWidth);
					}
				} catch (Exception e) {
				}
			}
		}
	}

	public HashMap<String, Integer> getWidthMap() {
		return widthMap;
	}

	public void setWidthMap(HashMap<String, Integer> widthMap) {
		this.widthMap = widthMap;
	}
}
