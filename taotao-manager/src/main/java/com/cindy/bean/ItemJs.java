package com.cindy.bean;

import java.util.List;

public class ItemJs {
	private int code;
	private  String msg;
	private int count;
	private List<TbItem> data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<TbItem> getData() {
		return data;
	}
	public void setData(List<TbItem> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ItemJs [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}
}
