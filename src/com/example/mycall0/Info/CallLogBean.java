package com.example.mycall0.Info;

/**
 * ͨ����¼ʵ����
 * 
 * @author Administrator
 * 
 */
public class CallLogBean {

	private int id;
	private String name; // ���
	private String number; // ����
	private String date; // ����
	private int type; // ����:1������:2,δ��:3
	private int count; // ͨ������

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
