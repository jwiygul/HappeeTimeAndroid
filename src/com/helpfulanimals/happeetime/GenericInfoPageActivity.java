package com.helpfulanimals.happeetime;

import android.support.v4.app.Fragment;

public class GenericInfoPageActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		int position = (Integer) getIntent().getSerializableExtra(HappeeTimeTabActivity.EXTRA_INFO_PAGE_POSITION);
		return GenericInfoPageFragment.newInstance(position);
	}

}
