package dev.unit.obab.room.domain;

import static dev.unit.obab.room.domain.MealType.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RoomTest {

	@Test
	void 방_생성_테스트() {
		//given
		int totalCount = 10;
		MealType mealType = BREAKFAST;
		//when
		Room room = new Room(totalCount, mealType);

		//then
		assertThat(room.getTotalCount()).isEqualTo(totalCount);
		assertThat(room.getMealType()).isEqualTo(mealType);
	}

	@Nested
	class 방_입장_테스트{

		Room room;

		@BeforeEach
		void setUp() {
			room = new Room(2, LUNCH);
		}

		@Test
		void 방_입장_테스트_성공() {
			//given
			String device1 = "device1";
			String device2 = "device2";

			//when
			room.enter(device1);
			room.enter(device2);

			//then
			assertThat(room.getDeviceIds())
				.containsExactlyInAnyOrder(device1, device2);
		}

		@Test
		void 방_입장_테스트_실패() {
			//given
			room.enter("device1");
			room.enter("device2");

			//when, then
			assertThatIllegalArgumentException()
				.isThrownBy(() -> room.enter("device2"));
		}
	}

	@Test
	void 정원_다참_테스트() {
		//given
		Room room = new Room(2, NIGHT);

		room.enter("device1");
		room.enter("device2");

		//when
		boolean result = room.isFull();

		//then
		assertThat(result).isTrue();

	}
}
