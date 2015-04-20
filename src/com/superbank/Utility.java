package com.superbank;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import name.studiarbeit.hbci.AccountReference;
import name.studiarbeit.hbci.Balance;
import name.studiarbeit.hbci.Money;
import name.studiarbeit.hbci.Transaction;
import name.studiarbeit.hbci.Transactions;
import name.studiarbeit.hbci.impl.HbciAccount;
import name.studiarbeit.hbci.impl.HbciCredentials;
import name.studiarbeit.hbci.impl.HbciSession;
import name.studiarbeit.hbci.impl.HbciVersion;

import org.dom4j.DocumentException;
import org.dom4j.io.DOMWriter;
import org.dom4j.io.SAXReader;
import org.kapott.hbci.manager.HBCIUtils;
import org.kapott.hbci.manager.HBCIUtilsInternal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import android.content.SharedPreferences.Editor;

//		Diese Credentials werden für jedes konto i(intStr) gesetzt. 
//
// 		"hbciHost" + "_" + intStr
//		"hbciServletUrl" + "_" + intStr
//		"hbciHostVersion" + "_" + intStr
//		"hbciServletUrlVersion" + "_" + intStr
//		"kontoUsername" + "_" + intStr
//		"kontoPasswd" + "_" + intStr
//		"currentTanMethod" + "_" + intStr
//		"filterTyp" + "_" + intStr // Base64 oder None
//		"hbciHost" + "_" + intStr
//		"laenderKennung" + "_" + intStr
//		"kontoNummer" + "_" + intStr
//		"bankleitzahl" + "_" + intStr
//		"transaction" + "_" + transNr+ "_" + intStr + "_" + "money"
//		"transaction" + "_" + transNr+ "_" + intStr + "_" + "date"
//		"transaction" + "_" + transNr+ "_" + intStr + "_" + "counterAccount"
//		"transaction" + "_" + transNr+ "_" + intStr + "_" + "transactionText_0"
//		"transaction" + "_" + transNr+ "_" + intStr + "_" + "transactionText_1"
//		"transaction" + "_" + transNr+ "_" + intStr + "_" + "transactionText_2"
//		"transaction" + "_" + transNr+ "_" + intStr + "_" + "transactionText_3"
//		"bankName" + "_" + intStr"

public class Utility {
	// Bei DDV bzw. RDH
	public static String hbciHost;
	public static Object hbciHostVersion;
	// Bei PinTan
	public static String hbciServletUrl;
	public static Object hbciServletUrlVersion;
	// Bankname - Bankort
	public static String bankName;
	private static String blzData;
	private static String bankLocation;

