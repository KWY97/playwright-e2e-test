package io.jenkins.plugins.playwright_e2e.extensions.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ScriptModel {
    private String title;
    private List<Scenario> scenarios = new ArrayList<>();

    public ScriptModel() { }

    public ScriptModel(String title, List<Scenario> scenarios) {
        this.title = title;
        this.scenarios = scenarios != null ? scenarios : new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public static class Scenario {
        private String title;
        private List<String> steps = new ArrayList<>();

        public Scenario() { }

        public Scenario(String title, List<String> steps) {
            this.title = title;
            this.steps = steps != null ? steps : new ArrayList<>();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getSteps() {
            return steps;
        }

        public void setSteps(List<String> steps) {
            this.steps = steps;
        }
    }

    // JSON -> Object
    public static ScriptModel fromJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, ScriptModel.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON parsing failed", e);
        }
    }

    // Object -> JSON (for debugging)
    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON serialization failed", e);
        }
    }
}
