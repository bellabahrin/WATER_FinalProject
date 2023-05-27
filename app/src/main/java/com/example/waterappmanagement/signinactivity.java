package com.example.waterappmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class signinactivity extends AppCompatActivity {

    MaterialButton Signin, forgotpass;
    MaterialCardView Signup;
    TextView logoText, msgText;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference ref;
    ProgressBar progressBar;
    TextInputLayout email, password, username;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);


        //hooks

        image = findViewById(R.id.signin_logo);
        logoText = findViewById(R.id.signin_name);
        msgText = findViewById(R.id.signin_msg);
       // email = findViewById(R.id.signin_email);
        username = findViewById(R.id.signin_email);
        password = findViewById(R.id.signin_password);
        Signin = findViewById(R.id.signin_btn);
        Signup = findViewById(R.id.signup_screen);
        forgotpass = findViewById(R.id.forgotpass);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ? ");
                passwordResetDialog.setTitle("Enter Your Email To Received Reset Link. ");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // extract link email and password

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(signinactivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(signinactivity.this, "Error! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });



        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()){
                    return;
                }
                else{
                    isUser();
                }
            }

        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(signinactivity.this, signupactivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(msgText, "logo_msg");
                pairs[3] = new Pair<View, String>(username, "email_tran");
                pairs[4] = new Pair<View, String>(password, "password_tran");
                pairs[5] = new Pair<View, String>(Signin, "button_tran");
                pairs[6] = new Pair<View, String>(Signup, "signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(signinactivity.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });


    }

    private Boolean validateEmail() {

        String val_email = username.getEditText().getText().toString();

        if (val_email.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePassword() {

        String val_password = password.getEditText().getText().toString();


        if (val_password.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

     /*   public void loginUser (View view){

        if (!validateEmail() | !validatePassword()){
             return;
            }
            else{
                isUser();
        }
        }*/

    private void isUser() {

        final String userEnterUsername = username.getEditText().getText().toString().trim();
        final String userEnterPassword = password.getEditText().getText().toString().trim();

        DatabaseReference ref = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users");
        Query checkUser = ref.orderByChild("username").equalTo(userEnterUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnterUsername).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnterPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String namefromDB = snapshot.child(userEnterUsername).child("fullName").getValue(String.class);
                        String usernamefromDB = snapshot.child(userEnterUsername).child("username").getValue(String.class);
                        String idNofromDB = snapshot.child(userEnterUsername).child("idNo").getValue(String.class);
                        String emailfromDB = snapshot.child(userEnterUsername).child("email").getValue(String.class);
                        String phoneNofromDB = snapshot.child(userEnterUsername).child("phoneNo").getValue(String.class);
                        String addressfromDB = snapshot.child(userEnterUsername).child("address").getValue(String.class);
                        String passwordfromDB = snapshot.child(userEnterUsername).child("password").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), homepageactivity.class);



                        intent.putExtra("fullName", namefromDB);
                        intent.putExtra("username", usernamefromDB);
                        intent.putExtra("idNo", idNofromDB);
                        intent.putExtra("email", emailfromDB);
                        intent.putExtra("phoneNo", phoneNofromDB);
                        intent.putExtra("address", addressfromDB);
                        intent.putExtra("password", passwordfromDB);

                        startActivity(intent);
                    }
                    else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else {
                    username.setError("No such user exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

        /*//Email = findViewById(R.id.signin_email);
        //Password = findViewById(R.id.signin_password);
        //Submit = findViewById(R.id.signin_submit);
        //SignUp = findViewById(R.id.user_signup);xe
        //ForgotPass = findViewById(R.id.signin_forgotpass);

        fAuth = FirebaseAuth.getInstance();
        //progressBar = findViewById(R.id.signin_progressbar);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Email.setError("Email is required");
                    return;
                }

                if (password.length()<6){
                    Password.setError("Password must be >= 6 character");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

               fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {

                       if(task.isSuccessful()){
                           Toast.makeText(signinactivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(getApplicationContext(), homepageactivity.class));
                       }else{
                           Toast.makeText(signinactivity.this, "Please create an account first" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           progressBar.setVisibility(View.GONE);
                       }

                   }
               });
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), signupactivity.class));


            }
        });

        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password ? ");
                passwordResetDialog.setTitle("Enter Your Email To Received Reset Link. ");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    // extract link email and password

                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(signinactivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(signinactivity.this, "Error! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close dialog
                    }
                });

                passwordResetDialog.create().show();

            }
        });
    }*/

    }
