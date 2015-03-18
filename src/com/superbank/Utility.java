package com.superbank;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public class Utility {

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
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in, "UTF-8"), 8192);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long startT = System.currentTimeMillis();

		HbciAccount a = new HbciAccount("max_emmert", "61050000", w3cDoc);
		a.setVersion(HbciVersion.PLUS);
		a.setCredentials(new HbciCredentials());
		a.setAccountNumber("1000482517"); // TODO: kann man bestimmt wieder
											// rausnehmen!!

		a.getCredentials().setPin("PASSWORD");
		long accountT = System.currentTimeMillis();

		HbciSession session = (HbciSession) a.createHbciSession();
		long sessionT = System.currentTimeMillis();

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
		long loginT = System.currentTimeMillis();

		session.acquireBalance();

		session.acquireTransactions();

		Transactions transactions = a.getTransactions();

		Balance balance = a.getBalance();
		long balanceT = System.currentTimeMillis();

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
		System.out.println("\n");
		System.out.println("_END_");

		System.out.println("Total execution time balance: "
				+ (balanceT - startT));
		System.out.println("Total execution time login: " + (loginT - startT));
		System.out.println("Total execution time session: "
				+ (sessionT - startT));
		System.out.println("Total execution time account: "
				+ (accountT - startT));

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
}
