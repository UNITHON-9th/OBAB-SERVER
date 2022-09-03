package dev.unit.obab.room.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void 방_생성_테스트() throws Exception {
		//given
		final ObjectNode createRoomDto = objectMapper.createObjectNode();
		createRoomDto.put("totalCount", 5);

		//when
		mockMvc.perform(post("/rooms")
			.contentType(MediaType.APPLICATION_JSON)
			.content(createRoomDto.toString()))

			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.inviteCode").exists());
	}
}
