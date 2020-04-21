package tud.sqa.exam;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;
import tud.sqa.exam.Survey.Survey;
import tud.sqa.exam.Survey.SurveyController;
import tud.sqa.exam.Survey.SurveyService;

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
}
