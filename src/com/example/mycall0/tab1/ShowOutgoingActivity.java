package com.example.mycall0.tab1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.example.mycall0.R;
import com.example.mycall0.Info.CallLogBean;
import com.example.mycall0.R.id;
import com.example.mycall0.R.layout;
import com.example.mycall0.R.menu;
import com.example.mycall0.myadapter.DialAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ShowOutgoingActivity extends Activity {

	private AsyncQueryHandler asyncQuery;
	private ListView callLogListView;
	private DialAdapter adapter;
	private List<CallLogBean> callLogs;

	private Boolean showBox = false;
	private Button choose_all;

	private LinearLayout bottomout1;
	private LinearLayout bottomout2;
	private Button show_outgoing_goback;
	private Button show_outgoing_ok1;
	private Button show_outgoing_choose_all;
	private Button show_outgoing_cancle1;
	private Button show_outgoing_delete1;

	private List<Integer> strid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_outgoing);
		callLogListView = (ListView) findViewById(R.id.show_outgoing);
		choose_all = (Button) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_choose_all);
		bottomout1 = (LinearLayout) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_out1);
		bottomout2 = (LinearLayout) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_out2);
		show_outgoing_goback = (Button) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_goback);
		show_outgoing_ok1 = (Button) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_ok1);
		show_outgoing_choose_all = (Button) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_choose_all);
		show_outgoing_cancle1 = (Button) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_cancle1);
		show_outgoing_delete1 = (Button) ShowOutgoingActivity.this
				.findViewById(R.id.show_outgoing_delete);
		strid = new ArrayList<Integer>();
		bottomout1.setVisibility(View.VISIBLE);
		asyncQuery = new MyAsyncQueryHandler(getContentResolver());
		init();
		work();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.show_outgoing_goback: {
			ShowOutgoingActivity.this.finish();
			break;
		}
		case R.id.show_outgoing_delete: {
			showBox = true;
			setAllVisibility();
			checkShowBox();
			break;
		}
		case R.id.show_outgoing_choose_all: {
			showBox = true;
			checkShowBox();
			setAllVisibility();
			setAllYes();
			break;
		}
		case R.id.show_outgoing_cancle1: {
			showBox = false;
			setAllNo();
			checkShowBox();
			break;
		}
		case R.id.show_outgoing_ok1: {
			deletePart();
			showBox = false;
			setAllNo();
			checkShowBox();
			break;
		}

		default:
			break;
		}

	}

	public void work() {
		callLogListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				// if (showBox) {
				// CheckBox checkBox0 = (CheckBox) arg1
				// .findViewById(R.id.flag_call);
				// if (checkBox0.isChecked()) {
				// checkBox0.setChecked(false);
				// strid.remove("" + arg2);
				// } else {
				// checkBox0.setChecked(true);
				// strid.add("" + arg2);
				// }
				// }
			}

		});
	}

	private void deletePart() {

		HashMap<Integer, Boolean> isSelected0 = adapter.getIsSelected();
		Iterator iter = isSelected0.entrySet().iterator();
		if (isSelected0.size() > 1) {
			while (iter.hasNext()) {
				Entry entry = (Entry) iter.next();

				Boolean values = (Boolean) entry.getValue();
				Integer pos = (Integer) entry.getKey();
				if (values) {
					strid.add(pos);
				}
			}
		} else {
			Entry entry = (Entry) iter.next();

			Boolean values = (Boolean) entry.getValue();
			Integer pos = (Integer) entry.getKey();
			if (values) {
				strid.add(pos);
			}
		}

		for (int i = 0; i < strid.size(); i++) {
			callLogs.remove(strid.get(i).shortValue());
		}
		adapter.notifyDataSetChanged();

		ContentResolver resolver = ShowOutgoingActivity.this
				.getContentResolver();
		for (int i = 0; i < strid.size(); i++) {
			resolver.delete(CallLog.Calls.CONTENT_URI, "_id=?",
					new String[] { strid.get(i).toString() });
		}
	}

	public void checkShowBox() {
		if (!showBox) {
			setAllInvisibility();
			bottomout1.setVisibility(View.VISIBLE);
			show_outgoing_goback.setClickable(true);
			show_outgoing_delete1.setClickable(true);
			show_outgoing_choose_all.setClickable(true);
			bottomout2.setVisibility(View.INVISIBLE);
			show_outgoing_cancle1.setClickable(false);
			show_outgoing_ok1.setClickable(false);
		} else {
			setAllVisibility();
			bottomout1.setVisibility(View.INVISIBLE);
			show_outgoing_goback.setClickable(false);
			show_outgoing_delete1.setClickable(false);
			show_outgoing_choose_all.setClickable(false);
			bottomout2.setVisibility(View.VISIBLE);
			show_outgoing_cancle1.setClickable(true);
			show_outgoing_ok1.setClickable(true);
		}
	}

	public void setAllVisibility() {
		showBox = true;
		for (int i = 0; i < callLogListView.getChildCount(); i++) {
			CheckBox checkBox0 = (CheckBox) callLogListView.getChildAt(i)
					.findViewById(R.id.flag_call);
			checkBox0.setVisibility(View.VISIBLE);
			TextView textView0 = (TextView) callLogListView.getChildAt(i)
					.findViewById(R.id.call_btn);
			textView0.setVisibility(View.INVISIBLE);
			textView0.setClickable(false);

		}
	}

	public void setAllInvisibility() {
		showBox = false;
		setAllNo();
		for (int i = 0; i < callLogListView.getChildCount(); i++) {

			CheckBox checkBox0 = (CheckBox) callLogListView.getChildAt(i)
					.findViewById(R.id.flag_call);
			checkBox0.setVisibility(View.INVISIBLE);
			TextView textView0 = (TextView) callLogListView.getChildAt(i)
					.findViewById(R.id.call_btn);
			textView0.setVisibility(View.VISIBLE);
			textView0.setClickable(true);
		}
	}

	public void setAllNo() {
		// 遍历list的长度，将MyAdapter中的map值全部设为true
		for (int i = 0; i < callLogs.size(); i++) {
			adapter.getIsSelected().put(i, false);
		}
		adapter.notifyDataSetChanged();
	}

	public void setAllYes() {
		// 遍历list的长度，将MyAdapter中的map值全部设为true
		for (int i = 0; i < callLogs.size(); i++) {
			adapter.getIsSelected().put(i, true);
		}
		adapter.notifyDataSetChanged();
	}

	private void init() {
		Uri uri = android.provider.CallLog.Calls.CONTENT_URI;
		// 查询的列
		String[] projection = { CallLog.Calls.DATE, // 日期
				CallLog.Calls.NUMBER, // 号码
				CallLog.Calls.TYPE, // 类型
				CallLog.Calls.CACHED_NAME, // 名字
				CallLog.Calls._ID, // id
		};
		asyncQuery.startQuery(0, null, uri, projection, null, null,
				CallLog.Calls.DEFAULT_SORT_ORDER);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_outgoing, menu);
		return true;
	}

	private class MyAsyncQueryHandler extends AsyncQueryHandler {

		public MyAsyncQueryHandler(ContentResolver cr) {
			super(cr);
		}

		@Override
		protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
			if (cursor != null && cursor.getCount() > 0) {
				callLogs = new ArrayList<CallLogBean>();
				SimpleDateFormat sfd = new SimpleDateFormat("MM-dd hh:mm");
				Date date;
				cursor.moveToFirst(); // 游标移动到第一项
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					date = new Date(cursor.getLong(cursor
							.getColumnIndex(CallLog.Calls.DATE)));
					String number = cursor.getString(cursor
							.getColumnIndex(CallLog.Calls.NUMBER));
					int type = cursor.getInt(cursor
							.getColumnIndex(CallLog.Calls.TYPE));
					String cachedName = cursor.getString(cursor
							.getColumnIndex(CallLog.Calls.CACHED_NAME));// 缓存的名称与电话号码，如果它的存在
					int id = cursor.getInt(cursor
							.getColumnIndex(CallLog.Calls._ID));

					CallLogBean callLogBean = new CallLogBean();
					callLogBean.setId(id);
					callLogBean.setNumber(number);
					callLogBean.setName(cachedName);
					if (null == cachedName || "".equals(cachedName)) {
						callLogBean.setName(number);
					}
					callLogBean.setType(type);
					callLogBean.setDate(sfd.format(date));
					if (type == 1) {
						callLogs.add(callLogBean);
					}
				}
				if (callLogs.size() > 0) {
					setAdapter(callLogs);
				}
			}
			super.onQueryComplete(token, cookie, cursor);
		}
	}

	private void setAdapter(List<CallLogBean> callLogs) {
		adapter = new DialAdapter(ShowOutgoingActivity.this, callLogs);
		callLogListView.setAdapter(adapter);
	}
}
