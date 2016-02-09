package com.example.mycall0;

import com.example.mycall0.tab4.MyWeatherActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MyTab4Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_tab4);

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.check_weather: {
			Intent intent = new Intent(MyTab4Activity.this,
					MyWeatherActivity.class);
			MyTab4Activity.this.startActivity(intent);
			break;
		}
		case R.id.check_location: {
			Intent intent = new Intent(MyTab4Activity.this,
					MyLocationActivity.class);
			MyTab4Activity.this.startActivity(intent);
			break;
		}
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_tab4, menu);
		return true;
	}

}
