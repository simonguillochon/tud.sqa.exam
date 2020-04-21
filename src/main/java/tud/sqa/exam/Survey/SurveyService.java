package tud.sqa.exam.Survey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SurveyService {
    private static final Logger logger = LoggerFactory.getLogger(SurveyService.class);
    private final List<Survey> surveyList = new ArrayList<>(Arrays.asList(
            new Survey(1, "Survey 1"),
            new Survey(2, "Survey 2"),
            new Survey(3, "Survey 3")
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
        return surveyList.stream().filter(survey -> survey.getName().equals(name)).findFirst().get();
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

}
