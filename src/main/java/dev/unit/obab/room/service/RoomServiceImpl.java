package dev.unit.obab.room.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.core.util.RedisUtil;
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

	@Override
	public String createRoom(int totalCount) {
		Room room = new Room(totalCount);

		final Room savedRoom = roomRedisRepository.save(room);

		String inviteCode = RandomUtils.createInviteCode();

		/* invitecode : roomno 저장 */
		redisUtil.setData(inviteCode, savedRoom.getRoomNo());

		if (!Objects.nonNull(redisUtil.getData(inviteCode))) {
			throw new IllegalArgumentException("레디스에 저장이 되지 않았습니다.");
		}

		return inviteCode;
	}

	@Override
	public String enterRoom(String inviteCode, String deviceId) {
		String roomNo = Optional.ofNullable(redisUtil.getData(inviteCode))
			.orElseThrow(() -> new IllegalArgumentException("잘못된 추천 코드입니다."));

		Room room = roomRedisRepository.findById(roomNo)
			.orElseThrow(() -> new IllegalStateException("해당하는 roomNo에 해당하는 방이 없습니다."));

		return room.getRoomNo();
	}

}
