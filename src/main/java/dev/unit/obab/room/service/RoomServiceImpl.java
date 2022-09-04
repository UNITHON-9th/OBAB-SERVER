package dev.unit.obab.room.service;

import dev.unit.obab.room.controller.dto.RoomResponseDto;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.room.service.dto.EnterRoomResult;
import dev.unit.obab.util.RandomUtils;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

	private final RoomRedisRepository roomRedisRepository;
	private final RedisUtil redisUtil;

	@Override
	public RoomResponseDto createRoom(int totalCount) {
		Room room = new Room(totalCount);

		final Room savedRoom = roomRedisRepository.save(room);

		String inviteCode = RandomUtils.createInviteCode();

		/* invitecode : roomno 저장 */
		redisUtil.setData(inviteCode, savedRoom.getRoomNo());

		return RoomResponseDto.builder()
			.inviteCode(inviteCode)
			.roomNo(savedRoom.getRoomNo())
			.build();
	}

	@Override
	public EnterRoomResult enterRoom(String inviteCode, String deviceId) {
		String roomNo = Optional.ofNullable(redisUtil.getData(inviteCode))
			.orElseThrow(() -> new IllegalArgumentException("잘못된 추천 코드입니다."));

		Room room = getRoom(roomNo);

		if (room.isAlreadyEntered(deviceId)) {
			return new EnterRoomResult(room.getRoomNo(), true);
		}

		room.enter(deviceId);
		roomRedisRepository.save(room);

		return new EnterRoomResult(room.getRoomNo(), false);
	}

	@Override
	public Room getRoom(final String roomNo) {
		return roomRedisRepository.findById(roomNo)
			.orElseThrow(() -> new IllegalStateException("해당하는 roomNo에 해당하는 방이 없습니다."));
	}
}
