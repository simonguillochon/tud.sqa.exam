package tud.sqa.exam.SurveyResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
}
