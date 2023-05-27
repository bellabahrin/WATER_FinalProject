package com.example.waterappmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signupactivity extends AppCompatActivity {
    TextInputLayout FullName, Username, IdNo, PhoneNo, Email, Address, Password;
    Button SignUp;
    MaterialCardView Signin;
    FirebaseAuth fAuth;
    FirebaseDatabase db;
    DatabaseReference ref;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //hooks

        FullName = findViewById(R.id.signup_fullname);
        Username = findViewById(R.id.signup_username);
        IdNo = findViewById(R.id.signup_idNo);
        PhoneNo = findViewById(R.id.signup_phoneNo);
        Email = findViewById(R.id.signup_email);
        Address = findViewById(R.id.signup_address);
        Password = findViewById(R.id.signup_password);
        SignUp = findViewById(R.id.signup_btn);
        Signin = findViewById(R.id.signuptosignin_btn);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = FirebaseDatabase.getInstance("https://waterappmanagement-2295a-default-rtdb.asia-southeast1.firebasedatabase.app");
                ref = db.getReference("users");

                if (!validateName() || !validateUsername() || !validateIdNo() || !validateEmail() || !validatePhoneNo() || !validateAddress() || !validatePassword()) {
                    return;
                } else {

                    //get values
                    String name = FullName.getEditText().getText().toString();
                    String username = Username.getEditText().getText().toString();
                    String idno = IdNo.getEditText().getText().toString();
                    String email = Email.getEditText().getText().toString();
                    String phoneno = PhoneNo.getEditText().getText().toString();
                    String address = Address.getEditText().getText().toString();
                    String password = Password.getEditText().getText().toString();

                    /*Intent intent = new Intent(getApplicationContext(),verifyno.class);
                    intent.putExtra("phoneNo", phoneno);
                    startActivity(intent);*/

                    UserHelperClass helperClass = new UserHelperClass(name, username, idno, email, phoneno, address, password);

                     ref.child(username).setValue(helperClass);


                    Intent intent = new Intent(signupactivity.this, signinactivity.class);
                   startActivity(intent);
                }
            }
        });


        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signupactivity.this, signinactivity.class);
                startActivity(intent);
            }
        });

    }

    private Boolean validateName() {

        String val_name = FullName.getEditText().getText().toString();

        if (val_name.isEmpty()) {
            FullName.setError("Field cannot be empty");
            return false;
        } else {
            FullName.setError(null);
            FullName.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateUsername() {

        String val_username = Username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val_username.isEmpty()) {
            Username.setError("Field cannot be empty");
            return false;
        } else if (val_username.length() >= 15) {
            Username.setError("Username too  long");
            return false;
        } else if (!val_username.matches(noWhiteSpace)) {
            Username.setError("White spaces are not allowed");
            return false;
        } else {
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateIdNo() {

        String val_idNo = IdNo.getEditText().getText().toString();
        String noDash = "-_";

        if (val_idNo.isEmpty()) {
            IdNo.setError("Field cannot be empty");
            return false;
        } else if (val_idNo.matches(noDash)) {
            IdNo.setError("Dash are not allowed");
            return false;

        } else if (val_idNo.length() >= 13) {
            IdNo.setError("ID No too  long");
            return false;
        } else {
            IdNo.setError(null);
            return true;
        }

    }

    private Boolean validateEmail() {

        String val_email = Email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val_email.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else if (!val_email.matches(emailPattern)) {
            Email.setError("Invalid email address");
            return false;
        } else {
            Email.setError(null);
            Email.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatePhoneNo() {

        String val_name = PhoneNo.getEditText().getText().toString();

        if (val_name.isEmpty()) {
            PhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            PhoneNo.setError(null);
            PhoneNo.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validateAddress() {

        String val_name = Address.getEditText().getText().toString();

        if (val_name.isEmpty()) {
            Address.setError("Field cannot be empty");
            return false;
        } else {
            Address.setError(null);
            return true;
        }

    }

    private Boolean validatePassword() {

        String val_password = Password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" + // at least 1 digit
                // "(?=.*[a-z])" + // at least 1 lower case letter
                // "(?=.*[A-Z])" + // at least 1 upper case letter
                "(?=.*[a-zA-Z])" + // any letter
                "(?=.*[@#$%^&+=])" + // at least 1 special character
                "(?=\\S+$)" +// no white spaces
                ".{4,}" +// at least 4 characters
                "$";

        if (val_password.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else if (!val_password.matches(passwordVal)) {
            Password.setError("Password is too weak");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }

    }


    /*public void registerUser(View view) {

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("users");

        if (!validateName() || !validateUsername() || !validateIdNo() || !validateEmail() || !validatePhoneNo() || !validateAddress() || !validatePassword())
        {
            return;
        } else {


        String name = FullName.getEditText().getText().toString();
        String username = Username.getEditText().getText().toString();
        String idno = IdNo.getEditText().getText().toString();
        String email = Email.getEditText().getText().toString();
        String phoneno = PhoneNo.getEditText().getText().toString();
        String address = Address.getEditText().getText().toString();
        String password = Password.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, username, idno, email,phoneno, address, password);

        ref.child(idno).setValue(helperClass);

        ref.setValue("First data storage");

    }
}*/
}

