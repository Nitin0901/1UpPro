package app.app1uppro.apibase.modelclass;

import java.util.ArrayList;
import java.util.List;

public class MessageDetailsModel {


    /**
     * success : 200
     * message : Messages.
     * data : [{"id":"2","user_from":"10","user_to":"29","subject":"hfhjgj","message_text":"Chhokar","read_status":"1","status":"0","created_at":"2018-09-27 11:15:13","reply":[{"id":"","message_id":"2","from":"10","to":"29","message_reply":"Chhokar","receiver_name":"amit3123","receiver_profile_image":"","sender_name":"nitin5911","sender_profile_image":"http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg","created_at":"2018-09-27 11:15:13","message_type":"1"},{"id":"1","message_id":"2","from":"29","to":"10","message_reply":"Reply done","created_at":"2018-09-27 11:16:52","receiver_name":"nitin5911","receiver_profile_image":"http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg","sender_name":"amit3123","sender_profile_image":"","message_type":"0"},{"id":"2","message_id":"2","from":"29","to":"10","message_reply":"Hi maggie","created_at":"2018-09-28 11:25:21","receiver_name":"nitin5911","receiver_profile_image":"http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg","sender_name":"amit3123","sender_profile_image":"","message_type":"0"}]}]
     */

    private int success;
    private String message;
    private ArrayList<DataBean> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * user_from : 10
         * user_to : 29
         * subject : hfhjgj
         * message_text : Chhokar
         * read_status : 1
         * status : 0
         * created_at : 2018-09-27 11:15:13
         * reply : [{"id":"","message_id":"2","from":"10","to":"29","message_reply":"Chhokar","receiver_name":"amit3123","receiver_profile_image":"","sender_name":"nitin5911","sender_profile_image":"http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg","created_at":"2018-09-27 11:15:13","message_type":"1"},{"id":"1","message_id":"2","from":"29","to":"10","message_reply":"Reply done","created_at":"2018-09-27 11:16:52","receiver_name":"nitin5911","receiver_profile_image":"http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg","sender_name":"amit3123","sender_profile_image":"","message_type":"0"},{"id":"2","message_id":"2","from":"29","to":"10","message_reply":"Hi maggie","created_at":"2018-09-28 11:25:21","receiver_name":"nitin5911","receiver_profile_image":"http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg","sender_name":"amit3123","sender_profile_image":"","message_type":"0"}]
         */

        private String id;
        private String user_from;
        private String user_to;
        private String subject;
        private String message_text;
        private String read_status;
        private String status;
        private String created_at;
        private ArrayList<ReplyBean> reply;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_from() {
            return user_from;
        }

        public void setUser_from(String user_from) {
            this.user_from = user_from;
        }

        public String getUser_to() {
            return user_to;
        }

        public void setUser_to(String user_to) {
            this.user_to = user_to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getMessage_text() {
            return message_text;
        }

        public void setMessage_text(String message_text) {
            this.message_text = message_text;
        }

        public String getRead_status() {
            return read_status;
        }

        public void setRead_status(String read_status) {
            this.read_status = read_status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public ArrayList<ReplyBean> getReply() {
            return reply;
        }

        public void setReply(ArrayList<ReplyBean> reply) {
            this.reply = reply;
        }

        public static class ReplyBean {
            /**
             * id :
             * message_id : 2
             * from : 10
             * to : 29
             * message_reply : Chhokar
             * receiver_name : amit3123
             * receiver_profile_image :
             * sender_name : nitin5911
             * sender_profile_image : http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg
             * created_at : 2018-09-27 11:15:13
             * message_type : 1
             */

            private String id;
            private String message_id;
            private String from;
            private String to;
            private String message_reply;
            private String receiver_name;
            private String receiver_profile_image;
            private String sender_name;
            private String sender_profile_image;
            private String created_at;
            private String message_type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMessage_id() {
                return message_id;
            }

            public void setMessage_id(String message_id) {
                this.message_id = message_id;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public String getMessage_reply() {
                return message_reply;
            }

            public void setMessage_reply(String message_reply) {
                this.message_reply = message_reply;
            }

            public String getReceiver_name() {
                return receiver_name;
            }

            public void setReceiver_name(String receiver_name) {
                this.receiver_name = receiver_name;
            }

            public String getReceiver_profile_image() {
                return receiver_profile_image;
            }

            public void setReceiver_profile_image(String receiver_profile_image) {
                this.receiver_profile_image = receiver_profile_image;
            }

            public String getSender_name() {
                return sender_name;
            }

            public void setSender_name(String sender_name) {
                this.sender_name = sender_name;
            }

            public String getSender_profile_image() {
                return sender_profile_image;
            }

            public void setSender_profile_image(String sender_profile_image) {
                this.sender_profile_image = sender_profile_image;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getMessage_type() {
                return message_type;
            }

            public void setMessage_type(String message_type) {
                this.message_type = message_type;
            }
        }
    }
}
