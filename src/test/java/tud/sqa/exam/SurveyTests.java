package tud.sqa.exam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import tud.sqa.exam.Survey.Survey;
import tud.sqa.exam.Survey.SurveyController;
import tud.sqa.exam.Survey.SurveyService;
import tud.sqa.exam.SurveyResponse.SurveyResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SurveyTests {
    SurveyController surveyController = new SurveyController(new SurveyService());

    @Test
    @DisplayName("[Survey] GET /survey/{name} gets a Survey of the list by name")
    void testGetSurvey() {
        surveyController.addSurvey(new Survey(42, "Test Survey"));
        Survey survey = surveyController.getSurvey("Test Survey");

        Assert.notNull(survey);
        Assert.isInstanceOf(Survey.class, survey);
        assertEquals(survey.getId(), 42);
        assertNotEquals(survey.getId(), 1);
        assertEquals(survey.getName(), "Test Survey");
        assertNotEquals(survey.getName(), "QWERTY");

        surveyController.addSurvey(new Survey(43, "Test Survey"));
        survey = surveyController.getSurvey("Test Survey");
        assertNotEquals(survey.getId(), 43);
    }

    @Test
    @DisplayName("[Survey] POST /surveys adds a Survey to the list")
    void testAddSurvey() {
        surveyController.addSurvey(new Survey(42, "Test Survey"));

        Survey survey42 = surveyController.getSurvey("Test Survey");
        assertEquals(survey42.getId(), 42);
        assertNotEquals(survey42.getId(), "66");
        assertEquals(survey42.getName(), "Test Survey");
        assertNotEquals(survey42.getName(), "Survey 1");
    }

    @Test
    @DisplayName("[Survey] DELETE /surveys/{id} deletes a Survey from list")
    void testDeleteSurvey() {
        surveyController.addSurvey(new Survey(42, "Test Survey"));
        Survey survey = surveyController.getSurvey("Test Survey");
        List<Survey> surveyList = surveyController.getSurveys();

        Assert.isTrue(surveyList.stream().anyMatch(survey1 -> survey1.getId().equals(survey.getId())));
        surveyController.deleteSurvey(42);
        Assert.isTrue(surveyList.stream().noneMatch(survey1 -> survey1.getId().equals(survey.getId())));
    }

    @Test
    @DisplayName("[Survey] POST /surveys/response adds a SurveyResponse to responses list")
    void testAddSurveyResponse() {
        List<SurveyResponse> surveyResponses;
        SurveyResponse surveyResponse420, surveyResponse421, surveyResponse422, surveyResponse423, surveyResponse424, surveyResponse425, surveyResponse426, surveyResponse427, surveyResponse428, surveyResponse429, surveyResponse4210;

        surveyController.addSurvey(new Survey(42, "Test Survey"));
        surveyResponse420 = new SurveyResponse(420, "SR 42 0");
        surveyResponse421 = new SurveyResponse(421, "SR 42 1");
        surveyResponse422 = new SurveyResponse(422, "SR 42 2");
        surveyResponse423 = new SurveyResponse(423, "SR 42 3");
        surveyResponse424 = new SurveyResponse(424, "SR 42 4");
        surveyResponse425 = new SurveyResponse(425, "SR 42 5");
        surveyResponse426 = new SurveyResponse(426, "SR 42 6");
        surveyResponse427 = new SurveyResponse(427, "SR 42 7");
        surveyResponse428 = new SurveyResponse(428, "SR 42 8");
        surveyResponse429 = new SurveyResponse(429, "SR 42 9");
        surveyController.addSurveyResponse(42, surveyResponse420);
        surveyController.addSurveyResponse(42, surveyResponse421);
        surveyController.addSurveyResponse(42, surveyResponse422);
        surveyController.addSurveyResponse(42, surveyResponse423);
        surveyController.addSurveyResponse(42, surveyResponse424);
        surveyController.addSurveyResponse(42, surveyResponse425);
        surveyController.addSurveyResponse(42, surveyResponse426);
        surveyController.addSurveyResponse(42, surveyResponse427);
        surveyController.addSurveyResponse(42, surveyResponse428);
        surveyController.addSurveyResponse(42, surveyResponse429);

        surveyResponses = surveyController.getSurvey("Test Survey").getResponses();
        Assert.noNullElements(surveyResponses, "List of SurveyResponse must not be null");
        assertEquals(surveyResponses.size(), 10, "List should contain 10 survey responses");
        assertEquals(surveyResponses.get(0), surveyResponse420, "SurveyResponse 0 should be surveyResponse420");
        assertEquals(surveyResponses.get(1), surveyResponse421, "SurveyResponse 1 should be surveyResponse421");
        assertEquals(surveyResponses.get(2), surveyResponse422, "SurveyResponse 2 should be surveyResponse422");
        assertEquals(surveyResponses.get(3), surveyResponse423, "SurveyResponse 3 should be surveyResponse423");
        assertEquals(surveyResponses.get(4), surveyResponse424, "SurveyResponse 4 should be surveyResponse424");
        assertEquals(surveyResponses.get(5), surveyResponse425, "SurveyResponse 5 should be surveyResponse425");
        assertEquals(surveyResponses.get(6), surveyResponse426, "SurveyResponse 6 should be surveyResponse426");
        assertEquals(surveyResponses.get(7), surveyResponse427, "SurveyResponse 7 should be surveyResponse427");
        assertEquals(surveyResponses.get(8), surveyResponse428, "SurveyResponse 8 should be surveyResponse428");
        assertEquals(surveyResponses.get(9), surveyResponse429, "SurveyResponse 9 should be surveyResponse429");

        surveyResponse4210 = new SurveyResponse(4210, "SR 42 10");
        surveyController.addSurveyResponse(42, surveyResponse4210);
        surveyResponses = surveyController.getSurvey("Test Survey").getResponses();
        assertEquals(surveyResponses.size(), 10, "List should not contain 11 survey responses");
    }
}
