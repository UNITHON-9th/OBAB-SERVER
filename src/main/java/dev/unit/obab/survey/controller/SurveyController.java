package dev.unit.obab.survey.controller;

import dev.unit.obab.survey.domain.SurveyDto;
import dev.unit.obab.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
