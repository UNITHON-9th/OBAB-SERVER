package dev.unit.obab.food.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.unit.obab.food.domain.Food;
import dev.unit.obab.food.domain.vo.Country;
import dev.unit.obab.food.domain.vo.FoodType;

public interface FoodRepository extends JpaRepository<Food, Long> {

	Optional<Food> findByCountryAndFoodTypeAndSpicyAndSoupAndHot(
		Country country,
		FoodType foodType,
		boolean spicy,
		boolean soup,
		boolean hot
	);
}
