package tud.sqa.exam.Survey;

import org.springframework.web.bind.annotation.*;
import tud.sqa.exam.SurveyResponse.SurveyResponse;

import java.util.List;

@RestController
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    /**
     * Return a list with all surveys of the list
     *
     * @return a list of all Survey object
     */
    @RequestMapping("/surveys")
    public List<Survey> getSurveys() {
        return surveyService.getSurveys();
    }

    /**
     * Return survey corresponding to name @param
     *
     * @param name : name of the survey to get
     * @return object Survey corresponding to the name
     */
    @RequestMapping("/surveys/{name}")
    public Survey getSurvey(@PathVariable("name") String name) {
        return surveyService.getSurvey(name);
    }

    /**
     * Add a survey to survey list
     *
     * @param survey : survey to be added to the list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/surveys")
    public void addSurvey(@RequestBody Survey survey) {
        surveyService.addSurvey(survey);
    }

    /**
     * Delete survey from the list
     *
     * @param id : id of the survey to be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/surveys/{id}")
    public void deleteSurvey(@PathVariable("id") Integer id) {
        surveyService.deleteSurvey(id);
    }

    /**
     * Add SurveyResponse to the responses list
     *
     * @param surveyId       : id of the Survey that will contain new SurveyResponse
     * @param surveyResponse : object to be added to the list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/surveys/response")
    public void addSurveyResponse(@RequestParam("surveyId") Integer surveyId, @RequestBody SurveyResponse surveyResponse) {
        surveyService.addSurveyResponse(surveyId, surveyResponse);
    }

    /**
     * Get average value for responses of a survey
     *
     * @param surveyName : name of the survey to get responses with
     * @return a double containing the average of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/surveys/{name}/avg")
    public double getSurveyAverage(@PathVariable("name") String surveyName) {
        return surveyService.getSurveyAverage(surveyName);
    }

    /**
     * Get minimum value for responses of a survey
     *
     * @param surveyName : name of the survey to get responses with
     * @return a double containing the minimum of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/surveys/{name}/min")
    public double getSurveyMinimum(@PathVariable("name") String surveyName) {
        return surveyService.getSurveyMinimum(surveyName);
    }

    /**
     * Get maximum value for responses of a survey
     *
     * @param surveyName : name of the survey to get responses with
     * @return a double containing the maximum of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/surveys/{name}/max")
    public double getSurveyMaximum(@PathVariable("name") String surveyName) {
        return surveyService.getSurveyMaximum(surveyName);
    }

    /**
     * Get standard deviation value for responses of a survey
     *
     * @param surveyName : name of the survey to get responses with
     * @return a double containing the standard deviation of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/surveys/{name}/std")
    public double getSurveyStandardDeviation(@PathVariable("name") String surveyName) {
        return surveyService.getSurveyStandardDeviation(surveyName);
    }
}
