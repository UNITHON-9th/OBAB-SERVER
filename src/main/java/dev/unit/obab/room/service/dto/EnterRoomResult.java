package dev.unit.obab.room.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EnterRoomResult {

	private String roomNo;
	private boolean duplicatedUser;

}
