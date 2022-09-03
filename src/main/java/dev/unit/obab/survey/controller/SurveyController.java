package dev.unit.obab.survey.controller;

import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.repository.SurveyRepository;
import dev.unit.obab.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Cacheable;

@RequiredArgsConstructor
@RestController("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public void saveSurveyResult(@RequestBody Survey survey) {
        this.surveyService.saveSurveyResult(survey);
    }



}
