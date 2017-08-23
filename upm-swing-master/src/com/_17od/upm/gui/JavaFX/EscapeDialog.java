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

import javafx.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class EscapeDialog {

    private static final long serialVersionUID = 1L;
    Stage dialog;
    String title;
    Scene display;
    boolean modal;
    public EscapeDialog(Scene frame, String title, boolean modal) {
        dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        display = frame;
        display.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler() 
        {
			public void handle(Event event) {
				// TODO Auto-generated method stub
				KeyEvent e = (KeyEvent) event;
				if(e.getCode() == KeyCode.ESCAPE)
        		{
        			dialog.close();
        		}
				
			}
        });
        dialog.setScene(display);
        dialog.show();
    }
    

    public EscapeDialog(Scene frame, boolean modal) {
    	dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        display = frame;
        display.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler() 
        {
			public void handle(Event event) {
				// TODO Auto-generated method stub
				KeyEvent e = (KeyEvent) event;
				if(e.getCode() == KeyCode.ESCAPE)
        		{
        			dialog.close();
        		}
				
			}
        });
        dialog.setScene(display);
        dialog.show();
    }


    protected Pane createPane() {
       
        Pane rootPane = new Pane();
        return rootPane;
    }

}
