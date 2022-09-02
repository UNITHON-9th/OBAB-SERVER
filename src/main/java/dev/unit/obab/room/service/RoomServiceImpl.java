package dev.unit.obab.room.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.room.domain.MealType;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.util.RandomUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class RoomServiceImpl implements RoomService{

	private final RoomRedisRepository roomRedisRepository;

	public String createRoom(int totalCount, MealType mealType){

		String inviteCode = RandomUtils.createInviteCode();

		Room room = new Room(inviteCode, totalCount, mealType);

		roomRedisRepository.save(room);

		return inviteCode;
	}

}
