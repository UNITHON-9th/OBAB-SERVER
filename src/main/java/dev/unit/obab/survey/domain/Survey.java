package dev.unit.obab.survey.domain;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Builder
@RedisHash(value = "survey")
public class Survey implements Serializable {

    /* 각 디바이스 ID */
    @Id
    private String deviceId;

    private String roomNo;
    private Integer country;
    private Integer food;
    private Boolean isSpicy;
    private Boolean isSoup;
    private Boolean isHot;

}
