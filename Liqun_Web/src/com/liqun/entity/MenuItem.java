package com.liqun.entity;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Serializable {
	private Integer id;
	private String text;
	private String iconCls;
	private Integer parentId;
	private Integer firMenuId;
	private int menu;
	private boolean checked;
	private String state;
	private Integer sort;
	private List<MenuItem> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public int getMenu() {
		return menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<MenuItem> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItem> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public Integer getFirMenuId() {
		return firMenuId;
	}

	public void setFirMenuId(Integer firMenuId) {
		this.firMenuId = firMenuId;
	}
	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItem(Integer id, String text, String iconCls, Integer parentId, Integer menu, boolean checked,
			String state, List<MenuItem> children) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parentId = parentId;
		this.menu = menu;
		this.checked = checked;
		this.state = state;
		this.children = children;
	}

	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", text=" + text + ", iconCls=" + iconCls + ", parentId=" + parentId + ", menu="
				+ menu + ", checked=" + checked + ", state=" + state + ", children=" + children + "]";
	}

}
