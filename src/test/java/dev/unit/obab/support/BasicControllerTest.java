package dev.unit.obab.support;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Transactional
@ActiveProfiles("local")
@AutoConfigureMockMvc
@TestPropertySource(properties = {
	"logging.level.org.hibernate.SQL=DEBUG",
	"logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE"
})
@SpringBootTest
public class BasicControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	protected String 방_생성(int totalCount, String mealType) throws Exception {
		//given
		ObjectNode createRoomDto = objectMapper.createObjectNode();
		createRoomDto.put("totalCount", totalCount);
		createRoomDto.put("mealType", mealType);

		//when
		MockHttpServletResponse response = mockMvc.perform(post("/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createRoomDto.toString()))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse();

		return objectMapper.readValue(response.getContentAsString(), JsonNode.class)
			.get("data").get("inviteCode").toString();
	}
}
