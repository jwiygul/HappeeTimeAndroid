package com.helpfulanimals.happeetime;

import android.support.v4.app.Fragment;

public class BristolChartActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new BristolChartFragment();
	}

}
