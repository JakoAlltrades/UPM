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


import com._17od.upm.util.Translator;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AboutDialog extends EscapeDialog {

    private static final long serialVersionUID = 1L;

    public AboutDialog(Scene frame) {
        super(frame, Translator.translate("aboutUPM"), true);

        String version = AboutDialog.class.getPackage().getImplementationVersion();
        if (version == null) {
            version = "<version unknown>";
        }
       
        
        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        TextArea textArea = new TextArea();
        StringBuffer aboutText = new StringBuffer();
        aboutText.append("Universal Password Manager\n");
        aboutText.append(version);
        aboutText.append("\n\n");
        aboutText.append("Copyright \u00a9 2005-2013 Adrian Smith & Contributors\n\n");
        aboutText.append("adrian@17od.com\n");
        aboutText.append("http://upm.sourceforge.net");
        textArea.setText(aboutText.toString());
        //jTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        //align center
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", 12));
        //textArea.setBorder(new Border());
        final Stage stage = new Stage();
        Scene scene = new Scene(textArea);
        stage.setScene(scene);
//        panel.add(textArea);
        //panel.add(new JSeparator());
        Pane pane = new Pane();
        pane.getChildren().add(scene);
        //panel.add(Box.createRigidArea(new Dimension(0, 10)));

        Button okButton = new Button(Translator.translate("ok"));
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
			public void handle(Event arg0) {
				try{
					stage.close();
				} catch(Exception e){
					
				}
			}
        	
        });
//        okButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                setVisible(false);
//                dispose();
//            }
//        });
        //okButtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.getChildren().add(okButton);
        Pane innerPane = new Pane();
        pane.getChildren().add(innerPane);
        //setDefaultButton(okButton);
        //getContentPane().add(panel);
        //setResizable(false);
        
    }

}
