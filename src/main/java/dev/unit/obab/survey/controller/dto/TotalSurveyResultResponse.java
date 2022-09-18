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
		this.image = food.map(Food::getImage).orElse("https://unihton-9th.s3.ap-northeast-2.amazonaws.com/obab_img/%E1%84%80%E1%85%A1%E1%86%AF%E1%84%87%E1%85%B5%E1%84%90%E1%85%A1%E1%86%BC.jpeg");
		this.name = food.map(Food::getName).orElse("갈비탕");
		this.totalCount = surveyResult.getTotalCount();
		this.countries = surveyResult.getCountryList();
		this.foods = surveyResult.getFoodTypeList();
		this.spicys = surveyResult.getSpicyList();
		this.soups = surveyResult.getSoupList();
		this.hots = surveyResult.getHotList();
	}
}
