package app.app1uppro.apibase.modelclass;

public class UserProfileModel {


    /**
     * success : 200
     * message : Profile Details
     * data : {"ID":"10","username":"nitin5911","firstname":"nitin","lastname":"kumar","email":"sfs.nitin18@gmail.com","password":"22901c101863c90ddc4471f1f1ce2f2e","profile_image":"","device_type":"android","device_token":"","latitude":"","longitude":"","authentication_token":"xqtOgR5Lptx","status":"1","created_at":"2018-08-23 18:28:22"}
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
         * ID : 10
         * username : nitin5911
         * firstname : nitin
         * lastname : kumar
         * email : sfs.nitin18@gmail.com
         * password : 22901c101863c90ddc4471f1f1ce2f2e
         * profile_image :
         * device_type : android
         * device_token :
         * latitude :
         * longitude :
         * authentication_token : xqtOgR5Lptx
         * status : 1
         * created_at : 2018-08-23 18:28:22
         */

        private String ID;
        private String username;
        private String firstname;
        private String lastname;
        private String email;
        private String password;
        private String profile_image;
        private String device_type;
        private String device_token;
        private String latitude;
        private String longitude;
        private String authentication_token;
        private String status;
        private String created_at;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getAuthentication_token() {
            return authentication_token;
        }

        public void setAuthentication_token(String authentication_token) {
            this.authentication_token = authentication_token;
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
