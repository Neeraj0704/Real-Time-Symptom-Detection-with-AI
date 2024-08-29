package com.healthcare.chatbot;

public class ResponseFormatter {

    // Method to format the result string
    public static String formatResult(String result) {
        // Simple formatting logic
        return "The following symptoms were detected:Fever\n Headache " + result;
    }
}
