package tud.sqa.exam.SurveyResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SurveyResponseService {
    private static final Logger logger = LoggerFactory.getLogger(SurveyResponseService.class);
    private final SurveyResponse surveyResponse = new SurveyResponse(1, "SurveyResponse 1");

    public void setResponse(Integer response) {
        if (surveyResponse.getResponses().contains(response)) {
            surveyResponse.setResponse(response);
        } else {
            logger.error("[ERROR] Response value is not in the list");
        }
    }

    public Integer getResponse() {
        return surveyResponse.getResponse();
    }

    /**
     * Add a response to the SurveyResponse
     *
     * @param response Integer response to add
     */
    public void addResponse(Integer response) {
        if (response >= 1 && response <= 5) {
            surveyResponse.addResponses(response);
        } else {
            logger.error("[ERROR] Response value is out of range");
        }
    }

    /**
     * Get list of responses of the SurveyResponse
     *
     * @return List of responses as integer
     */
    public List<Integer> getResponses() {
        return surveyResponse.getResponses();
    }

    /**
     * Get the average of the responses of a SurveyResponse
     *
     * @return a double containing the value of the average
     */
    public double getResponseAverage() {
        double responseSum = 0;
        List<Integer> responses = this.getResponses();

        for (Integer responseValue : responses) {
            responseSum += responseValue;
        }
        return responseSum / responses.size();
    }

    /**
     * Get the minimum value of the responses
     *
     * @return a double containing the value of the minimum
     */
    public double getResponseMinimum() {
        List<Integer> responses = this.getResponses();

        Collections.sort(responses);
        return responses.get(0);
    }

    /**
     * Get the maximum value of the responses
     *
     * @return a double containing the value of the maximum
     */
    public double getResponseMaximum() {
        List<Integer> responses = this.getResponses();

        Collections.sort(responses);
        return responses.get(responses.size() - 1);
    }

    /**
     * Get the standard deviation value of the responses
     *
     * @return a double containing the standard deviation of the minimum
     */
    public double getResponseStandardDeviation() {
        double tmp = 0;
        double average = getResponseAverage();
        List<Integer> responses = this.getResponses();

        for (Integer response : responses) {
            tmp += Math.pow(response - average, 2);
        }
        return Math.sqrt(tmp / (double) (responses.size()));
    }
}
