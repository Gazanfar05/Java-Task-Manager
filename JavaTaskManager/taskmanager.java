import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class TaskManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> taskList = new JList<>(listModel);
    private final JTextField taskInput = new JTextField(20);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManager().createAndShowUI());
    }

    private void createAndShowUI() {
        JFrame frame = new JFrame("Java Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Task:"));
        topPanel.add(taskInput);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addTask());
        topPanel.add(addButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton doneButton = new JButton("Mark Done");
        doneButton.addActionListener(e -> markTaskDone());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteTask());

        bottomPanel.add(doneButton);
        bottomPanel.add(deleteButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addTask() {
        String name = taskInput.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a task name.");
            return;
        }

        tasks.add(new Task(name));
        taskInput.setText("");
        refreshTasks();
    }

    private void markTaskDone() {
        int index = taskList.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Select a task first.");
            return;
        }

        tasks.get(index).markDone();
        refreshTasks();
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Select a task first.");
            return;
        }

        tasks.remove(index);
        refreshTasks();
    }

    private void refreshTasks() {
        listModel.clear();
        for (Task task : tasks) {
            listModel.addElement(task.toString());
        }
    }
}