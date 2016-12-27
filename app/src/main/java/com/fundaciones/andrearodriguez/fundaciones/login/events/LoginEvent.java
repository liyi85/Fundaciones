package com.fundaciones.andrearodriguez.fundaciones.login.events;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class LoginEvent {
    public final static int onSignInError = 0;
    public final static int onSignInSuccess = 1;
    public final static int onFailedRecoverSession = 2;

    private int eventType;
    private String errorMessage;
    private String currenUserEmail;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCurrenUserEmail() {
        return currenUserEmail;
    }

    public void setCurrenUserEmail(String currenUserEmail) {
        this.currenUserEmail = currenUserEmail;
    }
}
