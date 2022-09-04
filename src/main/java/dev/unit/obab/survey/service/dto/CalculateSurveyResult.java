package dev.unit.obab.survey.service.dto;

import java.util.List;

import dev.unit.obab.food.domain.service.dto.SearchFoodCommand;
import dev.unit.obab.food.domain.vo.Country;
import dev.unit.obab.food.domain.vo.FoodType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CalculateSurveyResult {
	int country;
	int foodType;
	boolean spicy;
	boolean soup;
	boolean hot;

	List<Integer> countryList;
	List<Integer> foodTypeList;
	List<Integer> spicyList;
	List<Integer> soupList;
	List<Integer> hotList;

	int totalCount;

	public CalculateSurveyResult(
		final List<Integer> countryList,
		final List<Integer> foodTypeList,
		final List<Integer> spicyList,
		final List<Integer> soupList,
		final List<Integer> hotList,
		final int totalCount) {
		this.countryList = countryList;
		this.foodTypeList = foodTypeList;
		this.spicyList = spicyList;
		this.soupList = soupList;
		this.hotList = hotList;
		this.totalCount = totalCount;

		this.country = findLargeIndex(countryList);
		this.foodType = findLargeIndex(foodTypeList);
		this.spicy = findTrueFalse(spicyList);
		this.soup = findTrueFalse(soupList);
		this.hot = findTrueFalse(hotList);

	}

	private int findLargeIndex(List<Integer> list) {
		int index = -1;
		int max = -1;

		for (int i = 0; i < list.size(); ++i) {
			if (max < list.get(i)) {
				max = list.get(i);
				index = i;
			}
		}

		return index;
	}

	private boolean findTrueFalse(List<Integer> list) {
		if (list.get(0) > list.get(1)) {
			return true;
		}
		return false;
	}

	public SearchFoodCommand toSearchFoodCommand() {
		return new SearchFoodCommand(
			Country.values()[country],
			FoodType.values()[foodType],
			spicy,
			soup,
			hot
		);
	}
}
