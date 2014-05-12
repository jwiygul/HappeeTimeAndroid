package com.helpfulanimals.happeetime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class OutputDataEntryFragment extends Fragment {
	private static final String TAG = "OutputDataEntryFragment";
	private Button mDateButton;
	private Button mTypeButton;
	private Button mWhatButton;
	private Button mAmountButton;
	private ImageView mIcon;
	private Drawable icon;
	private static final String DIALOG_TIME ="time";
	private static final String DIALOG_TYPE="type";
	private static final String DIALOG_AMOUNT="amount";
	public static final String OUTPUT_TYPE = "outputType";
	public static final String EXTRA_ITEM ="item";
	public static final int REQUEST_DATE = 0;
	public static final int REQUEST_TYPE=1;
	public static final int REQUEST_AMOUNT=2;
	private OutputLogItem item;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		item = OutputLogItemStore.sharedStore(getActivity()).createItem();
	}
	public static OutputDataEntryFragment newInstance(){
		OutputDataEntryFragment fragment = new OutputDataEntryFragment();
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
			item.setTypeIntake(type);
			if(type.compareTo("Poop Accident")==0){
				mAmountButton.setText("Nothing to Enter");
			}
			else
				mAmountButton.setText("Pick an Amount/Quality");
			}
		}
		
		else if (requestCode==REQUEST_AMOUNT){
			if(data.getSerializableExtra(AmountPickerDialogFragment.EXTRA_AMOUNT)!=null){
			String Amount = (String)data.getSerializableExtra(AmountPickerDialogFragment.EXTRA_AMOUNT);
			mAmountButton.setText(Amount.toString());
			item.setAmountIntake(Amount);
			}
		}
			
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.output_data_entry_fragment, parent, false);
		mDateButton = (Button)v.findViewById(R.id.date_button);
		mDateButton.setText("Pick a Time");
		mDateButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm=getActivity().getSupportFragmentManager();
				TimePickerFragment dialog = TimePickerFragment.newInstance(new Date());
				dialog.setTargetFragment(OutputDataEntryFragment.this, REQUEST_DATE);
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
				dialog.setTargetFragment(OutputDataEntryFragment.this, REQUEST_TYPE);
				Bundle bundle = new Bundle();
				bundle.putInt(OUTPUT_TYPE, 3);
				dialog.setArguments(bundle);
				dialog.show(fm, null);
			}
		});
		
		mAmountButton = (Button)v.findViewById(R.id.amount_button);
		mAmountButton.setText("Pick an Amount/Quality");
		mAmountButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				AmountPickerDialogFragment dialog = AmountPickerDialogFragment.newInstance();
				dialog.setTargetFragment(OutputDataEntryFragment.this, REQUEST_AMOUNT);
				Bundle bundle = new Bundle();
				if(mTypeButton.getText().toString().compareTo("Pee")==0)
					bundle.putInt(OUTPUT_TYPE, 3);
				else if(mTypeButton.getText().toString().compareTo("Poop")==0)
					bundle.putInt(OUTPUT_TYPE, 4);
				else if(mTypeButton.getText().toString().compareTo("Pee Accident")==0)
					bundle.putInt(OUTPUT_TYPE, 5);
				else
					return;
					
				dialog.setArguments(bundle);
				dialog.show(fm, null);
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

		if(item.getTypeIntake().compareTo("Poop Accident")!=0){
		int count =OutputLogItemStore.sharedStore(getActivity()).getBigContainer().size();
		OutputLogItemContainer container = OutputLogItemStore.sharedStore(getActivity()).getBigContainer().get(count-1);
			if(item.getTypeIntake().compareTo("Pee Accident")==0){
				OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().setHadPeeAccident(true);
			}
			else if(item.getTypeIntake().compareTo("Poop")==0&& !OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().isAlreadySetPoopAward())
			{
				OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().setDidPoop(true);
				for (int i =(OutputLogItemStore.sharedStore(getActivity()).getBigContainer().size()-1); i>-1;i--)
				{
					OutputLogItemContainer newContainer = OutputLogItemStore.sharedStore(getActivity()).getBigContainer().get(i);
					if(!newContainer.getDidPoop() || newContainer.isAlreadySetPoopAward())
						break;
					container = newContainer;
				}
				Date currentDate = OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().getDateCreated();
				double timeDifference = (double)((currentDate.getTime()-container.getDateCreated().getTime()))/86400000;
				Log.d(TAG, Double.toString(timeDifference));
				if(timeDifference > 4.0){
					OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().setAlreadySetPoopAward(true);
					item.setInfoType(1);
				}
			}
			else if(item.getTypeIntake().compareTo("Pee")==0&&!OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().isAlreadySetPeeAward())
			{
					for (int i =(OutputLogItemStore.sharedStore(getActivity()).getBigContainer().size()-1); i>-1;i--)
					{
						OutputLogItemContainer newContainer = OutputLogItemStore.sharedStore(getActivity()).getBigContainer().get(i);
						if(newContainer.isAlreadySetPeeAward() || newContainer.hadPeeAccident())
							break;
						container = newContainer;
					}
					Date currentDate = OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().getDateCreated();
					double timeDifference = (double)((currentDate.getTime()-container.getDateCreated().getTime()))/86400000;
					
					if(timeDifference > 3.0){
						OutputLogItemStore.sharedStore(getActivity()).getLogItemContainer().setAlreadySetPeeAward(true);
						item.setInfoType(2);
					}
				
			}
		}
	}
	
}
