package com.example.mycall0.myadapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.mycall0.R;
import com.example.mycall0.Info.SMSBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * ����������
 * 
 * @author Administrator
 * 
 */
public class SMSAdpter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<SMSBean> smsList;
	private Date date;
	private SimpleDateFormat sdf;
	private LayoutInflater inflater;

	// 用来控制CheckBox的选中状况
	private static HashMap<Integer, Boolean> isSelected;

	public SMSAdpter(Context context) {
		mInflater = LayoutInflater.from(context);
		this.smsList = new ArrayList<SMSBean>();
		this.date = new Date();
		this.sdf = new SimpleDateFormat("MM/dd HH:mm");
		this.inflater = LayoutInflater.from(context);
		isSelected = new HashMap<Integer, Boolean>();
		// 初始化数据
		initDate();
	}

	// 初始化isSelected的数据
	private void initDate() {
		for (int i = 0; i < smsList.size(); i++) {
			getIsSelected().put(i, false);
		}
	}

	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		SMSAdpter.isSelected = isSelected;
	}

	public void assignment(List<SMSBean> smsList) {
		this.smsList = smsList;
	}

	public void add(SMSBean bean) {
		smsList.add(bean);
	}

	public void remove(int position) {
		smsList.remove(position);
	}

	@Override
	public int getCount() {
		return smsList.size();
	}

	@Override
	public Object getItem(int position) {
		return smsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.sms_list_item, parent,
					false);
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.count = (TextView) convertView.findViewById(R.id.count);
			holder.date = (TextView) convertView.findViewById(R.id.date);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			holder.checkBox = (CheckBox) convertView
					.findViewById(R.id.msg_main_checkbox);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(smsList.get(position).getAddress());
		holder.count.setText("(" + smsList.get(position).getMsg_count() + ")");

		this.date.setTime(smsList.get(position).getDate());
		holder.date.setText(this.sdf.format(date));

		holder.content.setText(smsList.get(position).getMsg_snippet());

		// 监听checkBox并根据原来的状态来设置新的状态
		holder.checkBox.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (smsList.get(position).getCheckBox()) {
					isSelected.put(position, false);
					setIsSelected(isSelected);
					smsList.get(position).setCheckBox(false);

				} else {
					isSelected.put(position, true);
					setIsSelected(isSelected);
					smsList.get(position).setCheckBox(true);
				}
			}

		});

		// 根据isSelected来设置checkbox的选中状况
		holder.checkBox.setChecked(smsList.get(position).getCheckBox());
		convertView.setTag(holder);
		return convertView;
	}

	public final class ViewHolder {
		public TextView name;
		public TextView count;
		public TextView date;
		public TextView content;
		public CheckBox checkBox;
	}
}
