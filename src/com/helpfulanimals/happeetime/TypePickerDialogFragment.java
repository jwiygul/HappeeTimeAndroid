package com.helpfulanimals.happeetime;

import java.util.Calendar;
import java.util.Date;







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

public class TypePickerDialogFragment extends DialogFragment {
	public static final String EXTRA_TYPE = "com.helpfulanimal.happeetime.type";
	private String typeIntake;
	private int What;
	private void sendResult(int resultCode){
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_TYPE, typeIntake);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	public static TypePickerDialogFragment newInstance(){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_TYPE, null);
		TypePickerDialogFragment fragment = new TypePickerDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		View v = getActivity().getLayoutInflater().inflate(R.layout.number_picker_dialog, null);
		NumberPicker n = (NumberPicker)v.findViewById(R.id.dialog_type_picker);
		String[] a={" ","Food","Drink","Fiber"};
		String[] b = {" ","Pee", "Poop", "Pee Accident", "Poop Accident"};
		What = this.getArguments().getInt(OutputDataEntryFragment.OUTPUT_TYPE);
		  n.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	        if (What == 3){
	        n.setDisplayedValues(b);
	        n.setMaxValue(4);
	        }
	        else{
        n.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        n.setDisplayedValues(a);
        n.setMaxValue(3);
        //n.setMinValue(0);
	        }
        n.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				if (What ==3){
					switch(newVal){
					case 1:
						typeIntake = new String("Pee");
						break;
					case 2:
						typeIntake = new String("Poop");
						break;
					case 3:
						typeIntake = new String("Pee Accident");
						break;
					case 4:
						typeIntake = new String("Poop Accident");
						break;
					default:
						break;
					}
				}
				else{
				switch(newVal){
				case 1:
					typeIntake = new String("Food");
					break;
				case 2:
					typeIntake = new String("Drink");
					break;
				case 3:
					typeIntake = new String ("Fiber");
					break;
				default:
					break;
				}
			}
			}
		});
        if(What==3){
        return new AlertDialog.Builder(getActivity()).setView(v)
				.setTitle(R.string.output_type_picker)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				}).create();
        }
        else{
        	return new AlertDialog.Builder(getActivity()).setView(v)
    				.setTitle(R.string.type_picker)
    				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
    					
    					@Override
    					public void onClick(DialogInterface dialog, int which) {
    						sendResult(Activity.RESULT_OK);
    					}
    				}).create();
        }
	}
}
