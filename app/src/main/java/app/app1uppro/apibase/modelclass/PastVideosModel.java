package app.app1uppro.apibase.modelclass;

import java.util.ArrayList;
import java.util.List;

public class PastVideosModel {


    /**
     * success : 200
     * message : Past Videos Lists.
     * data : [{"videoID":"1","videoCatID":"1","videoEmbedID":"2esnPMjJOIY","videoName":"new video","videoDescription":"sbsndj","videoOwnerID":"3","videoUpVotes":"1","videoDownVotes":"0","videoScore":"1","videoCatName":"Music","challengeID":"1"},{"videoID":"7","videoCatID":"5","videoEmbedID":"Ia61iXAzGac","videoName":"shsd","videoDescription":"bsn","videoOwnerID":"4","videoUpVotes":"1","videoDownVotes":"0","videoScore":"1","videoCatName":"Art","challengeID":"2"},{"videoID":"2","videoCatID":"1","videoEmbedID":"hbZhfJH3-wM","videoName":"new title","videoDescription":"helo","videoOwnerID":"4","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","videoCatName":"Music","challengeID":"1"},{"videoID":"3","videoCatID":"5","videoEmbedID":"yUr4JP_L8mk","videoName":"open","videoDescription":"sndn","videoOwnerID":"3","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","videoCatName":"Art","challengeID":"2"},{"videoID":"4","videoCatID":"6","videoEmbedID":"M0wL_YDBlDI","videoName":"hello","videoDescription":"ssbsh","videoOwnerID":"4","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","videoCatName":"Magic","challengeID":"3"},{"videoID":"5","videoCatID":"3","videoEmbedID":"q5iP3m92h5k","videoName":"satisfaction","videoDescription":"sbsnd","videoOwnerID":"4","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","videoCatName":"Sports","challengeID":"4"},{"videoID":"6","videoCatID":"1","videoEmbedID":"6gqiRrHT4eA","videoName":"music","videoDescription":"z dnddndnd","videoOwnerID":"4","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","videoCatName":"Music","challengeID":"5"}]
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
         * videoID : 1
         * videoCatID : 1
         * videoEmbedID : 2esnPMjJOIY
         * videoName : new video
         * videoDescription : sbsndj
         * videoOwnerID : 3
         * videoUpVotes : 1
         * videoDownVotes : 0
         * videoScore : 1
         * videoCatName : Music
         * challengeID : 1
         */

        private String videoID;
        private String videoCatID;
        private String videoEmbedID;
        private String videoName;
        private String videoDescription;
        private String videoOwnerID;
        private String videoUpVotes;
        private String videoDownVotes;
        private String videoScore;
        private String videoCatName;
        private String challengeID;

        public String getVideoID() {
            return videoID;
        }

        public void setVideoID(String videoID) {
            this.videoID = videoID;
        }

        public String getVideoCatID() {
            return videoCatID;
        }

        public void setVideoCatID(String videoCatID) {
            this.videoCatID = videoCatID;
        }

        public String getVideoEmbedID() {
            return videoEmbedID;
        }

        public void setVideoEmbedID(String videoEmbedID) {
            this.videoEmbedID = videoEmbedID;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public String getVideoDescription() {
            return videoDescription;
        }

        public void setVideoDescription(String videoDescription) {
            this.videoDescription = videoDescription;
        }

        public String getVideoOwnerID() {
            return videoOwnerID;
        }

        public void setVideoOwnerID(String videoOwnerID) {
            this.videoOwnerID = videoOwnerID;
        }

        public String getVideoUpVotes() {
            return videoUpVotes;
        }

        public void setVideoUpVotes(String videoUpVotes) {
            this.videoUpVotes = videoUpVotes;
        }

        public String getVideoDownVotes() {
            return videoDownVotes;
        }

        public void setVideoDownVotes(String videoDownVotes) {
            this.videoDownVotes = videoDownVotes;
        }

        public String getVideoScore() {
            return videoScore;
        }

        public void setVideoScore(String videoScore) {
            this.videoScore = videoScore;
        }

        public String getVideoCatName() {
            return videoCatName;
        }

        public void setVideoCatName(String videoCatName) {
            this.videoCatName = videoCatName;
        }

        public String getChallengeID() {
            return challengeID;
        }

        public void setChallengeID(String challengeID) {
            this.challengeID = challengeID;
        }
    }
}
