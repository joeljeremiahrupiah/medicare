package com.moringaschool.medicare.ui.activities;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.moringaschool.medicare.MainActivity;
import com.moringaschool.medicare.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import util.UserApi;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.etSigninEmail)
    EditText UserEmail;
    @BindView(R.id.etLoginPassword)
    EditText UserPassword;
    @BindView(R.id.btnLogin)
    Button login;
    @BindView(R.id.btnSignupNow)
    Button userSignUp;
    @BindView(R.id.btnForgotPassword)
    Button forgotPassword;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentuser;

    // Firebase Connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // bind current view
        ButterKnife.bind(this);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fetch user details from firebase to verify user autheniticity

                loginEmailPasswordUser(UserEmail.getText().toString().trim(),
                        UserPassword.getText().toString().trim());
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

    private void loginEmailPasswordUser(String userEmail, String userPassword) {
        if (!TextUtils.isEmpty(userEmail)
                && !TextUtils.isEmpty(userPassword)) {
            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            assert user != null;
                            String currentUserId = user.getUid();

                            collectionReference.whereEqualTo("userId", currentUserId)
                                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                        @Override
                                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                                            if (e != null) {
                                            }
                                            assert queryDocumentSnapshots != null;
                                            if (!queryDocumentSnapshots.isEmpty()) {
                                                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                                                    UserApi userApi = UserApi.getInstance();
                                                    userApi.setUserFirstName(snapshot.getString("userFirstName"));
                                                    userApi.setUserId(snapshot.getString("userId"));

                                                    // Go to Main activity
                                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                    Log.i(TAG, "Navigating to Main Activity ...");
                                                }
                                            }
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "Email and Password required",
                            Toast.LENGTH_LONG)
                    .show();
        }
    }
}
