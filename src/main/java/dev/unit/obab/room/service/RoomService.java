package dev.unit.obab.room.service;

import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.service.dto.EnterRoomResult;

public interface RoomService {

	/* 방 생성 */
	String createRoom(int totalCount);

	/* 방 입장 */
	EnterRoomResult enterRoom(String inviteCode, String deviceId);

	/* 방 조회 */
	Room getRoom(String roomNo);

}
