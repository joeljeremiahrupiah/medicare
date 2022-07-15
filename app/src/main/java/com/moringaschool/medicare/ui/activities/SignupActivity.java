package com.moringaschool.medicare.ui.activities;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.moringaschool.medicare.R;
import com.moringaschool.medicare.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.UserApi;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.actvSignupEmail)
    EditText email;
    @BindView(R.id.etFirstName)
    EditText firstName;
    @BindView(R.id.etLastName)
    EditText lastName;
    @BindView(R.id.etSigninPassword)
    EditText password;
    @BindView(R.id.btnCreateAccount)
    Button signUp;
    @BindView(R.id.btnImageGoogle)
    ImageButton googleSignIn;
    @BindView(R.id.btnImageFacebook)
    ImageButton facebookSignIn;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar;
    @BindView(R.id.bc)
    ImageView bck;

    // Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // bind current view
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // provide a means to authenticate with google

            }
        });
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ints = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(ints);
            }
        });
        facebookSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // provide a means to authenticate with facebook
            }
        });
    }

    // firebase method for account creation
    private void registerUser() {
        String mail= Objects.requireNonNull(email.getText()).toString().trim();
        String firName= Objects.requireNonNull(firstName.getText()).toString().trim();
        String secName= Objects.requireNonNull(lastName.getText()).toString().trim();
        String passwd= password.getText().toString().trim();

        if (firName.isEmpty()){
            firstName.setError("First name is required");
            firstName.requestFocus();
            return;
        }
        if (secName.isEmpty()){
            lastName.setError("Second name is required");
            lastName.requestFocus();
            return;
        }
        if (passwd.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (mail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            email.setError("Please provide a valid email");
            email.requestFocus();
            return;
        }
        if (passwd.length() <6){
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail,passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user= new User(mail,firName,secName);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(SignupActivity.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }else {
                                                Toast.makeText(SignupActivity.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(SignupActivity.this, "Failed to register!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

}
