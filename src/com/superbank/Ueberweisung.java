package com.superbank;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.superbank.R;

public class Ueberweisung extends Fragment {

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static Ueberweisung newInstance(int sectionNumber) {
		Ueberweisung fragment = new Ueberweisung();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public Ueberweisung() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_ueberweisung,
				container, false);

		if (MainActivity.bankname1 != null) {
			RadioButton radioKto1 = (RadioButton) rootView
					.findViewById(R.id.radiokonto1);
			radioKto1.setText(MainActivity.bankname1);
			radioKto1.setVisibility(View.VISIBLE);
		}

		if (MainActivity.bankname2 != null) {
			RadioButton radioKto2 = (RadioButton) rootView
					.findViewById(R.id.radiokonto2);
			radioKto2.setText(MainActivity.bankname2);
			radioKto2.setVisibility(View.VISIBLE);
		}

		if (MainActivity.bankname3 != null) {
			RadioButton radioKto3 = (RadioButton) rootView
					.findViewById(R.id.radiokonto3);
			radioKto3.setText(MainActivity.bankname3);
			radioKto3.setVisibility(View.VISIBLE);
		}

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
