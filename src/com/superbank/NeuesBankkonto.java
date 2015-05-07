package com.superbank;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.superbank.R;

public class NeuesBankkonto extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private static Spinner tan;
	private static Spinner filter;
	private static Spinner land;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static NeuesBankkonto newInstance(int sectionNumber) {
		NeuesBankkonto fragment = new NeuesBankkonto();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public NeuesBankkonto() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_neuesbankkonto,
				container, false);
		((MainActivity) getActivity()).setTitle("Neues Bankkonto anlegen");
		tan = (Spinner) rootView.findViewById(R.id.tanmethode_spinner);
		filter = (Spinner) rootView.findViewById(R.id.filtertypspinner);
		land = (Spinner) rootView.findViewById(R.id.landspinner);

		ArrayAdapter<CharSequence> landAdapter = ArrayAdapter
				.createFromResource(getActivity().getBaseContext(),
						R.array.land_spinner_items,
						android.R.layout.simple_spinner_item);
		landAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		land.setAdapter(landAdapter);

		ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter
				.createFromResource(getActivity().getBaseContext(),
						R.array.filter_spinner_items,
						android.R.layout.simple_spinner_item);
		filterAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		filter.setAdapter(filterAdapter);

		ArrayAdapter<CharSequence> tanAdapter = ArrayAdapter
				.createFromResource(getActivity().getBaseContext(),
						R.array.tan_spinner_items,
						android.R.layout.simple_spinner_item);
		tanAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		tan.setAdapter(tanAdapter);

		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
