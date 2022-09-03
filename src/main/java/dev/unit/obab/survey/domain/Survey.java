package dev.unit.obab.survey.domain;


import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@RedisHash(value = "survey", timeToLive = 300)
public class Survey implements Serializable {

    /* 각 디바이스 ID */
    @Id
    private String deviceId;

    /* 디바이스별 선택 목록 */
    private List<Integer> checkList = new ArrayList<>();


    public Survey(String deviceId) {
        this.deviceId = deviceId;
    }


//    public void addCheckList(Integer checkList) { this.checkList.add(checkList);}

}
