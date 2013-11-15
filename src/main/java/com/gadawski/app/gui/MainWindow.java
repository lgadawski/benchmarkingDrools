package com.gadawski.app.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gadawski.app.ObjectsFromFileExample;
import com.gadawski.app.util.ObjectsRandomizer;

/**
 * Creates default window for generating and firing rules.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class MainWindow {
	/**
	 * Frame.
	 */
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		final JButton btnNewButton = new JButton("Fire!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final ObjectsFromFileExample app = new ObjectsFromFileExample();
				app.startInferencing();
			}
		});
		panel.add(btnNewButton);

		final JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});

		final JButton btnGenerateRandomData = new JButton(
				"Generate random data to file");
		btnGenerateRandomData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				ObjectsRandomizer.generateData("data/" + "generatedData.dat");
			}
		});
		panel.add(btnGenerateRandomData);
		panel.add(btnClose);
	}
}
