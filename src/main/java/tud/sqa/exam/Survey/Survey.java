package tud.sqa.exam.Survey;

import lombok.NonNull;
import tud.sqa.exam.SurveyResponse.SurveyResponse;

import java.util.ArrayList;
import java.util.List;

public class Survey {
    @NonNull
    private final Integer id;
    @NonNull
    private final String name;
    @NonNull
    private final List<SurveyResponse> responses;

    public Survey(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.responses = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SurveyResponse> getResponses() {
        return responses;
    }
}
