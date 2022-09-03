package dev.unit.obab.survey.service;
import dev.unit.obab.survey.domain.SurveyDto;

public interface SurveyService {

    /* 조사 결과 저장 */
    void saveSurveyResult(SurveyDto surveyDto);
}
