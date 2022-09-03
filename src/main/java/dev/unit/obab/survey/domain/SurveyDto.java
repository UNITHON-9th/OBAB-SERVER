package dev.unit.obab.survey.domain;

import io.swagger.models.auth.In;
import lombok.Getter;

import java.util.List;

@Getter
public class SurveyDto {
    private String roomNo;
    private String deviceId;
    private Integer country;
    private Integer food;
    private Boolean isSpicy;
    private Boolean isSoup;
    private Boolean isHot;

    public Survey toEntity() {
        return Survey.builder()
                .roomNo(this.roomNo)
                .deviceId(this.deviceId)
                .country(this.country)
                .food(this.food)
                .isSpicy(this.isSpicy)
                .isHot(this.isHot)
                .isSoup(this.isSoup)
                .build();
    }

}
