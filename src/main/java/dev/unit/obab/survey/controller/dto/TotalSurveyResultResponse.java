package dev.unit.obab.survey.controller.dto;

import java.util.List;
import java.util.Optional;

import dev.unit.obab.food.domain.Food;
import dev.unit.obab.survey.service.dto.CalculateSurveyResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TotalSurveyResultResponse {

	private String image;
	private String name;
	private int totalCount;
	private List<Integer> countries;
	private List<Integer> foods;
	private List<Integer> spicys;
	private List<Integer> soups;
	private List<Integer> hots;

	public TotalSurveyResultResponse(CalculateSurveyResult surveyResult, Optional<Food> food) {
		this.image = food.map(Food::getImage).orElse("no image");
		this.name = food.map(Food::getName).orElse("no food");
		this.totalCount = surveyResult.getTotalCount();
		this.countries = surveyResult.getCountryList();
		this.foods = surveyResult.getFoodTypeList();
		this.spicys = surveyResult.getSpicyList();
		this.soups = surveyResult.getSoupList();
		this.hots = surveyResult.getHotList();
	}
}
