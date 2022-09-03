package dev.unit.obab.survey.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.survey.domain.SurveyDto;
import dev.unit.obab.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final RoomRedisRepository roomRedisRepository;

    // Redis Template
    private final RedisUtil redisUtil;

    /* 개인 투표 결과 저장 */
    public void saveSurveyResult(SurveyDto surveyDto) {
        surveyRepository.save(surveyDto.toEntity());

        Room room = roomRedisRepository.findById(surveyDto.getRoomNo())
            .orElseThrow(() -> new IllegalStateException("해당하는 Room을 찾을 수 없습니다."));

        checkValidDeviceId(room, surveyDto.getDeviceId());
        room.addSubmittedCount();
        roomRedisRepository.save(room);

        if (room.isFull()) {
            // todo : 이벤트 -> 다른 서비스 호출 (통계, 다른 정보)
            // room deviceId -> 자료구조에 저장 한 후 서비스 호출
        }
    }

    /* 방에 참여한 사용자인지 유효성 체크 */
    private void checkValidDeviceId(Room room, String deviceId) {
        if (!room.getDeviceIds().contains(deviceId)) {
            throw new IllegalArgumentException("방에 참여한 사용자가 아닙니다.");
        }
    }
}
