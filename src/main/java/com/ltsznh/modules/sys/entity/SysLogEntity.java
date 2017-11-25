package com.ltsznh.modules.sys.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统日志
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2017-03-08 10:40:56
 */
public class SysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
//	//用户名
//	private String username;
	//用户ID
	private Long userId;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long costTime;
	//IP地址
	private String logIp;
	//创建时间
	private Date createDate;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置：用户操作
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * 获取：用户操作
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * 设置：请求方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：请求方法
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置：请求参数
	 */
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * 获取：请求参数
	 */
	public String getParams() {
		return params;
	}
	/**
	 * 设置：IP地址
	 */
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	/**
	 * 获取：IP地址
	 */
	public String getLogIp() {
		return logIp;
	}
	/**
	 * 设置：创建时间
	 */

	public Long getCostTime() {
		return costTime;
	}

	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
