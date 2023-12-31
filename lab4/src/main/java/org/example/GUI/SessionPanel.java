package org.example.GUI;

import org.apache.commons.validator.GenericValidator;
import org.example.Logic.ProgramOperation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class SessionPanel extends JPanel implements ActionListener, TableModelListener, ListSelectionListener {
    private String date;
    private String selectedValue;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel NorthPanel;
    private JPanel centerPanel;
    private JTextField dateField;
    private JLabel dateLabel;
    private JComboBox<String> comboBox;
    private JComboBox<String> sorting;
    private JButton addButton;
    private JTextField timeField;
    private JButton deleteButton;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        NorthPanel = new JPanel();
        NorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        NorthPanel.setFocusTraversalPolicyProvider(true);
        Font NorthPanelFont = this.$$$getFont$$$(null, -1, 14, NorthPanel.getFont());
        if (NorthPanelFont != null) NorthPanel.setFont(NorthPanelFont);
        NorthPanel.setPreferredSize(new Dimension(700, 40));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$(null, -1, 14, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("choose session");
        NorthPanel.add(label1);
        comboBox = new JComboBox();
        NorthPanel.add(comboBox);
        dateLabel = new JLabel();
        Font dateLabelFont = this.$$$getFont$$$(null, -1, 14, dateLabel.getFont());
        if (dateLabelFont != null) dateLabel.setFont(dateLabelFont);
        dateLabel.setText("set date");
        NorthPanel.add(dateLabel);
        dateField = new JTextField();
        dateField.setPreferredSize(new Dimension(100, 30));
        dateField.setSelectionStart(8);
        dateField.setText("YYYY-MM-DD");
        dateField.setToolTipText("YYYY-MM-DD");
        dateField.setVerifyInputWhenFocusTarget(true);
        NorthPanel.add(dateField);
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, -1, 14, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("time");
        label2.setToolTipText("");
        NorthPanel.add(label2);
        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(80, 30));
        timeField.setSelectionStart(8);
        timeField.setText("HH:MM:SS");
        timeField.setToolTipText("HH:MM:SS");
        NorthPanel.add(timeField);
        addButton = new JButton();
        addButton.setFocusPainted(false);
        addButton.setFocusable(true);
        addButton.setText("add");
        NorthPanel.add(addButton);
        final JSeparator separator1 = new JSeparator();
        separator1.setBackground(new Color(-16645885));
        separator1.setFocusable(false);
        separator1.setForeground(new Color(-16645885));
        separator1.setOrientation(1);
        separator1.setPreferredSize(new Dimension(15, 0));
        NorthPanel.add(separator1);
        deleteButton = new JButton();
        deleteButton.setFocusPainted(false);
        deleteButton.setText("delete");
        NorthPanel.add(deleteButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return NorthPanel;
    }

    SessionPanel() {
        this.setLayout(new BorderLayout());
        this.add(NorthPanel, BorderLayout.NORTH);
        comboBox.setModel(new DefaultComboBoxModel<>(ProgramOperation.trainingNames()));
        addButton.addActionListener(e -> inputValidator());
        String[] columns = {"training name", "date", "time"};
        String[][] stringArray = ProgramOperation.selectSessions().stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        table = new JTable(stringArray, columns);
        table.setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this);
        table.getModel().addTableModelListener(this);
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(this);
    }

    private void inputValidator() {
        boolean isGood = true;
        if (GenericValidator.isDate(dateField.getText(), "yyyy-MM-dd", true)) {
            dateField.setBorder(null);
        } else {
            dateField.setBorder(BorderFactory.createLineBorder(Color.RED));
            isGood = false;
        }
        if (GenericValidator.isDate(timeField.getText(), "HH:mm:ss", true)) {
            timeField.setBorder(null);
        } else {
            timeField.setBorder(BorderFactory.createLineBorder(Color.RED));
            isGood = false;
        }
        if (isGood) {
            if (!ProgramOperation.addSession((String) comboBox.getSelectedItem(), dateField.getText(), timeField.getText())) {
                JOptionPane.showMessageDialog(this, "Training a few times during one day is unhealthy", "Occupied date", JOptionPane.ERROR_MESSAGE);
                dateField.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        System.out.println(table.getSelectedRow() + table.getSelectedColumn());
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        String newValue = model.getValueAt(row, column).toString();
        System.out.println("previous" + selectedValue);
        System.out.println("new " + newValue);
        if (columnName.equals("training name") && !ProgramOperation.doesTrainingNameExist(newValue)) {
            JOptionPane.showMessageDialog(this, "Training name doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
            model.setValueAt(selectedValue, row, column);
        } else if (columnName.equals("date") && !GenericValidator.isDate(newValue, "yyyy-MM-dd", true)) {
            JOptionPane.showMessageDialog(this, "Invalid date", "Error", JOptionPane.ERROR_MESSAGE);
            model.setValueAt(selectedValue, row, column);
        } else if (columnName.equals("time") && !GenericValidator.isDate(newValue, "HH:mm:ss", true)) {
            JOptionPane.showMessageDialog(this, "Invalid time", "Error", JOptionPane.ERROR_MESSAGE);
            model.setValueAt(selectedValue, row, column);
        } else if (!ProgramOperation.modifySession(newValue, columnName, date)) {
            JOptionPane.showMessageDialog(this, "Training a few times during one day is unhealthy", "Occupied date", JOptionPane.ERROR_MESSAGE);
            model.setValueAt(selectedValue, row, column);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        selectedValue = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
        date = (String) table.getValueAt(table.getSelectedRow(), 1);
        deleteButton.setEnabled(table.getSelectedRow() != -1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            ProgramOperation.deleteSession(date);
//            scrollPane.remove(table);
//            String[] columns = {"training name", "date", "time"};
//            String[][] stringArray = ProgramOperation.selectSessions().stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
//            table = new JTable(stringArray, columns);
//            table.setCellSelectionEnabled(true);
//            table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//            table.getSelectionModel().addListSelectionListener(this);
//            table.getModel().addTableModelListener(this);
//            scrollPane.add(table);
//            scrollPane.repaint();

        }
    }
}
