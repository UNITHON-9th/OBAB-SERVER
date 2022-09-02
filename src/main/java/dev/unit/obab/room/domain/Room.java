package dev.unit.obab.room.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;

/**
 * 방
 */
@Getter
@RedisHash(value = "room", timeToLive = 300)
public class Room {

	/* 초대 코드 */
	@Id
	private String inviteCode;

	/* 방 총 인원 */
	private int totalCount;

	/* 현재 입장 인원 */
	private int enteredCount = 0;

	/* 입장 디바이스 정보들*/
	private List<String> deviceIds = new ArrayList<>();

	/* 식사 타입 */
	private MealType mealType;

	public Room(String inviteCode, int totalCount, MealType mealType) {
		this.inviteCode = inviteCode;
		this.totalCount = totalCount;
		this.mealType = mealType;
	}

	public void addDeviceIds(String deviceId) {
		this.deviceIds.add(deviceId);
	}

	public boolean isFull() {
		return this.totalCount == this.enteredCount;
	}

}
