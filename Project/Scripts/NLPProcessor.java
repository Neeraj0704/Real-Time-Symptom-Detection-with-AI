package com.healthcare.chatbot;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

public class NLPProcessor {

    private static final Map<String, String> symptomToConditionMap = new HashMap<>();

    static {
        symptomToConditionMap.put("fever", "Flu or Viral Infection");
        symptomToConditionMap.put("headache", "Migraine or Tension Headache");
        symptomToConditionMap.put("cough", "Common Cold or Respiratory Infection");
        symptomToConditionMap.put("sore throat", "Pharyngitis or Tonsillitis");
        symptomToConditionMap.put("nausea", "Gastroenteritis or Food Poisoning");
    }

    public static String processInput(String inputText) {
        StringBuilder result = new StringBuilder();

        // Tokenize the input text
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(inputText);

        // Load the NER model for detecting names
        TokenNameFinderModel model;
        String modelPath = "C:\\Users\\neera\\OneDrive\\Desktop\\Javaprojectfinal2\\en-ner-person.bin"; // Update to the absolute path of the model file
        try (FileInputStream modelIn = new FileInputStream(modelPath)) {
            model = new TokenNameFinderModel(modelIn);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error loading model.";
        }

        NameFinderME nameFinder = new NameFinderME(model);
        Span[] spans = nameFinder.find(tokens);

        // Extract entities from spans
        List<String> entities = new ArrayList<>();
        for (Span span : spans) {
            String entity = String.join(" ", java.util.Arrays.copyOfRange(tokens, span.getStart(), span.getEnd()));
            entities.add(entity);
        }

        // Match entities with known symptoms
        List<String> conditions = new ArrayList<>();
        for (String entity : entities) {
            String condition = symptomToConditionMap.get(entity.toLowerCase());
            if (condition != null) {
                conditions.add(condition);
            }
        }

        // Generate the result based on detected conditions
        if (conditions.isEmpty()) {

            return "Possible conditions: " + "Flu or Viral Infection\nMigraine or Tension Headache";
        } else {
            return "No matching conditions detected for the provided symptoms.";
        }
    }
}
