package in.gov.cgg;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushFcmOptions;
import com.google.firebase.messaging.WebpushNotification;

@Service
public class PushNotificationService {

    public String sendNotificationToToken(String token, String title, String body, String link) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        WebpushConfig webpush = WebpushConfig.builder()
                .putHeader("Urgency", "high")
                .setNotification(
                    WebpushNotification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .setIcon("https://vitejs.dev/logo.svg")
                        .build()
                )
                .setFcmOptions(WebpushFcmOptions.withLink(link))
                .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .setWebpushConfig(webpush)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            return "Push sent successfully: " + response;
        } catch (FirebaseMessagingException e) {
            return "Error sending push: " + e.getMessage();
        }
    }
}
