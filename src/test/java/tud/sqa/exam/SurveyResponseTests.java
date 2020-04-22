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

    @Test
    @DisplayName("[SurveyResponse] GET /responses/avg gets average response of a SurveyResponse")
    void testGetResponseAverage() {
        double responseAverage;

        surveyResponseController.addResponse(1);
        surveyResponseController.addResponse(2);
        surveyResponseController.addResponse(3);
        responseAverage = surveyResponseController.getResponseAverage();
        assertEquals(responseAverage, 2);

        surveyResponseController.addResponse(4);
        surveyResponseController.addResponse(5);
        responseAverage = surveyResponseController.getResponseAverage();
        assertEquals(responseAverage, 3);
    }

    @Test
    @DisplayName("[SurveyResponse] GET /responses/min gets minimum response of SurveyResponse")
    void testGetResponseResponseMinimum() {
        double responseMinimum;

        surveyResponseController.addResponse(4);
        surveyResponseController.addResponse(5);
        responseMinimum = surveyResponseController.getResponseMinimum();
        assertEquals(responseMinimum, 4);

        surveyResponseController.addResponse(1);
        surveyResponseController.addResponse(2);
        surveyResponseController.addResponse(3);
        responseMinimum = surveyResponseController.getResponseMinimum();
        assertEquals(responseMinimum, 1);
    }

    @Test
    @DisplayName("[SurveyResponse] GET /responses/max gets maximum response of SurveyResponse")
    void testGetResponseMaximum() {
        double responseMaximum;

        surveyResponseController.addResponse(1);
        surveyResponseController.addResponse(2);
        surveyResponseController.addResponse(3);
        responseMaximum = surveyResponseController.getResponseMaximum();
        assertEquals(responseMaximum, 3);

        surveyResponseController.addResponse(4);
        surveyResponseController.addResponse(5);
        responseMaximum = surveyResponseController.getResponseMaximum();
        assertEquals(responseMaximum, 5);
    }

    @Test
    @DisplayName("[SurveyResponse] GET /responses/std gets standard deviation response of SurveyResponse")
    void testGetResponseStandardDeviation() {
        double responseStandardDeviation;

        surveyResponseController.addResponse(1);
        surveyResponseController.addResponse(2);
        surveyResponseController.addResponse(3);
        responseStandardDeviation = surveyResponseController.getResponseStandardDeviation();
        System.out.println(responseStandardDeviation);
        assertEquals(0.816496580927726, responseStandardDeviation);

        surveyResponseController.addResponse(4);
        responseStandardDeviation = surveyResponseController.getResponseStandardDeviation();
        System.out.println(responseStandardDeviation);
        assertEquals(1.118033988749895, responseStandardDeviation);
    }
}
