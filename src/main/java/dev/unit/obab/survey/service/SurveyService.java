package dev.unit.obab.survey.service;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;

public interface SurveyService {
<<<<<<< Updated upstream

    /* 조사 결과 저장 */
    void saveSurveyResult(SurveyDto surveyDto);
=======
    void saveSurveyResult(CreateSurveyRequest createSurveyRequest);
    SurveyResponse getSurveyResult(String deviceId);
>>>>>>> Stashed changes
}
