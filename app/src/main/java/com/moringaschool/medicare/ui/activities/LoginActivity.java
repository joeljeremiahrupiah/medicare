package com.moringaschool.medicare.ui.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.moringaschool.medicare.MainActivity;
import com.moringaschool.medicare.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.UserApi;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.etSigninEmail)
    EditText loginEmail;
    @BindView(R.id.etLoginPassword)
    EditText loginPassword;
    @BindView(R.id.btnLogin)
    Button login;
    @BindView(R.id.btnSignupNow)
    Button userSignUp;
    @BindView(R.id.btnForgotPassword)
    Button forgotPassword;

    private FirebaseAuth mAuth;
    // Firebase Connection

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // bind current view
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fetch user details from firebase to verify user authenticity
                userLogin();
            }
        });

        userSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opens Signup activity
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // provide a means for users who have forgotten their password

            }
        });
    }
    private void userLogin() {
        String usrEmail = Objects.requireNonNull(loginEmail.getText()).toString().trim();
        String usrPassword= Objects.requireNonNull(loginPassword.getText()).toString().trim();

        if (usrPassword.isEmpty()){
            loginPassword.setError("Password is required");
            loginPassword.requestFocus();
            return;
        }
        if (usrEmail.isEmpty()){
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(usrEmail).matches()){
            loginEmail.setError("Please provide a valid email");
            loginEmail.requestFocus();
            return;
        }
        if (usrPassword.length() <6){
            loginPassword.setError("Min password length should be 6 characters");
            loginPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(usrEmail,usrPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, DoctorsActivity.class);
                    intent.putExtra("user",usrEmail);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
