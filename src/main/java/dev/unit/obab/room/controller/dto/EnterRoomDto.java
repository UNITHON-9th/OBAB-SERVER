package dev.unit.obab.room.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;

@Getter
public class EnterRoomDto {

	@Pattern(regexp = "^[0-9]{6}")
	@NotBlank
	private String inviteCode;

	@NotBlank
	private String deviceId;
}
