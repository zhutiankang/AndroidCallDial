package com.example.mycall0;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.mycall0.Info.SMSBean;
import com.example.mycall0.myadapter.SMSAdpter;
import com.example.mycall0.tab1.ShowCallRecordActivity;
import com.example.mycall0.tab3.MessageBoxList;
import com.example.mycall0.tab3.NewMsgActivity;
import com.example.mycall0.util.BaseIntentUtil;
import com.example.mycall0.util.RexseeSMS;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MyTab3Activity extends Activity {
	private ListView smsListView;
	private SMSAdpter smsAdpter;
	private RexseeSMS rsms;
	private List<SMSBean> list_mmt;

	private LinearLayout bottomout1;
	private LinearLayout bottomout2;
	private Button msg_main_new;
	private Button msg_main_delete;
	private Button msg_main_choose_all;
	private Button msg_main_cancle;
	private Button msg_main_ok;
	private Boolean showBox = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tab3);
		bottomout1 = (LinearLayout) MyTab3Activity.this
				.findViewById(R.id.msg_main_bottom_out1);
		bottomout2 = (LinearLayout) MyTab3Activity.this
				.findViewById(R.id.msg_main_bottom_out2);
		msg_main_new = (Button) MyTab3Activity.this
				.findViewById(R.id.msg_main_new);
		msg_main_delete = (Button) MyTab3Activity.this
				.findViewById(R.id.msg_main_delete);
		msg_main_choose_all = (Button) MyTab3Activity.this
				.findViewById(R.id.msg_main_choose_all);
		msg_main_cancle = (Button) MyTab3Activity.this
				.findViewById(R.id.msg_main_cancle1);
		msg_main_ok = (Button) MyTab3Activity.this
				.findViewById(R.id.msg_main_ok1);
		smsListView = (ListView) findViewById(R.id.sms_list);

		smsAdpter = new SMSAdpter(MyTab3Activity.this);
		rsms = new RexseeSMS(MyTab3Activity.this);
		list_mmt = rsms.getThreadsNum(rsms.getThreads(0));
		smsAdpter.assignment(list_mmt);
		smsListView.setAdapter(smsAdpter);
		smsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!showBox) {
					Map<String, String> map = new HashMap<String, String>();
					SMSBean sb = (SMSBean) smsAdpter.getItem(position);
					map.put("phoneNumber", sb.getAddress());
					map.put("threadId", sb.getThread_id());

					BaseIntentUtil.intentSysDefault(MyTab3Activity.this,
							MessageBoxList.class, map);
				} else {
				}
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		smsAdpter.notifyDataSetChanged();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.msg_main_new: {
			Intent intent = new Intent(MyTab3Activity.this,
					NewMsgActivity.class);
			MyTab3Activity.this.startActivity(intent);
			break;
		}
		case R.id.msg_main_delete: {
			showBox = true;
			setAllVisibility();
			checkShowBox();
			break;
		}
		case R.id.msg_main_choose_all: {
			showBox = true;
			checkShowBox();
			setAllVisibility();
			setAllYes();
			break;
		}
		case R.id.msg_main_cancle1: {
			showBox = false;
			setAllNo();
			checkShowBox();
			setAllInvisibility();
			break;
		}
		case R.id.msg_main_ok1: {
			deletePart();
			showBox = false;
			setAllNo();
			checkShowBox();
			setAllInvisibility();
			break;
		}

		default:
			break;
		}

	}

	public void checkShowBox() {
		if (!showBox) {
			setAllInvisibility();
			bottomout1.setVisibility(View.VISIBLE);
			msg_main_new.setClickable(true);
			msg_main_delete.setClickable(true);
			msg_main_choose_all.setClickable(true);
			bottomout2.setVisibility(View.INVISIBLE);
			msg_main_cancle.setClickable(false);
			msg_main_ok.setClickable(false);
		} else {
			setAllVisibility();
			bottomout1.setVisibility(View.INVISIBLE);
			msg_main_new.setClickable(false);
			msg_main_delete.setClickable(false);
			msg_main_choose_all.setClickable(false);
			bottomout2.setVisibility(View.VISIBLE);
			msg_main_cancle.setClickable(true);
			msg_main_ok.setClickable(true);
		}
	}

	public void deletePart() {
		ContentResolver contentResolver = getContentResolver();
		Uri uri = Uri.parse("content://sms");
		for (int i = 0; i < list_mmt.size(); i++) {
			SMSBean smsBean = list_mmt.get(i);

			if (smsBean.getCheckBox()) {
				contentResolver.delete(uri, "thread_id=?",
						new String[] { smsBean.getThread_id() });
				list_mmt.remove(smsBean);
			}
		}
		smsAdpter.notifyDataSetChanged();
	}

	public void setAllVisibility() {
		showBox = true;
		for (int i = 0; i < smsListView.getChildCount(); i++) {
			CheckBox checkBox0 = (CheckBox) smsListView.getChildAt(i)
					.findViewById(R.id.msg_main_checkbox);
			checkBox0.setVisibility(View.VISIBLE);

		}
	}

	public void setAllInvisibility() {
		showBox = false;
		setAllNo();
		for (int i = 0; i < smsListView.getChildCount(); i++) {
			CheckBox checkBox0 = (CheckBox) smsListView.getChildAt(i)
					.findViewById(R.id.msg_main_checkbox);
			checkBox0.setVisibility(View.INVISIBLE);

		}
	}

	public void setAllNo() {
		// 遍历list的长度，将MyAdapter中的map值全部设为true
		for (int i = 0; i < list_mmt.size(); i++) {
			smsAdpter.getIsSelected().put(i, false);
			list_mmt.get(i).setCheckBox(false);
		}
		smsAdpter.notifyDataSetChanged();
	}

	public void setAllYes() {
		// 遍历list的长度，将MyAdapter中的map值全部设为true
		for (int i = 0; i < list_mmt.size(); i++) {
			smsAdpter.getIsSelected().put(i, true);
			list_mmt.get(i).setCheckBox(true);
		}
		smsAdpter.notifyDataSetChanged();
	}
}
