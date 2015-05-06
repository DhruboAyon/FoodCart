package com.example.foodcart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ItemFragmentTab extends Fragment {
	
	private ListView mDrawerList;
	private String[] itemNames;
	private List<FoodItem> foodItemList;
	private static FoodItemListAdapter foodItemListAdapter;
	String menuItemName;
	int foodItemId;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		foodItemList = new ArrayList<FoodItem>();
		View v = inflater.inflate(R.layout.item_list_view, container,false);
		mDrawerList = (ListView) v.findViewById(R.id.foodItemListView);
		setItems(R.array.food_items,"food",new ArrayList<String>());
		return v;
	}
	
	public void setItems(int foodItemId, String menuItemName, List<String> alreadyList) {
		this.foodItemId = foodItemId;
		this.menuItemName = menuItemName;
		
		if(foodItemList != null){
			foodItemList.clear();
		}
		itemNames = getResources().getStringArray(foodItemId);
		for(String item:itemNames){
			boolean isContaining = false;
			for(String have:alreadyList){
				if(item.equals(have)){
					foodItemList.add(new FoodItem(item,true));
					isContaining = true;
					break;
				}
			}
			if(!isContaining)
				foodItemList.add(new FoodItem(item,false));
		}
		foodItemListAdapter = new FoodItemListAdapter(getActivity(), R.layout.food_list_item,foodItemList);
		
		mDrawerList.setAdapter(foodItemListAdapter);
		mDrawerList.invalidate();
		
	}

	public static List<String> getItemList(){
		List<String> selectedItemList = new ArrayList<String>();
		if(foodItemListAdapter!=null){
			for(FoodItem item:foodItemListAdapter.getFoodItemList()){
				if(item.isChecked())
					selectedItemList.add(item.getItemName());
			}
		}
		
		return selectedItemList;
	}
	
	public void setTarget(){
		((MainActivity)getActivity()).getMySelectedFragmentTab().setSelected(getItemList(),menuItemName);
	}
	
	class FoodItemListAdapter extends ArrayAdapter<FoodItem> {
		
		private Context context;
		List<FoodItem> foodItemList;
		public List<FoodItem> getFoodItemList() {
			return foodItemList;
		}

		int resourceId;

		public FoodItemListAdapter(Context context, int resourceId,
				List<FoodItem> foodItemList) {
			super(context, resourceId, foodItemList);
			this.context = context;
			this.resourceId = resourceId;
			this.foodItemList = foodItemList; 
			
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater mInflater = (LayoutInflater) context
						.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				convertView = mInflater.inflate(R.layout.food_list_item, null);
			}

			CheckedTextView foodCheckedTextView = (CheckedTextView) convertView.findViewById(R.id.icon);
			
			foodCheckedTextView.setText(foodItemList.get(position).getItemName());
			foodCheckedTextView.setChecked(foodItemList.get(position).isChecked());

			foodCheckedTextView.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v)
		        {
		        	CheckedTextView checkbox = (CheckedTextView) v;
		            checkbox.toggle();
		            if(foodItemList.get(position).isChecked())
		            	foodItemList.get(position).setChecked(false);
		            else
		            	foodItemList.get(position).setChecked(true);
		            setTarget();
		        }
		    });

			return convertView;
		}

	}
}


