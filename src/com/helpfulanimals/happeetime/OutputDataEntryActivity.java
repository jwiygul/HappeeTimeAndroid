package com.helpfulanimals.happeetime;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class OutputDataEntryActivity extends SingleFragmentActivity implements AmountPickerDialogFragment.Callbacks {

	@Override
	protected Fragment createFragment() {
		return OutputDataEntryFragment.newInstance();
	}

	@Override
	public void onButtonPushed() {
		Intent intent = new Intent(this, BristolChartActivity.class);
		startActivity(intent);		
	}

}
