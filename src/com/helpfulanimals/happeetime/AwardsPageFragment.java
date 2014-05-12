package com.helpfulanimals.happeetime;

import java.util.ArrayList;



import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AwardsPageFragment extends Fragment{
	private ListView list;
	private ArrayList<Integer> entries;
	private static final String EXTRA_POSITION = "POSITION";
	private static final String TAG = "AwardsPageFragment";
	private int FiberAwardsSet = 0;
	private int FluidAwardsSet=0;
	private int PoopAwardsSet= 0;
	private int PeeAwardsSet=0;
	int typeAward = 0;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		setRetainInstance(true);
		  
	}
	
	public static AwardsPageFragment newInstance(int pos){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_POSITION, pos);
		AwardsPageFragment fragment = new AwardsPageFragment();
		fragment.setArguments(args);
		return fragment;
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.awards_fragment, parent, false);
		list = (ListView)v.findViewById(R.id.listView);
		list.setDivider(null);
		typeAward = (Integer) getArguments().getSerializable(EXTRA_POSITION);
		int numberOfRows = 0;		
		switch(typeAward)
		{
		case 0:
			FiberAwardsSet = LogItemStore.sharedStore(getActivity()).countFiberAwardsSet();
			numberOfRows = LogItemStore.sharedStore(getActivity()).returnNumberOfFiberRows();
			break;
		case 1:
			FluidAwardsSet= LogItemStore.sharedStore(getActivity()).countFluidAwardsSet();
			numberOfRows = LogItemStore.sharedStore(getActivity()).returnNumberOfFluidRows();
			break;
		case 2:
			PeeAwardsSet = OutputLogItemStore.sharedStore(getActivity()).countPeeAwardsSet();
			numberOfRows = OutputLogItemStore.sharedStore(getActivity()).returnNumberOfPeeRows();
			break;
		case 3:
			PoopAwardsSet = OutputLogItemStore.sharedStore(getActivity()).countPoopAwardsSet();
			numberOfRows = OutputLogItemStore.sharedStore(getActivity()).returnNumberOfPoopRows();
			break;
		default:
			break;
		}
		entries = new ArrayList<Integer>();
		for (int i=0; i<numberOfRows; i++)
			entries.add(null);
		
		EntryAdapter adapter = new EntryAdapter(entries);
		list.setAdapter(adapter);
		list.setPadding(10, 10, 10, 10);
		list.setDividerHeight(10);
		return v;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity())!=null){
				NavUtils.navigateUpFromSameTask(getActivity());
			}
				
			return true;
		default:
			return true;
		}
	}
	private class EntryAdapter extends ArrayAdapter<Integer>{
		public EntryAdapter(ArrayList<Integer> crimes){
			super(getActivity(),0,crimes);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView == null)
				convertView=getActivity().getLayoutInflater().inflate(R.layout.awards_list_items,null);
			Integer c= getItem(position);
			ImageView firstImage = new ImageView(getActivity());
			
			ImageView secondImage = new ImageView(getActivity());
			ImageView thirdImage = new ImageView(getActivity());
			ImageView fourthImage = new ImageView(getActivity());
			ImageView fifthImage= new ImageView(getActivity());
			firstImage = null;
			secondImage = null;
			thirdImage = null;
			fourthImage = null;
			fifthImage = null;
			
			switch(typeAward){
			case 0:
				int numberOfAwards =0;
				if(FiberAwardsSet <5)
					numberOfAwards = FiberAwardsSet;
				else
					numberOfAwards = 5;
				for(int count =0; count <numberOfAwards; count++){
					if (firstImage == null && count>=0){
						firstImage = (ImageView)convertView.findViewById(R.id.imageView1);
						Drawable icon = getResources().getDrawable(R.drawable.explosion_phone);
						firstImage.setVisibility(View.VISIBLE);
						firstImage.setImageDrawable(icon);
					}
					else if (secondImage == null && count>=0){
						secondImage = (ImageView)convertView.findViewById(R.id.imageView2);
						Drawable icon = getResources().getDrawable(R.drawable.explosion_phone);
						secondImage.setVisibility(View.VISIBLE);
						secondImage.setImageDrawable(icon);
					}
					else if (thirdImage == null && count>1){
						thirdImage = (ImageView)convertView.findViewById(R.id.imageView3);
						Drawable icon = getResources().getDrawable(R.drawable.explosion_phone);
						thirdImage.setVisibility(View.VISIBLE);
						thirdImage.setImageDrawable(icon);
					}
					else if (fourthImage == null && count>2){
						fourthImage = (ImageView)convertView.findViewById(R.id.imageView4);
						Drawable icon = getResources().getDrawable(R.drawable.explosion_phone);
						fourthImage.setVisibility(View.VISIBLE);
						fourthImage.setImageDrawable(icon);
					}
					else if (fifthImage == null && count>3){
						fifthImage = (ImageView)convertView.findViewById(R.id.imageView5);
						Drawable icon = getResources().getDrawable(R.drawable.explosion_phone);
						fifthImage.setVisibility(View.VISIBLE);
						fifthImage.setImageDrawable(icon);
					}
					FiberAwardsSet--;
				}
					break;
				
			case 1:
				int numberOfFluidAwards =0;
				if(FluidAwardsSet <5)
					numberOfFluidAwards = FluidAwardsSet;
				else
					numberOfFluidAwards = 5;
				for(int count =0; count <numberOfFluidAwards; count++){
					if (firstImage == null && count>=0){
						firstImage = (ImageView)convertView.findViewById(R.id.imageView1);
						Drawable icon = getResources().getDrawable(R.drawable.thumbs_up);
						firstImage.setVisibility(View.VISIBLE);
						firstImage.setImageDrawable(icon);
					}
					else if (secondImage == null && count>=0){
						secondImage = (ImageView)convertView.findViewById(R.id.imageView2);
						Drawable icon = getResources().getDrawable(R.drawable.thumbs_up);
						secondImage.setVisibility(View.VISIBLE);
						secondImage.setImageDrawable(icon);
					}
					else if (thirdImage == null && count>1){
						thirdImage = (ImageView)convertView.findViewById(R.id.imageView3);
						Drawable icon = getResources().getDrawable(R.drawable.thumbs_up);
						thirdImage.setVisibility(View.VISIBLE);
						thirdImage.setImageDrawable(icon);
					}
					else if (fourthImage == null && count>2){
						fourthImage = (ImageView)convertView.findViewById(R.id.imageView4);
						Drawable icon = getResources().getDrawable(R.drawable.thumbs_up);
						fourthImage.setVisibility(View.VISIBLE);
						fourthImage.setImageDrawable(icon);
					}
					else if (fifthImage == null && count>3){
						fifthImage = (ImageView)convertView.findViewById(R.id.imageView5);
						Drawable icon = getResources().getDrawable(R.drawable.thumbs_up);
						fifthImage.setVisibility(View.VISIBLE);
						fifthImage.setImageDrawable(icon);
					}
					FluidAwardsSet--;
				}
					break;
			case 2:
					int numberOfPeeAwards =0;
					if(PeeAwardsSet <5)
						numberOfPeeAwards = PeeAwardsSet;
					else
						numberOfPeeAwards = 5;
					for(int count =0; count <numberOfPeeAwards; count++){
						if (firstImage == null && count>=0){
							firstImage = (ImageView)convertView.findViewById(R.id.imageView1);
							Drawable icon = getResources().getDrawable(R.drawable.flush_phone);
							firstImage.setVisibility(View.VISIBLE);
							firstImage.setImageDrawable(icon);
						}
						else if (secondImage == null && count>=0){
							secondImage = (ImageView)convertView.findViewById(R.id.imageView2);
							Drawable icon = getResources().getDrawable(R.drawable.flush_phone);
							secondImage.setVisibility(View.VISIBLE);
							secondImage.setImageDrawable(icon);
						}
						else if (thirdImage == null && count>1){
							thirdImage = (ImageView)convertView.findViewById(R.id.imageView3);
							Drawable icon = getResources().getDrawable(R.drawable.flush_phone);
							thirdImage.setVisibility(View.VISIBLE);
							thirdImage.setImageDrawable(icon);
						}
						else if (fourthImage == null && count>2){
							fourthImage = (ImageView)convertView.findViewById(R.id.imageView4);
							Drawable icon = getResources().getDrawable(R.drawable.flush_phone);
							fourthImage.setVisibility(View.VISIBLE);
							fourthImage.setImageDrawable(icon);
						}
						else if (fifthImage == null && count>3){
							fifthImage = (ImageView)convertView.findViewById(R.id.imageView5);
							Drawable icon = getResources().getDrawable(R.drawable.flush_phone);
							fifthImage.setVisibility(View.VISIBLE);
							fifthImage.setImageDrawable(icon);
						}
						PeeAwardsSet--;
					}
						break;
			case 3:
				int numberOfPoopAwards =0;
				
				if(PoopAwardsSet <5)
					numberOfPoopAwards = PoopAwardsSet;
				else
					numberOfPoopAwards = 5;
				for(int count =0; count <numberOfPoopAwards; count++){
					if (firstImage == null && count>=0){
						firstImage = (ImageView)convertView.findViewById(R.id.imageView1);
						Drawable icon = getResources().getDrawable(R.drawable.poop_phone);
						firstImage.setVisibility(View.VISIBLE);
						firstImage.setImageDrawable(icon);
					}
					else if (secondImage == null && count>=0){
						secondImage = (ImageView)convertView.findViewById(R.id.imageView2);
						Drawable icon = getResources().getDrawable(R.drawable.poop_phone);
						secondImage.setVisibility(View.VISIBLE);
						secondImage.setImageDrawable(icon);
					}
					else if (thirdImage == null && count>1){
						thirdImage = (ImageView)convertView.findViewById(R.id.imageView3);
						Drawable icon = getResources().getDrawable(R.drawable.poop_phone);
						thirdImage.setVisibility(View.VISIBLE);
						thirdImage.setImageDrawable(icon);
					}
					else if (fourthImage == null && count>2){
						fourthImage = (ImageView)convertView.findViewById(R.id.imageView4);
						Drawable icon = getResources().getDrawable(R.drawable.poop_phone);
						fourthImage.setVisibility(View.VISIBLE);
						fourthImage.setImageDrawable(icon);
					}
					else if (fifthImage == null && count>3){
						fifthImage = (ImageView)convertView.findViewById(R.id.imageView5);
						Drawable icon = getResources().getDrawable(R.drawable.poop_phone);
						fifthImage.setVisibility(View.VISIBLE);
						fifthImage.setImageDrawable(icon);
					}
					PoopAwardsSet--;
				}
					break;
			}
			convertView.setBackgroundColor(Color.TRANSPARENT);
			
			return convertView;
		}
	}
	
	
}
