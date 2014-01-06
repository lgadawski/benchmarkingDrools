package com.gadawski.app.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.gadawski.app.ObjectsFromFileExample;
import com.gadawski.util.ObjectsRandomizer;

/**
 * Creates default window for generating and firing rules.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class MainWindow {
    private static final String CLOSE = "close";
    private static final String USE_DB = "Use DB";
    private static final String GENERATE_RANDOM_DATA_TO_FILE = "Generate random data to file";
    private static final String NUMBER_OF_HOUSES = "Number of Houses:";
    private static final String NUMBER_OF_CARS = "Number of Cars:";
    private static final String NUMBER_OF_CUSTOMERS = "Number of Customers:";
    public final static String path = "D:\\_development\\workspace\\example\\util\\src\\main\\resources\\data\\generatedData.dat";
    /**
     * Frame.
     */
    private JFrame m_frame;
    private JRadioButton m_radioButtonUseDb;
    private JTextField m_noCars;
    private JTextField m_noCustomers;
    private JTextField m_noHouses;

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    final MainWindow window = new MainWindow();
                    window.m_frame.setVisible(true);
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
        m_frame = new JFrame();
        m_frame.setBounds(100, 100, 278, 269);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = new JPanel();
        m_frame.getContentPane().add(panel, BorderLayout.EAST);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 55, 0 };
        gbl_panel.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        final JButton btnNewButton = new JButton("Fire!");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final ObjectsFromFileExample app = new ObjectsFromFileExample(
                        m_radioButtonUseDb.isSelected());
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

        GridBagConstraints gbc_btnSaveObjTo = new GridBagConstraints();
        gbc_btnSaveObjTo.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnSaveObjTo.anchor = GridBagConstraints.NORTH;
        gbc_btnSaveObjTo.insets = new Insets(0, 0, 5, 0);
        gbc_btnSaveObjTo.gridx = 0;
        gbc_btnSaveObjTo.gridy = 2;

        final JButton btnClose = new JButton(CLOSE);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 2;
        panel.add(panel_1, gbc_panel_1);
        panel_1.setLayout(new GridLayout(3, 3, 0, 0));

        JLabel lblNoCustomers = new JLabel(NUMBER_OF_CUSTOMERS);
        panel_1.add(lblNoCustomers);

        m_noCustomers = new JTextField();
        m_noCustomers.setText("10");
        panel_1.add(m_noCustomers);
        m_noCustomers.setColumns(10);

        JLabel lblNumberOfCars = new JLabel(NUMBER_OF_CARS);
        panel_1.add(lblNumberOfCars);

        m_noCars = new JTextField();
        m_noCars.setText("10");
        panel_1.add(m_noCars);
        m_noCars.setColumns(10);

        JLabel lblNoCars = new JLabel(NUMBER_OF_HOUSES);
        panel_1.add(lblNoCars);

        m_noHouses = new JTextField();
        m_noHouses.setText("10");
        m_noHouses.setColumns(10);
        panel_1.add(m_noHouses);

        final JButton btnGenerateRandomData = new JButton(
                GENERATE_RANDOM_DATA_TO_FILE);
        btnGenerateRandomData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                ObjectsRandomizer.generateData(path,
                        Integer.valueOf(m_noCustomers.getText()),
                        Integer.valueOf(m_noCars.getText()),
                        Integer.valueOf(m_noHouses.getText()));
            }
        });
        GridBagConstraints gbc_btnGenerateRandomData = new GridBagConstraints();
        gbc_btnGenerateRandomData.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnGenerateRandomData.anchor = GridBagConstraints.NORTH;
        gbc_btnGenerateRandomData.insets = new Insets(0, 0, 5, 0);
        gbc_btnGenerateRandomData.gridx = 0;
        gbc_btnGenerateRandomData.gridy = 3;
        panel.add(btnGenerateRandomData, gbc_btnGenerateRandomData);

        m_radioButtonUseDb = new JRadioButton(USE_DB);
        m_radioButtonUseDb.setSelected(true);
        GridBagConstraints gbc_rdbtnUseDb = new GridBagConstraints();
        gbc_rdbtnUseDb.insets = new Insets(0, 0, 5, 0);
        gbc_rdbtnUseDb.gridx = 0;
        gbc_rdbtnUseDb.gridy = 4;
        panel.add(m_radioButtonUseDb, gbc_rdbtnUseDb);
        GridBagConstraints gbc_btnClose = new GridBagConstraints();
        gbc_btnClose.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnClose.anchor = GridBagConstraints.NORTH;
        gbc_btnClose.gridx = 0;
        gbc_btnClose.gridy = 5;
        panel.add(btnClose, gbc_btnClose);
    }
}
