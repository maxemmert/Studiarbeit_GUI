package com.superbank;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.dom4j.io.SAXReader;
import org.kapott.hbci.manager.HBCIUtils;
import org.kapott.hbci.manager.XMLStorage;
import org.w3c.dom.Element;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.superbank.R;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	// new menue
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	Context context;

	public static String checkBeguenstigter;
	public static String checkKontonummer;
	public static String checkBlz;
	public static String checkBank;
	public static String checkBetrag;
	public static String checkVerwendung;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	// private CharSequence mTitle;
	private final boolean loggedIn = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when a drawer has settled in a completely closed state. */
			@Override
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getActionBar().setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle(mDrawerTitle);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		// mTitle = getTitle();
		//
		// // Set up the drawer.
		// mNavigationDrawerFragment.setUp(
		// R.id.navigation_drawer,
		// (DrawerLayout) findViewById(R.id.drawer_layout));

		// new menu
		mNavigationDrawerItemTitles = getResources().getStringArray(
				R.array.navigation_drawer_items_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.navigation_drawer);

		ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[9];
		drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_meine_konten,
				"Meine Konten");
		drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_umsatz,
				"Umsatzanzeige");
		drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_action_ueberweisung,
				"Überweisung");
		drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_action_zugang,
				"Kontoverwaltung");
		drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_action_kontakte,
				"Kontakte");
		drawerItem[5] = new ObjectDrawerItem(R.drawable.ic_action_sperren,
				"Sperren");
		drawerItem[6] = new ObjectDrawerItem(R.drawable.ic_action_hilfe,
				"Hilfe");
		drawerItem[7] = new ObjectDrawerItem(R.drawable.ic_action_impressum,
				"Impressum");
		drawerItem[8] = new ObjectDrawerItem(R.drawable.ic_action_logout,
				"Logout");

		DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this,
				R.layout.listview_item_row, drawerItem);
		mDrawerList.setAdapter(adapter);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		// if (loggedIn == false) {
		// fragmentManager.beginTransaction()
		// .replace(R.id.container, Login.newInstance(position))
		// .commit();
		// } else {

		switch (position + 1) {
		case 1:
			fragmentManager.beginTransaction()
					.replace(R.id.container, MeineKonten.newInstance(position))
					.commit();
			break;
		case 2:
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							Umsatzanzeige.newInstance(position)).commit();
			break;
		case 3:
			fragmentManager
					.beginTransaction()
					.replace(R.id.container, Ueberweisung.newInstance(position))
					.commit();
			break;
		case 4:
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							Kontoverwaltung.newInstance(position)).commit();
			break;
		case 5:
			fragmentManager.beginTransaction()
					.replace(R.id.container, Kontakte.newInstance(position))
					.commit();
			break;
		case 6:
			fragmentManager.beginTransaction()
					.replace(R.id.container, Sperren.newInstance(position))
					.commit();
			break;
		case 7:
			fragmentManager.beginTransaction()
					.replace(R.id.container, Hilfe.newInstance(position))
					.commit();
			break;
		case 8:
			fragmentManager.beginTransaction()
					.replace(R.id.container, Impressum.newInstance(position))
					.commit();
			break;
		case 9:
			logout(this.getCurrentFocus());
			break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number + 1) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			break;
		case 7:
			mTitle = getString(R.string.title_section7);
			break;
		case 8:
			mTitle = getString(R.string.title_section8);
			break;
		case 9:
			mTitle = "Logout";
		}
		mDrawerLayout.closeDrawers(); // close the navigation drawer after
										// selecting a list item
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onPause() {
		deleteSession(this.getCurrentFocus());
		super.onPause();
	}

	public void logout(final View view) {
		new AlertDialog.Builder(this)
				.setTitle("Meldung")
				.setMessage("Möchtest du dich wirklich ausloggen?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								SharedPreferences sharedpreferences = getSharedPreferences(
										LoginActivity.MyPREFERENCES,
										Context.MODE_PRIVATE);
								Editor editor = sharedpreferences.edit();
								editor.clear();
								editor.commit();
								openLoginActivity(view);
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continue
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	public void deleteSession(View vi) {
		SharedPreferences sharedpreferences = getSharedPreferences(
				LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		editor.clear();
		editor.commit();
		exit(vi);
	}

	public void openLoginActivity(View vi) {
		Intent intent = new Intent(vi.getContext(), LoginActivity.class);
		startActivity(intent);
	}

	public void exit(View view) {
		moveTaskToBack(true);
		MainActivity.this.finish();
	}

	// BUTTONS

	public void deleteAccount(final View view) {
		new AlertDialog.Builder(this)
				.setTitle("Warnung")
				.setMessage(
						"Alle Einstellungen und Konten werden gelöscht. Fortfahren?")
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								SharedPreferences credentials = getSharedPreferences(
										NeuerAccount.MyCREDENTIALS,
										Context.MODE_PRIVATE);
								Editor edit = credentials.edit();
								edit.clear();
								edit.commit();
								moveTaskToBack(true);
								logout(view);
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continue
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	/**
	 * Runs if the abbrechen button is clicked opens the Kontenübersicht
	 * 
	 * @param vi
	 */
	public void abbrechen(View vi) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, MeineKonten.newInstance(1)).commit();
	}

	public void kontostandAbrufen(View vi) {

		String xmlString = null;
		try {
			xmlString = Utility.getStringFromFile();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		InputStream stream = null;
		try {
			stream = new ByteArrayInputStream(xmlString.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("ISO-8859-1");
		org.dom4j.Document dom4jDoc = null;
		org.w3c.dom.Document w3cDoc = null;
		w3cDoc = Utility.convertDocument(stream, saxReader, dom4jDoc, w3cDoc);
		HashMap<String, Element> hashMap = Utility.buildHashMap(w3cDoc);
		XMLStorage.setHashMap(hashMap);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		EditText kontostand = (EditText) findViewById(R.id.kontostand);
		kontostand.setText(Utility.getKontostand(w3cDoc));

	}

	/**
	 * Runs, if the snyc button is clicked in Zugangsdaten
	 * 
	 * @param vi
	 */
	public void syncKonto(View vi) {
		System.out.println("sync mein Konto");
	}

	/**
	 * Runs if the bank-field in ueberweisung is selected. Gets the bank for the
	 * given blz.
	 * 
	 * @param vi
	 */
	public void printBankByBLZ(View vi) {

		AssetManager am = getAssets();
		InputStream is = null;
		try {
			is = am.open("blz.properties");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HBCIUtils.initDataStructures();
		try {
			HBCIUtils.refreshBLZList(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		EditText beguenstigterBLZ = (EditText) findViewById(R.id.beguenstigter_blz);
		String beguenstigterBLZStr = beguenstigterBLZ.getText().toString();
		String blzStr = Utility.getBankNameForBLZ(beguenstigterBLZStr);
		EditText bankName = (EditText) findViewById(R.id.beguenstigter_bank);
		bankName.setText(blzStr);

	}

	/**
	 * Runs, if the forward button is clicked in Ueberweisung
	 * 
	 * @param vi
	 */
	public void ueberweisungFortfahren(View vi) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, CheckTransaction.newInstance(1))
				.addToBackStack(null).commit();

		EditText beguenstigter = (EditText) findViewById(R.id.beguenstigter);
		checkBeguenstigter = beguenstigter.getText().toString();
		EditText kontonummer = (EditText) findViewById(R.id.beguenstigter_kontonummer);
		checkKontonummer = kontonummer.getText().toString();
		EditText blz = (EditText) findViewById(R.id.beguenstigter_blz);
		checkBlz = blz.getText().toString();
		EditText bank = (EditText) findViewById(R.id.beguenstigter_bank);
		checkBank = bank.getText().toString();
		EditText betrag = (EditText) findViewById(R.id.beguenstigter_betrag);
		checkBetrag = betrag.getText().toString();
		EditText verwendung = (EditText) findViewById(R.id.verwendungszweck);
		checkVerwendung = verwendung.getText().toString();

	}

	public void neuesBankkontoAnlegen(View vi) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, NeuesBankkonto.newInstance(1))
				.addToBackStack(null).commit();
	}

	/**
	 * Runs, if the OK button is clicked in Login
	 * 
	 * @param vi
	 */
	// public void loginApp(View vi) {
	// FragmentManager fragmentManager = getFragmentManager();
	// fragmentManager.beginTransaction()
	// .replace(R.id.container, MeineKonten.newInstance(1)).commit();
	// }

	/**
	 * Runs, if the Go Back button is clicked in CheckTransaction
	 * 
	 * @param vi
	 */
	public void goBack(View vi) {
		FragmentManager fm = this.getFragmentManager();
		fm.popBackStack();
	}

}
