package dev.unit.obab.survey.service;

import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.domain.SurveyDto;
import dev.unit.obab.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final RoomRedisRepository roomRedisRepository;

    // Redis Template
    private final RedisUtil redisUtil;

    /* 개인 투표 결과 저장 */
    // 스피드왕의 선택은?!
    // 점수 api 쏘는 시간
    // 너의 선택은? = /
    // 랜덤 ----> 점수로 (아무거나)
    public void saveSurveyResult(SurveyDto surveyDto) {
        log.info("@@@@@@@@ {}", surveyDto.getDeviceId());
        log.info("@@@@@@@@ {}", surveyDto.getRoomNo());
        surveyRepository.save(surveyDto.toEntity());

        Room room = roomRedisRepository.findById(surveyDto.getRoomNo())
                .orElseThrow(() -> new IllegalStateException("no room"));

        room.enter(surveyDto.getDeviceId());
        roomRedisRepository.save(room);

        if (room.isFull()) {
            // todo : 이벤트 -> 다른 서비스 호출 (통계, 다른 정보)
            // room deviceId -> 자료구조에 저장 한 후 서비스 호출
        }

    }

}
