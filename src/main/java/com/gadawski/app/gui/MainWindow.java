package com.gadawski.app.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gadawski.app.App;
import com.gadawski.app.ObjectsFromFileExample;
import com.gadawski.util.ObjectsRandomizer;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
    public final static String path = 
            "D:\\_development\\workspace\\example\\util\\src\\main\\resources\\data\\generatedData.dat";

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
		frame.setBounds(100, 100, 188, 161);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{55, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
						
								final JButton btnGenerateRandomData = new JButton(
										"Generate random data to file");
								btnGenerateRandomData.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(final ActionEvent arg0) {
										ObjectsRandomizer.generateData(path );
									}
								});
								
										final JButton btnNewButton = new JButton("Fire!");
										btnNewButton.addActionListener(new ActionListener() {
											public void actionPerformed(final ActionEvent e) {
												final ObjectsFromFileExample app = new ObjectsFromFileExample();
												app.startInferencing();
											}
										});
										GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
										gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
										gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
										gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
										gbc_btnNewButton.gridx = 0;
										gbc_btnNewButton.gridy = 0;
										panel.add(btnNewButton, gbc_btnNewButton);
								GridBagConstraints gbc_btnGenerateRandomData = new GridBagConstraints();
								gbc_btnGenerateRandomData.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnGenerateRandomData.anchor = GridBagConstraints.NORTH;
								gbc_btnGenerateRandomData.insets = new Insets(0, 0, 5, 0);
								gbc_btnGenerateRandomData.gridx = 0;
								gbc_btnGenerateRandomData.gridy = 1;
								panel.add(btnGenerateRandomData, gbc_btnGenerateRandomData);
						
						JButton btnSaveObjTo = new JButton("Save obj to DB");
						btnSaveObjTo.setEnabled(false);
						btnSaveObjTo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								App.main(null);
							}
						});
						GridBagConstraints gbc_btnSaveObjTo = new GridBagConstraints();
						gbc_btnSaveObjTo.fill = GridBagConstraints.HORIZONTAL;
						gbc_btnSaveObjTo.anchor = GridBagConstraints.NORTH;
						gbc_btnSaveObjTo.insets = new Insets(0, 0, 5, 0);
						gbc_btnSaveObjTo.gridx = 0;
						gbc_btnSaveObjTo.gridy = 2;
						panel.add(btnSaveObjTo, gbc_btnSaveObjTo);
						
								final JButton btnClose = new JButton("close");
								btnClose.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(final ActionEvent e) {
										System.exit(0);
									}
								});
								GridBagConstraints gbc_btnClose = new GridBagConstraints();
								gbc_btnClose.fill = GridBagConstraints.HORIZONTAL;
								gbc_btnClose.anchor = GridBagConstraints.NORTH;
								gbc_btnClose.gridx = 0;
								gbc_btnClose.gridy = 3;
								panel.add(btnClose, gbc_btnClose);
	}
}
