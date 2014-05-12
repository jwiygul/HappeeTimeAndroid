package com.helpfulanimals.happeetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment{
	public static final String EXTRA_TIME = "com.helpfulanimals.happeetime.time";
	
	private Date mDate;
	private void sendResult(int resultCode){
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_TIME, mDate);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	public static TimePickerFragment newInstance(Date date){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_TIME, date);
		TimePickerFragment fragment = new TimePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_TIME);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
	
		
		//View v= getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);
		TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(mDate);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
				mDate = new GregorianCalendar(year, month, day, hourOfDay, minute).getTime();
				getArguments().putSerializable(EXTRA_TIME, mDate);
				
				sendResult(Activity.RESULT_OK);
				
			}
		};
		
		return new TimePickerDialog(getActivity(),listener,hour, minute,false);
	}

}
