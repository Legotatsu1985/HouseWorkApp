package com.github.kyontan.HouseWorkApp.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import com.github.kyontan.HouseWorkApp.model.ScheduleItem;

public class ResultPanel extends JPanel {

    private JButton backButton;
    private JButton calendarButton;  // ← 追加
    private JPanel listPanel;
    private List<ScheduleItem> scheduleItems = new ArrayList<>();


    public ResultPanel() {
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // タイトル
        JLabel title = new JLabel("🌟 家事分担結果");
        title.setFont(new Font("メイリオ", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // 結果表示カード部分
        listPanel = new JPanel();
        listPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(scrollPane, BorderLayout.CENTER);

        // --- ボタンパネル ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        backButton = new JButton("← 戻る");
        backButton.setFont(new Font("メイリオ", Font.PLAIN, 14));

        calendarButton = new JButton("📅 カレンダーで表示");
        calendarButton.setFont(new Font("メイリオ", Font.PLAIN, 14));

        buttonPanel.add(backButton);
        buttonPanel.add(calendarButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setResult(List<String> people, List<String> tasks) {
        listPanel.removeAll();

        // ===== 均等割り当て =====
        Map<String, String> assignment = new LinkedHashMap<>();
        int index = 0;

        for (String task : tasks) {
            assignment.put(task, people.get(index % people.size()));
            index++;
        }

        // ===== 結果カードUI追加 =====
        for (var entry : assignment.entrySet()) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 1));
            panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));

            JLabel taskLabel = new JLabel("🧹 タスク: " + entry.getKey());
            taskLabel.setFont(new Font("メイリオ", Font.BOLD, 15));

            JLabel personLabel = new JLabel("👤 担当: " + entry.getValue());
            personLabel.setFont(new Font("メイリオ", Font.PLAIN, 14));

            panel.add(taskLabel);
            panel.add(personLabel);

            listPanel.add(panel);
        }

        revalidate();
        repaint();
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getCalendarButton() {  // ← MainFrame で使えるよう公開
        return calendarButton;
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

}
