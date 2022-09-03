package dev.unit.obab.survey.service;

import dev.unit.obab.notification.service.NotificationService;
import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
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

    private final NotificationService notificationService;

    /* 개인 투표 결과 저장 */
    public void saveSurveyResult(SurveyDto surveyDto) {
        surveyRepository.save(surveyDto.toEntity());

        Room room = roomRedisRepository.findById(surveyDto.getRoomNo())
            .orElseThrow(() -> new IllegalStateException("해당하는 Room을 찾을 수 없습니다."));

        checkValidDeviceId(room, surveyDto.getDeviceId());
        room.addSubmittedCount();
        roomRedisRepository.save(room);

        log.info(String.valueOf(room.isFull()));
        log.info(String.valueOf(room.getDeviceIds()));

        if (room.isFull()) {
            log.info("@@@@@@@@@@@ Survey Finished @@@@@@@@@@@");
            this.notificationService.sendMessageTo(room.getDeviceIds(), room.getRoomNo());
        }
    }

    /* 방에 참여한 사용자인지 유효성 체크 */
    private void checkValidDeviceId(Room room, String deviceId) {
        if (!room.getDeviceIds().contains(deviceId)) {
            throw new IllegalArgumentException("방에 참여한 사용자가 아닙니다.");
        }
    }
}
