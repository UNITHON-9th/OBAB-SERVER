package dev.unit.obab.survey.controller;

import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public void saveSurveyResult(@RequestBody CreateSurveyRequest createSurveyRequest) {
        this.surveyService.saveSurveyResult(createSurveyRequest);
    }

    @GetMapping("/{deviceId}")
    public SurveyResponse getSurveyResult(
            @PathVariable String deviceId,
            @RequestParam String roomNo ) {
        return surveyService.getSurveyResult(deviceId, roomNo);
    }
}
