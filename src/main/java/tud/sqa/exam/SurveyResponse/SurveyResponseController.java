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

    /**
     * Returns the average of the responses
     *
     * @return a double containing the average of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/responses/avg")
    public double getResponseAverage() {
        return surveyResponseService.getResponseAverage();
    }

    /**
     * Returns the minimum of the responses
     *
     * @return a double containing the minimum of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/responses/min")
    public double getResponseMinimum() {
        return surveyResponseService.getResponseMinimum();
    }

    /**
     * Returns the maximum of the responses
     *
     * @return a double containing the maximum of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/responses/max")
    public double getResponseMaximum() {
        return surveyResponseService.getResponseMaximum();
    }

    /**
     * Returns the standard deviation of the responses
     *
     * @return a double containing the standard deviation of the responses
     */
    @RequestMapping(method = RequestMethod.GET, value = "/responses/std")
    public double getResponseStandardDeviation() {
        return surveyResponseService.getResponseStandardDeviation();
    }
}
