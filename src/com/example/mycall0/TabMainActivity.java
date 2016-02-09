package com.example.mycall0;

import java.util.ArrayList;
import java.util.List;

import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabMainActivity extends TabActivity {

	// ҳ������
	private ViewPager mPager;
	// Tabҳ���б�
	private List<View> listViews;
	private Button mytab4;
	// ��ǰҳ�����
	private LocalActivityManager manager = null;
	private final Context context = TabMainActivity.this;
	private TabHost mTabHost;
	private int count = 1;

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			count++;

			if (count == 3) {
				TabMainActivity.this.finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tab_main);
		mytab4 = (Button) TabMainActivity.this.findViewById(R.id.mytab4);

		mTabHost = getTabHost();

		mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator("拨号")
				.setContent(new Intent(this, MyTab1Activity.class)));
		TextView textView1 = (TextView) mTabHost.getTabWidget().getChildAt(0)
				.findViewById(android.R.id.title);
		textView1.setTextSize(16);
		textView1.setTextColor(Color.GREEN);
		mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("联系人")
				.setContent(new Intent(this, MyTab2Activity.class)));
		TextView textView2 = (TextView) mTabHost.getTabWidget().getChildAt(1)
				.findViewById(android.R.id.title);
		textView2.setTextSize(16);
		textView2.setTextColor(Color.WHITE);
		mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator("消息")
				.setContent(new Intent(this, MyTab3Activity.class)));
		TextView textView3 = (TextView) mTabHost.getTabWidget().getChildAt(2)
				.findViewById(android.R.id.title);
		textView3.setTextSize(16);
		textView3.setTextColor(Color.WHITE);
		mytab4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TabMainActivity.this,
						MyTab4Activity.class);
				TabMainActivity.this.startActivity(intent);
			}
		});
		// mTabHost.addTab(mTabHost
		// .newTabSpec("3")
		// .setIndicator(null,
		// getResources().getDrawable(R.drawable.person1))
		// .setContent(new Intent(this, MyTab4Activity.class)));
		// mTabHost.setCurrentTab(0);
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				mPager.setCurrentItem(Integer.parseInt(tabId));
				switch (Integer.parseInt(tabId)) {
				case 0: {
					TextView textView1 = (TextView) mTabHost.getTabWidget()
							.getChildAt(0).findViewById(android.R.id.title);
					TextView textView2 = (TextView) mTabHost.getTabWidget()
							.getChildAt(1).findViewById(android.R.id.title);
					TextView textView3 = (TextView) mTabHost.getTabWidget()
							.getChildAt(2).findViewById(android.R.id.title);
					textView1.setTextColor(Color.GREEN);
					textView2.setTextColor(Color.WHITE);
					textView3.setTextColor(Color.WHITE);
					break;
				}
				case 1: {
					TextView textView1 = (TextView) mTabHost.getTabWidget()
							.getChildAt(0).findViewById(android.R.id.title);
					TextView textView2 = (TextView) mTabHost.getTabWidget()
							.getChildAt(1).findViewById(android.R.id.title);
					TextView textView3 = (TextView) mTabHost.getTabWidget()
							.getChildAt(2).findViewById(android.R.id.title);
					textView1.setTextColor(Color.WHITE);
					textView2.setTextColor(Color.GREEN);
					textView3.setTextColor(Color.WHITE);
					break;
				}
				case 2: {

					TextView textView1 = (TextView) mTabHost.getTabWidget()
							.getChildAt(0).findViewById(android.R.id.title);
					TextView textView2 = (TextView) mTabHost.getTabWidget()
							.getChildAt(1).findViewById(android.R.id.title);
					TextView textView3 = (TextView) mTabHost.getTabWidget()
							.getChildAt(2).findViewById(android.R.id.title);
					textView1.setTextColor(Color.WHITE);
					textView2.setTextColor(Color.WHITE);
					textView3.setTextColor(Color.GREEN);
					break;
				}

				default:
					break;
				}

			}
		});
		// tabhost�ı�ͬ��ı�ViewPager������
		// mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
		//
		// @Override
		// public void onTabChanged(String tabId) {
		// mPager.setCurrentItem(Integer.parseInt(tabId));
		// }
		// });

		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);

		InitViewPager();
	}

	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		MyPagerAdapter mpAdapter = new MyPagerAdapter(listViews);
		Intent intent = new Intent(context, MyTab1Activity.class);
		listViews.add(getView("A", intent));
		Intent intent2 = new Intent(context, MyTab2Activity.class);
		listViews.add(getView("B", intent2));
		Intent intent3 = new Intent(context, MyTab3Activity.class);
		listViews.add(getView("C", intent3));
		// Intent intent4 = new Intent(context, MyTab4Activity.class);
		// listViews.add(getView("D", intent4));
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
			// case 3:
			// mTabHost.setCurrentTab(3);
			// break;
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
}