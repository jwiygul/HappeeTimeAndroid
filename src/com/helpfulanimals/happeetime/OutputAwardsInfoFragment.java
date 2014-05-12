package com.helpfulanimals.happeetime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputAwardsInfoFragment extends Fragment {
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
		if (OutputLogItemStore.sharedStore(getActivity()).getLogItems().get(position).getTypeIntake().compareTo("Poop")==0)
		tv.setText("You won MR.(or MS) CONSISTENCY!  You've pooped at least 4 days in a row - Congratulations!  " +
				"Although it may seem a little funny, when poop stays inside for a long time, it can get hard and push on your bladder, " +
				"making it hard to control when and where you pee.  So if you're pooping well, you're probably peeing well, too.  " +
				"And that's the whole point!");
		else if(OutputLogItemStore.sharedStore(getActivity()).getLogItems().get(position).getTypeIntake().compareTo("Pee")==0)
			tv.setText("You won the TOILET FLUSH!  You've gone at least 3 days without a pee accident!  " +
					"The more Toilet Flushes you get, the better you're doing!  " +
					"But don't worry if you still get wet every now and then.  " +
					"Remember, learning to pee is like riding a bike.  If you fall off, you just get right back on and KEEP PEDALING!");
		
		return v;
	}
	public static OutputAwardsInfoFragment newInstance(int position){
		OutputAwardsInfoFragment fragment = new OutputAwardsInfoFragment();
		fragment.position = position;
		return fragment;
	}
}
