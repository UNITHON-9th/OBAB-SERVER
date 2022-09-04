package dev.unit.obab.food.domain.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.food.domain.Food;
import dev.unit.obab.food.domain.service.dto.SearchFoodCommand;
import dev.unit.obab.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class FoodServiceImpl implements FoodService {

	private final FoodRepository foodRepository;

	@Override
	public Optional<Food> searchFood(final SearchFoodCommand searchCommand) {
		return foodRepository.findByCountryAndFoodTypeAndSpicyAndSoupAndHot(
			searchCommand.getCountry(),
			searchCommand.getFoodType(),
			searchCommand.isSpicy(),
			searchCommand.isSoup(),
			searchCommand.isHot()
		);
	}
}
