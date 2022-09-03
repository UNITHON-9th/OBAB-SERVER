package dev.unit.obab.survey.service;
import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.survey.domain.SurveyDto;
import dev.unit.obab.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
                .orElseThrow(() -> new IllegalStateException("no room"));

        room.enter(surveyDto.getDeviceId());
        roomRedisRepository.save(room);

        if (room.isFull()) {
            // todo : 이벤트 -> 다른 서비스 호출 (통계, 다른 정보)
            // room deviceId -> 자료구조에 저장 한 후 서비스 호출
        }
    }
}
