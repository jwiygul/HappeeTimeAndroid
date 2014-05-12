package com.helpfulanimals.happeetime;
import com.helpfulanimals.happeetime.R;
import com.helpfulanimals.happeetime.IntakePageFragment.Callbacks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;


public class AmountPickerDialogFragment extends DialogFragment {

	public static final String EXTRA_AMOUNT = "com.helpfulanimal.happeetime.amount";
	private Integer amountIntake;
	private String amountOutput;
	private int What;
	Callbacks mCallbacks;
	public interface Callbacks{
		 void onButtonPushed(); 
	}
	private void sendResult(int resultCode){
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		if(amountIntake !=null)
			i.putExtra(EXTRA_AMOUNT, amountIntake);
		else
			i.putExtra(EXTRA_AMOUNT, amountOutput);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
	public static AmountPickerDialogFragment newInstance(){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_AMOUNT, null);
		AmountPickerDialogFragment fragment = new AmountPickerDialogFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		 mCallbacks = (Callbacks)activity;
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int whatNow = 0;
		whatNow = this.getArguments().getInt(OutputDataEntryFragment.OUTPUT_TYPE);
		NumberPicker n = new NumberPicker(getActivity());
		View v = new View(getActivity());
		if(whatNow!=0){
			What = whatNow;
			if(whatNow==4){
			 v=getActivity().getLayoutInflater().inflate(R.layout.output_amount_picker, null);
			 n = (NumberPicker)v.findViewById(R.id.dialog_type_picker);
				/*ImageView mIcon = (ImageView)v.findViewById(R.id.poop_Image);
				Drawable icon = getResources().getDrawable(R.drawable.bristol_stool_chart);
				mIcon.setImageDrawable(icon);*/
			Button mAmountButton = (Button)v.findViewById(R.id.amount_button);
				mAmountButton.setText("See Bristol Chart");
				mAmountButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						mCallbacks.onButtonPushed();
					}
				});
			}
			 else{
				 v = getActivity().getLayoutInflater().inflate(R.layout.number_picker_dialog, null);
					n = (NumberPicker)v.findViewById(R.id.dialog_type_picker);
			}
		}
		else{
			What = this.getArguments().getInt(IntakeDataEntryFragment.DIALOG_WHAT);
			 v = getActivity().getLayoutInflater().inflate(R.layout.number_picker_dialog, null);
			n = (NumberPicker)v.findViewById(R.id.dialog_type_picker);
		}
		
		
		String[] a={" ","5 oz","10 oz","15 oz", "20 oz", "25 oz", "30 oz", "35 oz", "40 oz", "45 oz", "50 oz"};
		String[] b={" ","1 gm", "2 gm", "3 gm", "4 gm", "5 gm", "10 gm", "15 gm", "20 gm", "25 gm"};
		String[] c ={" ", "a few drops", "medium amount", "large amount"};
		String[] d={" ", "Type 1", "Type 2", "Type 3", "Type 4", "Type 5", "Type 6", "Type 7"};
		String[] e={" ", "spotted underwear", "soaked underwear", "soaked pants"};
		
		
        n.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        if (What == 2){
        n.setDisplayedValues(a);
        n.setMaxValue(10);
        }
        else if(What == 3){
        	n.setDisplayedValues(c);
        	n.setMaxValue(3);
        }
        else if(What==4){
        	n.setDisplayedValues(d);
        	n.setMaxValue(7);
        }
        else if(What==5){
        	n.setDisplayedValues(e);
        	n.setMaxValue(3);
        }
        else{
        	n.setDisplayedValues(b);
        	n.setMaxValue(9);
        }
        
        //n.setMinValue(0);
        n.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				if(What ==2){
				switch(newVal){
				case 1:
					amountIntake = 5;
					break;
				case 2:
					amountIntake = 10;
					break;
				case 3:
					amountIntake = 15;
					break;
				case 4:
					amountIntake = 20;
					break;
				case 5:
					amountIntake = 25;
					break;
				case 6:
					amountIntake = 30;
					break;
				case 7:
					amountIntake = 35;
					break;
				case 8:
					amountIntake = 40;
					break;
				case 9:
					amountIntake = 45;
					break;
				case 10:
					amountIntake = 50;
					break;
				default:
					break;
				}
				}
				else if(What==1)
				switch(newVal){
				case 1:
					amountIntake = 1;
					break;
				case 2:
					amountIntake = 2;
					break;
				case 3:
					amountIntake = 3;
					break;
				case 4:
					amountIntake = 4;
					break;
				case 5:
					amountIntake = 5;
					break;
				case 6:
					amountIntake = 10;
					break;
				case 7:
					amountIntake = 15;
					break;
				case 8:
					amountIntake = 20;
					break;
				case 9:
					amountIntake = 25;
					break;
				
				default:
					break;
				}
				else if(What==3){
					switch(newVal){
					case 1:
						amountOutput = new String("a few drops");
						break;
					case 2:
						amountOutput = new String("medium amount");
						break;
					case 3:
						amountOutput = new String("large amount");
						break;
					default:
						break;
					}
				}
				else if (What ==4){
					switch(newVal){
					case 1:
						amountOutput = new String("Type 1");
						break;
					case 2:
						amountOutput = new String("Type 2");
						break;
					case 3:
						amountOutput = new String("Type 3");
						break;
					case 4:
						amountOutput = new String("Type 4");
						break;
					case 5:
						amountOutput = new String("Type 5");
						break;
					case 6:
						amountOutput = new String("Type 6");
						break;
					case 7:
						amountOutput = new String("Type 7");
						break;
					default:
						break;
					}
				}
				else if(What==5){
					switch(newVal){
					case 1:
						amountOutput = new String("spotted underwear");
						break;
					case 2:
						amountOutput = new String("soaked underwear");
						break;
					case 3:
						amountOutput = new String("soaked pants");
						break;
					default:
						break;
					}
				}
			}
		});
        if (What==1 || What==2)
        return new AlertDialog.Builder(getActivity()).setView(v)
				.setTitle(R.string.type_picker)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				}).create();
        else 
        	return new AlertDialog.Builder(getActivity()).setView(v)
		.setTitle(R.string.output_amount_picker)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				sendResult(Activity.RESULT_OK);
			}
		}).create();
        	
        	
	}
}
