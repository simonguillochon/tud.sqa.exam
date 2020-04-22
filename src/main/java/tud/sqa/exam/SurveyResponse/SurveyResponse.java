package tud.sqa.exam.SurveyResponse;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SurveyResponse {
    @NonNull
    private Integer id;
    @NonNull Integer response;
    @NonNull
    private List<Integer> responses;

    public SurveyResponse() {
    }

    public SurveyResponse(Integer id, String title) {
        this.id = id;
        this.response = -1;
        this.responses = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public List<Integer> getResponses() {
        return responses;
    }

    public void addResponses(Integer response) {
        this.responses.add(response);
    }
}
