package com.superbank;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		View rootView = inflater.inflate(R.layout.fragment_umsatzanzeige,
				container, false);
		((MainActivity) getActivity()).setTitle("Umsatzanzeige");
		TextView money = (TextView) rootView.findViewById(R.id.umsatzmoney);
		money.setText(LoginActivity.credentials.getString(
				"transaction_1_1_money", ""));

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
