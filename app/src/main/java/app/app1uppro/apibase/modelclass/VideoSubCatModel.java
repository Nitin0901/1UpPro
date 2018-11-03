package app.app1uppro.apibase.modelclass;

import java.util.List;

public class VideoSubCatModel {

    /**
     * success : 200
     * message : Video Sub Categories
     * data : [{"videoCatID":"8","videoCatName":"Instrumental Beat","videoCatParentID":"1","CatChild":0},{"videoCatID":"9","videoCatName":"Rap","videoCatParentID":"1","CatChild":2},{"videoCatID":"10","videoCatName":"Beat Box","videoCatParentID":"1","CatChild":3},{"videoCatID":"11","videoCatName":"Singing with a Beat","videoCatParentID":"1","CatChild":3},{"videoCatID":"12","videoCatName":"Singing Acapella","videoCatParentID":"1","CatChild":3},{"videoCatID":"13","videoCatName":"Band","videoCatParentID":"1","CatChild":2},{"videoCatID":"66","videoCatName":"Lip Sync","videoCatParentID":"1","CatChild":0}]
     */

    private int success;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * videoCatID : 8
         * videoCatName : Instrumental Beat
         * videoCatParentID : 1
         * CatChild : 0
         */

        private String videoCatID;
        private String videoCatName;
        private String videoCatParentID;
        private int CatChild;

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

        public String getVideoCatParentID() {
            return videoCatParentID;
        }

        public void setVideoCatParentID(String videoCatParentID) {
            this.videoCatParentID = videoCatParentID;
        }

        public int getCatChild() {
            return CatChild;
        }

        public void setCatChild(int CatChild) {
            this.CatChild = CatChild;
        }
    }
}
