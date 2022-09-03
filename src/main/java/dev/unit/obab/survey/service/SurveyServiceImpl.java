package dev.unit.obab.survey.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;
import dev.unit.obab.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final RoomRedisRepository roomRedisRepository;

    // Redis Template
    private final RedisUtil redisUtil;

    /* 개인 투표 결과 저장 */
    public void saveSurveyResult(CreateSurveyRequest createSurveyRequest) {
        surveyRepository.save(createSurveyRequest.toEntity());

        Room room = getRoom(createSurveyRequest);

        checkValidDeviceId(room, surveyDto.getDeviceId());
        room.addSubmittedCount();
        roomRedisRepository.save(room);

        if (room.isFull()) {
            // todo : 이벤트 -> 다른 서비스 호출 (통계, 다른 정보)
            // 자료구조에 저장 한 뒤 -> 알고리즘
            /*
            배열 내 deviceId값 반환하여 저장 -> 이후 deviceId값에 따라 SurveyResult에 저장
            SurveyResult에서
             */
            List<String> deviceIds = room.getDeviceIds();
            for(int i = 0; i< deviceIds.size(); i++) {
                // 첫번째
            }
        }
    }

    @Override
    public SurveyResponse getSurveyResult(String deviceId) {
        return surveyRepository.findById(deviceId).orElseThrow();
    }

    private Room getRoom(CreateSurveyRequest createSurveyRequest) {
        Room room = roomRedisRepository.findById(createSurveyRequest.getRoomNo())
                .orElseThrow(() -> new IllegalStateException("no room"));
        return room;
    }



    /* 방에 참여한 사용자인지 유효성 체크 */
    private void checkValidDeviceId(Room room, String deviceId) {
        if (!room.getDeviceIds().contains(deviceId)) {
            throw new IllegalArgumentException("방에 참여한 사용자가 아닙니다.");
        }
    }
}
