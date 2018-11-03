package app.app1uppro.apibase.modelclass;

import java.util.ArrayList;
import java.util.List;

public class FriendsListModel {


    /**
     * success : 200
     * message : Friend Lists.
     * data : [{"id":"2","user_id":"10","friend_id":"11","status":"0","username":"amit","name":"Amit Kumar"},{"id":"3","user_id":"10","friend_id":"4","status":"0","username":"kirti","name":"Kirti Khatri"},{"id":"4","user_id":"10","friend_id":"1","status":"0","username":"deepika","name":"Deepika chopra"},{"id":"5","user_id":"10","friend_id":"3","status":"0","username":"Ankit","name":"Ankit Sethi"}]
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
         * user_id : 10
         * friend_id : 11
         * status : 0
         * username : amit
         * name : Amit Kumar
         */

        private String id;
        private String user_id;
        private String friend_id;
        private String status;
        private String username;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getFriend_id() {
            return friend_id;
        }

        public void setFriend_id(String friend_id) {
            this.friend_id = friend_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
