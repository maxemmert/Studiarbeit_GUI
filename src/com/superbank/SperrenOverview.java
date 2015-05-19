package com.superbank;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.superbank.R;

public class SperrenOverview extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static SperrenOverview newInstance(int sectionNumber) {
		SperrenOverview fragment = new SperrenOverview();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public SperrenOverview() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_sperrenoverview,
				container, false);
		((MainActivity) getActivity()).setTitle("Sperrrufnummern");
		SharedPreferences rufnummerCollection = getActivity()
				.getSharedPreferences(MainActivity.MyRUFNUMMERN,
						Context.MODE_PRIVATE);

		final LinearLayout lm = (LinearLayout) rootView
				.findViewById(R.id.linearlayoutsperrenoverview);

		// Create numbers
		if (rufnummerCollection != null) {
			for (int j = 1; j <= rufnummerCollection.getAll().size() / 2 + 1; j++) {
				// Create LinearLayout
				LinearLayout ll = new LinearLayout(this.getActivity());
				ll.setOrientation(LinearLayout.HORIZONTAL);

				// Create TextView
				TextView institut = new TextView(this.getActivity());
				institut.setText(rufnummerCollection.getString("institut_" + j,
						"") + "    ");
				ll.addView(institut);

				// Create TextView
				TextView rufnummer = new TextView(this.getActivity());
				rufnummer.setText(rufnummerCollection.getString("rufnummer_"
						+ j, ""));
				ll.addView(rufnummer);

				// Add to LinearLayout defined in XML
				lm.addView(ll);

			}
		}
		if (MainActivity.RufnummernAreDeleted == true) {
			LinearLayout ll = new LinearLayout(this.getActivity());
			ll.setOrientation(LinearLayout.HORIZONTAL);

			// Create TextView
			TextView noNumber = new TextView(this.getActivity());
			noNumber.setText("Keine Rufnummern hinterlegt");
			ll.addView(noNumber);
			lm.addView(ll);
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
