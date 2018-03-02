package com.liqun.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.bson.Document;
import org.springframework.data.domain.Page;

import com.liqun.entity.SysPermission;

public class DataToTree {
	//节点ID
	int id;
	//显示文本
	String text;
	//是否选中
	boolean checked;
	//图标
    String iconCls;
	//伸展状态
	String state;
	//属性
	List<String> attributes;
	//孩子节点
	List<DataToTree> children = null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	public List<DataToTree> getChildren() {
		return children;
	}
	public void setChildren(List<DataToTree> children) {
		this.children = children;
	}

	public DataToTree() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static Object ToTree(List<SysPermission> permissList) {
		//Stream<SysPermission> toplevel = page.stream().filter((e)-> {return e.getParent()==null;});
		//转换为easyUIjson所需字段
		List<DataToTree> treeList=new LinkedList<>();
		for (SysPermission sysPermission : permissList) {
			DataToTree tempData=new DataToTree();
			tempData.setId(sysPermission.getPermissionId());//权限ID
			tempData.setText(sysPermission.getPermissionName());//权限名称
			tempData.setIconCls(sysPermission.getIcon());//图标
			tempData.setState("open");//菜单默认全部打开
			List<String> attrList=new LinkedList<>();
			attrList.add("url:"+sysPermission.getUrl());
			attrList.add("sort:"+sysPermission.getSort());
			tempData.setAttributes(attrList);//属性
			treeList.add(tempData);
		}
		//拼装树形结构
		Map<Integer, Document> documents = new HashMap<>();
		for (DataToTree treeitem : treeList) {
			documents.put(treeitem.getId(),
					new Document("id",treeitem.getId())
					.append("icon", treeitem.getIconCls())
					.append("text",treeitem.getText())
					.append("url", treeitem.getAttributes()));
		}
		return documents;
	}
	
}
