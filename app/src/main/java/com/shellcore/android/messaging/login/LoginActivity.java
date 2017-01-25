package com.shellcore.android.messaging.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shellcore.android.messaging.R;
import com.shellcore.android.messaging.contactList.ContactListActivity;
import com.shellcore.android.messaging.login.ui.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {

    // Servicios
    private LoginPresenter presenter;

    // Componentes
    @Bind(R.id.edtEmail)
    EditText edtEmail;
    @Bind(R.id.tilEmail)
    TextInputLayout tilEmail;
    @Bind(R.id.edtPassword)
    EditText edtPassword;
    @Bind(R.id.tilPassword)
    TextInputLayout tilPassword;
    @Bind(R.id.lnrButtons)
    LinearLayout lnrButtons;
    @Bind(R.id.barLogin)
    ProgressBar barLogin;
    @Bind(R.id.btnSignin)
    Button btnSignin;
    @Bind(R.id.btnSignup)
    Button btnSignup;
    @Bind(R.id.activity_login)
    RelativeLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImpl(this);
        presenter.checkForAuthenticatedUser();
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
        barLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        barLogin.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignin)
    @Override
    public void handleSignin() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (email != null
                && !email.isEmpty()
                && password != null
                && !password.isEmpty()) {
            presenter.validateLogin(email, password);
        }
    }

    @OnClick(R.id.btnSignup)
    @Override
    public void handleSignup() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            presenter.registerNewUser(email, password);
        }
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(new Intent(this, ContactListActivity.class));
    }

    @Override
    public void loginError(String error) {
        edtPassword.setText("");
        String errorMessage = String.format(getString(R.string.login_error_signin), error);
        edtPassword.setError(errorMessage);
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(activityLogin, R.string.login_notice_signup, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void newUserError(String error) {
        edtPassword.setText("");
        String errorMessage = String.format(getString(R.string.login_error_signup), error);
        edtPassword.setError(errorMessage);
    }

    private void setInputs(boolean enabled) {
        edtEmail.setEnabled(enabled);
        edtPassword.setEnabled(enabled);
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
    }
}
