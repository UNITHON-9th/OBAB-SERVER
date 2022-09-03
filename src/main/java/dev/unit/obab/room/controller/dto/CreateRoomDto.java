package dev.unit.obab.room.controller.dto;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;

@Getter
public class CreateRoomDto {

	@Range(min = 1, max = 100)
	private int totalCount;

}
