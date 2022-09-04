package dev.unit.obab.food.domain;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import dev.unit.obab.food.domain.vo.Country;
import dev.unit.obab.food.domain.vo.FoodType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	private String image;

	private String name;

	@Enumerated(STRING)
	private Country country;

	@Enumerated(STRING)
	private FoodType foodType;

	private boolean spicy;

	private boolean soup;

	private boolean hot;

	@Builder
	public Food(String image, String name, Country country, FoodType foodType,
		boolean spicy, boolean soup, boolean hot) {
		this.image = image;
		this.name = name;
		this.country = country;
		this.foodType = foodType;
		this.spicy = spicy;
		this.soup = soup;
		this.hot = hot;
	}
}
