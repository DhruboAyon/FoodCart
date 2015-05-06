package com.example.foodcart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class SelectedFragmentTab extends Fragment {
	private List<String> selectedItemList = new ArrayList<String>();
	ExpandedListView foodSelectedItemListView,beverageSelectedItemListView,dessertSelectedItemListView,iceSelectedItemListView,soupSelectedItemListView;
	private ArrayAdapter<String> adapter;
	Set<String> foodList,beverageList,dessertList,icecreamList,soupList;
	LinearLayout foodContent,beverageContent,dessertContent,iceContent,soupContent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		foodList = new HashSet<String>();
		beverageList = new HashSet<String>();
		dessertList = new HashSet<String>();
		icecreamList = new HashSet<String>();
		soupList = new HashSet<String>();
		
		View v = inflater.inflate(R.layout.selected_item_list_view, container,false);
		foodSelectedItemListView = (ExpandedListView)v.findViewById(R.id.foodSelectedItemListView);
		beverageSelectedItemListView = (ExpandedListView)v.findViewById(R.id.beverageSelectedItemListView);
		dessertSelectedItemListView = (ExpandedListView)v.findViewById(R.id.dessertSelectedItemListView);
		iceSelectedItemListView = (ExpandedListView)v.findViewById(R.id.iceSelectedItemListView);
		soupSelectedItemListView = (ExpandedListView)v.findViewById(R.id.soupSelectedItemListView);
		
		foodContent = (LinearLayout)v.findViewById(R.id.foodContent);
		beverageContent = (LinearLayout)v.findViewById(R.id.beverageContent);
		dessertContent = (LinearLayout)v.findViewById(R.id.dessertContent);
		iceContent = (LinearLayout)v.findViewById(R.id.iceContent);
		soupContent = (LinearLayout)v.findViewById(R.id.soupContent);

		return v;
	}
	
	public void setSelected(List<String> list, String menuItemName){
		if(menuItemName.equals("food")){
			foodList.clear();
			for(String item:list){
				foodList.add(item);
			}
		}else if(menuItemName.equals("beverage")){
			beverageList.clear();
			for(String item:list){
				beverageList.add(item);
			}
		}else if(menuItemName.equals("dessert")){
			dessertList.clear();
			for(String item:list){
				dessertList.add(item);
			}
		}else if(menuItemName.equals("icecream")){
			icecreamList.clear();
			for(String item:list){
				icecreamList.add(item);
			}
		}else if(menuItemName.equals("soup")){
			soupList.clear();
			for(String item:list){
				soupList.add(item);
			}
		}

		if(foodList.size()>0){
			adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_list_item, new ArrayList<String>(foodList));
			foodSelectedItemListView.setAdapter(adapter);
			foodSelectedItemListView.invalidate();
			foodContent.setVisibility(View.VISIBLE);
		}else{
			foodContent.setVisibility(View.GONE);
		}
		if(beverageList.size()>0){
			adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_list_item, new ArrayList<String>(beverageList));
			beverageSelectedItemListView.setAdapter(adapter);
			beverageSelectedItemListView.invalidate();
			beverageContent.setVisibility(View.VISIBLE);
		}else{
			beverageContent.setVisibility(View.GONE);
		}
		if(dessertList.size()>0){
			adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_list_item, new ArrayList<String>(dessertList));
			dessertSelectedItemListView.setAdapter(adapter);
			dessertSelectedItemListView.invalidate();
			dessertContent.setVisibility(View.VISIBLE);
		}else{
			dessertContent.setVisibility(View.GONE);
		}
		if(icecreamList.size()>0){
			adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_list_item, new ArrayList<String>(icecreamList));
			iceSelectedItemListView.setAdapter(adapter);
			iceSelectedItemListView.invalidate();
			iceContent.setVisibility(View.VISIBLE);
		}else{
			iceContent.setVisibility(View.GONE);
		}
		if(soupList.size()>0){
			adapter = new ArrayAdapter<String>(getActivity(), R.layout.selected_list_item, new ArrayList<String>(soupList));
			soupSelectedItemListView.setAdapter(adapter);
			soupSelectedItemListView.invalidate();
			soupContent.setVisibility(View.VISIBLE);
		}else{
			soupContent.setVisibility(View.GONE);
		}
		
		
	}

	public List<String> getAlreadyHaveList(String itemName) {
		Set<String> alreadyList = new HashSet<String>();
		if(itemName.equals("food")){
			alreadyList = foodList;
		}else if(itemName.equals("beverage")){
			alreadyList = beverageList;
		}else if(itemName.equals("dessert")){
			alreadyList = dessertList;
		}else if(itemName.equals("icecream")){
			alreadyList = icecreamList;
		}else if(itemName.equals("soup")){
			alreadyList = soupList;
		}
		return new ArrayList<String>(alreadyList);
	}
}
