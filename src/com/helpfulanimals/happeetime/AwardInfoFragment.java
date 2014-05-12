package com.helpfulanimals.happeetime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AwardInfoFragment extends Fragment {
	private int position;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(false);
		setRetainInstance(false);

	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.short_info_fragment, parent, false);
		TextView tv =(TextView)v.findViewById(R.id.TextView);
		tv.setTextSize(16);
		tv.setPadding(10, 10, 10, 10);
		if (LogItemStore.sharedStore(getActivity()).getLogItems().get(position).getTypeIntake().compareTo("Fiber")==0)
		tv.setText("You won the ROCKBREAKER!  You ate at least 25 grams of fiber today.  " +
				"Fiber is something that's in food that your body doesn't absorb-instead, it helps push everything else through your body, which helps beat CONSTIPATION.  " +
				"And constipation can make it hard to know when you need to pee.  So if you eat more fiber, you can go to the bathroom more regularly!");
		else if(LogItemStore.sharedStore(getActivity()).getLogItems().get(position).getTypeIntake().compareTo("Drink")==0)
			tv.setText("You won the THUMBS UP!  You drank more than 40 ounces of fluid today, which means you are doing a good job beating CONSTIPATION.  Remember what constipation is?  That’s when it’s hard to poop, it hurts, or you don’t go every day.  " +
					"And by drinking lots of fluids – like water, juice and sports drinks like gatorade – you make it a lot harder for CONSTIPATION to beat YOU!");
		
		return v;
	}
	public static AwardInfoFragment newInstance(int position){
		AwardInfoFragment fragment = new AwardInfoFragment();
		fragment.position = position;
		return fragment;
	}
}
