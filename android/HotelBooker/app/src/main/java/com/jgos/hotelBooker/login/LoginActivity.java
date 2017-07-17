package com.jgos.hotelBooker.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.jgos.hotelBooker.R;
import com.jgos.hotelBooker.filter.FilterActivity;
import com.jgos.hotelBooker.login.entity.LoginReqParam;
import com.jgos.hotelBooker.login.entity.Result;
import com.jgos.hotelBooker.login.interfaces.LoginPresenterOps;
import com.jgos.hotelBooker.login.interfaces.LoginViewOps;

import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginViewOps {

    //Presenter interface
    LoginPresenterOps mPresenter;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private AutoCompleteTextView mLoginView;
    private EditText mPasswordView;
    private View mProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupMVP();

        // Set up the login form.
        mLoginView = (AutoCompleteTextView) findViewById(R.id.user_login);

        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mProgressView = findViewById(R.id.login_progress);
        addEmailsToAutoComplete();

        mPresenter.onStartup();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private void setupMVP() {
        LoginPresenter presenter = new LoginPresenter(this);
        mPresenter = presenter;
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        Log.d("MyApp_login", "view attemptLogin invoked");
        // Store values at the time of the login attempt.
        String login = mLoginView.getText().toString();
        String password = mPasswordView.getText().toString();

        LoginReqParam loginReqParam = new LoginReqParam(login, password);

        mPresenter.attemptLogin(loginReqParam);
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @Override
    public void showProgress(final boolean show) {

        // The ViewPropertyAnimator APIs are not available, so simply show
        // and hide the relevant UI components.
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    private void addEmailsToAutoComplete() {
        List<String> emailAddressCollection = new ArrayList<>();
        emailAddressCollection.add("john.doe@gmail.com");

        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mLoginView.setAdapter(adapter);
    }

    @Override
    public void showLoginError(Result msg, String message) {

        //todo improve
        switch (msg) {
            case LoginInvalid:
                mLoginView.setError(getString(R.string.error_invalid_email) + message);
                break;
            case PasswordInvalid:
                mPasswordView.setError(getString(R.string.error_invalid_password) + message);
                break;
            case AuthFailed:
                mLoginView.setError(getString(R.string.error_authentication_failed) + ": " + message);
                break;
            default:
                mLoginView.setError(getString(R.string.error_login_other) + message);
                Log.e("MyApp_login", "showLoginError, unexpected result code: " + msg);
                break;

        }

    }

    @Override
    public void resetLoginErrors() {
        // Reset errors.
        mLoginView.setError(null);
        mPasswordView.setError(null);
    }

    @Override
    public void showFilterActivity() {
        Intent myIntent = new Intent(this, FilterActivity.class);
        this.startActivity(myIntent);
    }

}


