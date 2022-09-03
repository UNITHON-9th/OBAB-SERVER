package dev.unit.obab.survey.controller.dto;

import dev.unit.obab.survey.domain.Survey;
import lombok.Getter;

@Getter
public class SurveyResponse {
    private Integer country;
    private Integer food;
    private Boolean isSpicy;
    private Boolean isSoup;
    private Boolean isHot;
    private int submitCount;
    private int totalCount;

   public SurveyResponse(Survey survey, int submitCount, int totalCount) {
       this.country = survey.getCountry();
       this.food = survey.getFood();
       this.isSpicy = survey.getIsSpicy();
       this.isSoup = survey.getIsSoup();
       this.isHot = survey.getIsHot();
       this.submitCount = submitCount;
       this.totalCount = totalCount;
   }

}
