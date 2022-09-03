package dev.unit.obab.survey.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
public class SurveyResult {

    @Id
    private String roomNo;
    private int countryResult;
    private int foodResult;
    private int isSpicyResult;
    private int isSoupResult;
    private int isHotResult;

}
