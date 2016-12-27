package com.fundaciones.andrearodriguez.fundaciones.signup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.fundaciones.andrearodriguez.fundaciones.FundacionesApp;
import com.fundaciones.andrearodriguez.fundaciones.main.ui.MainActivity;
import com.fundaciones.andrearodriguez.fundaciones.R;
import com.fundaciones.andrearodriguez.fundaciones.signup.AddFundacionPresenter;
import com.fundaciones.andrearodriguez.fundaciones.signup.SignupPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class SignupActivity extends AppCompatActivity implements SignupView, AddFundacionView {

    @Bind(R.id.txtRepresentante)
    EditText txtRepresentante;
    @Bind(R.id.wraperrepresentante)
    TextInputLayout wraperrepresentante;
    @Bind(R.id.txtFundacion)
    EditText txtFundacion;
    @Bind(R.id.wraperfundacion)
    TextInputLayout wraperfundacion;
    @Bind(R.id.txtAddress)
    EditText txtAddress;
    @Bind(R.id.wraperaddress)
    TextInputLayout wraperaddress;
    @Bind(R.id.txtTelefono)
    EditText txtTelefono;
    @Bind(R.id.wraperphone)
    TextInputLayout wraperphone;
    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.wraperemail)
    TextInputLayout wraperemail;
    @Bind(R.id.txtPassword)
    EditText txtPassword;
    @Bind(R.id.wraperpassword)
    TextInputLayout wraperpassword;
    @Bind(R.id.btnSignUp)
    Button btnSignUp;
    @Bind(R.id.layoutButtons)
    LinearLayout layoutButtons;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer2)
    RelativeLayout layoutMainContainer2;

    @Inject
    SignupPresenter signupPresenter;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    AddFundacionPresenter fundacionPresenter;

    FundacionesApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        app = (FundacionesApp) getApplication();
        setupInjection();
        signupPresenter.onCreate();
        fundacionPresenter.onCreate();

        txtEmail.addTextChangedListener(new MyTextWatcher(txtEmail));
        txtPassword.addTextChangedListener(new MyTextWatcher(txtPassword));
        txtRepresentante.addTextChangedListener(new MyTextWatcher(txtRepresentante));
        txtAddress.addTextChangedListener(new MyTextWatcher(txtAddress));
        txtFundacion.addTextChangedListener(new MyTextWatcher(txtFundacion));
        txtTelefono.addTextChangedListener(new MyTextWatcher(txtTelefono));
    }

    private void setupInjection() {
        app.getSignupComponent(this, this).inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        signupPresenter.onResume();
        fundacionPresenter.onResume();

    }

    @Override
    protected void onPause() {
        signupPresenter.onPause();
        fundacionPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        signupPresenter.onDestroy();
        fundacionPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
    
    @OnClick(R.id.btnSignUp)
    @Override
    public void handleSignUp() {

        submitForm();



    }

    private void crearUsuario() {
        signupPresenter.registerNewUser(txtEmail.getText().toString(),
                txtPassword.getText().toString());
    }

    private void guardandoDatos() {
        String nombre = txtFundacion.getText().toString().trim();
        String direccion = txtAddress.getText().toString().trim();
        String telefono = txtTelefono.getText().toString().trim();
        String persona = txtRepresentante.getText().toString().trim();
        String correo = txtEmail.getText().toString().trim();

        fundacionPresenter.uploadFundacion(nombre, direccion, telefono, persona, correo);

    }

    private void submitForm() {
        if (!validateRepresentante()) {
            return;
        }
        if (!validateFundacion()) {
            return;
        }

        if (!validateDireccion()) {
            return;
        }
        if (!validateTelefono()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Snackbar.make(layoutMainContainer2, R.string.signup_registro_completo, Snackbar.LENGTH_SHORT).show();

        guardandoDatos();
        crearUsuario();

    }

    private boolean validateFundacion() {
        if (txtFundacion.getText().toString().trim().isEmpty()) {
            wraperfundacion.setError(getString(R.string.err_msg_name));
            requestFocus(txtFundacion);
            return false;
        } else {
            wraperfundacion.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {

        if (txtEmail.getText().toString().trim().isEmpty() || !isValidEmail(txtEmail.getText().toString().trim())) {
            wraperemail.setError(getString(R.string.err_msg_email));
            requestFocus(txtEmail);
            return false;
        } else {
            wraperemail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTelefono() {
        if (txtTelefono.getText().toString().trim().isEmpty()) {
            wraperphone.setError(getString(R.string.err_msg_phone));
            requestFocus(txtTelefono);
            return false;
        } else {
            wraperphone.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (txtPassword.getText().toString().trim().isEmpty()) {
            wraperpassword.setError(getString(R.string.err_msg_password));
            requestFocus(txtPassword);
            return false;
        } else {
            wraperpassword.setErrorEnabled(false);
        }
        return true;

    }


    private boolean validateDireccion() {
        if (txtAddress.getText().toString().trim().isEmpty()) {
            wraperaddress.setError(getString(R.string.err_msg_direccion));
            requestFocus(txtAddress);
            return false;
        } else {
            wraperaddress.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRepresentante() {
        if (txtRepresentante.getText().toString().trim().isEmpty()) {
            wraperrepresentante.setError(getString(R.string.err_msg_representante));
            requestFocus(txtRepresentante);
            return false;
        } else {
            wraperrepresentante.setErrorEnabled(false);
        }

        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.txtFundacion:
                    validateFundacion();
                    break;
                case R.id.txtEmail:
                    validateEmail();
                    break;
                case R.id.txtPassword:
                    validatePassword();
                    break;
                case R.id.txtAddress:
                    validateDireccion();
                    break;
                case R.id.txtTelefono:
                    validateTelefono();
                    break;
                case R.id.txtRepresentante:
                    validateRepresentante();
                    break;
            }
        }
    }


    @Override
    public void navigationToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer2, R.string.loging_notice_signin, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.loging_error_messagge_signup, error));
        txtPassword.setError(msgError);
    }

    @Override
    public void setUserEmail(String email) {
        if (email != null) {
            sharedPreferences.edit().putString(app.getEmailKey(), email).commit();
            onSharedPReferences(txtEmail.toString(), txtPassword.toString());

        }
    }

    public void onSharedPReferences(String email, String password){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.commit();
        editor.apply();
    }

    private void setInputs(boolean enabled) {
        txtEmail.setEnabled(enabled);
        txtPassword.setEnabled(enabled);
        txtTelefono.setEnabled(enabled);
        txtFundacion.setEnabled(enabled);
        txtAddress.setEnabled(enabled);
        txtRepresentante.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
    }

    @Override
    public void onUploadInit() {
        showSnackbar(R.string.main_notice_upload_init);
    }

    @Override
    public void onUploadComplete() {
        showSnackbar(R.string.main_notice_upload_complete);

    }

    @Override
    public void onUploadError(String error) {
        showSnackbar(error);
    }
    private void showSnackbar(String msg) {
        Snackbar.make(layoutMainContainer2, msg, Snackbar.LENGTH_SHORT).show();
    }


    private void showSnackbar(int strResult) {
        Snackbar.make(layoutMainContainer2, strResult, Snackbar.LENGTH_SHORT).show();
    }
}
