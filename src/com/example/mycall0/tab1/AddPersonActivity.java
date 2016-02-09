package com.example.mycall0.tab1;

import com.example.mycall0.R;
import com.example.mycall0.Info.AllOverData;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts.Data;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPersonActivity extends Activity {

	// private String strnum1 = "";
	// private String strname1 = "";
	// private String stremail1 = "";
	// private String strgroup1 = "";
	private EditText add_num1;
	private EditText add_name1;
	private EditText add_email1;
	private EditText add_group1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_person);
		add_num1 = (EditText) AddPersonActivity.this
				.findViewById(R.id.add_num1);
		add_name1 = (EditText) AddPersonActivity.this
				.findViewById(R.id.add_name1);
		add_email1 = (EditText) AddPersonActivity.this
				.findViewById(R.id.add_email1);
		add_group1 = (EditText) AddPersonActivity.this
				.findViewById(R.id.add_group1);
		// strnum1 = add_num1.getText().toString();
		// strname1 = add_name1.getText().toString();
		// stremail1 = add_email1.getText().toString();
		// strgroup1 = add_group1.getText().toString();
		Bundle bundle1 = AddPersonActivity.this.getIntent().getExtras();
		String num0 = bundle1.get("tab1num").toString();
		if (!num0.equals("")) {
			add_num1.setText(num0);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.cancle1: {
			AddPersonActivity.this.finish();
			break;
		}
		case R.id.ok1: {
			// ��ȡ��������е�3���ı���
			String name = ((EditText) findViewById(R.id.add_name1)).getText()
					.toString();
			String phone = ((EditText) findViewById(R.id.add_num1)).getText()
					.toString();
			String email = ((EditText) findViewById(R.id.add_email1)).getText()
					.toString();
			if (name != null && !name.equals("")) {
				// ����һ���յ�ContentValues
				ContentValues values = new ContentValues();
				// ��RawContacts.CONTENT_URIִ��һ����ֵ���룬
				// Ŀ���ǻ�ȡϵͳ���ص�rawContactId
				Uri rawContactUri = getContentResolver().insert(
						RawContacts.CONTENT_URI, values);
				long rawContactId = ContentUris.parseId(rawContactUri);
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				// ������������
				values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
				// ������ϵ������
				values.put(StructuredName.GIVEN_NAME, name);
				// ����ϵ��URI�����ϵ������
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
				// ������ϵ�˵ĵ绰����
				values.put(Phone.NUMBER, phone);
				// ���õ绰����
				values.put(Phone.TYPE, Phone.TYPE_MOBILE);
				// ����ϵ�˵绰����URI��ӵ绰����
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				values.clear();
				values.put(Data.RAW_CONTACT_ID, rawContactId);
				values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
				// ������ϵ�˵�Email��ַ
				values.put(Email.DATA, email);
				// ���øõ����ʼ�������
				values.put(Email.TYPE, Email.TYPE_WORK);
				// if()
				// ����ϵ��Email URI���Email���
				getContentResolver().insert(
						android.provider.ContactsContract.Data.CONTENT_URI,
						values);
				Toast.makeText(AddPersonActivity.this, "添加好友成功！",
						Toast.LENGTH_LONG).show();
				AllOverData.setAction("add");
				AllOverData.setName(name);
				AddPersonActivity.this.finish();
			} else {
				Toast.makeText(AddPersonActivity.this, "添加好友姓名不能为空！",
						Toast.LENGTH_LONG).show();
				Toast.makeText(AddPersonActivity.this, "添加好友失败！",
						Toast.LENGTH_LONG).show();

				AddPersonActivity.this.finish();
			}
			break;
		}

		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_person, menu);
		return true;
	}

}
