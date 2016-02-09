package com.example.mycall0.myadapter;

import java.util.HashMap;
import java.util.List;

import com.example.mycall0.R;
import com.example.mycall0.Info.CallLogBean;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class DialAdapter extends BaseAdapter {

	private Context context;
	private List<CallLogBean> callLogs;
	private LayoutInflater inflater;

	// 用来控制CheckBox的选中状况
	private static HashMap<Integer, Boolean> isSelected;

	public DialAdapter(Context context, List<CallLogBean> callLogs) {
		this.context = context;
		this.callLogs = callLogs;
		this.inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();
		// 初始化数据
		initDate();
	}

	// 初始化isSelected的数据
	private void initDate() {
		for (int i = 0; i < callLogs.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		DialAdapter.isSelected = isSelected;
	}

	@Override
	public int getCount() {
		return callLogs.size();
	}

	@Override
	public Object getItem(int position) {
		return callLogs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.contact_record_list_item,
					null);
			holder = new ViewHolder();
			holder.call_type = (ImageView) convertView
					.findViewById(R.id.call_type);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.number = (TextView) convertView.findViewById(R.id.number);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.call_btn = (TextView) convertView
					.findViewById(R.id.call_btn);
			holder.flag_call = (CheckBox) convertView
					.findViewById(R.id.flag_call);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CallLogBean callLog = callLogs.get(position);
		switch (callLog.getType()) {
		case 1:
			holder.call_type
					.setBackgroundResource(R.drawable.ic_calllog_outgoing_nomal);
			break;
		case 2:
			holder.call_type
					.setBackgroundResource(R.drawable.ic_calllog_incomming_normal);
			break;
		case 3:
			holder.call_type
					.setBackgroundResource(R.drawable.ic_calllog_missed_normal);
			break;
		}

		// 监听checkBox并根据原来的状态来设置新的状态
		holder.flag_call.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (isSelected.get(position)) {
					isSelected.put(position, false);
					setIsSelected(isSelected);
				} else {
					isSelected.put(position, true);
					setIsSelected(isSelected);
				}

			}
		});

		// 根据isSelected来设置checkbox的选中状况
		holder.flag_call.setChecked(getIsSelected().get(position));
		holder.name.setText(callLog.getName());
		holder.number.setText(callLog.getNumber());
		holder.time.setText(callLog.getDate());

		addViewListener(holder.call_btn, callLog, position);

		return convertView;
	}

	private static class ViewHolder {
		ImageView call_type;
		TextView name;
		TextView number;
		TextView time;
		TextView call_btn;
		CheckBox flag_call;
	}

	private void addViewListener(View view, final CallLogBean callLog,
			final int position) {
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:" + callLog.getNumber());
				Intent intent = new Intent(Intent.ACTION_CALL, uri);
				context.startActivity(intent);
			}
		});
	}
}
