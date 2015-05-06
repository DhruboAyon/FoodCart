package com.example.foodcart;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private ListView mDrawerList;
	private ViewPager mViewPager;
	private static final int TWO_FRAGMENTS = 2;
	TabHost tabHost;
	private ItemFragmentTab myItemFragmentTab = new ItemFragmentTab();
	private SelectedFragmentTab mySelectedFragmentTab = new SelectedFragmentTab();
	private String[] itemSelectedMenuName = {"food","beverage","dessert","icecream","soup"};
	private int[] itemSelectedMenuID = {R.array.food_items,R.array.beverage_items,R.array.dessert_items,R.array.ice_cream_items,R.array.soup_items};
	private String selectedItem;
	// View view ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		setupTabHost();

		setupNavigationDrawer();

	}

	private void setupNavigationDrawer() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.bringToFront();

		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new NavDrawerListAdapter(
				getApplicationContext(), navMenuTitles, navMenuIcons));
		
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				myItemFragmentTab.setItems(itemSelectedMenuID[arg2],itemSelectedMenuName[arg2],getMySelectedFragmentTab().getAlreadyHaveList(itemSelectedMenuName[arg2]));
				selectedItem = itemSelectedMenuName[arg2];
				mDrawerLayout.closeDrawers();
			}
		});
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.close_drawer, /* nav drawer icon to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description */
		R.string.drawer_close /* "close drawer" description */
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle("Food Cart - "+selectedItem);
				tabHost.bringToFront();
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				//mDrawerLayout.bringToFront();
				getActionBar().setTitle("Choose Cart Menu Item");
				tabHost.setVisibility(View.VISIBLE);
				mDrawerList.bringToFront();
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle your other action bar items...

		return super.onOptionsItemSelected(item);
	}

	private void setupTabHost() {
		TabContentFactory mFactory = new TabContentFactory() {

			@Override
			public View createTabContent(String tag) {
				View v = new View(getApplicationContext());
				v.setMinimumHeight(0);
				return v;
			}
		};
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(new CustomerInnerPagerAdapter(
				getSupportFragmentManager()));
		final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec("Item").setIndicator("Item")
				.setContent(mFactory));
		tabHost.addTab(tabHost.newTabSpec("Selected").setIndicator("Selected")
				.setContent(mFactory));
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				tabHost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals("Item")) {
					mViewPager.setCurrentItem(0);
				} else if (tabId.equals("Selected")) {
					mViewPager.setCurrentItem(1);
				}//
			}
		});

	}

	private class CustomerInnerPagerAdapter extends FragmentPagerAdapter {

		public CustomerInnerPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return myItemFragmentTab;
			} else if (position == 1) {
				return mySelectedFragmentTab;
			}
			return null;
		}

		@Override
		public int getCount() {
			return TWO_FRAGMENTS;
		}

	}

	public ItemFragmentTab getMyItemFragmentTab() {
		return myItemFragmentTab;
	}

	public SelectedFragmentTab getMySelectedFragmentTab() {
		return mySelectedFragmentTab;
	}

}