package com.shellcore.android.messaging;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignin)
    public void handleSignin() {

    }

    @OnClick(R.id.btnSignup)
    public void handleSignup() {

    }
}
