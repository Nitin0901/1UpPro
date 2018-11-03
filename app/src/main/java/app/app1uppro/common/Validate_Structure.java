package app.app1uppro.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;


/**
 * Created by PC-DESTOP on 7/15/2017.
 */

public class Validate_Structure {

    @NonNull
    public static Boolean validateLogin(EditText email, EditText password) {
        if (email.length() == 0) {
            email.setError("please enter username");
            email.requestFocus();
            return false;
        } else if (password.getText().toString().length() == 0) {
            password.setError("please enter password");
            password.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    @NonNull
    public static Boolean validateCreateChallenge(Context context,ArrayList<String> categoryIds) {
        if (categoryIds.size() == 0) {
            Toasty.error(context, "please choose video category", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }

    }

    @NonNull
    public static Boolean validateSignup(EditText username, EditText firstname, EditText lastname, EditText email, EditText password, EditText confirmpass) {

        if (username.getText().length() == 0) {
            username.setError("please enter User Name");
            username.requestFocus();
            return false;
        } else if (firstname.getText().length() == 0) {
            firstname.setError("please enter first Name");
            firstname.requestFocus();
            return false;
        } else if (firstname.getText().length() > 25) {
            firstname.setError("you can enter characters upto 25 only");
            firstname.requestFocus();
            return false;
        } else if (lastname.length() == 0) {
            lastname.setError("please enter last Name");
            lastname.requestFocus();
            return false;
        } else if (lastname.length() > 25) {
            lastname.setError("you can enter characters upto 25 only");
            lastname.requestFocus();
            return false;
        } else if (email.getText().toString().trim().length() == 0) {
            email.setError("please enter email");
            email.requestFocus();
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
            email.setError("please enter valid email");
            email.requestFocus();
            return false;
        } else if (password.getText().toString().trim().length() == 0) {
            password.setError("please enter password");
            password.requestFocus();
            return false;
        } else if (password.getText().length() < 8 || password.getText().length() > 20) {
            password.setError("Password must be between 8-20 characters");
            password.requestFocus();
            return false;
        } else if (confirmpass.getText().toString().trim().length() == 0) {
            confirmpass.setError("please enter confirm password");
            confirmpass.requestFocus();
            return false;
        } else if (!confirmpass.getText().toString().equals(password.getText().toString())) {
            confirmpass.setError("password mismatch");
            confirmpass.requestFocus();
            return false;
        } else {
            return true;
        }

    }


    public static boolean validatePassword(EditText currentpass, EditText newpass, EditText confmpass) {

        if (currentpass.getText().length() == 0) {
            // oldLayout.setErrorEnabled(true);
            currentpass.setError("please enter current password");
            currentpass.requestFocus();
            return false;
        } else if (newpass.getText().length() == 0) {
            newpass.setError("please enter new password");
            newpass.requestFocus();
            return false;
        } else if (newpass.getText().length() < 8 || newpass.getText().length() > 20) {
            newpass.setError("New password must be between 8-20 characters");
            newpass.requestFocus();
            return false;
        } else if (confmpass.getText().length() == 0) {
            confmpass.setError("please enter confirm password");
            confmpass.requestFocus();
            return false;
        } else if (!confmpass.getText().toString().trim().equals(newpass.getText().toString().trim())) {
            confmpass.setError("password does not match");
            confmpass.requestFocus();
            return false;
        } else {
            return true;
        }
    }


    public static boolean validateMessage(String userid,EditText userTo,
                                          EditText subject, EditText message) {

        if (userid.length() == 0) {
            userTo.requestFocus();
            return false;
        } else if (subject.getText().length() == 0) {
            subject.setError("please enter Subject");
            subject.requestFocus();
            return false;
        }else if (message.getText().length() == 0) {
            message.setError("please enter message");
            message.requestFocus();
            return false;
        }  else {
            return true;
        }
    }


    @NonNull
    public static Boolean validateProfile(EditText username,
                                          EditText firstname, EditText lastname) {

        Pattern pattern = Pattern.compile("[a-zA-Z ]*");
        // Matcher matcher = pattern.matcher(str);
        if (username.getText().length() == 0) {
            username.setError("please enter User Name");
            username.requestFocus();
            return false;
        } else if (firstname.getText().length() == 0) {
            firstname.setError("please enter first Name");
            firstname.requestFocus();
            return false;
        } else if (firstname.getText().length() > 25) {
            firstname.setError("you can enter characters upto 25 only");
            firstname.requestFocus();
            return false;
        } else if (lastname.length() == 0) {
            lastname.setError("please enter last Name");
            lastname.requestFocus();
            return false;
        } else if (lastname.length() > 25) {
            lastname.setError("you can enter characters upto 25 only");
            lastname.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    @NonNull
    public static Boolean validateVideoUpload(Context context, EditText title, String filePath) {
        if (title.getText().length() == 0) {
            Toasty.error(context, "Please enter title", Toast.LENGTH_SHORT).show();
            return false;
        } else if (filePath.isEmpty()) {
            Toasty.error(context, "Please choose file", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }//end validateVideoUpload


    @NonNull
    public static Boolean validateChatScreen(EditText title) {
        if (title.getText().length() == 0) {
            title.setError("Please enter message");
            title.requestFocus();
            return false;
        } else {
            return true;
        }
    }//end validateVideoUpload


    @NonNull
    public static Boolean validateSocialData(Context context, EditText email, EditText firstName, EditText lastName, String gender) {


        Pattern pattern = Pattern.compile("[a-zA-Z ]*");

        if (email.getText().toString().trim().length() == 0) {
            email.setError("please enter email");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
            email.setError("please enter valid email");
            return false;
        } else if (firstName.length() == 0) {
            firstName.setError("please enter valid first Name");
            return false;
        } else if (!pattern.matcher(firstName.getText().toString()).matches()) {
            firstName.setError("enter only alphabetic");
            return false;
        } else if (lastName.length() == 0) {
            lastName.setError("please enter valid last Name");
            return false;
        } else if (!pattern.matcher(lastName.getText().toString()).matches()) {
            lastName.setError("enter only alphabetic");
            return false;
        } else if (gender.length() == 0) {
            Toast.makeText(context, "please select gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

    public static boolean addAddress(TextInputEditText firstName, TextInputLayout fstNameLayout, TextInputEditText mobile, TextInputLayout mobileLayout,
                                     TextInputEditText country, TextInputLayout cntryLayout, TextInputEditText address, TextInputLayout adrsLayout, TextInputEditText city, TextInputLayout cityLayout,
                                     TextInputEditText postal, TextInputLayout postalLayout) {
        Pattern pattern = Pattern.compile("[a-zA-Z ]*");

        if (firstName.length() == 0) {
            fstNameLayout.setErrorEnabled(true);
            fstNameLayout.setError("please enter full Name");
            return false;
        } else if (!pattern.matcher(firstName.getText().toString()).matches()) {
            fstNameLayout.setErrorEnabled(true);
            fstNameLayout.setError("enter only alphabetic");
            return false;
        } else if (mobile.getText().toString().length() == 0) {
            mobileLayout.setErrorEnabled(true);
            mobileLayout.setError("please enter mobile");
            return false;
        } else if (mobile.length() > 10 || mobile.length() < 9) {
            mobileLayout.setErrorEnabled(true);
            mobileLayout.setError("Invalid Mobile No.");
            return false;
        } else if (address.length() == 0) {
            // mobileLayout.setError(null);
            adrsLayout.setErrorEnabled(true);
            adrsLayout.setError("please enter valid address");
            return false;
        } else if (city.length() == 0) {
            adrsLayout.setError(null);
            cityLayout.setErrorEnabled(true);
            cityLayout.setError("please enter valid city");
            return false;
        } else if (country.length() == 0) {
            cityLayout.setError(null);
            cntryLayout.setErrorEnabled(true);
            cntryLayout.setError("please enter country name");
            return false;
        } else if (postal.length() == 0) {
            cntryLayout.setError(null);
            postalLayout.setErrorEnabled(true);
            postalLayout.setError("please enter postal");
            return false;
        } else if (postal.length() > 7) {
            postalLayout.setErrorEnabled(true);
            postalLayout.setError("please enter valid postal");
            return false;
        } else {
   /*         fstNameLayout.setError(null);
            lstNameLayout.setError(null);
            mobileLayout.setError(null);*/
            cntryLayout.setError(null);
            adrsLayout.setError(null);
            cityLayout.setError(null);
            postalLayout.setError(null);
            return true;
        }


    }

    public static Boolean validateMakeAppointmnet(Context context, String date, EditText description, String consultationMode, String startTime, String endTime) {

        if (date.length() == 0) {
            Toasty.error(context, "select date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (date.equals("Select Date")) {
            Toasty.error(context, "select date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (description.length() == 0) {
            description.setError("please enter valid description");
            return false;
        } else if (description.getText().length() <= 10) {
            description.setError("description length is too small");
            return false;
        } else if (consultationMode.length() == 0) {
            Toasty.error(context, "enter consultation mode", Toast.LENGTH_SHORT).show();
            return false;
        } else if (startTime.equals("")) {
            Toasty.error(context, "please select start date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (endTime.equals("")) {
            Toasty.error(context, "please select end date", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }


    public static boolean validateCart(Context context, String custDeliveryId, String paymentMethod) {

        if (custDeliveryId.length() == 0) {
            Toast.makeText(context, "please enter delivery Address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (paymentMethod.length() == 0) {
            Toast.makeText(context, "please enter payment Method", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }


    }

    public static boolean addReminder(Context context, EditText title, String date, String time, EditText desc, String scheduleTime) {

        if (title.length() == 0) {
            title.setError("please enter title");
            return false;
        } else if (date.length() == 0) {
            Toast.makeText(context, "please enter reminder date", Toast.LENGTH_SHORT).show();
            return false;
        } else if (time.length() == 0) {
            Toast.makeText(context, "please enter reminder time", Toast.LENGTH_SHORT).show();
            return false;
        } else if (desc.length() == 0) {
            desc.setError("please enter description");
            return false;
        } else if (scheduleTime.length() == 0) {
            Toast.makeText(context, "please enter scheduleTime", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    public static Boolean validateFamilyData(EditText title, EditText description) {

        Pattern pattern = Pattern.compile("[a-zA-Z ]*");

        if (description.length() == 0) {
            description.setError("please enter valid description");
            return false;
        } else if (description.getText().length() <= 10) {
            description.setError("description length is too small");
            return false;
        } else if (title.length() == 0) {
            title.setError("please enter valid title");
            return false;
        } else if (!pattern.matcher(title.getText().toString()).matches()) {
            title.setError("enter only alphabetic");
            return false;
        } else {
            return true;
        }

    }


    public static boolean validatePatientreview(EditText title, EditText reviewmsg) {

        if (title.length() == 0) {
            title.setError("please enter title");
            return false;
        } else if (reviewmsg.length() == 0) {
            reviewmsg.setError("please enter message");
            return false;
        } else {
            return true;
        }
    }
}
