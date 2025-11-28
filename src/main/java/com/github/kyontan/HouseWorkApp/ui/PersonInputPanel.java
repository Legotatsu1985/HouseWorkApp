package com.github.kyontan.HouseWorkApp.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PersonInputPanel extends JPanel {

    private JTextField nameField;
    private DefaultListModel<String> listModel;
    private JList<String> nameList;
    private ArrayList<String> participants;
    private JButton nextButton;

    public PersonInputPanel() {
        participants = new ArrayList<>();
        setLayout(new BorderLayout());

        //========上部：入力欄========
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("名前を入力");
        nameField = new JTextField();

        JButton addButton = new JButton("追加");
        addButton.addActionListener(e -> addPerson());

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(new JLabel()); //空白
        inputPanel.add(addButton);

        //========中央：リスト表示========
        listModel = new DefaultListModel<>();
        nameList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(nameList);

        //========下部：次へ進むボタン========
        nextButton = new JButton("次へ");
        nextButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "次の画面へ進みます");
        });

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(nextButton, BorderLayout.SOUTH);
    }

    private void addPerson() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "名前を入力してください！");
            return;
        }

        participants.add(name);
        listModel.addElement(name);
        nameField.setText("");
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public JButton getNextButton() {
        return nextButton;
    }
}
