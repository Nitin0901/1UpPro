package app.app1uppro.apibase.modelclass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class VideoCategoryModel {


    /**
     * success : 200
     * message : Video Categories
     * data : [{"videoCatID":"1","videoCatName":"Music","videoCatParentID":"0","CatChild":7},{"videoCatID":"2","videoCatName":"Poetry","videoCatParentID":"0","CatChild":3},{"videoCatID":"3","videoCatName":"Sports","videoCatParentID":"0","CatChild":15},{"videoCatID":"4","videoCatName":"Dance","videoCatParentID":"0","CatChild":2},{"videoCatID":"5","videoCatName":"Art","videoCatParentID":"0","CatChild":6},{"videoCatID":"6","videoCatName":"Magic","videoCatParentID":"0","CatChild":0},{"videoCatID":"7","videoCatName":"Comedy","videoCatParentID":"0","CatChild":5}]
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

    public static class DataBean implements Parcelable {
        /**
         * videoCatID : 1
         * videoCatName : Music
         * videoCatParentID : 0
         * CatChild : 7
         */

        private String videoCatID;
        private String videoCatName;
        private String videoCatParentID;
        private int CatChild;

        protected DataBean(Parcel in) {
            videoCatID = in.readString();
            videoCatName = in.readString();
            videoCatParentID = in.readString();
            CatChild = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(videoCatID);
            dest.writeString(videoCatName);
            dest.writeString(videoCatParentID);
            dest.writeInt(CatChild);
        }
    }
}
