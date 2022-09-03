package dev.unit.obab.room.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.util.RandomUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class RoomServiceImpl implements RoomService{

	private final RoomRedisRepository roomRedisRepository;
	private final RedisUtil redisUtil;

	public String createRoom(int totalCount){
		Room room = new Room(totalCount);

		final Room savedRoom = roomRedisRepository.save(room);

		String inviteCode = RandomUtils.createInviteCode();

		/* invitecode : roomno 저장 */
		redisUtil.setData(inviteCode, savedRoom.getRoomno());

		return inviteCode;
	}

}
