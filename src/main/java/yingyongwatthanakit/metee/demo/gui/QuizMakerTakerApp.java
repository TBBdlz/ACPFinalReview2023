package yingyongwatthanakit.metee.demo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizMakerTakerApp extends JFrame implements ActionListener {

    protected JPanel mainPanel;
    protected JTabbedPane tabbedPane;
    protected JPanel createQuizPanel, takeQuizPanel;
    protected JButton addQuestionButton, submitQuizButton, takeQuizButton, submitAnswerButton;
    protected JTextField questionTextField;
    protected JRadioButton[] answerRadioButtons;
    protected JSlider difficultySlider;
    protected JComboBox<String> quizDropdown;
    protected JFileChooser addImageFileChooser;
    protected JTextArea questionTextArea;
    protected JProgressBar quizProgressBar;
    protected JTextField[] optionTextFields;
    protected JButton saveQuizButton, loadQuizButton;
    protected ButtonGroup answerGroup;
    protected List<Object> loadedQuizData;


    public QuizMakerTakerApp(String title) {
        super(title);
    }

    protected void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();

        createQuizPanel = new JPanel();
        createQuizPanel.setLayout(new BoxLayout(createQuizPanel, BoxLayout.Y_AXIS));
        takeQuizPanel = new JPanel();
        takeQuizPanel.setLayout(new BoxLayout(takeQuizPanel, BoxLayout.Y_AXIS));

        addQuestionButton = new JButton("Add Question");
        submitQuizButton = new JButton("Submit Quiz");
        takeQuizButton = new JButton("Start Quiz");
        submitAnswerButton = new JButton("Submit Answer");

        questionTextField = new JTextField(20);
        answerRadioButtons = new JRadioButton[4]; // Assuming 4 possible answers
        answerGroup = new ButtonGroup();
        for (int i = 0; i < answerRadioButtons.length; i++) {
            answerRadioButtons[i] = new JRadioButton();
            answerGroup.add(answerRadioButtons[i]);
        }

        difficultySlider = new JSlider(0, 100, 50);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setPaintLabels(true);
        difficultySlider.setMajorTickSpacing(25);

        addImageFileChooser = new JFileChooser();
        quizDropdown = new JComboBox<>();
        questionTextArea = new JTextArea(5, 20);
        quizProgressBar = new JProgressBar(0, 100);

        optionTextFields = new JTextField[4]; // Assuming 4 options
        for (int i = 0; i < optionTextFields.length; i++) {
            optionTextFields[i] = new JTextField(20);
        }

        saveQuizButton = new JButton("Save Quiz");
        saveQuizButton.addActionListener(this);
        loadQuizButton = new JButton("Load Quiz");
        submitAnswerButton = new JButton("Submit Answer");
        loadQuizButton.addActionListener(this);
        submitAnswerButton.addActionListener(this);
    }

    protected void addComponents() {
        createQuizPanel.add(new JLabel("Question:"));
        createQuizPanel.add(questionTextField);
        for (int i = 0; i < optionTextFields.length; i++) {
            JPanel optionPanel = new JPanel();
            optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            optionPanel.add(new JLabel("Option " + (i + 1) + ":"));
            optionPanel.add(optionTextFields[i]);
            optionPanel.add(answerRadioButtons[i]);
            createQuizPanel.add(optionPanel);
        }

        createQuizPanel.add(difficultySlider);
        createQuizPanel.add(new JLabel("Add image:"));
        createQuizPanel.add(addImageFileChooser);
        createQuizPanel.add(saveQuizButton);

        takeQuizPanel.add(new JLabel("Select Quiz:"));
        takeQuizPanel.add(quizDropdown);
        takeQuizPanel.add(questionTextArea);
        takeQuizPanel.add(new JLabel("Answer:"));
        takeQuizPanel.add(loadQuizButton); // Add load quiz button
        takeQuizPanel.add(submitAnswerButton); // Add submit answer button
        for (JRadioButton rb : answerRadioButtons) {
            takeQuizPanel.add(rb);
        }
        takeQuizPanel.add(quizProgressBar);
        takeQuizPanel.add(submitAnswerButton);

        tabbedPane.addTab("Create Quiz", createQuizPanel);
        tabbedPane.addTab("Take Quiz", takeQuizPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);

        saveQuizButton.addActionListener(this::saveQuizAction);

    }

    protected void setFrameFeatures() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void saveQuizAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose location to save the quiz");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileOutputStream fos = new FileOutputStream            (fileToSave);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                List<String> quizData = new ArrayList<>();
                quizData.add(questionTextField.getText());
                for (int i = 0; i < optionTextFields.length; i++) {
                    quizData.add(optionTextFields[i].getText()); // Save each option text
                }

                int correctAnswerIndex = -1;
                for (int i = 0; i < answerRadioButtons.length; i++) {
                    if (answerRadioButtons[i].isSelected()) {
                        correctAnswerIndex = i; // Save the index of the correct answer
                        break;
                    }
                }
                quizData.add(Integer.toString(correctAnswerIndex)); // Save the correct answer index

                // Save the difficulty level
                quizData.add(Integer.toString(difficultySlider.getValue()));

                oos.writeObject(quizData);
                JOptionPane.showMessageDialog(this, "Quiz saved successfully to " + fileToSave.getAbsolutePath(), "Quiz Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save the quiz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    protected void saveQuiz() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Quiz");
        // Set default directory for file chooser
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        // Set file chooser to save dialog
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileOutputStream fileOut = new FileOutputStream(selectedFile);
                 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

                // Collect quiz data
                List<Object> quizData = new ArrayList<>();
                quizData.add(questionTextField.getText().trim()); // Question
                List<String> options = new ArrayList<>();
                for (JTextField optionField : optionTextFields) {
                    options.add(optionField.getText().trim()); // Options
                }
                quizData.add(options);

                int correctAnswer = -1;
                for (int i = 0; i < answerRadioButtons.length; i++) {
                    if (answerRadioButtons[i].isSelected()) {
                        correctAnswer = i; // Correct answer index
                        break;
                    }
                }
                quizData.add(correctAnswer);
                quizData.add(difficultySlider.getValue()); // Difficulty

                // Write quiz data to file
                out.writeObject(quizData);

                JOptionPane.showMessageDialog(this, "Quiz saved successfully to " + selectedFile.getAbsolutePath(), "Quiz Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to save the quiz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadQuizAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Quiz");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileInputStream fileIn = new FileInputStream(selectedFile);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {

                loadedQuizData = (List<Object>) in.readObject();
                displayQuiz(loadedQuizData);

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to load the quiz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void submitAnswerAction() {
        // Assuming the correct answer index is stored in quizData.get(2)
        int correctAnswer = (int) loadedQuizData.get(2);
        int selectedAnswer = -1;
        for (int i = 0; i < answerRadioButtons.length; i++) {
            if (answerRadioButtons[i].isSelected()) {
                selectedAnswer = i;
                break;
            }
        }

        if (selectedAnswer == correctAnswer) {
            JOptionPane.showMessageDialog(this, "Correct answer!", "Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Wrong answer. Try again!", "Result", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayQuiz(List<Object> quizData) {
        if (!quizData.isEmpty()) {
            questionTextArea.setText((String) quizData.get(0)); // Display question
            // Assuming options are in quizData.get(1), which is a List<String>
            List<String> options = (List<String>) quizData.get(1);
            for (int i = 0; i < options.size(); i++) {
                answerRadioButtons[i].setText(options.get(i));
                answerRadioButtons[i].setVisible(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object srcObj = e.getSource();
        if (srcObj == saveQuizButton) {
            // Save quiz logic remains unchanged
        } else if (srcObj == loadQuizButton) {
            loadQuizAction();
        } else if (srcObj == submitAnswerButton) {
            submitAnswerAction();
        }
    }


    public static void createAndShowGUI() {
        QuizMakerTakerApp quizApp = new QuizMakerTakerApp("Quiz Maker & Taker App");
         quizApp.initComponents();
         quizApp.addComponents();
         quizApp.setFrameFeatures();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizMakerTakerApp::createAndShowGUI);
    }



}