	public static String getStringFromFile() throws Exception {
		ClassLoader cl = MeineKonten.class.getClassLoader();
		String filename = "hbci-plus.xml";
		InputStream syntaxStream = cl.getResourceAsStream(filename);
		String ret = convertStreamToString(syntaxStream);
		// Make sure you close all streams.
		syntaxStream.close();
		return ret;
	}

	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		reader.close();
		return sb.toString();
	}

	public static org.w3c.dom.Document convertDocument(InputStream stream,
			SAXReader saxReader, org.dom4j.Document dom4jDoc,
			org.w3c.dom.Document w3cDoc) {
		try {
			dom4jDoc = saxReader.read(stream);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			w3cDoc = new DOMWriter().write(dom4jDoc);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return w3cDoc;
	}

	public static String getKontostand(Document w3cDoc) {
		String acct, code, name, securityCode;
		HbciAccount a = new HbciAccount("max_emmert", "61050000", w3cDoc);
		a.setVersion(HbciVersion.PLUS);
		a.setCredentials(new HbciCredentials());
		a.setAccountNumber("1000482517"); // TODO: kann man bestimmt wieder
											// rausnehmen!!

		a.getCredentials().setPin("PASSWORD");

		HbciSession session = (HbciSession) a.createHbciSession();

		String blz = "61050000";
		String country = "DE";
		String userId = "BENUTZER";
		int port = 443;
		String filterType = "Base64";
		String host = "hbci-pintan-bw.s-hbci.de/PinTanServlet";
		String currentTanMethod = "911";
		session.setCredentials(blz, country, userId, port, filterType, host,
				currentTanMethod);

		session.setPassportPath(null);

		session.logIn();

		session.acquireBalance();

		session.acquireTransactions();

		Transactions transactions = a.getTransactions();

		Balance balance = a.getBalance();

		if (transactions != null) {

			Iterator<Transaction> transactionIterator = transactions
					.getIterator();

			int i = 0;
			while (i < 10 && transactionIterator.hasNext()) {
				Transaction currentTransaction = transactionIterator.next();
				// Hole Überweisungsbetrag
				Money money = currentTransaction.getBalance();
				// Hole Überweisungsdatum
				Date date = currentTransaction.getBookingDate();
				// Hole Zielaccount bzw. "Gegenaccount"
				AccountReference counterAccount = currentTransaction
						.getCounterAccount();
				// Hole Überweisungstext als List<String> KP OB ES FUNZT!
				List<String> textList = currentTransaction.getUsageLines();
			}

			System.out.print("Latest Transaction: \n"
					+ transactions.latestTransaction().toString());
		}

		// Timestamp und Money vom Balance-Objekt holen
		if (balance != null) {
			Money money = balance.getAvailable();
			return "Available Money: " + money.toString();
		}
		return "fehler";
	}

	public static HashMap<String, Element> buildHashMap(Document doc) {
		HashMap<String, Element> hm = new HashMap<String, Element>();
		NodeList nodeList = doc.getDocumentElement().getChildNodes();

		for (int i = 0; i <= nodeList.getLength(); i++) {
			if (nodeList.item(i) instanceof Element) {
				NodeList nodeList2 = nodeList.item(i).getChildNodes();
				for (int j = 0; j <= nodeList2.getLength(); j++) {
					if (nodeList2.item(j) instanceof Element) {
						NamedNodeMap attributes = nodeList2.item(j)
								.getAttributes();
						if (attributes != null) {
							if (attributes.getNamedItem("id") != null) {
								hm.put(((Element) nodeList2.item(j))
										.getAttribute("id"),
										(Element) nodeList2.item(j));
							}
						}
					}
				}
			}
		}

		return hm;
	}

	/**
	 * Gibt den Banknamen anhand der blz zurück
	 * 
	 * @param blz
	 * @return name der bank+ort
	 */
	public static String getBankNameForBLZ(String blz) {
		String blzData = HBCIUtilsInternal.getBLZData(blz);
		String bankName = HBCIUtilsInternal.getNthToken(blzData, 1);
		String bankLocation = HBCIUtilsInternal.getNthToken(blzData, 2);
		return bankName + " (" + bankLocation + ")";

	}

	/**
	 * Löscht alle credentials zu dem konto i. Danach sollte man durch
	 * aktualisieren der Page die Konto-Liste aktualisieren.
	 * 
	 * @param i
	 */
	// Trennstriche noch entfernen in Main
	public static void deleteKonto(int i) {
		String intStr = String.valueOf(i);
		Editor editor = LoginActivity.credentials.edit();
		editor.remove("hbciHost" + "_" + intStr);
		editor.remove("hbciServletUrl" + "_" + intStr);
		editor.remove("hbciHostVersion" + "_" + intStr);
		editor.remove("hbciServletUrlVersion" + "_" + intStr);
		editor.remove("kontoUsername" + "_" + intStr);
		editor.remove("kontoPasswd" + "_" + intStr);
		editor.remove("currentTanMethod" + "_" + intStr);
		editor.remove("filterTyp" + "_" + intStr); // Base64 oder None
		editor.remove("hbciHost" + "_" + intStr);
		editor.remove("laenderKennung" + "_" + intStr);
		editor.remove("kontoNummer" + "_" + intStr);
		editor.remove("bankleitzahl" + "_" + intStr);
		editor.remove("transaction" + "_" + intStr + "_" + "money");
		editor.remove("transaction" + "_" + intStr + "_" + "date");
		editor.remove("transaction" + "_" + intStr + "_" + "counterAccount");
		editor.remove("transaction" + "_" + intStr + "_" + "transactionText_0");
		editor.remove("transaction" + "_" + intStr + "_" + "transactionText_1");
		editor.remove("transaction" + "_" + intStr + "_" + "transactionText_2");
		editor.remove("transaction" + "_" + intStr + "_" + "transactionText_3");
		editor.remove("bankName" + "_" + intStr);
	}

	/**
	 * Werte werden als Credentials gespeichert. Für konto i zb hbciHost_1. wir
	 * gehen mal davon aus, dass niemand einen benutzernamen oder ein passwort
	 * haben, die auf _1 enden.
	 * 
	 * @param blz
	 * @param i
	 * @param username
	 * @param passwd
	 */
	public static void newKontoToCredentials(String blz, String kontoNummer,
			String username, String passwd, String currentTanMethod,
			String filterTyp, String laenderKennung, int i) {
		String num = String.valueOf(i);
		Editor editor = LoginActivity.credentials.edit();
		editor.putString("hbciHost" + "_" + num,
				HBCIUtils.getHBCIHostForBLZ(blz));

		StringBuffer sb = new StringBuffer(HBCIUtils.getPinTanURLForBLZ(blz));
		if (sb.indexOf("https://") == 0) {
			sb.delete(0, 8);
		}

		editor.putString("hbciServletUrl" + "_" + num, sb.toString()); // TODO:
																		// beim
																		// debuggen
																		// mal
																		// gucken
																		// ob
																		// pintanurl
																		// und
																		// hbcihost
																		// nicht
																		// vertauscht
																		// wurden
		editor.putString("hbciHostVersion" + "_" + num,
				HBCIUtils.getHBCIVersionForBLZ(blz));
		editor.putString("hbciServletUrlVersion" + "_" + num,
				HBCIUtils.getPinTanVersionForBLZ(blz));
		editor.putString("kontoUsername" + "_" + num, username);
		editor.putString("kontoPasswd" + "_" + num, passwd);
		editor.putString("currentTanMethod" + "_" + num, currentTanMethod);
		editor.putString("filterTyp" + "_" + num, filterTyp);
		editor.putString("laenderKennung" + "_" + num, laenderKennung);
		editor.putString("kontoNummer" + "_" + num, kontoNummer);
		editor.putString("bankleitzahl" + "_" + num, blz);
		editor.putString("bankName" + "_" + num, getBankNameForBLZ(blz));
		editor.commit();
	}

	public static HbciVersion getVersionByString(String str) {
		if (str == "201") {
			return HbciVersion.V201;
		} else if (str == "210") {
			return HbciVersion.V210;
		} else if (str == "220") {
			return HbciVersion.V220;
		} else if (str == "300") {
			return HbciVersion.V300;
		} else
			return HbciVersion.PLUS;
	}

	/**
	 * Gibt false zurück, wenn beim Abfragen des kontostands etwas schief geht.
	 * Gibt true zurück, wenn alles gut ging und setzt die credentials für
	 * umsatz und transaktionen.
	 * 
	 * @param w3cDoc
	 * @param i
	 * @param pintanEnabled
	 * @return
	 */
	public static boolean getKontostandByCredentials(Document w3cDoc, int i,
			boolean pintanEnabled) {
		try {
			HbciAccount a = new HbciAccount(
					LoginActivity.credentials.getString("kontoUsername" + "_"
							+ i, ""), LoginActivity.credentials.getString(
							"bankleitzahl" + "_" + i, ""), w3cDoc);
			if (pintanEnabled) {
				String url = LoginActivity.credentials.getString(
						"hbciServletUrlVersion" + "_" + i, "");
				a.setVersion(getVersionByString(url));
			} else {
				String url = LoginActivity.credentials.getString(
						"hbciHostVersion" + "_" + i, "");
				a.setVersion(getVersionByString(url));
			}

			a.setCredentials(new HbciCredentials());
			a.setAccountNumber(LoginActivity.credentials.getString(
					"kontoNummer" + "_" + i, "")); // TODO: kann man bestimmt
													// wieder
													// rausnehmen!!

			a.getCredentials().setPin(
					LoginActivity.credentials.getString(
							"kontoPasswd" + "_" + i, ""));

			HbciSession session = (HbciSession) a.createHbciSession();

			String blz = LoginActivity.credentials.getString("bankleitzahl"
					+ "_" + i, "");
			String country = LoginActivity.credentials.getString(
					"laenderKennung" + "_" + i, "");
			String userId = LoginActivity.credentials.getString("kontoUsername"
					+ "_" + i, "");
			int port = 443;
			String filterType = LoginActivity.credentials.getString("filterTyp"
					+ "_" + i, "");

			String host = "";
			if (pintanEnabled) {
				host = LoginActivity.credentials.getString("hbciServletUrl"
						+ "_" + i, "");
			} else {
				host = LoginActivity.credentials.getString(
						"hbciHost" + "_" + i, "");
			}
			String currentTanMethod = LoginActivity.credentials.getString(
					"currentTanMethod" + "_" + i, "");
			session.setCredentials(blz, country, userId, port, filterType,
					host, currentTanMethod);

			session.setPassportPath(null);

			session.logIn();

			session.acquireBalance();

			session.acquireTransactions();

			Transactions transactions = a.getTransactions();

			Balance balance = a.getBalance();

			if (transactions != null) {

				Iterator<Transaction> transactionIterator = transactions
						.getIterator();

				// transaction_j_i j=transaktion 1-10 i=konto 1-3
				int j = 0;
				while (j < 10 && transactionIterator.hasNext()) {
					Editor editor = LoginActivity.credentials.edit();

					Transaction currentTransaction = transactionIterator.next();
					// Hole Überweisungsbetrag
					Money money = currentTransaction.getBalance();
					editor.putString("transaction" + "_" + String.valueOf(j)
							+ "_" + String.valueOf(i) + "_" + "money",
							money.toString());
					// Hole Überweisungsdatum
					Date date = currentTransaction.getBookingDate();
					editor.putString("transaction" + "_" + String.valueOf(j)
							+ "_" + String.valueOf(i) + "_" + "date",
							date.toGMTString());
					// Hole Zielaccount bzw. "Gegenaccount"

					AccountReference counterAccount = currentTransaction
							.getCounterAccount();
					editor.putString("transaction" + "_" + String.valueOf(j)
							+ "_" + String.valueOf(i) + "_" + "counterAccount",
							counterAccount.getName());
					// Hole Überweisungstext als List<String> KP OB ES FUNZT!
					List<String> textList = currentTransaction.getUsageLines();
					if (textList != null) {
						if (textList.get(0) != null) {
							editor.putString(
									"transaction" + "_" + String.valueOf(j)
											+ "_" + String.valueOf(i) + "_"
											+ "transactionText_0",
									textList.get(0));
						}
						if (textList.get(1) != null) {
							editor.putString(
									"transaction" + "_" + String.valueOf(j)
											+ "_" + String.valueOf(i) + "_"
											+ "transactionText_1",
									textList.get(1));
						}
						if (textList.get(2) != null) {
							editor.putString(
									"transaction" + "_" + String.valueOf(j)
											+ "_" + String.valueOf(i) + "_"
											+ "transactionText_2",
									textList.get(2));
						}
						if (textList.get(3) != null) {
							editor.putString(
									"transaction" + "_" + String.valueOf(j)
											+ "_" + String.valueOf(i) + "_"
											+ "transactionText_3",
									textList.get(3));
						}
					}
					editor.commit();
				}
			}

			// Timestamp und Money vom Balance-Objekt holen
			if (balance != null) {
				Money money = balance.getAvailable();

				Editor editor = LoginActivity.credentials.edit();
				editor.putString("kontoGuthaben" + "_" + String.valueOf(i),
						money.toString());
				editor.commit();

			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
