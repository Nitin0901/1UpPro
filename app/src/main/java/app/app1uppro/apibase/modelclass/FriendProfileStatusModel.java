package app.app1uppro.apibase.modelclass;

public class FriendProfileStatusModel {


    /**
     * success : 200
     * message : User Profile.
     * data : {"user_id":"10","profile_id":"10","username":"nitin5911","profile_image":"http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg","others":{"avgScore":0.14285714285714,"videoCount":21,"challengeTaken":7,"challengeGiven":12,"challengeFollow":0}}
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
         * user_id : 10
         * profile_id : 10
         * username : nitin5911
         * profile_image : http://1uppro.com/UpproApp/uploads/user/profile/1537944661.jpg
         * others : {"avgScore":0.14285714285714,"videoCount":21,"challengeTaken":7,"challengeGiven":12,"challengeFollow":0}
         */

        private String user_id;
        private String profile_id;
        private String username;
        private String profile_image;
        private OthersBean others;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getProfile_id() {
            return profile_id;
        }

        public void setProfile_id(String profile_id) {
            this.profile_id = profile_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public OthersBean getOthers() {
            return others;
        }

        public void setOthers(OthersBean others) {
            this.others = others;
        }

        public static class OthersBean {
            /**
             * avgScore : 0.14285714285714
             * videoCount : 21
             * challengeTaken : 7
             * challengeGiven : 12
             * challengeFollow : 0
             */

            private double avgScore;
            private int videoCount;
            private int challengeTaken;
            private int challengeGiven;
            private int challengeFollow;

            public double getAvgScore() {
                return avgScore;
            }

            public void setAvgScore(double avgScore) {
                this.avgScore = avgScore;
            }

            public int getVideoCount() {
                return videoCount;
            }

            public void setVideoCount(int videoCount) {
                this.videoCount = videoCount;
            }

            public int getChallengeTaken() {
                return challengeTaken;
            }

            public void setChallengeTaken(int challengeTaken) {
                this.challengeTaken = challengeTaken;
            }

            public int getChallengeGiven() {
                return challengeGiven;
            }

            public void setChallengeGiven(int challengeGiven) {
                this.challengeGiven = challengeGiven;
            }

            public int getChallengeFollow() {
                return challengeFollow;
            }

            public void setChallengeFollow(int challengeFollow) {
                this.challengeFollow = challengeFollow;
            }
        }
    }
}
