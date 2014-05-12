package com.helpfulanimals.happeetime;

import android.support.v4.app.Fragment;

public class IntakeDataEntryActivity extends SingleFragmentActivity implements AmountPickerDialogFragment.Callbacks{
	
	@Override
	protected Fragment createFragment() {
		return IntakeDataEntryFragment.newInstance(1);
	}

	@Override
	public void onButtonPushed() {
		// TODO Auto-generated method stub
		
	}

}
