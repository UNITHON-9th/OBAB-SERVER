package dev.unit.obab.survey.service;

import dev.unit.obab.core.util.RedisUtil;
import dev.unit.obab.survey.domain.Survey;
import dev.unit.obab.survey.domain.SurveyDto;
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

    // Redis Template
    private final RedisUtil redisUtil;

    /* 개인 투표 결과 저장 */
    // stringRedisTemplate.opsForList().rightPushAll(key, values);
    // value = first 어쩌구 쿼리스트링 값
    // 스피드왕의 선택은?!
    // 점수 api 쏘는 시간
    // 너의 선택은? = /
    // 랜덤 ----> 점수로 (아무거나)
    public void saveSurveyResult(SurveyDto surveyDto) {
        log.info("@@@@@@@@ {}", surveyDto.getDeviceId());
        redisUtil.setListData(surveyDto.getDeviceId(), surveyDto.getCheckList());
    }

    // redis 저장 ->> 투표 결과
    public void checkCount(Survey survey) {

    }
}
