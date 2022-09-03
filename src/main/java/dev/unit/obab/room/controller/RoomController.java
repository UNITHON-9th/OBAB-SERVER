package dev.unit.obab.room.controller;

import static dev.unit.obab.core.domain.ResponseType.*;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.unit.obab.core.domain.ResponseEntity;
import dev.unit.obab.room.controller.dto.CreateRoomDto;
import dev.unit.obab.room.controller.dto.EnterRoomDto;
import dev.unit.obab.room.service.RoomService;
import dev.unit.obab.room.service.dto.EnterRoomResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto) {
		String inviteCode = roomService.createRoom(createRoomDto.getTotalCount());

		return ResponseEntity.successResponse(Map.of("inviteCode", inviteCode));
	}

	@PostMapping("/enter")
	public ResponseEntity<Map<String, Object>> enterRoom(@RequestBody EnterRoomDto enterRoomDto) {
		EnterRoomResult enterRoomResult = roomService.enterRoom(enterRoomDto.getInviteCode(),
			enterRoomDto.getDeviceId());
		final Map<String, Object> roomNo = Map.of("roomNo", enterRoomResult.getRoomNo());

		if (enterRoomResult.isDuplicatedUser()) {
			return ResponseEntity.successResponse(USER_DUPLICATE_SUCCESS, roomNo);
		}

		return ResponseEntity.successResponse(roomNo);
	}

}
