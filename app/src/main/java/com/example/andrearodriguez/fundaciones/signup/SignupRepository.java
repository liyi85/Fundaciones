package com.example.andrearodriguez.fundaciones.signup;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public interface SignupRepository {
    void SignUp(String email, String password);
    void SignIn(String email, String password);

}
