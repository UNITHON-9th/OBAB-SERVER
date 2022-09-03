package dev.unit.obab.room.repository;

import org.springframework.data.repository.CrudRepository;

import dev.unit.obab.room.domain.Room;

public interface RoomRedisRepository extends CrudRepository<Room, String> {
}
