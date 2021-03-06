package com.superbank;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.superbank.R;

public class MeineKonten extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static MeineKonten newInstance(int sectionNumber) {
		MeineKonten fragment = new MeineKonten();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public MeineKonten() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_meinekonten,
				container, false);

		TableLayout summentable = (TableLayout) rootView
				.findViewById(R.id.gesamtsumme);
		if (!MainActivity.summe.equals("0.0 EUR")) {
			summentable.setBackgroundResource(R.color.green);
		}
		if (MainActivity.summe.equals("0.0 EUR")) {
			summentable.setBackgroundResource(R.color.orange);
		}
		if (MainActivity.summe.contains("-")) {
			summentable.setBackgroundResource(R.color.red);
		}
		// vorsichtshalber "fehlerhafte" konten loeschen
		for (int i = 1; i < 4; i++) {
			if (LoginActivity.credentials.getString("bankName_" + i, "")
					.contains("()")) {
				Utility.deleteKonto(i);
			}
		}

		// 1.Konto
		if (LoginActivity.credentials.getString("bankName_1", "") != ""
				&& LoginActivity.credentials.getString("kontoGuthaben_1", "") != "") {
			TextView kontostand1 = (TextView) rootView
					.findViewById(R.id.kontostand1);
			kontostand1.setText(MainActivity.kontostand1);

			TextView kontonummer1 = (TextView) rootView
					.findViewById(R.id.kontonummer1);
			kontonummer1.setText(MainActivity.kontonummer1);

			TextView bankname1 = (TextView) rootView
					.findViewById(R.id.bankname1);
			bankname1.setText(MainActivity.bankname1);

			TextView blz1 = (TextView) rootView.findViewById(R.id.blz1);
			blz1.setText(MainActivity.blz1);

			ImageView update1 = (ImageView) rootView
					.findViewById(R.id.updatekonto1);
			update1.setVisibility(View.VISIBLE);
			ImageView umsatz1 = (ImageView) rootView
					.findViewById(R.id.umsatzkonto1);
			umsatz1.setVisibility(View.VISIBLE);

			android.view.ViewGroup.LayoutParams lp;
			View trennstrich1 = rootView.findViewById(R.id.trennstrich1);
			lp = trennstrich1.getLayoutParams();
			lp.height = 1;
			trennstrich1.setLayoutParams(lp);
		}
		// 1. Konto Ende

		// 2.Konto
		if (LoginActivity.credentials.getString("bankName_2", "") != ""
				&& LoginActivity.credentials.getString("kontoGuthaben_2", "") != "") {
			TextView kontostand2 = (TextView) rootView
					.findViewById(R.id.kontostand2);
			kontostand2.setText(MainActivity.kontostand2);

			TextView kontonummer2 = (TextView) rootView
					.findViewById(R.id.kontonummer2);
			kontonummer2.setText(MainActivity.kontonummer2);

			TextView bankname2 = (TextView) rootView
					.findViewById(R.id.bankname2);
			bankname2.setText(MainActivity.bankname2);

			TextView blz2 = (TextView) rootView.findViewById(R.id.blz2);
			blz2.setText(MainActivity.blz2);

			ImageView update2 = (ImageView) rootView
					.findViewById(R.id.updatekonto2);
			update2.setVisibility(View.VISIBLE);
			ImageView umsatz2 = (ImageView) rootView
					.findViewById(R.id.umsatzkonto2);
			umsatz2.setVisibility(View.VISIBLE);

			android.view.ViewGroup.LayoutParams lp;
			View trennstrich2 = rootView.findViewById(R.id.trennstrich2);
			lp = trennstrich2.getLayoutParams();
			lp.height = 1;
			trennstrich2.setLayoutParams(lp);
		}
		// 2. Konto Ende

		// 3.Konto
		if (LoginActivity.credentials.getString("bankName_3", "") != ""
				&& LoginActivity.credentials.getString("kontoGuthaben_3", "") != "") {
			TextView kontostand3 = (TextView) rootView
					.findViewById(R.id.kontostand3);
			kontostand3.setText(MainActivity.kontostand3);

			TextView kontonummer3 = (TextView) rootView
					.findViewById(R.id.kontonummer3);
			kontonummer3.setText(MainActivity.kontonummer3);

			TextView bankname3 = (TextView) rootView
					.findViewById(R.id.bankname3);
			bankname3.setText(MainActivity.bankname3);

			TextView blz3 = (TextView) rootView.findViewById(R.id.blz3);
			blz3.setText(MainActivity.blz3);

			ImageView update3 = (ImageView) rootView
					.findViewById(R.id.updatekonto3);
			update3.setVisibility(View.VISIBLE);
			ImageView umsatz3 = (ImageView) rootView
					.findViewById(R.id.umsatzkonto3);
			umsatz3.setVisibility(View.VISIBLE);

			android.view.ViewGroup.LayoutParams lp;
			View trennstrich3 = rootView.findViewById(R.id.trennstrich3);
			lp = trennstrich3.getLayoutParams();
			lp.height = 1;
			trennstrich3.setLayoutParams(lp);
		}
		// 3. Konto Ende

		// Datum und Summe
		TextView datum = (TextView) rootView.findViewById(R.id.datum);
		datum.setText(printSimpleDateFormat());

		TextView summe = (TextView) rootView.findViewById(R.id.summe);
		summe.setText(MainActivity.summe);

		((MainActivity) getActivity()).setTitle("Meine Konten");
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	static String printSimpleDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy.MM.dd - HH:mm:ss ");
		Date currentTime = new Date();
		return formatter.format(currentTime);
	}

}
