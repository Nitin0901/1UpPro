package app.app1uppro.apibase.modelclass;

import java.util.ArrayList;
import java.util.List;

public class FollowedCategoryModel {


    /**
     * success : 200
     * message : Followed Video Categories
     * data : [{"ID":"127","userID":"10","catID":"3","created_at":"2018-09-10 17:16:04","videoCatName":"Sports"},{"ID":"126","userID":"10","catID":"4","created_at":"2018-09-10 17:16:00","videoCatName":"Dance"},{"ID":"125","userID":"10","catID":"6","created_at":"2018-09-10 17:15:56","videoCatName":"Magic"},{"ID":"124","userID":"10","catID":"1","created_at":"2018-09-10 17:15:49","videoCatName":"Music"},{"ID":"8","userID":"10","catID":"7","created_at":"2018-08-23 18:28:22","videoCatName":"Comedy"}]
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
         * ID : 127
         * userID : 10
         * catID : 3
         * created_at : 2018-09-10 17:16:04
         * videoCatName : Sports
         */

        private String ID;
        private String userID;
        private String catID;
        private String created_at;
        private String videoCatName;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getCatID() {
            return catID;
        }

        public void setCatID(String catID) {
            this.catID = catID;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getVideoCatName() {
            return videoCatName;
        }

        public void setVideoCatName(String videoCatName) {
            this.videoCatName = videoCatName;
        }
    }
}
