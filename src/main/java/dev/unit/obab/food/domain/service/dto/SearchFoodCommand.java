package dev.unit.obab.food.domain.service.dto;

import dev.unit.obab.food.domain.vo.Country;
import dev.unit.obab.food.domain.vo.FoodType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchFoodCommand {

	private Country country;
	private FoodType foodType;
	private boolean isSpicy;
	private boolean isSoup;
	private boolean isHot;
}
