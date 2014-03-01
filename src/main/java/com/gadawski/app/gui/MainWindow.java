package com.gadawski.app.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    private JFrame m_frame;
    private JRadioButton m_radioButtonUseDb;
    private JTextField m_noCars;
    private JTextField m_noCustomers;
    private JTextField m_noHouses;
    private JLabel m_warningMsgLabel;
    private JPanel panel_2;

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
        initializeVisualComponents();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initializeVisualComponents() {
        m_frame = new JFrame();
        m_frame.setBounds(100, 100, 334, 298);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel panel = setUpMainPanel();
        final JPanel panel_1 = setUpCenterPanel(panel);

        setUpFireButton(panel);
        setUpParameterFields(panel_1);
        setUpWarningLabel(panel_1);
        setUpGenerateButton(panel);
        setUpRadioButton(panel);
        setUpCloseButton(panel);
    }

    private JPanel setUpMainPanel() {
        panel_2 = new JPanel();
        m_frame.getContentPane().add(panel_2, BorderLayout.CENTER);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[] { 55, 0 };
        gbl_panel_2.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
        gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
                0.0, Double.MIN_VALUE };
        panel_2.setLayout(gbl_panel_2);
        return panel_2;
    }

    private JPanel setUpCenterPanel(final JPanel panel) {
        JPanel panel_1 = new JPanel();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panel.add(panel_1, gridBagConstraints);
        panel_1.setLayout(new GridLayout(4, 2, 0, 0));
        return panel_1;
    }

    private void setUpFireButton(final JPanel panel) {
        final JButton btnFireButton = new JButton("Fire!");
        btnFireButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final ObjectsFromFileExample app = new ObjectsFromFileExample(
                        m_radioButtonUseDb.isSelected());
                try {
                    app.start();
                } catch (IOException exception) {
                    showWarningMessage(exception);
                }
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel.add(btnFireButton, gridBagConstraints);
    }

    private void showWarningMessage(Exception exception) {
        m_warningMsgLabel.setVisible(true);
        m_warningMsgLabel.setText("<html><font color='red'>"
                + exception.getMessage() + "</font></html>");
    }

    private void setUpParameterFields(JPanel panel_1) {
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
    }

    private void setUpGenerateButton(final JPanel panel) {
        final JButton btnGenerateRandomData = new JButton(
                GENERATE_RANDOM_DATA_TO_FILE);
        btnGenerateRandomData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent arg0) {
                try {
                    ObjectsRandomizer randomizer = new ObjectsRandomizer(
                            ObjectsFromFileExample.GENERATED_DATA_RESOURCES_PATH);
                    randomizer.generateData(
                            Integer.valueOf(m_noCustomers.getText()),
                            Integer.valueOf(m_noCars.getText()),
                            Integer.valueOf(m_noHouses.getText()));
                } catch (NumberFormatException e) {
                    showWarningMessage(e);
                } catch (FileNotFoundException e) {
                    showWarningMessage(e);
                }
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        panel.add(btnGenerateRandomData, gridBagConstraints);
    }

    private void setUpCloseButton(final JPanel panel) {
        final JButton btnClose = new JButton(CLOSE);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        panel.add(btnClose, gridBagConstraints);
    }

    private void setUpWarningLabel(final JPanel panel) {
        m_warningMsgLabel = new JLabel();
        GridBagConstraints gbc_m_warningMsgLabel = new GridBagConstraints();
        panel.add(m_warningMsgLabel, gbc_m_warningMsgLabel);
        m_warningMsgLabel.setVisible(false);
    }

    private void setUpRadioButton(final JPanel panel) {
        m_radioButtonUseDb = new JRadioButton(USE_DB);
        m_radioButtonUseDb.setSelected(true);
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        panel.add(m_radioButtonUseDb, gridBagConstraints);
    }
}
