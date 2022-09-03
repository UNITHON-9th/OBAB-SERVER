package dev.unit.obab.notification.domain;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FcmMessage {
    private boolean validateOnly;
    private Message message;

    @Getter
    @Builder
    public static class Message {
        private Notification notification;
        private Map<String, String> data;
        private String token;
    }

    @Getter
    @Builder
    public static class Notification {
        private String title;
    }
}
