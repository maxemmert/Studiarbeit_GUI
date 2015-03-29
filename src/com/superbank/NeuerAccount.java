package com.superbank;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.superbank.R;

public class NeuerAccount extends Activity {

	private EditText username;
	private EditText password;
	private EditText repeatpassword;

	// Create User credentials and store them
	static SharedPreferences credentials;
	public static final String MyCREDENTIALS = "MyCreds";
	public static final String credentialsN = "name";
	public static final String credentialsP = "password";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_neueraccount);
		username = (EditText) findViewById(R.id.initialbenutzername);
		password = (EditText) findViewById(R.id.initialbenutzerpin);
		repeatpassword = (EditText) findViewById(R.id.repeatinitialbenutzerpin);
	}

	// create new credentials
	public void createUser(String u, String p) {
		credentials = getSharedPreferences(MyCREDENTIALS, Context.MODE_PRIVATE);
		Editor editor = credentials.edit();
		editor.putString(credentialsN, u);
		editor.putString(credentialsP, p);
		editor.commit();
	}

	// wenn auf ausführen geklickt wird
	public void ausfuehren(View vi) {
		String u = username.getText().toString();
		String p = password.getText().toString();
		String rp = repeatpassword.getText().toString();

		// öffne SuperBank, wenn neue Accountdaten stimmig sind
		if (!u.equals("") && !p.equals("") && !rp.equals("") && p.equals(rp)) {
			createUser(u, p);
			alertSuccess();
			Intent i = new Intent(this, com.superbank.LoginActivity.class);
			startActivity(i);
		} else
			alertWrongCredentials();
	}

	private void alertSuccess() {
		new AlertDialog.Builder(this)
				.setTitle("Meldung")
				.setMessage("Erfolgreich registriert. Bitte einloggen.")
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continue
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	private void alertWrongCredentials() {
		new AlertDialog.Builder(this)
				.setTitle("Meldung")
				.setMessage(
						"Daten leer oder fehlerhaft. Bitte erneut versuchen.")
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continue
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

}
