package dev.unit.obab.survey.controller;

import dev.unit.obab.survey.domain.SurveyDto;
import dev.unit.obab.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public void saveSurveyResult(@RequestBody SurveyDto surveyDto) {
        this.surveyService.saveSurveyResult(surveyDto);
    }
}
