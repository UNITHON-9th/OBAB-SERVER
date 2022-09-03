package dev.unit.obab.room.controller.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class EnterRoomDto {

	@Pattern(regexp = "^[0-9]{6}")
	@NotNull
	private String inviteCode;

	@NotNull
	private String deviceId;
}
