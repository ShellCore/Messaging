package com.shellcore.android.messaging.createAccount.ui;

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
import com.shellcore.android.messaging.contactList.ui.ContactListActivity;
import com.shellcore.android.messaging.createAccount.CreateAccountPresenter;
import com.shellcore.android.messaging.createAccount.CreateAccountPresenterImpl;
import com.shellcore.android.messaging.login.ui.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    // Servicios
    private CreateAccountPresenter presenter;

    // Components
    @Bind(R.id.edtEmail)
    EditText edtEmail;
    @Bind(R.id.tilEmail)
    TextInputLayout tilEmail;
    @Bind(R.id.edtPassword)
    EditText edtPassword;
    @Bind(R.id.tilPassword)
    TextInputLayout tilPassword;
    @Bind(R.id.btnCancel)
    Button btnCancel;
    @Bind(R.id.btnAccept)
    Button btnAccept;
    @Bind(R.id.lnrButtons)
    LinearLayout lnrButtons;
    @Bind(R.id.barLogin)
    ProgressBar barLogin;
    @Bind(R.id.activity_create_account)
    RelativeLayout activityCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        presenter = new CreateAccountPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInput() {
        setInputs(true);
    }

    @Override
    public void disableInput() {
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

    @OnClick(R.id.btnAccept)
    @Override
    public void handleSignUp() {
        String email = edtEmail.getText().toString();
        String pass = edtPassword.getText().toString();
        if (email != null
                &&!email.isEmpty()
                && pass != null
                && !pass.isEmpty()) {
            presenter.registerNewUser(email, pass);
        }
    }

    @OnClick(R.id.btnCancel)
    @Override
    public void handleCancel() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
        Snackbar.make(activityCreateAccount, R.string.login_notice_signup, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void newUserError(String error) {
        edtPassword.setText("");
        String errorMessage = String.format(getString(R.string.login_error_signup), error);
        edtPassword.setError(errorMessage);
    }

    private void setInputs(boolean enabled) {
        tilEmail.setEnabled(enabled);
        tilPassword.setEnabled(enabled);
        btnCancel.setEnabled(enabled);
        btnAccept.setEnabled(enabled);
    }
}
