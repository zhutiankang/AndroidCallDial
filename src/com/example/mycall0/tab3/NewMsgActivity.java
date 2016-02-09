package com.example.mycall0.tab3;

import java.util.ArrayList;
import java.util.List;

import com.example.mycall0.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewMsgActivity extends Activity {

	private EditText receiver_person;
	private EditText new_msg_context;
	private Button new_msg_send;
	private Button new_msg_cancle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_msg);

		receiver_person = (EditText) NewMsgActivity.this
				.findViewById(R.id.receiver_person);
		new_msg_context = (EditText) NewMsgActivity.this
				.findViewById(R.id.new_msg_context);
		new_msg_send = (Button) NewMsgActivity.this
				.findViewById(R.id.new_msg_send);
		new_msg_cancle = (Button) NewMsgActivity.this
				.findViewById(R.id.new_msg_cancle);
		new_msg_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NewMsgActivity.this.finish();
			}
		});
		
		new_msg_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phoneNumber = receiver_person.getText().toString()
						.trim();
				if (phoneNumber.equals("")) {
					Toast.makeText(NewMsgActivity.this, "号码不能为空！",
							Toast.LENGTH_SHORT).show();
					return;
				} else {
					String smsContent = new_msg_context.getText().toString()
							.trim();
					sendSms(phoneNumber, smsContent);
					writeToDataBase(phoneNumber, smsContent);
				}
			}
		});

	}

	/**
	 * send sms
	 * 
	 * 发送短信
	 * 
	 * */
	private void sendSms(String phoneNumber, String smsContent) {

		// 当文本超过限定字符长度的时候（中文70，英文160）,在2.2中会nullpoint，4.1.1中发送无效
		SmsManager smsManager = SmsManager.getDefault();
		// smsManager.sendTextMessage(phoneNumber, null, smsContent, null,
		// null);

		// 改为sendMultipartTextMessage()
		ArrayList<String> messageArray = smsManager.divideMessage(smsContent);
		smsManager.sendMultipartTextMessage(phoneNumber, null, messageArray,
				null, null);

		Toast.makeText(this, "发送成功！", Toast.LENGTH_LONG).show();
		receiver_person.setText("");
		new_msg_context.setText("");

		NewMsgActivity.this.finish();
	}

	/**
	 * write to database
	 * 
	 * 写入数据库
	 * 
	 * */
	private void writeToDataBase(String phoneNumber, String smsContent) {
		ContentValues values = new ContentValues();
		values.put("address", phoneNumber);
		values.put("body", smsContent);
		values.put("type", "2");
		values.put("read", "1");// "1"means has read ,1表示已读
		getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_msg, menu);
		return true;
	}

}
