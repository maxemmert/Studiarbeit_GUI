package com.superbank;

import org.w3c.dom.Text;

import com.example.superbank.R;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CheckTransaction extends Fragment{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static CheckTransaction newInstance(int sectionNumber) {
        	CheckTransaction fragment = new CheckTransaction();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public CheckTransaction() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_checktransaction, container, false);
            TextView beguenstigter= (TextView) rootView.findViewById(R.id.checkBeguenstigter);
            TextView konto= (TextView) rootView.findViewById(R.id.checkKontonummer);
            TextView blz= (TextView) rootView.findViewById(R.id.checkBlz);
            TextView bank= (TextView) rootView.findViewById(R.id.checkBank);
            TextView betrag= (TextView) rootView.findViewById(R.id.checkBetrag);
            TextView verwendung= (TextView) rootView.findViewById(R.id.checkVerwendung);
            
            beguenstigter.setText(MainActivity.checkBeguenstigter);
            konto.setText(MainActivity.checkKontonummer);
            blz.setText(MainActivity.checkBlz);
            bank.setText(MainActivity.checkBank);
            betrag.setText(MainActivity.checkBetrag);
            verwendung.setText(MainActivity.checkVerwendung);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

}
