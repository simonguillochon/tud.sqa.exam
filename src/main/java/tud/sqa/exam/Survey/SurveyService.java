package tud.sqa.exam.Survey;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tud.sqa.exam.SurveyResponse.SurveyResponse;

import java.util.*;

@Service
public class SurveyService {
    private static final Logger logger = LoggerFactory.getLogger(SurveyService.class);
    private final List<Survey> surveyList = new ArrayList<>(Arrays.asList(
            new Survey(1, "Survey1"),
            new Survey(2, "Survey2"),
            new Survey(3, "Survey3")
    ));

    /**
     * Return the list of surveys
     *
     * @return List of Survey objects
     */
    public List<Survey> getSurveys() {
        return this.surveyList;
    }

    /**
     * Return the survey corresponding to the name parameter
     *
     * @param name : name of the survey to be returned
     * @return Survey object
     */
    public Survey getSurvey(String name) {
        try {
            return surveyList.stream().filter(survey -> survey.getName().equals(name)).findFirst().get();
        } catch (Exception e) {
            logger.error("[ERROR] Survey not found in the list");
            throw new IllegalArgumentException("Survey not found");
        }
    }

    /**
     * Add a survey to the list of surveys
     *
     * @param survey : survey to be added to the list
     */
    public void addSurvey(Survey survey) {
        for (Survey surveyListElem : surveyList) {
            if (surveyListElem.getName().equals(survey.getName())) {
                logger.error("[ERROR] Survey already exists in the list");
                return;
            }
        }
        surveyList.add(survey);
    }

    /**
     * Delete survey from the list
     *
     * @param id : id of the survey to be deleted
     */
    public void deleteSurvey(Integer id) {
        surveyList.removeIf(survey -> survey.getId().equals(id));
    }

    /**
     * Add SurveyResponse to the list of responses
     *
     * @param surveyResponse : object to be added to the responses list
     */
    public void addSurveyResponse(Integer surveyId, SurveyResponse surveyResponse) {
        List<SurveyResponse> surveyResponses = surveyList.stream().filter(survey -> survey.getId().equals(surveyId)).findFirst().get().getResponses();

        if (surveyResponses != null && surveyResponses.size() < 10) {
            surveyResponses.add(surveyResponse);
        } else {
            logger.error("[ERROR] Response value is out of range");
        }
    }

    /**
     * Get average value for responses of a survey
     *
     * @param surveyName : id of the survey to get responses of
     */
    public double getSurveyAverage(String surveyName) {
        double responsesAverageSum = 0;
        Survey survey = getSurvey(surveyName);
        List<SurveyResponse> responses = survey.getResponses();

        for (SurveyResponse response : responses) {
            responsesAverageSum += response.getResponse();
        }
        return responsesAverageSum / responses.size();
    }

    /**
     * Get minimum value for responses of a survey
     *
     * @param surveyName : id of the survey to get responses of
     * @return a double containing the minimum of the responses
     */
    public double getSurveyMinimum(String surveyName) {
        List<Integer> responses = new ArrayList<>();

        for (SurveyResponse response : getSurvey(surveyName).getResponses()) {
            responses.add(response.getResponse());
        }
        Collections.sort(responses);
        return responses.get(0);
    }

    /**
     * Get maximum value for responses of a survey
     *
     * @param surveyName : id of the survey to get responses of
     * @return a double containing the maximum of the responses
     */
    public double getSurveyMaximum(String surveyName) {
        List<Integer> responses = new ArrayList<>();

        for (SurveyResponse response : getSurvey(surveyName).getResponses()) {
            responses.add(response.getResponse());
        }
        Collections.sort(responses);
        return responses.get(responses.size() - 1);
    }

    /**
     * Get standard deviation value for responses of a survey
     *
     * @param surveyName : id of the survey to get responses of
     * @return a double containing the standard deviation of the responses
     */
    public double getSurveyStandardDeviation(String surveyName) {
        double tmp = 0;
        double average = getSurveyAverage(surveyName);
        List<Integer> responses = new ArrayList<>();

        for (SurveyResponse response : getSurvey(surveyName).getResponses()) {
            responses.add(response.getResponse());
        }
        for (Integer response : responses) {
            tmp += Math.pow(response - average, 2);
        }
        return Math.sqrt(tmp / (double) (responses.size()));
    }}
