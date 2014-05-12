package com.helpfulanimals.happeetime;

import android.support.v4.app.Fragment;

public class OutputAwardsInfoActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		int awardType = (Integer) getIntent().getSerializableExtra(HappeeTimeTabActivity.EXTRA_POSITION);
		return OutputAwardsInfoFragment.newInstance(awardType);
	}

}
