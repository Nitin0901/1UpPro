package app.app1uppro.apibase.modelclass;

public class SucessModel {

    /**
     * success : 200
     * message : User Registered successfully.
     */

    private int success;
    private String message;
    private String RatingStatus;

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

    public String getRatingStatus() {
        return RatingStatus;
    }

    public void setRatingStatus(String ratingStatus) {
        RatingStatus = ratingStatus;
    }
}
