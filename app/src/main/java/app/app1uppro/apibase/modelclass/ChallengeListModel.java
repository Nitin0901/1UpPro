package app.app1uppro.apibase.modelclass;

import java.util.List;

public class ChallengeListModel {


    /**
     * success : 200
     * message : Challenge List.
     * data : {"challenge_data":[{"challengeID":"31","challengeStartDate":"2018-09-07","challengeEndDate":"2018-10-07","ChallengeCatID":"6","CatID":"6","challengeCreatorID":"26","challengerID":"26","challengeCreatorVideoID":"16","challengerVideoID":"18","challengeLength":"30","rules":"","challengeWinnerID":"0","orientation":"Vertical","sendChallengeTo":"","ChallengeType":"0","status":"1","created_at":"2018-09-07 17:11:58","creatorusername":"amit","creatorvideoEmbedID":"ZFZmZDswtLo","creatorvideoName":"testing","creatorvideoDescription":"","creatorvideoUpVotes":"0","creatorvideoDownVotes":"0","creatorvideoScore":"0","challengerusername":"amit","videoEmbedID":"hA5UwA2lgHE","videoName":"test ios video","videoDescription":"fjfffudjf","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","VideoCategories":[{"videoCatID":"6","videoCatName":"Magic"}]},{"challengeID":"14","challengeStartDate":"2018-08-31","challengeEndDate":"2018-10-07","ChallengeCatID":"34","CatID":"1,9,34","challengeCreatorID":"4","challengerID":"26","challengeCreatorVideoID":"10","challengerVideoID":"15","challengeLength":"30","rules":"","challengeWinnerID":"0","orientation":"Vertical","sendChallengeTo":"","ChallengeType":"0","status":"1","created_at":"2018-08-31 16:21:09","creatorusername":"kirti","creatorvideoEmbedID":"T1ZE-0xBkM4","creatorvideoName":"fjg","creatorvideoDescription":"hhh","creatorvideoUpVotes":"1","creatorvideoDownVotes":"0","creatorvideoScore":"1","challengerusername":"amit","videoEmbedID":"GBX-6Zxby1Q","videoName":"jjh","videoDescription":"hgj","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","VideoCategories":[{"videoCatID":"6","videoCatName":"Magic"},{"videoCatID":"1","videoCatName":"Music"},{"videoCatID":"9","videoCatName":"Rap"},{"videoCatID":"34","videoCatName":"With a Beat"}]},{"challengeID":"6","challengeStartDate":"2018-08-27","challengeEndDate":"2018-09-21","ChallengeCatID":"3","CatID":"3","challengeCreatorID":"3","challengerID":"26","challengeCreatorVideoID":"8","challengerVideoID":"12","challengeLength":"14","rules":"Ban","challengeWinnerID":"0","orientation":"Vertical","sendChallengeTo":"","ChallengeType":"0","status":"1","created_at":"2018-08-27 13:39:04","creatorusername":"Ankit","creatorvideoEmbedID":"T-6GNJQoMaw","creatorvideoName":"nans","creatorvideoDescription":"sndnd","creatorvideoUpVotes":"1","creatorvideoDownVotes":"0","creatorvideoScore":"1","challengerusername":"amit","videoEmbedID":"tQzKk7tZ5-I","videoName":"bghf","videoDescription":"gjgfjj","videoUpVotes":"1","videoDownVotes":"0","videoScore":"1","VideoCategories":[{"videoCatID":"6","videoCatName":"Magic"},{"videoCatID":"1","videoCatName":"Music"},{"videoCatID":"9","videoCatName":"Rap"},{"videoCatID":"34","videoCatName":"With a Beat"},{"videoCatID":"3","videoCatName":"Sports"}]}],"open_challenge_data":[{"challengeID":"33","challengeStartDate":"2018-09-10","challengeEndDate":"2018-10-10","ChallengeCatID":"4","CatID":"4","challengeCreatorID":"10","challengerID":"0","challengeCreatorVideoID":"19","challengerVideoID":"0","challengeLength":"30","rules":"Open rules","challengeWinnerID":"0","orientation":"Vertical","sendChallengeTo":"","ChallengeType":"0","status":"0","created_at":"2018-09-10 12:35:41","creatorusername":"nitin5911","videoEmbedID":"xgg-4fQ2HuM","videoName":"open","videoDescription":"jfjdjdjfh","videoUpVotes":"0","videoDownVotes":"0","videoScore":"0","VideoCategories":[{"videoCatID":"6","videoCatName":"Magic"},{"videoCatID":"1","videoCatName":"Music"},{"videoCatID":"9","videoCatName":"Rap"},{"videoCatID":"34","videoCatName":"With a Beat"},{"videoCatID":"3","videoCatName":"Sports"},{"videoCatID":"4","videoCatName":"Dance"}]}]}
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
        private List<ChallengeDataBean> challenge_data;
        private List<ChallengeDataBean> open_challenge_data;

        public List<ChallengeDataBean> getChallenge_data() {
            return challenge_data;
        }

        public void setChallenge_data(List<ChallengeDataBean> challenge_data) {
            this.challenge_data = challenge_data;
        }

        public List<ChallengeDataBean> getOpen_challenge_data() {
            return open_challenge_data;
        }

        public void setOpen_challenge_data(List<ChallengeDataBean> open_challenge_data) {
            this.open_challenge_data = open_challenge_data;
        }

        public static class ChallengeDataBean {
            /**
             * challengeID : 31
             * challengeStartDate : 2018-09-07
             * challengeEndDate : 2018-10-07
             * ChallengeCatID : 6
             * CatID : 6
             * challengeCreatorID : 26
             * challengerID : 26
             * challengeCreatorVideoID : 16
             * challengerVideoID : 18
             * challengeLength : 30
             * rules :
             * challengeWinnerID : 0
             * orientation : Vertical
             * sendChallengeTo :
             * ChallengeType : 0
             * status : 1
             * created_at : 2018-09-07 17:11:58
             * creatorusername : amit
             * creatorvideoEmbedID : ZFZmZDswtLo
             * creatorvideoName : testing
             * creatorvideoDescription :
             * creatorvideoUpVotes : 0
             * creatorvideoDownVotes : 0
             * creatorvideoScore : 0
             * challengerusername : amit
             * videoEmbedID : hA5UwA2lgHE
             * videoName : test ios video
             * videoDescription : fjfffudjf
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
            private String creatorvideoEmbedID;
            private String creatorvideoName;
            private String creatorvideoDescription;
            private String creatorvideoUpVotes;
            private String creatorvideoDownVotes;
            private String creatorvideoScore;
            private String challengerusername;
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

            public String getCreatorvideoEmbedID() {
                return creatorvideoEmbedID;
            }

            public void setCreatorvideoEmbedID(String creatorvideoEmbedID) {
                this.creatorvideoEmbedID = creatorvideoEmbedID;
            }

            public String getCreatorvideoName() {
                return creatorvideoName;
            }

            public void setCreatorvideoName(String creatorvideoName) {
                this.creatorvideoName = creatorvideoName;
            }

            public String getCreatorvideoDescription() {
                return creatorvideoDescription;
            }

            public void setCreatorvideoDescription(String creatorvideoDescription) {
                this.creatorvideoDescription = creatorvideoDescription;
            }

            public String getCreatorvideoUpVotes() {
                return creatorvideoUpVotes;
            }

            public void setCreatorvideoUpVotes(String creatorvideoUpVotes) {
                this.creatorvideoUpVotes = creatorvideoUpVotes;
            }

            public String getCreatorvideoDownVotes() {
                return creatorvideoDownVotes;
            }

            public void setCreatorvideoDownVotes(String creatorvideoDownVotes) {
                this.creatorvideoDownVotes = creatorvideoDownVotes;
            }

            public String getCreatorvideoScore() {
                return creatorvideoScore;
            }

            public void setCreatorvideoScore(String creatorvideoScore) {
                this.creatorvideoScore = creatorvideoScore;
            }

            public String getChallengerusername() {
                return challengerusername;
            }

            public void setChallengerusername(String challengerusername) {
                this.challengerusername = challengerusername;
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

        public static class OpenChallengeDataBean {
            /**
             * challengeID : 33
             * challengeStartDate : 2018-09-10
             * challengeEndDate : 2018-10-10
             * ChallengeCatID : 4
             * CatID : 4
             * challengeCreatorID : 10
             * challengerID : 0
             * challengeCreatorVideoID : 19
             * challengerVideoID : 0
             * challengeLength : 30
             * rules : Open rules
             * challengeWinnerID : 0
             * orientation : Vertical
             * sendChallengeTo :
             * ChallengeType : 0
             * status : 0
             * created_at : 2018-09-10 12:35:41
             * creatorusername : nitin5911
             * videoEmbedID : xgg-4fQ2HuM
             * videoName : open
             * videoDescription : jfjdjdjfh
             * videoUpVotes : 0
             * videoDownVotes : 0
             * videoScore : 0
             * VideoCategories : [{"videoCatID":"6","videoCatName":"Magic"},{"videoCatID":"1","videoCatName":"Music"},{"videoCatID":"9","videoCatName":"Rap"},{"videoCatID":"34","videoCatName":"With a Beat"},{"videoCatID":"3","videoCatName":"Sports"},{"videoCatID":"4","videoCatName":"Dance"}]
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
            private List<VideoCategoriesBeanX> VideoCategories;

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

            public List<VideoCategoriesBeanX> getVideoCategories() {
                return VideoCategories;
            }

            public void setVideoCategories(List<VideoCategoriesBeanX> VideoCategories) {
                this.VideoCategories = VideoCategories;
            }

            public static class VideoCategoriesBeanX {
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
    }
}
