package com.superbank;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.superbank.R;

public class LoginActivity extends Activity {

	Button btnStartMainActivity;
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private final Handler progressBarHandler = new Handler();
	private long delay = 0;

	// Create a session
	static SharedPreferences sharedpreferences;
	private EditText username;
	private EditText password;
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String name = "nameKey";
	public static final String pass = "passwordKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_login);
		username = (EditText) findViewById(R.id.benutzername);
		password = (EditText) findViewById(R.id.benutzerpin);
	}

	@Override
	protected void onResume() {
		SharedPreferences credentials = getSharedPreferences(
				NeuerAccount.MyCREDENTIALS, Context.MODE_PRIVATE);
		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);
		if (!credentials.contains(NeuerAccount.credentialsN)) {
			firstOpen();
		}
		super.onResume();
	}

	// wenn auf "Neuer Account" getippt wird
	public void erstelleAcount(View vi) {
		SharedPreferences credentials = getSharedPreferences(
				NeuerAccount.MyCREDENTIALS, Context.MODE_PRIVATE);

		if (credentials.getString(NeuerAccount.credentialsP, null) == null) {
			Intent intent = new Intent(this, com.superbank.NeuerAccount.class);
			startActivity(intent);
		} else {
			alertKontoBereitsRegistriert();
		}
	}

	// wenn auf "Einloggen" getippt wird
	public void login(View view) {
		SharedPreferences credentials = getSharedPreferences(
				NeuerAccount.MyCREDENTIALS, Context.MODE_PRIVATE);
		Editor editor = sharedpreferences.edit();
		String u = username.getText().toString();
		String p = password.getText().toString();
		editor.putString(name, u);
		editor.putString(pass, p);
		editor.commit();
		if (u.equals(credentials.getString(NeuerAccount.credentialsN, null))
				&& p.equals(credentials.getString(NeuerAccount.credentialsP,
						null)) && !u.equals("") && !p.equals("")) {
			delayAndWelcome(view);
			username.setText("");
			password.setText("");
			Intent i = new Intent(this, com.superbank.MainActivity.class);
			startActivity(i);
		} else {
			alertWrongCredentials();
		}
	}

	public void delayAndWelcome(View view) {

		progressBar = new ProgressDialog(view.getContext());
		progressBar.setCancelable(true);
		progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressBar.setProgress(0);
		progressBar.setMessage("willkommen");
		progressBar.show();
		progressBarStatus = 0;
		delay = 0;

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (progressBarStatus < 100) {

					// task
					progressBarStatus = delayAndStartApp();

					// a little delay ;)
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// Update the progress bar
					progressBarHandler.post(new Runnable() {
						@Override
						public void run() {
							progressBar.setProgress(progressBarStatus);
						}
					});
				}

				// ok, file is downloaded,
				if (progressBarStatus >= 100) {

					// next delay ;)
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// close proressbar
					progressBar.dismiss();
				}
			}
		}).start();
	}

	private void alertWrongCredentials() {
		new AlertDialog.Builder(this)
				.setTitle("Meldung")
				.setMessage(
						"Zugangsdaten leer oder fehlerhaft. Bitte erneut versuchen.")
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continue
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	private void alertKontoBereitsRegistriert() {
		new AlertDialog.Builder(this)
				.setTitle("Meldung")
				.setMessage("Sie haben bereits ein Konto registriert.")
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continue
							}
						}).setIcon(android.R.drawable.ic_dialog_alert).show();
	}

	private void firstOpen() {
		new AlertDialog.Builder(this)
				.setTitle("Willkommen")
				.setMessage(
						"Dies ist ihr erster Aufruf von SuperBank. Bitte legen Sie "
								+ "sich zunächst einen Account an. Das Team von SuperBank wünscht viel Spaß mit der App.")
				.setNeutralButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// continue
							}
						}).setIcon(android.R.drawable.ic_dialog_info).show();
	}

	// delay!
	public int delayAndStartApp() {

		while (delay <= 1000000) {
			delay++;
			if (delay == 100000) {
				return 10;
			} else if (delay == 200000) {
				return 20;
			} else if (delay == 300000) {
				return 30;
			} else if (delay == 400000) {
				return 40;
			} else if (delay == 500000) {
				return 50;
			}
		}
		return 100;
	}
}
