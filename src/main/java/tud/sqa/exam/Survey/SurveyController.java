package tud.sqa.exam.Survey;

import org.springframework.web.bind.annotation.*;

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
}
