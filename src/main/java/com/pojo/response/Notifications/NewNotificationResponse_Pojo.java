package com.pojo.response.Notifications;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
public class NewNotificationResponse_Pojo {
        private String body;
        private BigInteger id;
        private Timestamp created_at;
        private Timestamp updated_at;
        private String bodyenglish;
        private String bodyarabic;
        private String subjectenglish;
        private String subject;
        private String subjectarabic;
        private BigInteger user_id;
        private String notification_type;
        private BigInteger notification_template_id;
        private String email;
        private String phone_number;
        private String apm_id;
        private String fcm_id;
        private String status;
        private String provider;
        private String notification_alert_type;

        public String getBody() {
                return body;
        }

        public BigInteger getId() {
                return id;
        }

        public Timestamp getCreated_at() {
                return created_at;
        }

        public Timestamp getUpdated_at() {
                return updated_at;
        }

        public String getBodyenglish() {
                return bodyenglish;
        }

        public String getBodyarabic() {
                return bodyarabic;
        }

        public String getSubjectenglish() {
                return subjectenglish;
        }

        public String getSubject() {
                return subject;
        }

        public String getSubjectarabic() {
                return subjectarabic;
        }

        public BigInteger getUser_id() {
                return user_id;
        }

        public String getNotification_type() {
                return notification_type;
        }

        public BigInteger getNotification_template_id() {
                return notification_template_id;
        }

        public String getEmail() {
                return email;
        }

        public String getPhone_number() {
                return phone_number;
        }

        public String getApm_id() {
                return apm_id;
        }

        public String getFcm_id() {
                return fcm_id;
        }

        public String getStatus() {
                return status;
        }

        public String getProvider() {
                return provider;
        }

        public String getNotification_alert_type() {
                return notification_alert_type;
        }
}

