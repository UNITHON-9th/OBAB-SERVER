package dev.unit.obab.food.domain.service;

import java.util.Optional;

import dev.unit.obab.food.domain.Food;
import dev.unit.obab.food.domain.service.dto.SearchFoodCommand;

public interface FoodService {

	/* 필터에 알맞은 음식 찾기 */
	Optional<Food> searchFood(SearchFoodCommand searchCommand);
}
