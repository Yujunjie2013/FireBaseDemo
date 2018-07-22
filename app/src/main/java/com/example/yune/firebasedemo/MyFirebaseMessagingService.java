package com.example.yune.firebasedemo;


import android.app.Notification;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nfrom:")
                .append(remoteMessage.getFrom())
                .append("\nkey:")
                .append(remoteMessage.getCollapseKey())
                .append("\ndata:")
                .append(remoteMessage.getData())
                .append("\nmessageId:")
                .append(remoteMessage.getMessageId())
                .append("\nMessageType:")
                .append(remoteMessage.getMessageType())
                .append("\noriginal:")
                .append(remoteMessage.getOriginalPriority())
                .append("\nPriority:")
                .append(remoteMessage.getPriority())
                .append("\nSentTime:")
                .append(remoteMessage.getSentTime())
                .append("\nTo:")
                .append(remoteMessage.getTo())
                .append("\nNotifity:")
                .append(remoteMessage.getNotification())
                .append("\nTtal:")
                .append(remoteMessage.getTtl());
        if (remoteMessage.getNotification() != null) {
            RemoteMessage.Notification notification = remoteMessage.getNotification();
            sb.append("\nNotify:\n")
                    .append("\nbody:")
                    .append(notification.getBody())
                    .append("\nBodyKey:")
                    .append(notification.getBodyLocalizationKey())
                    .append("\nClickAction:")
                    .append(notification.getClickAction())
                    .append("\nColor:")
                    .append(notification.getColor())
                    .append("\nIcon:")
                    .append(notification.getIcon())
                    .append("\nLink:")
                    .append(notification.getLink())
                    .append("\nSound:")
                    .append(notification.getSound())
                    .append("\nTag:")
                    .append(notification.getTag())
                    .append("\nTitle:")
                    .append(notification.getTitle())
                    .append("\nTitleKey:")
                    .append(notification.getTitleLocalizationKey());
        }
//        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "收到消息--" + sb.toString());
        NotificationHelper notificationHelper = new NotificationHelper(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e(TAG, "我是26的版本");
            Notification.Builder builder = notificationHelper.getNotification1(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            notificationHelper.notify(0, builder);
        } else {
            Log.d(TAG, "不是26的版本--");
            NotificationCompat.Builder notificationCompat = notificationHelper.getNotificationCompat(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            notificationHelper.notifyCompat(0, notificationCompat.build());
        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d(TAG, "注册令牌:" + s);

    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        Log.d(TAG, "开始删除消息吗？");
    }
}
