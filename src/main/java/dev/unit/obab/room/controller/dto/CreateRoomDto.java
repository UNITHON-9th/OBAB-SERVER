package dev.unit.obab.room.controller.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;

@Getter
public class CreateRoomDto {

	@Min(1)
	@Max(100)
	private int totalCount;
}
