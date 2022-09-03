package dev.unit.obab.room.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.unit.obab.core.domain.ResponseEntity;
import dev.unit.obab.room.controller.dto.CreateRoomDto;
import dev.unit.obab.room.domain.MealType;
import dev.unit.obab.room.service.RoomService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	@PostMapping
	public ResponseEntity<Map<String, Object>> createRoom(@RequestBody CreateRoomDto createRoomDto) {
		String inviteCode = roomService.createRoom(createRoomDto.getTotalCount());

		return ResponseEntity.successResponse(Map.of("inviteCode", inviteCode));
	}
}
