package app.app1uppro.apibase.modelclass;

import java.util.ArrayList;
import java.util.List;

public class CallOutsModel {


    /**
     * success : 200
     * message : Callsout Lists.
     * data : [{"challengeID":"32","challengeStartDate":"2018-09-07","challengeEndDate":"2018-10-07","ChallengeCatID":"6","CatID":"6","challengeCreatorID":"26","challengerID":"10","challengeCreatorVideoID":"17","challengerVideoID":"0","challengeLength":"30","rules":"","challengeWinnerID":"0","orientation":"Vertical","sendChallengeTo":"sfs.nitin18@gmail.com","ChallengeType":"1","status":"0","created_at":"2018-09-07 17:13:32","creatorusername":"amit","videoEmbedID":"cp2iwsyFsz0","videoName":"testing nominate","videoDescription":"","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","VideoCategories":[{"videoCatID":"6","videoCatName":"Magic"}]}]
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
         * challengeID : 32
         * challengeStartDate : 2018-09-07
         * challengeEndDate : 2018-10-07
         * ChallengeCatID : 6
         * CatID : 6
         * challengeCreatorID : 26
         * challengerID : 10
         * challengeCreatorVideoID : 17
         * challengerVideoID : 0
         * challengeLength : 30
         * rules :
         * challengeWinnerID : 0
         * orientation : Vertical
         * sendChallengeTo : sfs.nitin18@gmail.com
         * ChallengeType : 1
         * status : 0
         * created_at : 2018-09-07 17:13:32
         * creatorusername : amit
         * videoEmbedID : cp2iwsyFsz0
         * videoName : testing nominate
         * videoDescription :
         * videoUpVotes : 0
         * videoDownVotes : 0
         * videoScore : 0
         * VideoCategories : [{"videoCatID":"6","videoCatName":"Magic"}]
         */

        private String challengeID;
        private String challengeStartDate;
        private String challengeEndDate;
        private String ChallengeCatID;
        private String CatID;
        private String challengeCreatorID;
        private String challengerID;
        private String challengeCreatorVideoID;
        private String challengerVideoID;
        private String challengeLength;
        private String rules;
        private String challengeWinnerID;
        private String orientation;
        private String sendChallengeTo;
        private String ChallengeType;
        private String status;
        private String created_at;
        private String creatorusername;
        private String videoEmbedID;
        private String videoName;
        private String videoDescription;
        private String videoUpVotes;
        private String videoDownVotes;
        private String videoScore;
        private List<VideoCategoriesBean> VideoCategories;

        public String getChallengeID() {
            return challengeID;
        }

        public void setChallengeID(String challengeID) {
            this.challengeID = challengeID;
        }

        public String getChallengeStartDate() {
            return challengeStartDate;
        }

        public void setChallengeStartDate(String challengeStartDate) {
            this.challengeStartDate = challengeStartDate;
        }

        public String getChallengeEndDate() {
            return challengeEndDate;
        }

        public void setChallengeEndDate(String challengeEndDate) {
            this.challengeEndDate = challengeEndDate;
        }

        public String getChallengeCatID() {
            return ChallengeCatID;
        }

        public void setChallengeCatID(String ChallengeCatID) {
            this.ChallengeCatID = ChallengeCatID;
        }

        public String getCatID() {
            return CatID;
        }

        public void setCatID(String CatID) {
            this.CatID = CatID;
        }

        public String getChallengeCreatorID() {
            return challengeCreatorID;
        }

        public void setChallengeCreatorID(String challengeCreatorID) {
            this.challengeCreatorID = challengeCreatorID;
        }

        public String getChallengerID() {
            return challengerID;
        }

        public void setChallengerID(String challengerID) {
            this.challengerID = challengerID;
        }

        public String getChallengeCreatorVideoID() {
            return challengeCreatorVideoID;
        }

        public void setChallengeCreatorVideoID(String challengeCreatorVideoID) {
            this.challengeCreatorVideoID = challengeCreatorVideoID;
        }

        public String getChallengerVideoID() {
            return challengerVideoID;
        }

        public void setChallengerVideoID(String challengerVideoID) {
            this.challengerVideoID = challengerVideoID;
        }

        public String getChallengeLength() {
            return challengeLength;
        }

        public void setChallengeLength(String challengeLength) {
            this.challengeLength = challengeLength;
        }

        public String getRules() {
            return rules;
        }

        public void setRules(String rules) {
            this.rules = rules;
        }

        public String getChallengeWinnerID() {
            return challengeWinnerID;
        }

        public void setChallengeWinnerID(String challengeWinnerID) {
            this.challengeWinnerID = challengeWinnerID;
        }

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {
            this.orientation = orientation;
        }

        public String getSendChallengeTo() {
            return sendChallengeTo;
        }

        public void setSendChallengeTo(String sendChallengeTo) {
            this.sendChallengeTo = sendChallengeTo;
        }

        public String getChallengeType() {
            return ChallengeType;
        }

        public void setChallengeType(String ChallengeType) {
            this.ChallengeType = ChallengeType;
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

        public String getCreatorusername() {
            return creatorusername;
        }

        public void setCreatorusername(String creatorusername) {
            this.creatorusername = creatorusername;
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

        public List<VideoCategoriesBean> getVideoCategories() {
            return VideoCategories;
        }

        public void setVideoCategories(List<VideoCategoriesBean> VideoCategories) {
            this.VideoCategories = VideoCategories;
        }

        public static class VideoCategoriesBean {
            /**
             * videoCatID : 6
             * videoCatName : Magic
             */

            private String videoCatID;
            private String videoCatName;

            public String getVideoCatID() {
                return videoCatID;
            }

            public void setVideoCatID(String videoCatID) {
                this.videoCatID = videoCatID;
            }

            public String getVideoCatName() {
                return videoCatName;
            }

            public void setVideoCatName(String videoCatName) {
                this.videoCatName = videoCatName;
            }
        }
    }
}//end main class
