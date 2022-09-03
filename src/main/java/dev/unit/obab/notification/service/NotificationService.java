package dev.unit.obab.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import dev.unit.obab.notification.domain.FcmMessage;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    @Value("${fcm.project-id}")
    private String projectId;
    @Value("${fcm.firebase-path}")
    private String firebasePath;


    private final ObjectMapper objectMapper;
    private final String FIREBASE_SCOPE = "https://www.googleapis.com/auth/cloud-platform";


    public void sendMessageTo(String targetToken, String title, String body) throws IOException {
        String message = makeMessage(targetToken, title, body);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message,
            MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
            .url("https://fcm.googleapis.com/v1/projects/" + projectId + "/messages:send")
            .post(requestBody)
            .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
            .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8").build();

        client.newCall(request).execute();
    }

    private String makeMessage(String targetToken, String title, String body)
        throws JsonProcessingException {
        FcmMessage message = FcmMessage.builder().message(
            FcmMessage.Message.builder().token(targetToken).notification(
                    FcmMessage.Notification.builder().title(title).body(body).build())
                .build()).validateOnly(false).build();
        return objectMapper.writeValueAsString(message);
    }

    private String getAccessToken() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
            new ClassPathResource(firebasePath).getInputStream()).createScoped(
            List.of(FIREBASE_SCOPE));
        googleCredentials.refreshIfExpired();

        return googleCredentials.getAccessToken().getTokenValue();
    }

}
