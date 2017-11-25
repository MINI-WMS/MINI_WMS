package com.ltsznh.modules.sys.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单管理
 * 
 * @author liutao
 * @email ltsznh@gmail.com
 * @date 2016年9月18日 上午9:26:39
 */
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单ID
	 */
	private Long menuId;

	/**
	 * 父菜单ID，一级菜单为0
	 */
	private Long parentMenuId;
	
	/**
	 * 父菜单名称
	 */
	private String parentMenuName;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单URL
	 */
	private String url;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perms;

	/**
	 * 类型     0：目录   1：菜单   2：按钮
	 */
	private Integer menuType;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;

	private int isEnabled;

	private Date createDate;
	private Long creatorId;
	private String creatorName;

	private Date modifyDate;
	private Long modifierId;
	private String modifierName;
	/**
	 * ztree属性
	 */
	private Boolean open;
	
	private List<?> list;

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getMenuId() {
		return menuId;
	}
	
	/**
	 * 设置：父菜单ID，一级菜单为0
	 * @param parentMenuId 父菜单ID，一级菜单为0
	 */
	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	/**
	 * 获取：父菜单ID，一级菜单为0
	 * @return Long
	 */
	public Long getParentMenuId() {
		return parentMenuId;
	}
	
	/**
	 * 设置：菜单名称
	 * @param menuName 菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 获取：菜单名称
	 * @return String
	 */
	public String getMenuName() {
		return menuName;
	}
	
	/**
	 * 设置：菜单URL
	 * @param url 菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：菜单URL
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	/**
	 * 设置：菜单图标
	 * @param icon 菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取：菜单图标
	 * @return String
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * 设置：排序
	 * @param orderNum 排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取：排序
	 * @return Integer
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public int getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifierId() {
		return modifierId;
	}

	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}

	public String getModifierName() {
		return modifierName;
	}

	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}
}
