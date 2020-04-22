package tud.sqa.exam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import tud.sqa.exam.SurveyResponse.SurveyResponseController;
import tud.sqa.exam.SurveyResponse.SurveyResponseService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyResponseTests {
    SurveyResponseService surveyResponseService = new SurveyResponseService();
    SurveyResponseController surveyResponseController = new SurveyResponseController(surveyResponseService);

    @Test
    @DisplayName("[SurveyResponse] POST /responses adds a response to SurveyResponse")
    void testAddResponse() {
        surveyResponseController.addResponse(6);
        surveyResponseController.addResponse(1);
        surveyResponseController.addResponse(2);
        surveyResponseController.addResponse(3);
        surveyResponseController.addResponse(4);
        List<Integer> responses = surveyResponseController.getResponses();

        assertNotEquals(responses.size(), 5);
        assertEquals(responses.indexOf(-1), -1);
        assertEquals(responses.indexOf(6), -1);
        surveyResponseController.addResponse(5);
        assertEquals(responses.size(), 5);
    }

    @Test
    @DisplayName("[SurveyResponse] POST /responses/{response} set response of SurveyResponse")
    void testSetResponse() {
        surveyResponseController.addResponse(1);
        surveyResponseController.addResponse(2);
        surveyResponseController.addResponse(3);
        List<Integer> responses;

        surveyResponseController.setResponse(2);
        responses = surveyResponseController.getResponses();
        assertTrue(responses.contains(2));
        assertEquals(2, surveyResponseService.getResponse());

        surveyResponseController.setResponse(4);
        assertFalse(responses.contains(4));
        assertEquals(2, surveyResponseService.getResponse());
    }

    @Test
    @DisplayName("[SurveyResponse] GET /responses gets complete list of responses")
    void testGetResponses() {
        List<Integer> responses = surveyResponseController.getResponses();

        Assert.noNullElements(responses, "List of responses must not contain null element");
    }
}
