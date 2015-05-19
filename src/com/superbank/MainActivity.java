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
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.superbank.R;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	// Rufnummer Collection
	public static SharedPreferences rufnummerCollection;
	public static final String MyRUFNUMMERN = "MyNUMBERS";
	private static int counterRufnummern;

	// new menue
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private PopupWindow pw;
	private Dialog loadingDialog;
	Context context;

	public static String checkBeguenstigter;
	public static String checkKontonummer;
	public static String checkBlz;
	public static String checkBank;
	public static String checkBetrag;
	public static String checkVerwendung;

	// neues bankkonto
	private static String newKontoKto;
	private static String newKontoBlz;
	private static String newKontoPin;
	private static String newKontoBenutzer;
	private static String newKontoTanmethod;
	private static String newKontoFiltertyp;
	private static String newKontoLocation;

	// Kontostaende
	// 1. Konto
	public static String kontostand1;
	public static String bankname1;
	public static String kontonummer1;
	public static String blz1;
	// 2. Konto
	public static String kontostand2;
	public static String bankname2;
	public static String kontonummer2;
	public static String blz2;
	// 3. Konto
	public static String kontostand3;
	public static String bankname3;
	public static String kontonummer3;
	public static String blz3;
	// Summe und Datum
	public static String datum;
	public static String summe;

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
	public static boolean RufnummernAreDeleted = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBankCredentialsInView();
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
			fragmentManager
					.beginTransaction()
					.replace(R.id.container,
							SperrenOverview.newInstance(position)).commit();
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
						}).setIcon(R.drawable.ic_launcher).show();
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

	public void kontostandAbrufen(View vi, int j) {

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

		Utility.getKontostandByCredentials(w3cDoc, j, true);

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

	private void setBankCredentialsInView() {
		// 1.Konto
		kontostand1 = LoginActivity.credentials
				.getString("kontoGuthaben_1", "").replace("[", "")
				.replace("]", "");
		bankname1 = LoginActivity.credentials.getString("bankName_1", "");
		kontonummer1 = LoginActivity.credentials.getString("kontoNummer_1", "");
		blz1 = LoginActivity.credentials.getString("bankleitzahl_1", "");
		// 2.Konto
		kontostand2 = LoginActivity.credentials
				.getString("kontoGuthaben_2", "").replace("[", "")
				.replace("]", "");
		bankname2 = LoginActivity.credentials.getString("bankName_2", "");
		kontonummer2 = LoginActivity.credentials.getString("kontoNummer_2", "");
		blz2 = LoginActivity.credentials.getString("bankleitzahl_2", "");
		// 3.Konto
		kontostand3 = LoginActivity.credentials
				.getString("kontoGuthaben_3", "").replace("[", "")
				.replace("]", "");
		bankname3 = LoginActivity.credentials.getString("bankName_3", "");
		kontonummer3 = LoginActivity.credentials.getString("kontoNummer_3", "");
		blz3 = LoginActivity.credentials.getString("bankleitzahl_3", "");

		// Summenbildung
		String ktSt1 = "0.0";
		String ktSt2 = "0.0";
		String ktSt3 = "0.0";
		if (kontostand1 != "") {
			ktSt1 = kontostand1;
		}
		if (kontostand2 != "") {
			ktSt2 = kontostand2;
		}
		if (kontostand3 != "") {
			ktSt3 = kontostand3;
		}

		summe = String.valueOf((Double.valueOf(ktSt1.replace(" EUR", ""))
				+ Double.valueOf(ktSt2.replace(" EUR", "")) + Double
				.valueOf(ktSt3.replace(" EUR", "")))) + " EUR";

	}

	// Lege neues Konto an und zeige Ladespinner
	public void neuesKontoAnlegenUndSpinnerZeigen(final View vi)
			throws IOException, InterruptedException {

		// Loading Screen
		try {
			ImageView darkenScreen = (ImageView) findViewById(R.id.darkenScreen);
			LayoutParams darkenParams = (LayoutParams) darkenScreen
					.getLayoutParams();
			darkenParams.height = LayoutParams.MATCH_PARENT;
			darkenParams.width = LayoutParams.MATCH_PARENT;
			darkenScreen.setLayoutParams(darkenParams);

			LayoutInflater inflater = (LayoutInflater) MainActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// Inflate the view from a predefined XML layout
			View layout = inflater.inflate(R.layout.popupwindow_spinner, null);
			loadingDialog = new Dialog(getApplicationContext());
			loadingDialog.getWindow().setType(
					WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			loadingDialog.addContentView(layout, darkenParams);
			loadingDialog.setCancelable(false);
			loadingDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread syncKto = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					neuesKontoHinzufuegen(vi);
					loadingDialog.dismiss();
					System.out.println("konto adden");

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		syncKto.start();

	}

	/**
	 * Runs if the Entfernen Button is pressed in Kontoverwaltung
	 * 
	 * @param vi
	 */
	public void bankkontoEntfernen(View vi) {
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
				MainActivity.this);
		builderSingle.setIcon(R.drawable.ic_launcher);
		builderSingle.setTitle("Konto auswählen");
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.select_dialog_singlechoice);

		if (LoginActivity.credentials.getString("bankName_1", "") != "") {
			arrayAdapter.add(LoginActivity.credentials.getString("bankName_1",
					""));
		}
		if (LoginActivity.credentials.getString("bankName_2", "") != "") {
			arrayAdapter.add(LoginActivity.credentials.getString("bankName_2",
					""));
		}
		if (LoginActivity.credentials.getString("bankName_3", "") != "") {
			arrayAdapter.add(LoginActivity.credentials.getString("bankName_3",
					""));
		}

		builderSingle.setNegativeButton("Abbrechen",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builderSingle.setAdapter(arrayAdapter,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						String strName = arrayAdapter.getItem(which);
						final int konto = which + 1;
						AlertDialog.Builder builderInner = new AlertDialog.Builder(
								MainActivity.this);
						builderInner.setMessage(strName);
						builderInner.setTitle("Ihre Auswahl:");
						builderInner.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										Utility.deleteKonto(konto);
										if (konto == 1) {
											kontostand1 = "0.0";
										} else if (konto == 2) {
											kontostand2 = "0.0";
										} else
											kontostand3 = "0.0";

										dialog.dismiss();
									}
								});
						builderInner.show();
					}
				});
		builderSingle.show();
	}

	private static org.w3c.dom.Document saxReaderAndW3cDoc() {
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
		return w3cDoc;
	}

	// Update Kontostaende

	/**
	 * Runs if the Update button of Konto1 is pressed
	 * 
	 * @param vi
	 */
	public void UpdateKontoEins(View vi) {
		final TextView kontostandView1 = (TextView) findViewById(R.id.kontostand1);
		// synchronising Screen
		try {

			kontostandView1.setText("[synchronisiere...]");
			kontostandView1.setTextColor(Color.GREEN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread updateKto1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// falls nicht geht, Zeile kommentieren
				Utility.refreshKontostandByCredentials(saxReaderAndW3cDoc(), 1,
						true);
				System.out.println("konto1 update");
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.container, MeineKonten.newInstance(1))
						.addToBackStack(null).commit();
			}
		});
		updateKto1.start();
		setBankCredentialsInView();
	}

	/**
	 * Runs if the Update button of Konto2 is pressed
	 * 
	 * @param vi
	 */
	public void UpdateKontoZwei(View vi) {
		final TextView kontostandView2 = (TextView) findViewById(R.id.kontostand2);
		// synchronising Screen
		try {

			kontostandView2.setText("[synchronisiere...]");
			kontostandView2.setTextColor(Color.GREEN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread updateKto2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// falls nicht geht, Zeile kommentieren
				Utility.refreshKontostandByCredentials(saxReaderAndW3cDoc(), 2,
						true);
				System.out.println("konto2 update");
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.container, MeineKonten.newInstance(1))
						.addToBackStack(null).commit();
			}
		});
		updateKto2.start();
		setBankCredentialsInView();
	}

	/**
	 * Runs if the Update button of Konto3 is pressed
	 * 
	 * @param vi
	 */
	public void UpdateKontoDrei(View vi) {
		final TextView kontostandView3 = (TextView) findViewById(R.id.kontostand3);
		// synchronising Screen
		try {

			kontostandView3.setText("[synchronisiere...]");
			kontostandView3.setTextColor(Color.GREEN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Thread updateKto3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// falls nicht geht, Zeile kommentieren
				Utility.refreshKontostandByCredentials(saxReaderAndW3cDoc(), 3,
						true);
				System.out.println("konto3 update");
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.container, MeineKonten.newInstance(1))
						.addToBackStack(null).commit();
			}
		});
		updateKto3.start();
		setBankCredentialsInView();
	}

	/**
	 * Runs if the Umsatz button of Konto1 is pressed
	 * 
	 * @param vi
	 */
	public void UmsatzKontoEins(View vi) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, Umsatzanzeige.newInstance(1))
				.addToBackStack(null).commit();
	}

	// Neues Bankkonto syncen
	public void neuesKontoHinzufuegen(View vi) throws IOException {

		// initiatePopupWindow();

		EditText kontonummer = (EditText) findViewById(R.id.kontonummerneu);
		newKontoKto = kontonummer.getText().toString();
		EditText blz = (EditText) findViewById(R.id.blznummerneu);
		newKontoBlz = blz.getText().toString();
		EditText pin = (EditText) findViewById(R.id.pinnummerneu);
		newKontoPin = pin.getText().toString();
		EditText benutzerkennung = (EditText) findViewById(R.id.benutzerneu);
		newKontoBenutzer = benutzerkennung.getText().toString();

		Spinner tanSpinner = (Spinner) findViewById(R.id.tanmethode_spinner);
		newKontoTanmethod = tanSpinner.getSelectedItem().toString(); // 911
		Spinner filterSpinner = (Spinner) findViewById(R.id.filtertypspinner);
		newKontoFiltertyp = filterSpinner.getSelectedItem().toString(); // Base64
		Spinner landSpinner = (Spinner) findViewById(R.id.landspinner);
		newKontoLocation = landSpinner.getSelectedItem().toString(); // DE

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
		boolean check = false;
		for (int j = 1; j <= 3; j++) {
			if ((((LoginActivity.credentials
					.getString("kontoGuthaben_" + j, "") == null || LoginActivity.credentials
					.getString("kontoGuthaben_" + j, "") == "")))
					&& check == false) {
				Utility.newKontoToCredentials(newKontoBlz, newKontoKto,
						newKontoBenutzer, newKontoPin, newKontoTanmethod,
						newKontoFiltertyp, newKontoLocation, j);
				kontostandAbrufen(vi, j);
				check = true;
			}
		}
		setBankCredentialsInView();
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, MeineKonten.newInstance(1))
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
	 * Runs if the neue Sperrrufnummer anlegen button is pressed
	 * 
	 * @param vi
	 */
	public void neueSperrrufnummerAnlegen(View vi) {
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, Sperren.newInstance(1))
				.addToBackStack(null).commit();
	}

	/**
	 * Speichert die Rufnummer dauerhaft in den credentials, um sie auf
	 * SperrenOverview anzuzeigen und wechsle View zur Sperren Overview
	 * 
	 * @param vi
	 */
	public void rufnummerSpeichern(View vi) {
		RufnummernAreDeleted = false;
		rufnummerCollection = getSharedPreferences(MainActivity.MyRUFNUMMERN,
				Context.MODE_PRIVATE);

		EditText institut = (EditText) findViewById(R.id.institut);
		EditText rufnummer = (EditText) findViewById(R.id.rufnummer);
		if (institut.getText().toString().equals("") == false
				|| rufnummer.getText().toString().equals("") == false) {

			Editor editor = rufnummerCollection.edit();
			counterRufnummern = rufnummerCollection.getAll().size() / 2 + 1;
			editor.putString("institut" + "_" + counterRufnummern, institut
					.getText().toString());
			editor.putString("rufnummer" + "_" + counterRufnummern, rufnummer
					.getText().toString());

			editor.commit();

			// Gehe zur Sperren Overview
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.container, SperrenOverview.newInstance(1))
					.addToBackStack(null).commit();
		} else {
			new AlertDialog.Builder(this)
					.setTitle("Meldung")
					.setMessage("Bitte Institut und Rufnummer eingeben.")
					.setNeutralButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// dismiss
								}
							}).setIcon(R.drawable.ic_launcher).show();
		}

	}

	/**
	 * 
	 * Löscht alle Rufnummern und geht zur SperrenOverview
	 * 
	 * @param vi
	 */
	public void alleRufnummernLoeschen(View vi) {
		RufnummernAreDeleted = true;
		rufnummerCollection = getSharedPreferences(MainActivity.MyRUFNUMMERN,
				Context.MODE_PRIVATE);
		Editor editor = rufnummerCollection.edit();
		for (int i = 1; i <= 1000; i++) {
			editor.remove("institut" + "_" + i);
			editor.remove("rufnummer" + "_" + i);
			editor.commit();
		}

		// Gehe zur Sperren Overview
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, SperrenOverview.newInstance(1))
				.addToBackStack(null).commit();
	}

	/**
	 * Runs, if the Go Back button is clicked in CheckTransaction
	 * 
	 * @param vi
	 */
	public void goBack(View vi) {
		FragmentManager fm = this.getFragmentManager();
		fm.popBackStack();
	}

	public void notimplemented(View vi) {
		new AlertDialog.Builder(this)
				.setTitle("Meldung")
				.setMessage("Diese Funktion ist zurzeit nicht implementiert.")
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss
							}
						}).setIcon(R.drawable.ic_launcher).show();

	}
}
