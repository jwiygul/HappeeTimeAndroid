package com.helpfulanimals.happeetime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

public class WhatPickerDialogFragment extends DialogFragment {
	private static final String TAG ="WhatPickerDialogFragment";
	private static int What;
	public static final String EXTRA_WHAT = "com.helpfulanimal.happeetime.what";
	private String whatIntake;
	private void sendResult(int resultCode){
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_WHAT, whatIntake);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	public static WhatPickerDialogFragment newInstance(){
		Bundle args = new Bundle();
		WhatPickerDialogFragment fragment = new WhatPickerDialogFragment();
		fragment.getArguments();
		return fragment;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.number_picker_dialog, null);
		NumberPicker n = (NumberPicker)v.findViewById(R.id.dialog_type_picker);
		What = this.getArguments().getInt(IntakeDataEntryFragment.DIALOG_WHAT);
        n.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        if (What ==2){
        	String[] b = {" ","Water","Juice","Cola","Milk","Tea/Coffee","Other"};
        n.setDisplayedValues(b);
        n.setMaxValue(6);
        }
        else{
        	String[] a={" ","Meat/Fish","Fruits/Veggies","Pasta/Bread/Cereal", "Cheese/Yogurt", "Other"};
    	n.setDisplayedValues(a);
    	n.setMaxValue(5);
    }
        
        //n.setMinValue(0);
        n.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				if(What == 1){
				switch(newVal){
				
				case 1:
					whatIntake = new String("Meat/Fish");
					break;
				case 2:
					whatIntake = new String("Fruits/Veggies");
					break;
				case 3:
					whatIntake = new String ("Pasta/Bread/Cereal");
					break;
				case 4:
					whatIntake = new String("Cheese/Yogurt");
					break;
				case 5:
					whatIntake = new String("Other");
					break;
				default:
					break;
				}
				}
				else
					switch(newVal){
					case 1:
						whatIntake = new String("Water");
						break;
					case 2:
						whatIntake = new String("Juice");
						break;
					case 3:
						whatIntake = new String ("Cola");
						break;
					case 4:
						whatIntake = new String("Milk");
						break;
					case 5:
						whatIntake = new String("Tea/Coffee");
						break;
					case 6:
						whatIntake = new String("Other");
						break;
					default:
						break;
					
			}
			}
		});
        return new AlertDialog.Builder(getActivity()).setView(v)
				.setTitle(R.string.what_picker)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				}).create();
	}
}
