package com.example.mycall0.tab2;

import com.example.mycall0.MyTab1Activity;
import com.example.mycall0.MyTab2Activity;
import com.example.mycall0.R;
import com.example.mycall0.R.layout;
import com.example.mycall0.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePersonActivity extends Activity {

	private EditText updatename;
	private EditText updatenum1;
	private EditText updatenum2;
	private EditText updateemail;
	private String updatenamestr = "";
	private String updateidstr = "";
	private String updateemaistr = "";
	private String updateNumber1str = "";
	private String updateNumber2str = "";
	private String name0 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_person);
		updatename = (EditText) UpdatePersonActivity.this
				.findViewById(R.id.update_name2);
		updatenum1 = (EditText) UpdatePersonActivity.this
				.findViewById(R.id.update_num2_1);
		updatenum2 = (EditText) UpdatePersonActivity.this
				.findViewById(R.id.update_num2_2);
		updateemail = (EditText) UpdatePersonActivity.this
				.findViewById(R.id.update_email2);
		Bundle bundle1 = UpdatePersonActivity.this.getIntent().getExtras();

		name0 = bundle1.get("updatename").toString();// 使用ContentResolver查找联系人数据
		if (name0 != null && !name0.equals("")) {
			Cursor cursor = getContentResolver().query(
					ContactsContract.Contacts.CONTENT_URI, null, null, null,
					null);
			// 遍历查询结果，获取系统中所有联系人
			while (cursor.moveToNext()) {
				// 获取联系人ID
				String contactId = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Contacts._ID));
				System.out.println("kkkkk" + cursor.getCount());
				// 获取联系人的名字
				String contactName = cursor
						.getString(cursor
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				if (contactName != null && contactName.equals(name0)) {
					// 使用ContentResolver查找联系人的电话号码
					updatenamestr = contactName;
					updateidstr = contactId;
					Cursor phones = getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					if (phones.moveToNext()) {
						updateNumber1str = phones
								.getString(phones
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

					}
					if (phones.moveToNext()) {
						updateNumber2str = phones
								.getString(phones
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					}
					phones.close();
					// 使用ContentResolver查找联系人的Email地址

					Cursor emails = getContentResolver().query(
							ContactsContract.CommonDataKinds.Email.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Email.CONTACT_ID
									+ " = " + contactId, null, null);
					if (emails.moveToNext()) {
						updateemaistr = emails
								.getString(emails
										.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
						emails.close();
					}
				}
			}
			cursor.close();
		}

		if (!updatenamestr.equals("")) {
			updatename.setText(updatenamestr);
		}
		if (!updateemaistr.equals("")) {
			updateemail.setText(updateemaistr);
		}
		if (!updateNumber1str.equals("")) {
			updatenum1.setText(updateNumber1str);
		}
		if (!updateNumber2str.equals("")) {
			updatenum2.setText(updateNumber2str);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.update_ok2: {
			if (updatenamestr != null && !updatenamestr.equals("")) {

				Uri uri = Uri.parse("content://com.android.contacts/data");// 对data表的所有数据操作
				ContentResolver resolver = this.getBaseContext()
						.getContentResolver();
				ContentValues values = new ContentValues();
				values.clear();
				updatenamestr = updatename.getText().toString();
				if (!updatename.equals("")) {
					values.put("data1", updatenamestr);
					System.out.println(updatenamestr + updateidstr);
					resolver.update(uri, values,
							"mimetype=? and raw_contact_id=?", new String[] {
									"vnd.android.cursor.item/name", "1" });
				}
				values.clear();
				if (!updateNumber1str.equals("")) {
					values.put("data1", updateNumber1str);
					resolver.update(uri, values,
							"mimetype=? and raw_contact_id=?", new String[] {
									"vnd.android.cursor.item/phone_v2", "1" });
				}
				Toast.makeText(UpdatePersonActivity.this, "修改成功！",
						Toast.LENGTH_SHORT).show();
				// if (!updateemaistr.equals("")) {
				// values.put("display_name", updatenamestr);
				// }
				// if (!updateNumber1str.equals("")) {
				// updatenum1.setText(updateNumber1str);
				// }
				// if (!updateNumber2str.equals("")) {
				// updatenum2.setText(updateNumber2str);
				// }
				// resolver.update(uri, values,
				// "mimetype=? and raw_contact_id=?",
				// new String[] { "vnd.android.cursor.item/phone_v2",
				// id + "" });

				break;
			}

		}
		case R.id.cancle_update2: {
			UpdatePersonActivity.this.finish();
			break;
		}
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_person, menu);
		return true;
	}

}
