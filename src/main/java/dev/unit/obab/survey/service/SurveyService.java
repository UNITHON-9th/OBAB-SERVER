package dev.unit.obab.survey.service;

import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.service.dto.CalculateSurveyResult;

public interface SurveyService {

    /* 조사 결과 저장 */
    void saveSurveyResult(CreateSurveyRequest createSurveyRequest);

    /* 개인 조사 결과 조회*/
    SurveyResponse getSurveyResult(String deviceId, String roomNo);

    /* 통합 조사 결과 계산 */
    CalculateSurveyResult calculateSurvey(String roomNo);
}
