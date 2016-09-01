package com.smashdishes.game;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	public static LinkedList<Activity> activities = new LinkedList<Activity>();

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		activities.add(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		activities.remove(this);
	}

	public static void finishAll() {
		for (Activity activity : activities) {
			activity.finish();
		}

		activities.clear();
	}

	public static void exit() {
		finishAll();
		System.exit(0);
	}
}