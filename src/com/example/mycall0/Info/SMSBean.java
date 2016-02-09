package com.example.mycall0.Info;

import android.widget.CheckBox;

public class SMSBean {

	private String thread_id;
	private String msg_count;
	private String msg_snippet;
	private String address;
	private Long date;
	private String read;
	private Boolean checkBox;

	public SMSBean(String threadId, String msgCount, String msgSnippet,
			Boolean checkBox) {
		thread_id = threadId;
		msg_count = msgCount;
		msg_snippet = msgSnippet;
		this.checkBox = checkBox;
	}

	public Boolean getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(Boolean checkBox) {
		this.checkBox = checkBox;
	}

	public SMSBean() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getThread_id() {
		return thread_id;
	}

	public void setThread_id(String threadId) {
		thread_id = threadId;
	}

	public String getMsg_count() {
		return msg_count;
	}

	public void setMsg_count(String msgCount) {
		msg_count = msgCount;
	}

	public String getMsg_snippet() {
		return msg_snippet;
	}

	public void setMsg_snippet(String msgSnippet) {
		msg_snippet = msgSnippet;
	}
}
