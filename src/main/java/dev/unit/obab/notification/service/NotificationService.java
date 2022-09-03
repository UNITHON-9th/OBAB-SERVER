package dev.unit.obab.notification.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;

import dev.unit.obab.core.domain.ResponseType;
import dev.unit.obab.core.exception.BadRequestException;
import dev.unit.obab.core.exception.ExternalServerException;
import dev.unit.obab.core.exception.NotFoundException;
import dev.unit.obab.notification.domain.FcmMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import reactor.core.publisher.Flux;


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


    public void sendMessageTo(List<String> targets, String title, String body) {
        OkHttpClient client = new OkHttpClient();

        Flux<String> fcmMessages = Flux.fromIterable(
            targets.stream().map(token -> makeMessage(token, title, body)).collect(Collectors.toList()));

        fcmMessages.subscribe(message -> {
                RequestBody requestBody = RequestBody.create(message,
                    MediaType.get("application/json; charset=utf-8"));

                Request request = new Request.Builder()
                    .url("https://fcm.googleapis.com/v1/projects/" + projectId + "/messages:send")
                    .post(requestBody)
                    .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                    .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8").build();

                try {
                    client.newCall(request).execute();
                } catch (Exception e) {
                    throw new ExternalServerException(ResponseType.FCM_CONNECT_FAILURE);
                }
            }
        );
    }

    private String makeMessage(String targetToken, String title, String body) {
        FcmMessage message = FcmMessage.builder().message(
            FcmMessage.Message.builder().token(targetToken).notification(
                    FcmMessage.Notification.builder().title(title).body(body).build())
                .build()).validateOnly(false).build();
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new BadRequestException(ResponseType.PARSE_FAILURE);
        }

    }

    private String getAccessToken() {
        try {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(
                new ClassPathResource(firebasePath).getInputStream()).createScoped(
                List.of(FIREBASE_SCOPE));
            googleCredentials.refreshIfExpired();

            return googleCredentials.getAccessToken().getTokenValue();
        } catch (IOException e) {
            throw new NotFoundException(ResponseType.FCM_KEY_NOT_FOUND);
        }
    }

}
