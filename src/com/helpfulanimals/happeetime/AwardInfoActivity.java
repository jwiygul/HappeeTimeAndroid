package com.helpfulanimals.happeetime;
import android.support.v4.app.Fragment;
import android.util.Log;


public class AwardInfoActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		int awardType = (Integer) getIntent().getSerializableExtra(HappeeTimeTabActivity.EXTRA_POSITION);
		return AwardInfoFragment.newInstance(awardType);
	}

}
