package com.helpfulanimals.happeetime;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class IntakeDataEntryFragment extends Fragment {
	private static final String TAG = "IntakeDataEntryFragment";
	private Button mDateButton;
	private Button mTypeButton;
	private Button mWhatButton;
	private Button mAmountButton;
	private ImageView mIcon;
	private Drawable icon;
	private static final String DIALOG_TIME ="time";
	private static final String DIALOG_TYPE="type";
	public static final String DIALOG_WHAT="what";
	public static final String EXTRA_ITEM ="item";
	public static final int REQUEST_DATE = 0;
	public static final int REQUEST_TYPE=1;
	public static final int REQUEST_WHAT=2;
	public static final int REQUEST_AMOUNT=3;
	private LogItem item;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		item = LogItemStore.sharedStore(getActivity()).createItem();
	}
	public static IntakeDataEntryFragment newInstance(int position){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_ITEM, position);
		IntakeDataEntryFragment fragment = new IntakeDataEntryFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		
		if (resultCode!=Activity.RESULT_OK) return;
		if(requestCode == REQUEST_DATE){
			if ((Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME)!=null){
			Date date = (Date)data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
			String timeFormat = new String("h:mm aa");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			String chosenTime = (String)DateFormat.format(timeFormat, calendar);
			mDateButton.setText(chosenTime);
			item.setTimeDateCreated(date);
			}
			else
				mDateButton.setText("");
		}
		else if (requestCode==REQUEST_TYPE){
			if(data.getSerializableExtra(TypePickerDialogFragment.EXTRA_TYPE)!=null){
			String type = (String)data.getSerializableExtra(TypePickerDialogFragment.EXTRA_TYPE);
			mTypeButton.setText(type);
			if(type.compareTo("Food")==0){
				mAmountButton.setText("Nothing to enter");
				mWhatButton.setText("What was it?");
			}
			else if(type.compareTo("Fiber")==0){
				mAmountButton.setText("Pick an amount");
				mWhatButton.setText("Fiber");
			}
			else if(type.compareTo("Drink")==0){
				mAmountButton.setText("Pick an amount");
				mWhatButton.setText("What was it?");
			}
			item.setTypeIntake(type);
			item.setWhatIntake("");
			item.setAmountIntake(0);
			}
		}
		else if (requestCode==REQUEST_WHAT){
			if(data.getSerializableExtra(WhatPickerDialogFragment.EXTRA_WHAT)!=null){
			String what = (String)data.getSerializableExtra(WhatPickerDialogFragment.EXTRA_WHAT);
			mWhatButton.setText(what);
			item.setWhatIntake(what);
			}
		}
		else if (requestCode==REQUEST_AMOUNT){
			if(data.getSerializableExtra(AmountPickerDialogFragment.EXTRA_AMOUNT)!=null){
			Integer Amount = (Integer)data.getSerializableExtra(AmountPickerDialogFragment.EXTRA_AMOUNT);
			Log.d(TAG, Amount.toString());
			mAmountButton.setText(Amount.toString());
			item.setAmountIntake(Amount.intValue());
			
			}
		}
			
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.data_entry_fragment, parent, false);
		mDateButton = (Button)v.findViewById(R.id.date_button);
		mDateButton.setText("Pick a Time");
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm=getActivity().getSupportFragmentManager();
				TimePickerFragment dialog = TimePickerFragment.newInstance(new Date());
				dialog.setTargetFragment(IntakeDataEntryFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_TIME);
			}
		});
		mTypeButton = (Button)v.findViewById(R.id.type_button);
		mTypeButton.setText("Pick a Type");
		mTypeButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				TypePickerDialogFragment dialog = TypePickerDialogFragment.newInstance();
				dialog.setTargetFragment(IntakeDataEntryFragment.this, REQUEST_TYPE);
				
				dialog.show(fm, DIALOG_TYPE);
			}
		});
		mWhatButton = (Button)v.findViewById(R.id.what_button);
		mWhatButton.setText("What was it?");
		mWhatButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				WhatPickerDialogFragment dialog = WhatPickerDialogFragment.newInstance();
				dialog.setTargetFragment(IntakeDataEntryFragment.this, REQUEST_WHAT);
				int setWhat=0;
				Bundle bundle = new Bundle();
				if(mTypeButton.getText().toString().compareTo("Food")==0)
					setWhat = 1;
				else if (mTypeButton.getText().toString().compareTo("Drink")==0)
					setWhat=2;
				if (setWhat == 0)
					return;
				bundle.putInt(DIALOG_WHAT, setWhat);
				dialog.setArguments(bundle);
				dialog.show(fm, null);
				
			}
		});
		mAmountButton = (Button)v.findViewById(R.id.amount_button);
		mAmountButton.setText("Pick an Amount");
		mAmountButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				AmountPickerDialogFragment dialog = AmountPickerDialogFragment.newInstance();
				dialog.setTargetFragment(IntakeDataEntryFragment.this, REQUEST_AMOUNT);
				int setWhat=0;
				Bundle bundle = new Bundle();
				if(mTypeButton.getText().toString().compareTo("Fiber")==0)
					setWhat = 1;
				else if (mTypeButton.getText().toString().compareTo("Drink")==0)
					setWhat=2;
				if (setWhat == 0)
					return;
				bundle.putInt(DIALOG_WHAT, setWhat);
				dialog.setArguments(bundle);
				dialog.show(fm, DIALOG_TYPE);
			}
		});
		mIcon = (ImageView)v.findViewById(R.id.imageView1);
		int random = (new Random().nextInt(4));
		if((random%3)==0){
			if(random == 0)
				icon = getResources().getDrawable(R.drawable.explosion_phone);
			else
				icon = getResources().getDrawable(R.drawable.awards_case);
		}
		else if(random%3==1)
			icon = getResources().getDrawable(R.drawable.flush_phone);
		else if(random%3 ==2)
			icon = getResources().getDrawable(R.drawable.thumbs_up);
		mIcon.setImageDrawable(icon);
		return v;
	}
	public void onPause(){
		super.onPause();
		int count =0;
		ArrayList<LogItem> logItems = LogItemStore.sharedStore(getActivity()).getLogItems();
		LogItem logItem = new LogItem();
		if(item.getTypeIntake().compareTo("Fiber")==0){
			for (int i =0; i<logItems.size();i++){
				logItem = logItems.get(i);
				if (logItem.getTypeIntake().compareTo("Fiber")==0){
					count += logItem.getAmountIntake();
				}
			}
			if(count >25 && LogItemStore.sharedStore(getActivity()).getLogItemContainer().isAlreadySetFiberAward()!=true){
				LogItemStore.sharedStore(getActivity()).getLogItemContainer().setAlreadySetFiberAward(true);
				item.setInfoType(1);
			}
		}
	
		else if(item.getTypeIntake().compareTo("Drink")==0){
			for (int i =0; i<logItems.size();i++){
				logItem = logItems.get(i);
				if (logItem.getTypeIntake().compareTo("Drink")==0){
					count += logItem.getAmountIntake();
				}
			}
			
			if(count >40 && LogItemStore.sharedStore(getActivity()).getLogItemContainer().isAlreadySetFluidAward()!=true){
				LogItemStore.sharedStore(getActivity()).getLogItemContainer().setAlreadySetFluidAward(true);
				item.setInfoType(2);
			}
		}
	}
	
}
