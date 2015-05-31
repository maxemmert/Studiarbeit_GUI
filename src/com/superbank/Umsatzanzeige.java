package com.superbank;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.superbank.R;

public class Umsatzanzeige extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static Umsatzanzeige newInstance(int sectionNumber) {
		Umsatzanzeige fragment = new Umsatzanzeige();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Umsatzanzeige() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_umsatzanzeige,
				container, false);
		((MainActivity) getActivity()).setTitle("Umsatzanzeige");

		final List<String> spinnerArray = new ArrayList<String>();

		if (LoginActivity.credentials.getString("bankName_1", "") != "") {
			spinnerArray.add(LoginActivity.credentials.getString("bankName_1",
					""));
		}
		if (LoginActivity.credentials.getString("bankName_2", "") != "") {
			spinnerArray.add(LoginActivity.credentials.getString("bankName_2",
					""));
		}
		if (LoginActivity.credentials.getString("bankName_3", "") != "") {
			spinnerArray.add(LoginActivity.credentials.getString("bankName_3",
					""));
		}

		// Testen
		// spinnerArray.add("Wert1");
		// spinnerArray.add("Wert2");
		//

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity()
				.getBaseContext(), android.R.layout.simple_spinner_item,
				spinnerArray);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner sItems = (Spinner) rootView.findViewById(R.id.kontospinner);
		sItems.setAdapter(adapter);

		sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int i, long l) {
				// Hier ist i oder j die jeweilige Position der Bank. Die erste
				// Bank hat den Index i = 0,
				// die zweite Bank i = 1 und die dritte i=2

				// // Felder Umsatz 1
				// TextView transactiontext1 = (TextView) rootView
				// .findViewById(R.id.umsatztransactiontext1);
				// TextView money1 = (TextView) rootView
				// .findViewById(R.id.umsatzmoney1);
				// TextView counterAccount1 = (TextView) rootView
				// .findViewById(R.id.umsatzcounterAccount1);
				// TextView date1 = (TextView) rootView
				// .findViewById(R.id.umsatzdate1);

				// Felder Umsatz 2
				TextView transactiontext2 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext2);
				TextView money2 = (TextView) rootView
						.findViewById(R.id.umsatzmoney2);
				TextView counterAccount2 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount2);
				TextView date2 = (TextView) rootView
						.findViewById(R.id.umsatzdate2);

				// Felder Umsatz 3
				TextView transactiontext3 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext3);
				TextView money3 = (TextView) rootView
						.findViewById(R.id.umsatzmoney3);
				TextView counterAccount3 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount3);
				TextView date3 = (TextView) rootView
						.findViewById(R.id.umsatzdate3);

				// Felder Umsatz 4
				TextView transactiontext4 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext4);
				TextView money4 = (TextView) rootView
						.findViewById(R.id.umsatzmoney4);
				TextView counterAccount4 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount4);
				TextView date4 = (TextView) rootView
						.findViewById(R.id.umsatzdate4);

				// Felder Umsatz 5
				TextView transactiontext5 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext5);
				TextView money5 = (TextView) rootView
						.findViewById(R.id.umsatzmoney5);
				TextView counterAccount5 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount5);
				TextView date5 = (TextView) rootView
						.findViewById(R.id.umsatzdate5);

				// Felder Umsatz 6
				TextView transactiontext6 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext6);
				TextView money6 = (TextView) rootView
						.findViewById(R.id.umsatzmoney6);
				TextView counterAccount6 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount6);
				TextView date6 = (TextView) rootView
						.findViewById(R.id.umsatzdate6);

				// Felder Umsatz 7
				TextView transactiontext7 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext7);
				TextView money7 = (TextView) rootView
						.findViewById(R.id.umsatzmoney7);
				TextView counterAccount7 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount7);
				TextView date7 = (TextView) rootView
						.findViewById(R.id.umsatzdate7);

				// Felder Umsatz 8
				TextView transactiontext8 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext8);
				TextView money8 = (TextView) rootView
						.findViewById(R.id.umsatzmoney8);
				TextView counterAccount8 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount8);
				TextView date8 = (TextView) rootView
						.findViewById(R.id.umsatzdate8);

				// Felder Umsatz 9
				TextView transactiontext9 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext9);
				TextView money9 = (TextView) rootView
						.findViewById(R.id.umsatzmoney9);
				TextView counterAccount9 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount9);
				TextView date9 = (TextView) rootView
						.findViewById(R.id.umsatzdate9);

				// Felder Umsatz 10
				TextView transactiontext10 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext10);
				TextView money10 = (TextView) rootView
						.findViewById(R.id.umsatzmoney10);
				TextView counterAccount10 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount10);
				TextView date10 = (TextView) rootView
						.findViewById(R.id.umsatzdate10);

				// Bankenabhaenginge Werte definieren

				int j = i + 1;
				// trans1
				// money1.setText(LoginActivity.credentials.getString(
				// "transaction_1_" + j + "_money", ""));
				// date1.setText(LoginActivity.credentials.getString(
				// "transaction_1_" + j + "_date", ""));
				// counterAccount1.setText(LoginActivity.credentials.getString(
				// "transaction_1_" + j + "_counterAccount", ""));
				// transactiontext1.setText(LoginActivity.credentials.getString(
				// "transaction_1_" + j + "_transactionText_0", ""));

				// trans2
				money2.setText(LoginActivity.credentials
						.getString("transaction_2_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date2.setText(LoginActivity.credentials.getString(
						"transaction_2_" + j + "_date", ""));
				counterAccount2.setText(LoginActivity.credentials.getString(
						"transaction_2_" + j + "_counterAccount", ""));
				transactiontext2.setText(LoginActivity.credentials.getString(
						"transaction_2_" + j + "_transactionText_0", ""));

				// trans3
				money3.setText(LoginActivity.credentials
						.getString("transaction_3_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date3.setText(LoginActivity.credentials.getString(
						"transaction_3_" + j + "_date", ""));
				counterAccount3.setText(LoginActivity.credentials.getString(
						"transaction_3_" + j + "_counterAccount", ""));
				transactiontext3.setText(LoginActivity.credentials.getString(
						"transaction_3_" + j + "_transactionText_0", ""));

				// trans4
				money4.setText(LoginActivity.credentials
						.getString("transaction_4_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date4.setText(LoginActivity.credentials.getString(
						"transaction_4_" + j + "_date", ""));
				counterAccount4.setText(LoginActivity.credentials.getString(
						"transaction_4_" + j + "_counterAccount", ""));
				transactiontext4.setText(LoginActivity.credentials.getString(
						"transaction_4_" + j + "_transactionText_0", ""));

				// trans5
				money5.setText(LoginActivity.credentials
						.getString("transaction_5_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date5.setText(LoginActivity.credentials.getString(
						"transaction_5_" + j + "_date", ""));
				counterAccount5.setText(LoginActivity.credentials.getString(
						"transaction_5_" + j + "_counterAccount", ""));
				transactiontext5.setText(LoginActivity.credentials.getString(
						"transaction_5_" + j + "_transactionText_0", ""));

				// trans6
				money6.setText(LoginActivity.credentials
						.getString("transaction_6_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date6.setText(LoginActivity.credentials.getString(
						"transaction_6_" + j + "_date", ""));
				counterAccount6.setText(LoginActivity.credentials.getString(
						"transaction_6_" + j + "_counterAccount", ""));
				transactiontext6.setText(LoginActivity.credentials.getString(
						"transaction_6_" + j + "_transactionText_0", ""));

				// trans7
				money7.setText(LoginActivity.credentials
						.getString("transaction_7_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date7.setText(LoginActivity.credentials.getString(
						"transaction_7_" + j + "_date", ""));
				counterAccount7.setText(LoginActivity.credentials.getString(
						"transaction_7_" + j + "_counterAccount", ""));
				transactiontext7.setText(LoginActivity.credentials.getString(
						"transaction_7_" + j + "_transactionText_0", ""));

				// trans8
				money8.setText(LoginActivity.credentials
						.getString("transaction_8_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date8.setText(LoginActivity.credentials.getString(
						"transaction_8_" + j + "_date", ""));
				counterAccount8.setText(LoginActivity.credentials.getString(
						"transaction_8_" + j + "_counterAccount", ""));
				transactiontext8.setText(LoginActivity.credentials.getString(
						"transaction_8_" + j + "_transactionText_0", ""));

				// trans9
				money9.setText(LoginActivity.credentials
						.getString("transaction_9_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date9.setText(LoginActivity.credentials.getString(
						"transaction_9_" + j + "_date", ""));
				counterAccount9.setText(LoginActivity.credentials.getString(
						"transaction_9_" + j + "_counterAccount", ""));
				transactiontext9.setText(LoginActivity.credentials.getString(
						"transaction_9_" + j + "_transactionText_0", ""));

				// trans10
				money10.setText(LoginActivity.credentials
						.getString("transaction_10_" + j + "_money", "")
						.replace("[", "").replace("]", ""));
				date10.setText(LoginActivity.credentials.getString(
						"transaction_10_" + j + "_date", ""));
				counterAccount10.setText(LoginActivity.credentials.getString(
						"transaction_10_" + j + "_counterAccount", ""));
				transactiontext10.setText(LoginActivity.credentials.getString(
						"transaction_10_" + j + "_transactionText_0", ""));

				// farben anpassen
				// money2
				if (money2.getText().equals("0.0 EUR")) {
					money2.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money2.getText()).contains("-")) {
					money2.setTextColor(getResources().getColor(R.color.red));
				} else {
					money2.setTextColor(getResources().getColor(R.color.green));
				}
				// money3
				if (money3.getText().equals("0.0 EUR")) {
					money3.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money3.getText()).contains("-")) {
					money3.setTextColor(getResources().getColor(R.color.red));
				} else {
					money3.setTextColor(getResources().getColor(R.color.green));
				}
				// money4
				if (money4.getText().equals("0.0 EUR")) {
					money4.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money4.getText()).contains("-")) {
					money4.setTextColor(getResources().getColor(R.color.red));
				} else {
					money4.setTextColor(getResources().getColor(R.color.green));
				}
				// money5
				if (money5.getText().equals("0.0 EUR")) {
					money5.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money5.getText()).contains("-")) {
					money5.setTextColor(getResources().getColor(R.color.red));
				} else {
					money5.setTextColor(getResources().getColor(R.color.green));
				}
				// monry6
				if (money6.getText().equals("0.0 EUR")) {
					money6.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money6.getText()).contains("-")) {
					money6.setTextColor(getResources().getColor(R.color.red));
				} else {
					money6.setTextColor(getResources().getColor(R.color.green));
				}
				// money7
				if (money7.getText().equals("0.0 EUR")) {
					money7.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money7.getText()).contains("-")) {
					money7.setTextColor(getResources().getColor(R.color.red));
				} else {
					money7.setTextColor(getResources().getColor(R.color.green));
				}
				// money8
				if (money8.getText().equals("0.0 EUR")) {
					money8.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money8.getText()).contains("-")) {
					money8.setTextColor(getResources().getColor(R.color.red));
				} else {
					money8.setTextColor(getResources().getColor(R.color.green));
				}
				// mney9
				if (money9.getText().equals("0.0 EUR")) {
					money9.setTextColor(getResources().getColor(R.color.orange));
				} else if (((String) money9.getText()).contains("-")) {
					money9.setTextColor(getResources().getColor(R.color.red));
				} else {
					money9.setTextColor(getResources().getColor(R.color.green));
				}
				// money10
				if (money10.getText().equals("0.0 EUR")) {
					money10.setTextColor(getResources()
							.getColor(R.color.orange));
				} else if (((String) money10.getText()).contains("-")) {
					money10.setTextColor(getResources().getColor(R.color.red));
				} else {
					money10.setTextColor(getResources().getColor(R.color.green));
				}

				// Hier muss die Umsatzanzeige gesetzt werden.
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		// TextView money = (TextView) rootView.findViewById(R.id.umsatzmoney);
		// money.setText(LoginActivity.credentials.getString(
		// "transaction_1_1_money", ""));

		// "transaction" + "_" + transNr+ "_" + intStr + "_" + "money"
		// "transaction" + "_" + transNr+ "_" + intStr + "_" + "date"
		// "transaction" + "_" + transNr+ "_" + intStr + "_" + "counterAccount"
		// "transaction" + "_" + transNr+ "_" + intStr + "_" +
		// "transactionText_0"
		// "transaction" + "_" + transNr+ "_" + intStr + "_" +
		// "transactionText_1"
		// "transaction" + "_" + transNr+ "_" + intStr + "_" +
		// "transactionText_2"
		// "transaction" + "_" + transNr+ "_" + intStr + "_" +
		// "transactionText_3"

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
