package com.example.mycall0;

import com.example.mycall0.tab1.AddPersonActivity;
import com.example.mycall0.tab1.CallRecordActivity;
import com.example.mycall0.tab1.ShowCallRecordActivity;
import com.example.mycall0.tab1.ShowMissCallActivity;
import com.example.mycall0.tab3.NewMsgActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class MyTab1Activity extends Activity {

	private String num = "";
	private TextView textView;
	private LinearLayout top1;
	private LinearLayout biggerout1;
	private LinearLayout smallerout1;
	private LinearLayout show1;
	private LinearLayout keyborad1;
	private LinearLayout interceptout1;
	private LinearLayout callout1;
	private LinearLayout threeout1;
	private LinearLayout qingkongout1;
	private Button bigger1;
	private Button smaller1;
	private Button intercept1;
	private Button call1;
	private Button three1;
	private Button qingkong1;
	private PopupMenu popup = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tab1);

		textView = (TextView) MyTab1Activity.this.findViewById(R.id.textView1);
		top1 = (LinearLayout) MyTab1Activity.this.findViewById(R.id.top1);
		biggerout1 = (LinearLayout) MyTab1Activity.this
				.findViewById(R.id.biggerout1);
		smallerout1 = (LinearLayout) MyTab1Activity.this
				.findViewById(R.id.smallerout1);
		show1 = (LinearLayout) MyTab1Activity.this.findViewById(R.id.show1);
		keyborad1 = (LinearLayout) MyTab1Activity.this
				.findViewById(R.id.keyborad1);
		interceptout1 = (LinearLayout) MyTab1Activity.this
				.findViewById(R.id.interceptout1);
		callout1 = (LinearLayout) MyTab1Activity.this
				.findViewById(R.id.callout1);
		threeout1 = (LinearLayout) MyTab1Activity.this
				.findViewById(R.id.threeout1);
		qingkongout1 = (LinearLayout) MyTab1Activity.this
				.findViewById(R.id.qingkongout1);
		bigger1 = (Button) MyTab1Activity.this.findViewById(R.id.bigger1);
		smaller1 = (Button) MyTab1Activity.this.findViewById(R.id.smaller1);
		intercept1 = (Button) MyTab1Activity.this.findViewById(R.id.intercept1);
		call1 = (Button) MyTab1Activity.this.findViewById(R.id.call1);
		three1 = (Button) MyTab1Activity.this.findViewById(R.id.three1);
		qingkong1 = (Button) MyTab1Activity.this.findViewById(R.id.qingkong1);
		bigger1.setClickable(false);
		smaller1.setClickable(true);
		intercept1.setClickable(true);
		call1.setClickable(false);
		three1.setClickable(true);
		qingkong1.setClickable(false);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_tab1, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.number1: {
			num += "1";
			textView.setText(num);
			break;
		}

		case R.id.number2: {

			num += "2";
			textView.setText(num);
			break;
		}
		case R.id.number3: {

			num += "3";
			textView.setText(num);
			break;
		}
		case R.id.number4: {

			num += "4";
			textView.setText(num);
			break;
		}
		case R.id.number5: {

			num += "5";
			textView.setText(num);
			break;
		}
		case R.id.number6: {

			num += "6";
			textView.setText(num);
			break;
		}
		case R.id.number7: {

			num += "7";
			textView.setText(num);
			break;
		}
		case R.id.number8: {

			num += "8";
			textView.setText(num);
			break;
		}
		case R.id.number9: {

			num += "9";
			textView.setText(num);
			break;
		}
		case R.id.number0: {

			num += "0";
			textView.setText(num);
			break;
		}
		case R.id.star1: {

			num += "*";
			textView.setText(num);
			break;
		}
		case R.id.jing1: {

			num += "#";
			textView.setText(num);
			break;
		}
		case R.id.delete0: {
			if (num.length() != 0) {
				num = num.substring(0, num.length() - 1);
				textView.setText(num);
				break;
			}
		}
		case R.id.qingkong1: {

			num = "";
			textView.setText(num);
			break;
		}
		case R.id.send_msg: {
			// Uri uri = Uri.parse("smsto:" + num);
			// Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
			// startActivity(intent);
			Intent intent = new Intent(MyTab1Activity.this,
					NewMsgActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.call1: {
			Uri uri = Uri.parse("tel:" + num);
			Intent intent = new Intent(Intent.ACTION_CALL, uri);
			startActivity(intent);
			break;
		}
		case R.id.add1_person: {
			Intent intent = new Intent(MyTab1Activity.this,
					AddPersonActivity.class);
			intent.putExtra("tab1num", num);
			MyTab1Activity.this.startActivity(intent);
			num = "";
			textView.setText(num);
			break;
		}
		case R.id.save_person: {
			Intent intent = new Intent(MyTab1Activity.this,
					MyTab2Activity.class);
			startActivity(intent);
			break;
		}
		case R.id.smaller1: {
			smaller1.setClickable(false);
			smallerout1.setVisibility(View.INVISIBLE);
			show1.setVisibility(View.INVISIBLE);
			keyborad1.setVisibility(View.INVISIBLE);
			biggerout1.setVisibility(View.VISIBLE);
			bigger1.setClickable(true);
			break;
		}
		case R.id.bigger1: {
			smaller1.setClickable(true);
			smallerout1.setVisibility(View.VISIBLE);
			show1.setVisibility(View.VISIBLE);
			keyborad1.setVisibility(View.VISIBLE);
			biggerout1.setVisibility(View.INVISIBLE);
			bigger1.setClickable(false);
			break;
		}	
		case R.id.intercept1:
			Intent intent = new Intent(this,CallSafeActivity.class);
			startActivity(intent);
			break;
		
		default:
			break;
		}
		if (num.equals("")) {
			top1.setVisibility(View.INVISIBLE);
			interceptout1.setVisibility(View.VISIBLE);
			intercept1.setClickable(true);
			callout1.setVisibility(View.INVISIBLE);
			call1.setClickable(false);
			threeout1.setVisibility(View.VISIBLE);
			three1.setClickable(true);
			qingkongout1.setVisibility(View.INVISIBLE);
			qingkong1.setClickable(false);
		} else {
			top1.setVisibility(View.VISIBLE);
			interceptout1.setVisibility(View.INVISIBLE);
			intercept1.setClickable(false);
			callout1.setVisibility(View.VISIBLE);
			call1.setClickable(true);
			threeout1.setVisibility(View.INVISIBLE);
			three1.setClickable(false);
			qingkongout1.setVisibility(View.VISIBLE);
			qingkong1.setClickable(true);
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void onPopupButtonClick(View button) {
		// 创建PopupMenu对象
		popup = new PopupMenu(this, button);
		// 将R.menu.popup_menu菜单资源加载到popup菜单中
		getMenuInflater().inflate(R.menu.popup_menu1, popup.getMenu());

		// 为popup菜单的菜单项单击事件绑定事件监听器
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.exitmenu1: {
					// 隐藏该对话框
					popup.dismiss();
					break;
				}
				case R.id.callrecordmenu: {
					// 查看通话记录
					Intent intent = new Intent(MyTab1Activity.this,
							CallRecordActivity.class);
					MyTab1Activity.this.startActivity(intent);
					break;
				}
				case R.id.callmissmenu: {
					// 查看未接电话
					Intent intent = new Intent(MyTab1Activity.this,
							ShowMissCallActivity.class);
					MyTab1Activity.this.startActivity(intent);
					break;
				}
				default:
					break;
				}
				return true;
			}
		});
		popup.show();
	}

}
