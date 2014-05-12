package com.helpfulanimals.happeetime;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BristolChartFragment extends Fragment {
	
	
	public static BristolChartFragment newInstance(){
		BristolChartFragment fragment = new BristolChartFragment();
		return fragment;
	}
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.bristol_view, parent, false);
		ImageView bristol =(ImageView)v.findViewById(R.id.poop_Image);
		Drawable icon = getResources().getDrawable(R.drawable.bristol_stool_chart);
		bristol.setImageDrawable(icon);
		return v;
	}
}
