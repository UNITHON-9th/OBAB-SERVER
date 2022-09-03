package dev.unit.obab.room.service;

public interface RoomService {

	/* 방 생성 */
	String createRoom(int totalCount);

	/* 방 입장 */
	String enterRoom(String inviteCode, String deviceId);
  
}
