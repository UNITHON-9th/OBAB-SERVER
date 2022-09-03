package dev.unit.obab.survey.repository;

import dev.unit.obab.survey.domain.Survey;
import org.springframework.data.repository.CrudRepository;

public interface SurveyRepository extends CrudRepository<Survey, String> {

}
