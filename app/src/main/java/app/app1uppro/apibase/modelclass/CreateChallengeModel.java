package app.app1uppro.apibase.modelclass;

public class CreateChallengeModel {


    /**
     * success : 200
     * message : Challenge created successfully
     * data : {"challengeID":"15","challengeStartDate":"2018-09-05","challengeEndDate":"2018-10-05","ChallengeCatID":"34","CatID":"1,7,34","challengeCreatorID":"10","challengerID":"0","challengeCreatorVideoID":"0","challengerVideoID":"0","challengeLength":"30","rules":"only one rule. donot follow any rule","challengeWinnerID":"0","orientation":"Vertical","sendChallengeTo":"","ChallengeType":"0","status":"0","created_at":"2018-09-05 11:03:36"}
     */

    private int success;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * challengeID : 15
         * challengeStartDate : 2018-09-05
         * challengeEndDate : 2018-10-05
         * ChallengeCatID : 34
         * CatID : 1,7,34
         * challengeCreatorID : 10
         * challengerID : 0
         * challengeCreatorVideoID : 0
         * challengerVideoID : 0
         * challengeLength : 30
         * rules : only one rule. donot follow any rule
         * challengeWinnerID : 0
         * orientation : Vertical
         * sendChallengeTo :
         * ChallengeType : 0
         * status : 0
         * created_at : 2018-09-05 11:03:36
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
    }
}
