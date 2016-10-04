package com.example.andrearodriguez.fundaciones.signup.events;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class SignupEvent {

    private int type;
    private String error;

    public final static int UPLOAD_INIT=5;
    public final static int UPLOAD_COMPLETE=6;
    public final static int UPLOAD_ERROR=7;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public final static int onSignInError = 0;
    public final static int onSignUpError = 1;
    public final static int onSignInSuccess = 2;
    public final static int onSignUpSuccess = 3;
    public final static int onFailedRecoverSession = 4;

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
