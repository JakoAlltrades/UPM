/*
 * Universal Password Manager
 * Copyright (C) 2005-2013 Adrian Smith
 *
 * This file is part of Universal Password Manager.
 *
 * Universal Password Manager is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Universal Password Manager is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Universal Password Manager; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package com._17od.upm.gui.JavaFX;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.event.HyperlinkEvent.EventType;

import org.apache.commons.codec.binary.Base64;

import javafx.*;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com._17od.upm.util.Preferences;
import com._17od.upm.util.Translator;
import com._17od.upm.util.Util;

public class OptionsDialog extends EscapeDialog {

	private static final long serialVersionUID = 1L;

	private TextField dbToLoadOnStartup;
	private CheckBox enableProxyCheckbox;
	private CheckBox hideAccountPasswordCheckbox;
	private CheckBox inclEscCharstoPassCheckbox;
	private CheckBox storeWindowPosCheckbox;
	private CheckBox appAlwaysonTopCheckbox;
	private Label accountPasswordLengthLabel;
	private TextField accountPasswordLength;
	private TextField httpProxyHost;
	private TextField httpProxyPort;
	private TextField httpProxyUsername;
	private PasswordField httpProxyPassword;
	private CheckBox hidePasswordCheckbox;
	private CheckBox databaseAutoLockCheckbox;
	private TextField databaseAutoLockTime;
	private CheckBox acceptSelfSignedCertsCheckbox;
	private Label proxyLabel;
	private Label proxyPortLabel;
	private Label proxyUsernameLabel;
	private Label proxyPasswordLabel;
	private ComboBox localeComboBox;
	private boolean okClicked = false;
	private Scene parentFrame;
	private boolean languageChanged;
	private char defaultEchoChar;
	private int locale;

	public OptionsDialog(Scene frame) {
		super(frame, Translator.translate("options"), true);

		//Container container = getContentPane();

		// Create a pane with an empty border for spacing
		//Border emptyBorder = new Border();
		Pane emptyBorderPanel = new Pane();
		//emptyBorderPanel.setLayout(new BoxLayout(emptyBorderPanel, BoxLayout.Y_AXIS));
		//emptyBorderPanel.setBorder(emptyBorder);
		//container.add(emptyBorderPanel);

		// ******************
		// *** The DB TO Load On Startup Section
		// ******************
		// Create a pane with an title etched border
		//Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
//		Border etchedTitleBorder = BorderFactory.createTitledBorder(etchedBorder,
//				' ' + Translator.translate("general") + ' ');
		Pane mainPanel = new Pane();
//		mainPanel.setBorder(etchedTitleBorder);
		emptyBorderPanel.getChildren().add(mainPanel);

		//GridBagConstraints c = new GridBagConstraints();

		// The "Database to Load on Startup" row
		Label urlLabel = new Label(Translator.translate("dbToLoadOnStartup"));
//		c.gridx = 0;
//		c.gridy = 0;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 3, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(urlLabel);

		// The "Database to Load on Startup" input field row
		dbToLoadOnStartup = new TextField(Preferences.get(Preferences.ApplicationOptions.DB_TO_LOAD_ON_STARTUP));
		//dbToLoadOnStartup.setHorizontalAlignment(JTextField.LEFT);
//		c.gridx = 0;
//		c.gridy = 1;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 5);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.getChildren().add(dbToLoadOnStartup);

		Button dbToLoadOnStartupButton = new Button("...");
		dbToLoadOnStartupButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler(){
			public void handle(Event arg0) {
				getDBToLoadOnStartup();
			}
        	
        });
//		dbToLoadOnStartupButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				getDBToLoadOnStartup();
//			}
//		});
//		c.gridx = 1;
//		c.gridy = 1;
//		c.anchor = GridBagConstraints.LINE_END;
//		c.insets = new Insets(0, 0, 5, 5);
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(dbToLoadOnStartupButton);

		// The "Language" label row
		Label localeLabel = new Label(Translator.translate("language"));
//		c.gridx = 0;
//		c.gridy = 2;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 3, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(localeLabel);

		// The "Locale" field row
		Object[] object= getSupportedLocaleNames();
		ObservableList list = null;
		list.addAll(object);
		localeComboBox = new ComboBox(list);
		for (int i = 0; i < localeComboBox.getItems().size(); i++) {
			// If the locale language is blank then set it to the English
			// language
			// I'm not sure why this happens. Maybe it's because the default
			// locale
			// is English???
			String currentLanguage = Translator.getCurrentLocale().getLanguage();
			if (currentLanguage.equals("")) {
				currentLanguage = "en";
			}

			if (currentLanguage.equals(Translator.SUPPORTED_LOCALES[i].getLanguage())) {
//				localeComboBox.
//				localeComboBox.setSelectedIndex(i);
				locale = i;
				break;
			}
		}
//		c.gridx = 0;
//		c.gridy = 3;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 8, 5);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.getChildren().add(localeComboBox);

		// The "Hide account password" row
		Boolean hideAccountPassword = new Boolean(
				Preferences.get(Preferences.ApplicationOptions.ACCOUNT_HIDE_PASSWORD, "true"));
		hideAccountPasswordCheckbox = new CheckBox(Translator.translate("hideAccountPassword"));
//		c.gridx = 0;
//		c.gridy = 4;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(hideAccountPasswordCheckbox);

		// The "Database auto lock" row
		Boolean databaseAutoLock = new Boolean(
				Preferences.get(Preferences.ApplicationOptions.DATABASE_AUTO_LOCK, "false"));
		databaseAutoLockCheckbox = new CheckBox(Translator.translate("databaseAutoLock"));
//		c.gridx = 0;
//		c.gridy = 5;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(databaseAutoLockCheckbox);
//		databaseAutoLockCheckbox.addEventHandler(EventType.ACTIVATED, new EventHandler(){
//			public void handle(Event arg0) {
				getDBToLoadOnStartup();
//			}
//        	
//        });
//		databaseAutoLockCheckbox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				databaseAutoLockTime.setEnabled(e.getStateChange() == ItemEvent.SELECTED);
//			}
//		});

		// The "Database auto lock" field row
		databaseAutoLockTime = new TextField(Preferences.get(Preferences.ApplicationOptions.DATABASE_AUTO_LOCK_TIME));
//		c.gridx = 1;
//		c.gridy = 5;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.getChildren().add(databaseAutoLockTime);
		//databaseAutoLockTime.setEnabled(databaseAutoLockCheckbox.isSelected());

		// The "Generated password length" row
		accountPasswordLengthLabel = new Label(Translator.translate("generatedPasswodLength"));
//		c.gridx = 0;
//		c.gridy = 6;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(accountPasswordLengthLabel);

		accountPasswordLength = new TextField(Preferences.get(Preferences.ApplicationOptions.ACCOUNT_PASSWORD_LENGTH, "8"));
//		c.gridx = 1;
//		c.gridy = 6;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
		mainPanel.getChildren().add(accountPasswordLength);

		// The "Include Escape Characters to Generated Passwords" row
		Boolean inclEscCharstoPass = new Boolean(
				Preferences.get(Preferences.ApplicationOptions.INCLUDE_ESCAPE_CHARACTERS, "true"));
		inclEscCharstoPassCheckbox = new CheckBox((Translator.translate("includePunctuationCharacters")));
//		c.gridx = 0;
//		c.gridy = 7;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(inclEscCharstoPassCheckbox);

		// The "Store Window position" row
		Boolean storeWindowPos = Boolean
				.valueOf(Preferences.get(Preferences.ApplicationOptions.REMEMBER_WINDOW_POSITION, "false"));
		storeWindowPosCheckbox = new CheckBox((Translator.translate("storeWindowPosition")));
//		c.gridx = 0;
//		c.gridy = 8;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(storeWindowPosCheckbox);

		// The "Application always on top" row
		Boolean appAlwaysonTop = new Boolean(
				Preferences.get(Preferences.ApplicationOptions.MAINWINDOW_ALWAYS_ON_TOP, "false"));
		appAlwaysonTopCheckbox = new CheckBox((Translator.translate("applicationAlwaysonTop")));
//		c.gridx = 0;
//		c.gridy = 9;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		mainPanel.getChildren().add(appAlwaysonTopCheckbox);

		// Some spacing
		//emptyBorderPanel.add(Box.createVerticalGlue());

		// ******************
		// *** The HTTPS Section
		// ******************
		// Create a pane with an title etched border
		//Border httpsEtchedTitleBorder = BorderFactory.createTitledBorder(etchedBorder, " HTTPS ");
		final Pane httpsPanel = new Pane();
		//httpsPanel.setBorder(httpsEtchedTitleBorder);
		//emptyBorderPanel.add(httpsPanel);

		// The "Accept Self Sigend Certificates" checkbox row
		Boolean acceptSelfSignedCerts = new Boolean(
				Preferences.get(Preferences.ApplicationOptions.HTTPS_ACCEPT_SELFSIGNED_CERTS, "false"));
		acceptSelfSignedCertsCheckbox = new CheckBox(Translator.translate("acceptSelfSignedCerts"));
//		c.gridx = 0;
//		c.gridy = 0;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
		httpsPanel.getChildren().add(acceptSelfSignedCertsCheckbox);

		// ******************
		// *** The Proxy Section
		// ******************
		// Create a pane with an title etched border
		//Border proxyEtchedTitleBorder = BorderFactory.createTitledBorder(etchedBorder,
		//		' ' + Translator.translate("httpProxy") + ' ');
		final Pane proxyPanel = new Pane();
//		proxyPanel.setBorder(proxyEtchedTitleBorder);
//		emptyBorderPanel.add(proxyPanel);

		// The "Enable Proxy" row
		Boolean proxyEnabled = new Boolean(Preferences.get(Preferences.ApplicationOptions.HTTP_PROXY_ENABLED));
		enableProxyCheckbox = new CheckBox(Translator.translate("enableProxy"));
		enableProxyCheckbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){

			public void handle(Event arg0) {
				enableProxyComponents(true);
			}
		});
	
//		c.gridx = 0;
//		c.gridy = 0;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 2, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		proxyPanel.getChildren().add(enableProxyCheckbox);

		// The "HTTP Proxy" label row
		proxyLabel = new Label(Translator.translate("httpProxy"));
//		c.gridx = 0;
//		c.gridy = 1;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 3, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.NONE;
		proxyPanel.getChildren().add(proxyLabel);

		// The "HTTP Proxy Port" label
		proxyPortLabel = new Label(Translator.translate("port"));
//		c.gridx = 1;
//		c.gridy = 1;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 3, 5);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		proxyPanel.getChildren().add(proxyPortLabel);

		// The "HTTP Proxy" field row
		httpProxyHost = new TextField(Preferences.get(Preferences.ApplicationOptions.HTTP_PROXY_HOST));
//		c.gridx = 0;
//		c.gridy = 2;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
		proxyPanel.getChildren().add(httpProxyHost);

		httpProxyPort = new TextField(Preferences.get(Preferences.ApplicationOptions.HTTP_PROXY_PORT));
//		c.gridx = 1;
//		c.gridy = 2;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 5);
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
		proxyPanel.getChildren().add(httpProxyPort);

		// The "HTTP Proxy Username" label row
		proxyUsernameLabel = new Label(Translator.translate("httpProxyUsername"));
//		c.gridx = 0;
//		c.gridy = 3;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 3, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.NONE;
		proxyPanel.getChildren().add(proxyUsernameLabel);

		// The "HTTP Proxy Username" field row
		httpProxyUsername = new TextField(Preferences.get(Preferences.ApplicationOptions.HTTP_PROXY_USERNAME));
//		c.gridx = 0;
//		c.gridy = 4;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 5);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.HORIZONTAL;
		proxyPanel.getChildren().add(httpProxyUsername);

		// The "HTTP Proxy Password" label row
		proxyPasswordLabel = new Label(Translator.translate("httpProxyPassword"));
//		c.gridx = 0;
//		c.gridy = 5;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 3, 0);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 2;
//		c.fill = GridBagConstraints.NONE;
		proxyPanel.getChildren().add(proxyPasswordLabel);

		// The "HTTP Proxy Password" field row
		String encodedPassword = Preferences.get(Preferences.ApplicationOptions.HTTP_PROXY_PASSWORD);
		String decodedPassword = null;
		if (encodedPassword != null) {
			decodedPassword = new String(Base64.decodeBase64(encodedPassword.getBytes()));
		}
		httpProxyPassword = new PasswordField();
		httpProxyPassword.appendText(decodedPassword);
//		c.gridx = 0;
//		c.gridy = 6;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 5);
//		c.weightx = 1;
//		c.weighty = 0;
//		c.gridwidth = 1;
		//c.fill = GridBagConstraints.HORIZONTAL;
		proxyPanel.getChildren().add(httpProxyPassword );

		hidePasswordCheckbox = new CheckBox(Translator.translate("hide"));
		defaultEchoChar = httpProxyPassword.getCharacters().charAt(0);
//		hidePasswordCheckbox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					httpProxyPassword.setEchoChar(defaultEchoChar);
//				} else {
//					httpProxyPassword.setEchoChar((char) 0);
//				}
//			}
//		});
//		c.gridx = 1;
//		c.gridy = 6;
//		c.anchor = GridBagConstraints.LINE_START;
//		c.insets = new Insets(0, 5, 5, 0);
//		c.weightx = 0;
//		c.weighty = 0;
//		c.gridwidth = 1;
//		c.fill = GridBagConstraints.NONE;
		proxyPanel.getChildren().add(hidePasswordCheckbox);

		// Some spacing
		//emptyBorderPanel.add(Box.createVerticalGlue());

		// The buttons row
		Pane buttonPanel = new Pane();
		emptyBorderPanel.getChildren().add(buttonPanel);
		Button okButton = new Button(Translator.translate("ok"));
		// Link Enter key to okButton
		//getRootPane().setDefaultButton(okButton);
		okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){

			public void handle(Event arg0) {
				okButtonAction();
			}
		});
		
		buttonPanel.getChildren().add(okButton);

		Button cancelButton = new Button(Translator.translate("cancel"));
		cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){

			public void handle(Event arg0) {
				//maybey close here
			}
		});
		
		buttonPanel.getChildren().add(cancelButton);

		enableProxyComponents(proxyEnabled.booleanValue());
	}

	private void enableProxyComponents(boolean enable) {
//		httpProxyHost.setEnabled(enable);
//		httpProxyPort.setEnabled(enable);
//		httpProxyUsername.setEnabled(enable);
//		httpProxyPassword.setEnabled(enable);
//		proxyLabel.setEnabled(enable);
//		proxyPortLabel.setEnabled(enable);
//		proxyUsernameLabel.setEnabled(enable);
//		proxyPasswordLabel.setEnabled(enable);
//		hidePasswordCheckbox.setEnabled(enable);
	}

	public boolean okClicked() {
		return okClicked;
	}

	private void okButtonAction() {
		try {
			if (databaseAutoLockCheckbox.isSelected()) {
				if (databaseAutoLockTime.getText() == null || databaseAutoLockTime.getText().trim().equals("")
						|| !Util.isNumeric(databaseAutoLockTime.getText())) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle(Translator.translate("invalidValueForDatabaseAutoLockTime"));
					alert.setHeaderText(Translator.translate("problem"));
					alert.setContentText("Error:"+Alert.AlertType.ERROR);

					alert.showAndWait();
					return;
				}
			}

			if (accountPasswordLength.getText() == null || accountPasswordLength.getText().trim().equals("")
					|| !Util.isNumeric(accountPasswordLength.getText())) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle(Translator.translate("invalidValueForAccountPasswordLength"));
				alert.setHeaderText(Translator.translate("problem"));
				alert.setContentText("Error:"+Alert.AlertType.ERROR);

				alert.showAndWait();
				return;
			}

			Preferences.set(Preferences.ApplicationOptions.DB_TO_LOAD_ON_STARTUP, dbToLoadOnStartup.getText());
			Preferences.set(Preferences.ApplicationOptions.ACCOUNT_HIDE_PASSWORD,
					String.valueOf(hideAccountPasswordCheckbox.isSelected()));
			Preferences.set(Preferences.ApplicationOptions.INCLUDE_ESCAPE_CHARACTERS,
					String.valueOf(inclEscCharstoPassCheckbox.isSelected()));
			Preferences.set(Preferences.ApplicationOptions.REMEMBER_WINDOW_POSITION,
					String.valueOf(storeWindowPosCheckbox.isSelected()));
			Preferences.set(Preferences.ApplicationOptions.MAINWINDOW_ALWAYS_ON_TOP,
					String.valueOf(appAlwaysonTopCheckbox.isSelected()));
			Preferences.set(Preferences.ApplicationOptions.DATABASE_AUTO_LOCK,
					String.valueOf(databaseAutoLockCheckbox.isSelected()));
			Preferences.set(Preferences.ApplicationOptions.DATABASE_AUTO_LOCK_TIME, databaseAutoLockTime.getText());
			Preferences.set(Preferences.ApplicationOptions.ACCOUNT_PASSWORD_LENGTH, accountPasswordLength.getText());
			Preferences.set(Preferences.ApplicationOptions.HTTPS_ACCEPT_SELFSIGNED_CERTS,
					String.valueOf(acceptSelfSignedCertsCheckbox.isSelected()));
			Preferences.set(Preferences.ApplicationOptions.HTTP_PROXY_HOST, httpProxyHost.getText());
			Preferences.set(Preferences.ApplicationOptions.HTTP_PROXY_PORT, httpProxyPort.getText());
			Preferences.set(Preferences.ApplicationOptions.HTTP_PROXY_USERNAME, httpProxyUsername.getText());
			String encodedPassword = new String(
					Base64.encodeBase64(new String(httpProxyPassword.getText()).getBytes()));
			Preferences.set(Preferences.ApplicationOptions.HTTP_PROXY_PASSWORD, encodedPassword);
			Preferences.set(Preferences.ApplicationOptions.HTTP_PROXY_ENABLED,
					String.valueOf(enableProxyCheckbox.isSelected()));

			MainWindow.setAppAlwaysonTop(appAlwaysonTopCheckbox.isSelected());

			// Save the new language and set a flag if it has changed
			String beforeLocale = Preferences.get(Preferences.ApplicationOptions.LOCALE);
			Locale selectedLocale = Translator.SUPPORTED_LOCALES[locale];
			String afterLocale = selectedLocale.getLanguage();
			if (!afterLocale.equals(beforeLocale)) {
				Preferences.set(Preferences.ApplicationOptions.LOCALE, selectedLocale.getLanguage());
				Translator.loadBundle(selectedLocale);
				languageChanged = true;
			}

			Preferences.save();
//			setVisible(false);
//			dispose();
			okClicked = true;
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle(Translator.translate("error"));
			alert.setHeaderText(Translator.translate("problem"));
			alert.setContentText("Error:"+Alert.AlertType.ERROR);

			alert.showAndWait();
		}
	}

	private void getDBToLoadOnStartup() {
		FileChooser fc = new FileChooser();
		fc.setTitle(Translator.translate("dbToOpenOnStartup"));
		File returnVal = fc.showSaveDialog(window);

		if (returnVal != null) {
			File databaseFile =  fc.showSaveDialog(window);
			dbToLoadOnStartup.setText(databaseFile.getAbsoluteFile().toString());
		}
	}

	private Object[] getSupportedLocaleNames() {
		Object[] names = new Object[Translator.SUPPORTED_LOCALES.length];

		for (int i = 0; i < Translator.SUPPORTED_LOCALES.length; i++) {
			names[i] = Translator.SUPPORTED_LOCALES[i].getDisplayLanguage(Translator.getCurrentLocale()) + " ("
					+ Translator.SUPPORTED_LOCALES[i].getDisplayLanguage(Translator.SUPPORTED_LOCALES[i]) + ')';
		}

		return names;
	}

	public boolean hasLanguageChanged() {
		return languageChanged;
	}

}