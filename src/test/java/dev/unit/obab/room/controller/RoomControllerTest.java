package dev.unit.obab.room.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.node.ObjectNode;

import dev.unit.obab.support.BasicControllerTest;

class RoomControllerTest extends BasicControllerTest {

	@Test
	void 방_생성_테스트() throws Exception {
		//given
		ObjectNode createRoomDto = objectMapper.createObjectNode();
		createRoomDto.put("totalCount", 5);

		//when
		mockMvc.perform(post("/rooms")
			.contentType(MediaType.APPLICATION_JSON)
			.content(createRoomDto.toString()))

			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.inviteCode").exists());
	}

	@Disabled
	@Test
	void 방_입장_테스트_성공() throws Exception {
		//given
		final String inviteCode = 방_생성(5, "BREAKFAST");

		final ObjectNode enterRoomDto = objectMapper.createObjectNode();
		enterRoomDto.put("inviteCode", inviteCode);
		enterRoomDto.put("deviceId", "device1");

		//when
		mockMvc.perform(post("/rooms/enter")
				.contentType(MediaType.APPLICATION_JSON)
				.content(enterRoomDto.toString()))

			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.roomNo").exists());
	}
}
