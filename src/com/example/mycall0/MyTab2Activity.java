package com.example.mycall0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.example.mycall0.R;
import com.example.mycall0.Info.AllOverData;
import com.example.mycall0.Info.SortModel;
import com.example.mycall0.myadapter.SMSAdpter.ViewHolder;
import com.example.mycall0.myadapter.SortAdapter;
import com.example.mycall0.tab1.AddPersonActivity;
import com.example.mycall0.tab2.CharacterParser;
import com.example.mycall0.tab2.ClearEditText;
import com.example.mycall0.tab2.PinyinComparator;
import com.example.mycall0.tab2.QueryPersonActivity;
import com.example.mycall0.tab2.SideBar;
import com.example.mycall0.tab2.SideBar.OnTouchingLetterChangedListener;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class MyTab2Activity extends Activity {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private FrameLayout tab2out;
	private ClearEditText mClearEditText;
	private int num = 0;
	private int flag = 0;
	private final static int REFRESH = 200;
	private LinearLayout bottomout1;
	private LinearLayout bottomout2;
	private Button add_person2;
	private Button group2;
	private Button three2;
	private Button cancledelete2;
	private Button okdelete2;
	private LinearLayout mydialogface;
	private PopupMenu popup = null;
	private List<String> names = new ArrayList<String>();
	/**
	 * ����ת����ƴ������
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	private List<String> names_delete = new ArrayList<String>();
	private List<Integer> id_delete = new ArrayList<Integer>();
	/**
	 * ���ƴ��������ListView����������
	 */
	private PinyinComparator pinyinComparator;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case REFRESH: {
				SourceDateList.clear();
				adapter.updateListView(SourceDateList);
				break;
			}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tab2);

		bottomout1 = (LinearLayout) MyTab2Activity.this
				.findViewById(R.id.myTab2BottomOut1);
		bottomout2 = (LinearLayout) MyTab2Activity.this
				.findViewById(R.id.myTab2BottomOut2);
		add_person2 = (Button) MyTab2Activity.this
				.findViewById(R.id.add_person2);
		group2 = (Button) MyTab2Activity.this.findViewById(R.id.group2);
		three2 = (Button) MyTab2Activity.this.findViewById(R.id.three2);
		cancledelete2 = (Button) MyTab2Activity.this
				.findViewById(R.id.cancledelete2);
		okdelete2 = (Button) MyTab2Activity.this.findViewById(R.id.okdelete2);
		tab2out = (FrameLayout) MyTab2Activity.this
				.findViewById(R.id.mytab2out);

		// ʹ��ContentResolver������ϵ�����
		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		num = 0;
		// �����ѯ����ȡϵͳ��������ϵ��
		while (cursor.moveToNext()) {
			// ��ȡ��ϵ��ID
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			// ��ȡ��ϵ�˵�����
			String name = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			if (name != null) {
				names.add(name);
			}
		}
		cursor.close();

		initViews();
	}

	public void checkFlag() {
		if (flag == 0) {
			setAllInvisibility();
			bottomout1.setVisibility(View.VISIBLE);
			add_person2.setClickable(true);
			group2.setClickable(true);
			three2.setClickable(true);
			bottomout2.setVisibility(View.INVISIBLE);
			cancledelete2.setClickable(false);
			okdelete2.setClickable(false);
		} else {
			setAllVisibility();
			bottomout1.setVisibility(View.INVISIBLE);
			add_person2.setClickable(false);
			group2.setClickable(false);
			three2.setClickable(false);
			bottomout2.setVisibility(View.VISIBLE);
			cancledelete2.setClickable(true);
			okdelete2.setClickable(true);
		}
	}

	public void setAllVisibility() {
		for (int i = 0; i < sortListView.getChildCount(); i++) {
			CheckBox checkBox0 = (CheckBox) sortListView.getChildAt(i)
					.findViewById(R.id.flag);
			checkBox0.setVisibility(View.VISIBLE);
		}
	}

	public void setAllInvisibility() {
		for (int i = 0; i < sortListView.getChildCount(); i++) {
			CheckBox checkBox0 = (CheckBox) sortListView.getChildAt(i)
					.findViewById(R.id.flag);
			checkBox0.setVisibility(View.INVISIBLE);
		}
	}

	public void setAllNo() {

		// 遍历list的长度，将MyAdapter中的map值全部设为true
		for (int i = 0; i < SourceDateList.size(); i++) {
			adapter.getIsSelected().put(i, false);
		}

		// 刷新listview和TextView的显示
		adapter.updateListView(SourceDateList);

	}

	public void setAllYes() {

		// 遍历list的长度，将MyAdapter中的map值全部设为true
		for (int i = 0; i < SourceDateList.size(); i++) {
			adapter.getIsSelected().put(i, true);
		}

		// 刷新listview和TextView的显示
		adapter.updateListView(SourceDateList);
	}

	public void deletePart() {
		HashMap<Integer, Boolean> isSelected0 = adapter.getIsSelected();
		Iterator iter = isSelected0.entrySet().iterator();
		if (isSelected0.size() > 1) {
			while (iter.hasNext()) {
				Entry entry = (Entry) iter.next();

				Boolean values = (Boolean) entry.getValue();
				Integer pos = (Integer) entry.getKey();
				if (values) {
					id_delete.add(pos);
				}
			}
		} else {
			Entry entry = (Entry) iter.next();

			Boolean values = (Boolean) entry.getValue();
			Integer pos = (Integer) entry.getKey();
			if (values) {
				id_delete.add(pos);
			}
		}

		for (int i = 0; i < id_delete.size(); i++) {
			String name = SourceDateList.get(id_delete.get(i)).getName();
			names_delete.add(name);
		}

		for (int i = 0; i < id_delete.size(); i++) {
			SourceDateList.remove(id_delete.get(i).shortValue());
		}
		adapter.updateListView(SourceDateList);
		// for (int i = 0; i < id_delete.size(); i++) {
		// SourceDateList.remove(id_delete.get(i));
		// }
		// adapter.updateListView(SourceDateList);
		//
		// Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		// ContentResolver resolver = MyTab2Activity.this.getContentResolver();
		// for (int i = 0; i < names_delete.size(); i++) {
		// String name = names_delete.get(i);
		//
		// resolver.delete(uri, "display_name=?", new String[] { name });
		// }

		Toast.makeText(MyTab2Activity.this, "删除选中联系人操作成功!", Toast.LENGTH_SHORT)
				.show();
	}

	private void initViews() {
		// ʵ����תƴ����
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		// �����Ҳഥ������
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// ����ĸ�״γ��ֵ�λ��
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position);
				}
			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);

		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ����Ҫ����adapter.getItem(position)����ȡ��ǰposition���Ӧ�Ķ���
				// String name = ((SortModel)
				// adapter.getItem(position)).getName();
				String name = ((TextView) view.findViewById(R.id.title))
						.getText().toString();
				if (flag == 0) {
					Toast.makeText(getApplication(), name, Toast.LENGTH_SHORT)
							.show();

					Intent intent = new Intent(MyTab2Activity.this,
							QueryPersonActivity.class);
					intent.putExtra("queryname", name);
					MyTab2Activity.this.startActivity(intent);
				}
			}
		});

		// ���Դ,���︳ֵ
		SourceDateList = filledData(names);

		// ���a-z��������Դ���
		Collections.sort(SourceDateList, pinyinComparator);
		adapter = new SortAdapter(this, SourceDateList);
		sortListView.setAdapter(adapter);

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		// ������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// ������������ֵΪ�գ�����Ϊԭ�����б?����Ϊ��������б�
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	/**
	 * ΪListView������
	 * 
	 * @param date
	 * @return
	 */
	private List<SortModel> filledData(List<String> date) {
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for (int i = 0; i < date.size(); i++) {
			SortModel sortModel = new SortModel();
			sortModel.setName(date.get(i));
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(date.get(i));
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				sortModel.setSortLetters(sortString.toUpperCase());
			} else {
				sortModel.setSortLetters("#");
			}
			sortModel.setFlag("false");
			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * ���������е�ֵ��������ݲ�����ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(String filterStr) {
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if (TextUtils.isEmpty(filterStr)) {
			filterDateList = SourceDateList;
		} else {
			filterDateList.clear();
			for (SortModel sortModel : SourceDateList) {
				String name = sortModel.getName();
				if (name.indexOf(filterStr.toString()) != -1
						|| characterParser.getSelling(name).startsWith(
								filterStr.toString())) {
					filterDateList.add(sortModel);
				}
			}
		}

		// ���a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_person2: {
			Intent intent = new Intent(MyTab2Activity.this,
					AddPersonActivity.class);
			intent.putExtra("tab1num", "");
			MyTab2Activity.this.startActivity(intent);
			break;
		}
		case R.id.cancledelete2: {
			flag = 0;
			checkFlag();
			setAllNo();
			break;
		}
		case R.id.okdelete2: {
			deletePart();
			adapter.updateListView(SourceDateList);
			flag = 0;
			checkFlag();
			setAllNo();
			// reFresh1();
			names_delete.clear();
			id_delete.clear();
			break;
		}

		default:
			break;
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public void onPopupButtonClick(View button) {
		// 创建PopupMenu对象
		popup = new PopupMenu(this, button);
		// 将R.menu.popup_menu菜单资源加载到popup菜单中
		getMenuInflater().inflate(R.menu.popup_menu2, popup.getMenu());

		// 为popup菜单的菜单项单击事件绑定事件监听器
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.exitmenu2: {
					// 隐藏该对话框
					popup.dismiss();
					break;
				}
				case R.id.deletepersonmenu2: {
					// 删除联系人
					flag = 1;
					checkFlag();
					break;
				}
				case R.id.deleteallpersonmenu2: {
					// 清空联系人
					setAllVisibility();
					setAllYes();
					mydialogface = (LinearLayout) getLayoutInflater().inflate(
							R.layout.my_dialog_face2, null);
					new AlertDialog.Builder(MyTab2Activity.this)
							.setIcon(R.drawable.person3)
							.setTitle("  提  示   ")
							.setView(mydialogface)
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											Uri uri = Uri
													.parse("content://com.android.contacts/raw_contacts");
											getContentResolver().delete(uri,
													"_id!=-1", null);
											SourceDateList.clear();
											adapter.updateListView(SourceDateList);
										}
									})
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											setAllNo();
											flag = 0;
											checkFlag();
										};
									}).create().show();
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (AllOverData.getAction().equals("add")) {
			reFresh2();
		}

	}

	public void reFresh2() {
		List<String> temp = new ArrayList<String>();

		for (int i = 0; i < names.size(); i++) {
			temp.add(names.get(i));
		}
		temp.add(AllOverData.getName());
		List<SortModel> SourceDateList0 = filledData(temp);
		adapter.updateListView(SourceDateList0);
	}
}
