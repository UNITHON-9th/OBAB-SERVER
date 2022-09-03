package dev.unit.obab.room.service;

import dev.unit.obab.room.domain.MealType;

public interface RoomService {

	/* 방 생성 */
	String createRoom(int totalCount, MealType mealType);

	/* 방 입장 */
	String enterRoom(String inviteCode, String deviceId);
}
