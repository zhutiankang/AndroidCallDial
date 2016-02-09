package com.example.mycall0.bean;

public class BlackNumberInfo {

	private String number;
	
	public BlackNumberInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlackNumberInfo(String number) {
		super();
		this.number = number;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "BlackNumberInfo [number=" + number + "]";
	}
	
}
