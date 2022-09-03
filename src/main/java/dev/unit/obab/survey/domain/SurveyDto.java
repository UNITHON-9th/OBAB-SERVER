package dev.unit.obab.survey.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class SurveyDto {

    private String deviceId;
    private List<String> checkList;

}
