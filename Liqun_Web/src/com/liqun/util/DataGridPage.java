package com.liqun.util;

import java.util.List;

import org.springframework.data.domain.Page;
//easyUI 列表转换类
public class DataGridPage<T> {
	//总条数
	int total;
	//数据
	List<T> Rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return Rows;
	}

	public void setRows(List<T> rows) {
		Rows = rows;
	}

	public static <E> DataGridPage<E> pageToGrid(Page<E> page) {

		DataGridPage<E> grid = new DataGridPage<>();
		grid.setTotal((int)page.getTotalElements());
		grid.setRows(page.getContent());
		return grid;
	}
	
}
