package util;

import android.app.Application;

public class UserApi extends Application {
    private String userFirstName;
    private String userId;
    private static UserApi instance;

    public static UserApi getInstance(){
        if (instance == null)
            instance = new UserApi();
            return instance;
    }
    // whenever you want to fetch current user firstName or ID in an activity we type the following statement
    // firstName - UserApi.getInstance().getUserFirstName
    // UserId - UserApi.getInstance().getUserId

    public UserApi(){}

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
