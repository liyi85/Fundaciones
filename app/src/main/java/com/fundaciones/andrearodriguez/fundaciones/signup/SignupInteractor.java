package com.fundaciones.andrearodriguez.fundaciones.signup;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public interface SignupInteractor {
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);

}
