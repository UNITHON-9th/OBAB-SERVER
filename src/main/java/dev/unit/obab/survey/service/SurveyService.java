package dev.unit.obab.survey.service;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;

public interface SurveyService {

    /* 조사 결과 저장 */
    void saveSurveyResult(CreateSurveyRequest createSurveyRequest);
    SurveyResponse getSurveyResult(String deviceId, String roomNo);
}
