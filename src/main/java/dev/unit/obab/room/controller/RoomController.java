package dev.unit.obab.room.controller;

import static dev.unit.obab.core.domain.ResponseType.USER_DUPLICATE_SUCCESS;

import dev.unit.obab.core.domain.ResponseEntity;
import dev.unit.obab.room.controller.dto.CreateRoomDto;
import dev.unit.obab.room.controller.dto.EnterRoomDto;
import dev.unit.obab.room.controller.dto.RoomResponseDto;
import dev.unit.obab.room.service.RoomService;
import dev.unit.obab.room.service.dto.EnterRoomResult;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	@PostMapping
	public ResponseEntity<RoomResponseDto> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto) {
		RoomResponseDto responseDto = roomService.createRoom(createRoomDto.getTotalCount());

		return ResponseEntity.successResponse(responseDto);
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
