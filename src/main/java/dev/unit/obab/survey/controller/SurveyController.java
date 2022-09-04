package dev.unit.obab.survey.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.unit.obab.food.domain.Food;
import dev.unit.obab.food.domain.service.FoodService;
import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.controller.dto.TotalSurveyResultResponse;
import dev.unit.obab.survey.service.SurveyService;
import dev.unit.obab.survey.service.dto.CalculateSurveyResult;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;
    private final FoodService foodService;

    @PostMapping
    public void saveSurveyResult(@RequestBody CreateSurveyRequest createSurveyRequest) {
        this.surveyService.saveSurveyResult(createSurveyRequest);
    }

    @GetMapping("/{deviceId}")
    public SurveyResponse getSurveyResult(
        @PathVariable String deviceId,
        @RequestParam String roomNo) {
        return surveyService.getSurveyResult(deviceId, roomNo);
    }

    @GetMapping("/total/{roomNo}")
    public TotalSurveyResultResponse getTotalSurveyResult(@PathVariable String roomNo) {
        CalculateSurveyResult surveyResult = surveyService.calculateSurvey(roomNo);
        // System.out.println("country : " + surveyResult.getCountry());
        // System.out.println("foodType : " + surveyResult.getFoodType());
        // System.out.println("isSpicy : " + surveyResult.isSpicy());
        // System.out.println("isSoup : " + surveyResult.isSoup());
        // System.out.println("isHot : " + surveyResult.isHot());

        final Optional<Food> food = foodService.searchFood(surveyResult.toSearchFoodCommand());

        return new TotalSurveyResultResponse(surveyResult, food);

    }
}
