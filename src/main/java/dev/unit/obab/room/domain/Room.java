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
@RedisHash(value = "room")
public class Room {

	/* 초대 코드 */
	@Id
	private String roomNo;

	/* 방 총 인원 */
	private int totalCount;

	/* 결과 제출한 디바이스 id*/
	private List<String> deviceIds = new ArrayList<>();

	public Room(int totalCount) {
		this.totalCount = totalCount;
	}

	public void enter(String deviceId) {
		if(deviceIds.size() >= totalCount){
			throw new IllegalArgumentException("정원이 다 찼습니다.");
		}
		this.deviceIds.add(deviceId);
	}

	public boolean isFull() {
		return this.totalCount == deviceIds.size();
	}

}
