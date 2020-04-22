package tud.sqa.exam.SurveyResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SurveyResponseController {
    private final SurveyResponseService surveyResponseService;

    public SurveyResponseController(SurveyResponseService surveyResponseService) {
        this.surveyResponseService = surveyResponseService;
    }

    /**
     * Set response value for the SurveyResponse
     *
     * @param response Integer containing the value of the response
     */
    @RequestMapping(method = RequestMethod.POST, value = "/response/{response}")
    public void setResponse(@PathVariable("response") Integer response) {
        surveyResponseService.setResponse(response);
    }

    /**
     * Adds a response to the response list of Survey
     *
     * @param response the response to be added to the responses list
     */
    @RequestMapping(method = RequestMethod.POST, value = "/responses")
    public void addResponse(@RequestParam Integer response) {
        surveyResponseService.addResponse(response);
    }

    /**
     * Returns a list of responses for the SurveyResponse object
     *
     * @return a list of integer containing responses
     */
    @RequestMapping("/responses")
    public List<Integer> getResponses() {
        return surveyResponseService.getResponses();
    }
}
