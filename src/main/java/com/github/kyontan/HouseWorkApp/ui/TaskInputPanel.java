package com.github.kyontan.HouseWorkApp.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TaskInputPanel extends JPanel {

    private JTextField taskField;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private ArrayList<String> tasks;
    private JButton backButton;
    private JButton nextButton;

    public TaskInputPanel() {

        tasks = new ArrayList<>();
        setLayout(new BorderLayout());

        //========上部：入力欄========
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel taskLabel = new JLabel("タスク名を入力");
        taskField = new JTextField();

        JButton addButton = new JButton("追加");
        addButton.addActionListener(e -> addTask());

        inputPanel.add(taskLabel);
        inputPanel.add(taskField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);

        //========中央：リスト表示========
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        //========下部：ボタン========
        JPanel buttonPanel = new JPanel(new FlowLayout());

        backButton = new JButton("戻る");
        nextButton = new JButton("次へ");

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addTask() {
        String task = taskField.getText().trim();

        if(task.isEmpty()) {
            JOptionPane.showMessageDialog(this, "タスク名を入力してください！");
            return;
        }

        tasks.add(task);
        listModel.addElement(task);
        taskField.setText("");
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }
}
