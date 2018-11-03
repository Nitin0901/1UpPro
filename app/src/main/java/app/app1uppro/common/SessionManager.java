package app.app1uppro.common;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by PC-DESTOP on 7/25/2017.
 */

public class SessionManager {
    private SharedPreferences sharedPreferences;

    @Inject
    public SessionManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setValue(String key, String value) {
        sharedPreferences.edit().putString(key,value).apply();
    }

    public void setLoginParams(String username, String user_id) {
        sharedPreferences.edit().putString(GlobalVariable.UserName,username).putString(GlobalVariable.User_id,user_id).apply();
    }

    public void logout()
    {
        sharedPreferences.edit().clear().apply();
    }

    public String getvalue(String key)
    {
        return sharedPreferences.getString(key,"");
    }

}
