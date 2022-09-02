package dev.unit.obab.room.service;

import static dev.unit.obab.room.domain.MealType.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.room.domain.MealType;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;

@ActiveProfiles("local")
@SpringBootTest
@Transactional
class RoomServiceImplTest {

	@Autowired
	private RoomService roomService;

	@Autowired
	private RoomRedisRepository roomRepository;

	@Test
	void 방_생성_테스트() {
		//given
		int totalCount = 5;
		MealType mealType = BREAKFAST;

		//when
		String inviteCode = roomService.createRoom(totalCount, mealType);

		//then
		assertThat(inviteCode).isNotNull();

		Room findRoom = roomRepository.findById(inviteCode).get();

		assertThat(findRoom).isNotNull();
		assertThat(findRoom.getTotalCount()).isEqualTo(totalCount);
		assertThat(findRoom.getMealType()).isEqualTo(BREAKFAST);

	}
}
