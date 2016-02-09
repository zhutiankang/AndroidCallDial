package com.example.mycall0.tab1;

import java.util.ArrayList;
import java.util.List;

import com.example.mycall0.MyTab1Activity;
import com.example.mycall0.MyTab2Activity;
import com.example.mycall0.MyTab3Activity;
import com.example.mycall0.MyTab4Activity;
import com.example.mycall0.R;
import com.example.mycall0.R.layout;
import com.example.mycall0.R.menu;
import com.example.mycall0.TabMainActivity.MyOnPageChangeListener;
import com.example.mycall0.TabMainActivity.MyPagerAdapter;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

public class CallRecordActivity extends TabActivity {

	// ҳ������
	private ViewPager mPager;
	// Tabҳ���б�
	private List<View> listViews;
	// ��ǰҳ�����
	private LocalActivityManager manager = null;
	private final Context context = CallRecordActivity.this;
	private TabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_call_record);

		mTabHost = getTabHost();

		mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator("所有通话记录")
				.setContent(new Intent(this, ShowCallRecordActivity.class)));
		TextView textView1 = (TextView) mTabHost.getTabWidget().getChildAt(0)
				.findViewById(android.R.id.title);
		textView1.setTextSize(16);
		textView1.setTextColor(Color.GREEN);
		mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("未接电话")
				.setContent(new Intent(this, ShowMissCallActivity.class)));
		TextView textView2 = (TextView) mTabHost.getTabWidget().getChildAt(1)
				.findViewById(android.R.id.title);
		textView2.setTextSize(16);
		textView2.setTextColor(Color.WHITE);
		mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator("已接来电")
				.setContent(new Intent(this, ShowIncommingActivity.class)));
		TextView textView3 = (TextView) mTabHost.getTabWidget().getChildAt(2)
				.findViewById(android.R.id.title);
		textView3.setTextSize(16);
		textView3.setTextColor(Color.WHITE);
		mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator("已拨电话")
				.setContent(new Intent(this, ShowOutgoingActivity.class)));
		TextView textView4 = (TextView) mTabHost.getTabWidget().getChildAt(3)
				.findViewById(android.R.id.title);
		textView4.setTextSize(16);
		textView4.setTextColor(Color.WHITE);
		mTabHost.setCurrentTab(0);
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				mPager.setCurrentItem(Integer.parseInt(tabId));
				switch (Integer.parseInt(tabId)) {
				case 0: {
					System.out.println("22");
					TextView textView1 = (TextView) mTabHost.getTabWidget()
							.getChildAt(0).findViewById(android.R.id.title);
					TextView textView2 = (TextView) mTabHost.getTabWidget()
							.getChildAt(1).findViewById(android.R.id.title);
					TextView textView3 = (TextView) mTabHost.getTabWidget()
							.getChildAt(2).findViewById(android.R.id.title);
					TextView textView4 = (TextView) mTabHost.getTabWidget()
							.getChildAt(3).findViewById(android.R.id.title);
					textView1.setTextColor(Color.GREEN);
					textView2.setTextColor(Color.WHITE);
					textView3.setTextColor(Color.WHITE);
					textView4.setTextColor(Color.WHITE);
					break;
				}
				case 1: {
					TextView textView1 = (TextView) mTabHost.getTabWidget()
							.getChildAt(0).findViewById(android.R.id.title);
					TextView textView2 = (TextView) mTabHost.getTabWidget()
							.getChildAt(1).findViewById(android.R.id.title);
					TextView textView3 = (TextView) mTabHost.getTabWidget()
							.getChildAt(2).findViewById(android.R.id.title);
					TextView textView4 = (TextView) mTabHost.getTabWidget()
							.getChildAt(3).findViewById(android.R.id.title);
					textView1.setTextColor(Color.WHITE);
					textView2.setTextColor(Color.GREEN);
					textView3.setTextColor(Color.WHITE);
					textView4.setTextColor(Color.WHITE);
					break;
				}
				case 2: {

					TextView textView1 = (TextView) mTabHost.getTabWidget()
							.getChildAt(0).findViewById(android.R.id.title);
					TextView textView2 = (TextView) mTabHost.getTabWidget()
							.getChildAt(1).findViewById(android.R.id.title);
					TextView textView3 = (TextView) mTabHost.getTabWidget()
							.getChildAt(2).findViewById(android.R.id.title);
					TextView textView4 = (TextView) mTabHost.getTabWidget()
							.getChildAt(3).findViewById(android.R.id.title);
					textView1.setTextColor(Color.WHITE);
					textView2.setTextColor(Color.WHITE);
					textView3.setTextColor(Color.GREEN);
					textView4.setTextColor(Color.WHITE);
					break;
				}
				case 3: {

					TextView textView1 = (TextView) mTabHost.getTabWidget()
							.getChildAt(0).findViewById(android.R.id.title);
					TextView textView2 = (TextView) mTabHost.getTabWidget()
							.getChildAt(1).findViewById(android.R.id.title);
					TextView textView3 = (TextView) mTabHost.getTabWidget()
							.getChildAt(2).findViewById(android.R.id.title);
					TextView textView4 = (TextView) mTabHost.getTabWidget()
							.getChildAt(3).findViewById(android.R.id.title);
					textView1.setTextColor(Color.WHITE);
					textView2.setTextColor(Color.WHITE);
					textView4.setTextColor(Color.GREEN);
					textView3.setTextColor(Color.WHITE);
					break;
				}

				default:
					break;
				}

			}
		});

		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		InitViewPager();
	}

	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager_record);
		listViews = new ArrayList<View>();
		MyPagerAdapter mpAdapter = new MyPagerAdapter(listViews);
		Intent intent = new Intent(context, ShowCallRecordActivity.class);
		listViews.add(getView("A", intent));
		Intent intent2 = new Intent(context, ShowMissCallActivity.class);
		listViews.add(getView("B", intent2));
		Intent intent3 = new Intent(context, ShowIncommingActivity.class);
		listViews.add(getView("C", intent3));
		Intent intent4 = new Intent(context, ShowOutgoingActivity.class);
		listViews.add(getView("D", intent4));
		mPager.setAdapter(mpAdapter);
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				mTabHost.setCurrentTab(0);
				break;
			case 1:
				mTabHost.setCurrentTab(1);
				break;
			case 2:
				mTabHost.setCurrentTab(2);
				break;
			case 3:
				mTabHost.setCurrentTab(3);
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.call_record, menu);
		return true;
	}

}
