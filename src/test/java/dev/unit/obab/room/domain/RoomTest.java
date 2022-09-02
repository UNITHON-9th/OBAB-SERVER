package dev.unit.obab.room.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoomTest {

	@Test
	void 방_생성_테스트() {
		//given
		String inviteCode = "234512";
		int totalCount = 10;

		//when
		Room room = new Room(inviteCode, totalCount, MealType.BREAKFAST);

		//then
		assertThat(room.getInviteCode()).isEqualTo(inviteCode);
		assertThat(room.getTotalCount()).isEqualTo(totalCount);
		assertThat(room.getMealType()).isEqualTo(MealType.BREAKFAST);
	}
}
