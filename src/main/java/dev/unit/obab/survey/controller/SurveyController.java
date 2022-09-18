package dev.unit.obab.survey.controller;

import dev.unit.obab.core.domain.ResponseEntity;
import dev.unit.obab.food.domain.Food;
import dev.unit.obab.food.domain.service.FoodService;
import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.controller.dto.TotalSurveyResultResponse;
import dev.unit.obab.survey.service.SurveyService;
import dev.unit.obab.survey.service.dto.CalculateSurveyResult;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;
    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<Void> saveSurveyResult(@RequestBody CreateSurveyRequest createSurveyRequest) {
        this.surveyService.saveSurveyResult(createSurveyRequest);

        return ResponseEntity.successResponse();
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<SurveyResponse> getSurveyResult(
        @PathVariable String deviceId,
        @RequestParam String roomNo) {
        return ResponseEntity.successResponse(surveyService.getSurveyResult(deviceId, roomNo));
    }

    @GetMapping("/total/{roomNo}")
    public ResponseEntity<TotalSurveyResultResponse> getTotalSurveyResult(@PathVariable String roomNo) {
        CalculateSurveyResult surveyResult = surveyService.calculateSurvey(roomNo);

        final Optional<Food> food = foodService.searchFood(surveyResult.toSearchFoodCommand());

        return ResponseEntity.successResponse(new TotalSurveyResultResponse(surveyResult, food));

    }
}
