package com.example.mycall0.tab2;

import java.util.ArrayList;

import com.example.mycall0.R;
import com.example.mycall0.R.layout;
import com.example.mycall0.R.menu;
import com.example.mycall0.tab1.AddPersonActivity;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QueryPersonActivity extends Activity {

	private TextView queryname;
	private TextView querynum1;
	private TextView querynum2;
	private TextView queryemail;
	private Button updateperson;
	private String phoneNumber;
	private String phoneNumber2;
	private String name0; // 点击的联系人

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_person);

		queryname = (TextView) QueryPersonActivity.this
				.findViewById(R.id.queryname);
		querynum1 = (TextView) QueryPersonActivity.this
				.findViewById(R.id.querynum1);
		querynum2 = (TextView) QueryPersonActivity.this
				.findViewById(R.id.querynum2);
		queryemail = (TextView) QueryPersonActivity.this
				.findViewById(R.id.queryemail);
		updateperson = (Button) QueryPersonActivity.this
				.findViewById(R.id.update_person2);
		Bundle bundle1 = QueryPersonActivity.this.getIntent().getExtras();

		name0 = bundle1.get("queryname").toString();// 使用ContentResolver查找联系人数据

		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		// 遍历查询结果，获取系统中所有联系人
		while (cursor.moveToNext()) {
			// 获取联系人ID
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			// 获取联系人的名字
			String name = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			if (name != null && name.equals(name0)) {
				// 使用ContentResolver查找联系人的电话号码
				Cursor phones = getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
								+ " = " + contactId, null, null);
				queryname.setText("姓名      " + name0);
				// ArrayList<String> detail = new ArrayList<String>();
				// // 遍历查询结果，获取该联系人的多个电话号码
				// while (phones.moveToNext()) {
				// // 获取查询结果中电话号码列中数据。
				// String phoneNumber = phones
				// .getString(phones
				// .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				// detail.add("电话号码：" + phoneNumber);
				// System.out.println("111" + detail.get(0).toString());
				// }
				if (phones.moveToNext()) {
					phoneNumber = phones
							.getString(phones
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

					querynum1.setText("      " + phoneNumber);
				} else {
					querynum1.setText("      ");
				}
				if (phones.moveToNext()) {
					phoneNumber2 = phones
							.getString(phones
									.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					querynum2.setText("      " + phoneNumber2);
				} else {
					querynum2.setText("     ");
				}
				phones.close();
				// 使用ContentResolver查找联系人的Email地址

				Cursor emails = getContentResolver().query(
						ContactsContract.CommonDataKinds.Email.CONTENT_URI,
						null,
						ContactsContract.CommonDataKinds.Email.CONTACT_ID
								+ " = " + contactId, null, null);
				// 遍历查询结果，获取该联系人的多个Email地址
				// while (emails.moveToNext()) {
				// // 获取查询结果中Email地址列中数据。
				// String emailAddress = emails
				// .getString(emails
				// .getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
				// detail.add("邮件地址：" + emailAddress);
				// }
				if (emails.moveToNext()) {
					String emailAddress = emails
							.getString(emails
									.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
					queryemail.setText("邮箱      " + emailAddress);
					emails.close();
				} else {
					queryemail.setText("邮箱      ");
				}

			}
		}
		cursor.close();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.call2_1: {
			if (phoneNumber != "" && phoneNumber != null) {
				Uri uri = Uri.parse("tel:" + phoneNumber);
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				startActivity(intent);
			} else {
				Toast.makeText(QueryPersonActivity.this, "您不能拨号为空！",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
		case R.id.send_msg2_1: {
			if (phoneNumber != "" && phoneNumber != null) {
				Uri uri = Uri.parse("smsto:" + phoneNumber);
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(intent);
			} else {
				Toast.makeText(QueryPersonActivity.this, "您所选号码为空！",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
		case R.id.call2_2: {
			if (phoneNumber2 != "" && phoneNumber2 != null) {
				Uri uri = Uri.parse("tel:" + phoneNumber2);
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				startActivity(intent);
			} else {
				Toast.makeText(QueryPersonActivity.this, "您不能拨号为空！",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
		case R.id.send_msg2_2: {
			if (phoneNumber2 != "" && phoneNumber2 != null) {
				Uri uri = Uri.parse("smsto:" + phoneNumber2);
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(intent);
			} else {
				Toast.makeText(QueryPersonActivity.this, "您所选号码为空！",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
		case R.id.edit2: {
			Intent intent = new Intent(QueryPersonActivity.this,
					UpdatePersonActivity.class);
			intent.putExtra("updatename", name0);
			QueryPersonActivity.this.startActivity(intent);
			break;
		}
		case R.id.deleteperson: {
			// 使用ContentResolver查找联系人数据
			Cursor cursor = getContentResolver().query(
					ContactsContract.Contacts.CONTENT_URI, null, null, null,
					null);
			// 遍历查询结果，获取系统中所有联系人
			while (cursor.moveToNext()) {
				// 获取联系人ID
				String contactId = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Contacts._ID));

				// 获取联系人的名字
				String name = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				if (name != null && name.equals(name0)) {
					// String where = ContactsContract.Data._ID;
					// where += "=";
					// String[] whereparams = new String[] { contactId };
					// getContentResolver().delete(
					// ContactsContract.RawContacts.CONTENT_URI, where,
					// whereparams);
					getContentResolver().delete(

					ContentUris.withAppendedId(RawContacts.CONTENT_URI,

					Integer.parseInt(contactId)), null, null);
					QueryPersonActivity.this.finish();

				}
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
		getMenuInflater().inflate(R.menu.query_person, menu);
		return true;
	}

}
