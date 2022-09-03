package dev.unit.obab.survey.controller;

import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.domain.SurveyDto;
import dev.unit.obab.survey.repository.SurveyRepository;
import dev.unit.obab.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Cacheable;

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
