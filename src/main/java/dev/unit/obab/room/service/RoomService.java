package dev.unit.obab.room.service;

import dev.unit.obab.room.service.dto.EnterRoomResult;

public interface RoomService {

	/* 방 생성 */
	String createRoom(int totalCount);

	/* 방 입장 */
	EnterRoomResult enterRoom(String inviteCode, String deviceId);

}
