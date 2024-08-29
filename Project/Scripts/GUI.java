package com.healthcare.chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    public static void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Healthcare Chatbot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a text area for user input
        JTextArea inputArea = new JTextArea("Can you please tell us what u are feeling?");
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        panel.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        // Create a button to submit input
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton, BorderLayout.SOUTH);

        // Add action listener to the button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input text
                String inputText = inputArea.getText();

                // Process the input using NLPProcessor
                System.out.println(1);
                String result = NLPProcessor.processInput(inputText);
                System.out.println(2);
                String formattedResult = ResponseFormatter.formatResult(result);

                // Display the result
                displayResult(formattedResult);
            }
        });

        // Set up the frame with the panel
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void displayResult(String result) {
        // Create a new frame for displaying results
        JFrame resultFrame = new JFrame("Results");
        resultFrame.setSize(400, 200);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a panel
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        // Create a text area to display results
        JTextArea resultArea = new JTextArea(result);
        resultArea.setEditable(false);
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Set up the result frame with the panel
        resultFrame.add(resultPanel);
        resultFrame.setVisible(true);
    }
}
