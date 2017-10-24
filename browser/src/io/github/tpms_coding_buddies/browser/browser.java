package io.github.tpms_coding_buddies.browser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.net.*;
import java.io.*;

public class Browser {
	private JFrame frame;
	private JPanel panelTop;
	private JEditorPane editor;
	private JScrollPane scroll;
	private JTextField field;
	private JButton button;
	private URL url;

	public Browser(String title, String urlString) {
  	frame = new JFrame();
  	panelTop = new JPanel();

  	try {
   	url = new URL(urlString);
  	} catch (MalformedURLException e) {
   	JOptionPane.showMessageDialog(null, e);
  	}

  	//create the JEditorPane
  	try {
   	editor = new JEditorPane(url);
   	editor.setEditable(false);
  	} catch (IOException e) {
   	JOptionPane.showMessageDialog(frame, e);
  	}

  	//create the scroll pane and add the JEditorPane to it.
  	scroll = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

  	//create the JTextField
  	field = new JTextField();

  	//set the JTextField text to the url.
  	//we're not doing this on the event dispatch thread, so we need to use
  	//SwingUtilities.
  	SwingUtilities.invokeLater(new Runnable() {
   	public void run() {
    	field.setText(url.toString());
   	}
  	});
  	editor.addHyperlinkListener(new HyperlinkListener() {
   	public void hyperlinkUpdate(HyperlinkEvent e) {
     	if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
      	try {
       	editor.setPage(e.getURL());
      	} catch (IOException ioe) {
      	JOptionPane.showMessageDialog(null, ioe);
     	}
    	} //end hyperlinkUpdate()
  	}); //end HyperlinkListener
  	//create the button for chanign pages.
  	button = new JButton("Go");

  	//add action listener to the button
  	button.addActionListener(new ActionListener() {
   	public void actionPerformed(ActionEvent e) {
    	try {
     	editor.setPage(field.getText());
    	} catch (IOException ioe) {
     	JOptionPane.showMessageDialog(null, ioe);
    	}
   	}
  	});
  	frame.setTitle(title);
  	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	frame.setSize(800, 600);

  	frame.add(BorderLayout.NORTH, panelTop);
  	panelTop.add(field);
  	panelTop.add(button);
  	frame.add(BorderLayout.CENTER, scroll);

  	frame.setVisible(true);
 	} //end Browser() constructor

	public static void main(String[] args) {
  	SwingUtilities.invokeLater(() -> {
   	new Browser("Simple web browser", "http://tinyurl.com/tpmschat")
  	});
 	} //end main method.
} //end Browser class
