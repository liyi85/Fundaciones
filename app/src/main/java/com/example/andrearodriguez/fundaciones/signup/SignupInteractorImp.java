package com.example.andrearodriguez.fundaciones.signup;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public class SignupInteractorImp implements SignupInteractor{

    private SignupRepository signupRepository;

    public SignupInteractorImp(SignupRepository signupRepository) {
        this.signupRepository = signupRepository;
    }

    @Override
    public void doSignUp(String email, String password) {
        signupRepository.SignUp(email, password);
    }

    @Override
    public void doSignIn(String email, String password) {
        signupRepository.SignIn(email, password);
    }
}
