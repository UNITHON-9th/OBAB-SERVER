package dev.unit.obab.room.service;

public interface RoomService {

	String createRoom(int totalCount);

	/* 방 입장 */
	String enterRoom(String inviteCode, String deviceId);
  
}
