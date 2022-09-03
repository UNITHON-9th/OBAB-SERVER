package dev.unit.obab.survey.service;

import dev.unit.obab.notification.service.NotificationService;
import dev.unit.obab.room.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.unit.obab.room.domain.Room;
import dev.unit.obab.room.repository.RoomRedisRepository;
import dev.unit.obab.survey.controller.dto.SurveyResponse;
import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.controller.dto.CreateSurveyRequest;
import dev.unit.obab.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
@Slf4j
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final RoomRedisRepository roomRedisRepository;
    private final RoomService roomService;
    private final NotificationService notificationService;

    /* 개인 투표 결과 저장 */
    public void saveSurveyResult(CreateSurveyRequest createSurveyRequest) {

        Room room = roomService.getRoom(createSurveyRequest.getRoomNo());

        checkValidDeviceId(room, createSurveyRequest.getDeviceId());
        surveyRepository.save(createSurveyRequest.toEntity());

        room.addSubmittedCount();
        roomRedisRepository.save(room);

        log.info(String.valueOf(room.isFull()));
        log.info(String.valueOf(room.getDeviceIds()));

        if (room.isFull()) {

            log.info("@@@@@@@@@@@ Survey Finished @@@@@@@@@@@");
            this.notificationService.sendMessageTo(room.getDeviceIds(), room.getRoomNo());

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
    public SurveyResponse getSurveyResult(String deviceId, String roomNo) {
        Survey survey = surveyRepository.findById(deviceId).orElseThrow();

        Room room = roomService.getRoom(roomNo);
        return new SurveyResponse(survey, room.getSubmittedCount(), room.getTotalCount());
    }

    /* 방에 참여한 사용자인지 유효성 체크 */
    private void checkValidDeviceId(Room room, String deviceId) {
        if (!room.getDeviceIds().contains(deviceId)) {
            throw new IllegalArgumentException("방에 참여한 사용자가 아닙니다.");
        }
    }
}
