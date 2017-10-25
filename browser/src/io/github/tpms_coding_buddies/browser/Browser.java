package io.github.tpms_coding_buddies.browser;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Browser {
	private JFrame frame;
	private JPanel panelTop;
	private JEditorPane editor;
	private JScrollPane scroll;
	private JTextField field;
	private JButton button;
	private URL url;

	public Browser(String title) {
		frame = new JFrame();
		panelTop = new JPanel();

		try {
			url = new URL("http://tinyurl.com/tpmschat");
		} catch (MalformedURLException ex) {
			JOptionPane.showMessageDialog(null, ex);
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
		SwingUtilities.invokeLater(() -> field.setText(url.toString()));
		//end hyperlinkUpdate()
		editor.addHyperlinkListener(e -> {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				try {
					editor.setPage(e.getURL());
					field.setText(e.getURL().toString());
				} catch (IOException ioe) {
					JOptionPane.showMessageDialog(null, ioe);
				}
		});//end HyperlinkListener
		//create the button for changing pages.
		button = new JButton("Go");

		//add action listener to the button
		button.addActionListener(e -> {
			try {
				editor.setPage(field.getText());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex);
			}
		});

		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);

		frame.add(BorderLayout.NORTH, panelTop);
		panelTop.add(field);
		panelTop.add(button);
		frame.add(BorderLayout.CENTER, scroll);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException ex) {
			JOptionPane.showMessageDialog(null, ex);
		}

		frame.setVisible(true);
	}//end Browser() constructor

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Browser("Simple web browser"));
	}//end main method.
}//end Browser class