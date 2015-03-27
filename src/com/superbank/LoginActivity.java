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

	// Create User credentials and store them
	SharedPreferences credentials;
	public static final String MyCREDENTIALS = "MyCreds";
	public static final String credentialsN = "name";
	public static final String credentialsP = "password";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_login);
		username = (EditText) findViewById(R.id.benutzername);
		password = (EditText) findViewById(R.id.benutzerpin);
	}

	@Override
	protected void onResume() {
		sharedpreferences = getSharedPreferences(MyPREFERENCES,
				Context.MODE_PRIVATE);
		credentials = getSharedPreferences(MyCREDENTIALS, Context.MODE_PRIVATE);
		if (!credentials.contains(credentialsN)) {
			firstOpen();
		}
		if (sharedpreferences.contains(name)) {
			if (sharedpreferences.contains(pass)) {
				if (credentials.getString(credentialsN, null).equals(
						sharedpreferences.getString(name, null))
						&& credentials.getString(credentialsP, null).equals(
								sharedpreferences.getString(pass, null))) {
					Intent i = new Intent(this,
							com.superbank.MainActivity.class);
					startActivity(i);
				}
			}
		}
		super.onResume();
	}

	public void login(View view) {
		Editor editor = sharedpreferences.edit();
		String u = username.getText().toString();
		String p = password.getText().toString();
		editor.putString(name, u);
		editor.putString(pass, p);

		if (!credentials.contains(credentialsN)) {
			if (!u.equals("") && !p.equals("")) {
				createUser(u, p);
			}
		}
		editor.commit();

		if (u.equals(credentials.getString(credentialsN, null))
				&& p.equals(credentials.getString(credentialsP, null))
				&& !u.equals("") && !p.equals("")) {
			delayAndWelcome(view);
			username.setText("");
			password.setText("");
			Intent i = new Intent(this, com.superbank.MainActivity.class);
			startActivity(i);
		} else {
			alertWrongCredentials();
		}
	}

	// create new credentials
	public void createUser(String u, String p) {
		credentials = getSharedPreferences(MyCREDENTIALS, Context.MODE_PRIVATE);
		Editor editor = credentials.edit();
		editor.putString(credentialsN, u);
		editor.putString(credentialsP, p);
		editor.commit();
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

	private void firstOpen() {
		new AlertDialog.Builder(this)
				.setTitle("Willkommen")
				.setMessage(
						"Lieber Nutzer, da dies dein erster Aufruf von SuperBank ist, musst du eine PIN hinterlegen, um deine "
								+ "Konten vor unberechtigtem Zugriff zu schützen. Bitte gib dazu einen Benutzernamen und ein "
								+ "Passwort an. Damit loggst du dich zukünftig immer ein. Falls du die Zugangsdaten vergessen hast, "
								+ "schaue bitte in das Benutzerhandbuch der App. Das Team von SuperBank wünscht dir viel Spaß mit der App.")
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
