package dev.unit.obab.room.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.room.domain.MealType;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.util.RandomUtils;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

	private final RoomRedisRepository roomRedisRepository;
	private final RedisUtil redisUtil;

	public String createRoom(int totalCount, MealType mealType) {
		Room savedRoom = roomRedisRepository.save(new Room(totalCount, mealType));

		String inviteCode = RandomUtils.createInviteCode();

		/* invitecode : roomno 저장 */
		redisUtil.setData(inviteCode, savedRoom.getRoomno());

		if(!Objects.nonNull(redisUtil.getData(inviteCode))){
			throw new IllegalArgumentException("레디스에 저장이 되지 않았습니다.");
		}

		return inviteCode;
	}

	@Override
	public String enterRoom(String inviteCode, String deviceId) {
		String roomNo = redisUtil.getData(inviteCode);

		System.out.println("roomNo : " + roomNo);

		Room room = roomRedisRepository.findById(roomNo)
			.orElseThrow(() -> new IllegalStateException());

		room.enter(deviceId);

		return roomRedisRepository.save(room).getRoomno();
	}

}
