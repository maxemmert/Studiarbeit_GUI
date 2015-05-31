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

				// Felder Umsatz 1
				TextView transactiontext1 = (TextView) rootView
						.findViewById(R.id.umsatztransactiontext1);
				TextView money1 = (TextView) rootView
						.findViewById(R.id.umsatzmoney1);
				TextView counterAccount1 = (TextView) rootView
						.findViewById(R.id.umsatzcounterAccount1);
				TextView date1 = (TextView) rootView
						.findViewById(R.id.umsatzdate1);

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

				// -----------------------Bank 1
				// trans1
				String transaction_1_1_money = "transaction_1_1_money";
				String transaction_1_1_date = "transaction_1_1_date";
				String transaction_1_1_counterAccount = "transaction_1_1_counterAccount";
				String transaction_1_1_transactionText_0 = "transaction_1_1_transactionText_0";
				String transaction_1_1_transactionText_1 = "transaction_1_1_transactionText_1";
				String transaction_1_1_transactionText_2 = "transaction_1_1_transactionText_2";
				String transaction_1_1_transactionText_3 = "transaction_1_1_transactionText_3";

				// trans2
				String transaction_2_1_money = "transaction_2_1_money";
				String transaction_2_1_date = "transaction_2_1_date";
				String transaction_2_1_counterAccount = "transaction_2_1_counterAccount";
				String transaction_2_1_transactionText_0 = "transaction_2_1_transactionText_0";
				String transaction_2_1_transactionText_1 = "transaction_2_1_transactionText_1";
				String transaction_2_1_transactionText_2 = "transaction_2_1_transactionText_2";
				String transaction_2_1_transactionText_3 = "transaction_2_1_transactionText_3";

				// -----------------------Bank 2
				// trans1
				String transaction_1_2_money = "transaction_1_2_money";
				String transaction_1_2_date = "transaction_1_2_date";
				String transaction_1_2_counterAccount = "transaction_1_2_counterAccount";
				String transaction_1_2_transactionText_0 = "transaction_1_2_transactionText_0";
				String transaction_1_2_transactionText_1 = "transaction_1_2_transactionText_1";
				String transaction_1_2_transactionText_2 = "transaction_1_2_transactionText_2";
				String transaction_1_2_transactionText_3 = "transaction_1_2_transactionText_3";

				// trans2
				String transaction_2_2_money = "transaction_2_2_money";
				String transaction_2_2_date = "transaction_2_2_date";
				String transaction_2_2_counterAccount = "transaction_2_2_counterAccount";
				String transaction_2_2_transactionText_0 = "transaction_2_2_transactionText_0";
				String transaction_2_2_transactionText_1 = "transaction_2_2_transactionText_1";
				String transaction_2_2_transactionText_2 = "transaction_2_2_transactionText_2";
				String transaction_2_2_transactionText_3 = "transaction_2_2_transactionText_3";

				// -----------------------Bank 3
				String transaction_1_3_money = "transaction_1_3_money";
				String transaction_1_3_date = "transaction_1_3_date";
				String transaction_1_3_counterAccount = "transaction_1_2_counterAccount";

				String transaction_2_3_money = "transaction_2_3_money";
				String transaction_2_3_date = "transaction_2_3_date";
				String transaction_2_3_counterAccount = "transaction_2_2_counterAccount";

				// Werte setzen TO DO

				if (i == 0) {
					// trans1
					money1.setText(LoginActivity.credentials.getString(
							transaction_1_1_money, ""));
					date1.setText(LoginActivity.credentials.getString(
							transaction_1_1_date, ""));
					counterAccount1.setText(LoginActivity.credentials
							.getString(transaction_1_1_counterAccount, ""));
					transactiontext1.setText(LoginActivity.credentials
							.getString(transaction_1_1_transactionText_1, ""));

					// trans2
					money2.setText(LoginActivity.credentials.getString(
							transaction_2_1_money, ""));
					date2.setText(LoginActivity.credentials.getString(
							transaction_2_1_date, ""));
					counterAccount2.setText(LoginActivity.credentials
							.getString(transaction_2_1_counterAccount, ""));
					transactiontext2.setText(LoginActivity.credentials
							.getString(transaction_2_1_transactionText_0 + "\n"
									+ transaction_2_1_transactionText_1 + "\n"
									+ transaction_2_1_transactionText_2 + "\n"
									+ transaction_2_1_transactionText_3, ""));

				}
				if (i == 1) {
					// trans1
					money1.setText(LoginActivity.credentials.getString(
							transaction_1_2_money, ""));
					date1.setText(LoginActivity.credentials.getString(
							transaction_1_2_date, ""));
					counterAccount1.setText(LoginActivity.credentials
							.getString(transaction_1_2_counterAccount, ""));
					transactiontext1.setText(LoginActivity.credentials
							.getString(transaction_1_2_transactionText_0 + "\n"
									+ transaction_1_2_transactionText_1 + "\n"
									+ transaction_1_2_transactionText_2 + "\n"
									+ transaction_1_2_transactionText_3, ""));

					// trans2
					money2.setText(LoginActivity.credentials.getString(
							transaction_2_2_money, ""));
					date2.setText(LoginActivity.credentials.getString(
							transaction_2_2_date, ""));
					counterAccount2.setText(LoginActivity.credentials
							.getString(transaction_2_2_counterAccount, ""));
					transactiontext2.setText(LoginActivity.credentials
							.getString(transaction_2_2_transactionText_0 + "\n"
									+ transaction_2_2_transactionText_1 + "\n"
									+ transaction_2_2_transactionText_2 + "\n"
									+ transaction_2_2_transactionText_3, ""));
				}

				if (i == 2) {
					money1.setText(LoginActivity.credentials.getString(
							transaction_1_3_money, ""));
				}
				System.out.println("i " + i);

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
