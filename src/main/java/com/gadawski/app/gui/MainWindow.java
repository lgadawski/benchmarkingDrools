package com.gadawski.app.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.gadawski.app.ObjectsFromFileExample;
import com.gadawski.app.util.ObjectsRandomizer;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
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
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Fire!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectsFromFileExample app = new ObjectsFromFileExample();
				app.startInferencing();
			}
		});
		panel.add(btnNewButton);
		
		JTextArea txtrFiringTime = new JTextArea();
		txtrFiringTime.setText("Firing time");
		panel.add(txtrFiringTime);
		
		JTextArea txtrtime = new JTextArea();
		txtrtime.setText("--time--");
		panel.add(txtrtime);
		
		JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JButton btnGenerateRandomData = new JButton("Generate random data to file");
		btnGenerateRandomData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObjectsRandomizer.generateData("data/" + "generatedData.dat");
			}
		});
		panel.add(btnGenerateRandomData);
		panel.add(btnClose);
	}

}
