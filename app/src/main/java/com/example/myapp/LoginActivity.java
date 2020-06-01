package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    final public static String TAG = "Activity";
    EditText edt_email;
    EditText edt_pass;
    EditText edt_confpass;
    Button btn_login;
    Button btn_register;
    String email;
    String pass;
    boolean valid=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        findView();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getText();
                isValid();
                if (valid==true) {
                    Login(email,pass);
                }
                else {

                }


            }

        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

   public void Login(String email,String password ) {
       mAuth.signInWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           Log.d(TAG, "signInWithEmail:success");
                           Toast.makeText(LoginActivity.this, "Đăng nhập thành công",
                                   Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                           startActivity(intent);

                       } else {
                           // If sign in fails, display a message to the user.
                           Log.w(TAG, "signInWithEmail:failure", task.getException());
                           Toast.makeText(LoginActivity.this, "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();

                       }

                       // ...
                   }
               });
   }

    public boolean isValid() {
        edt_email.setError(null);
        edt_pass.setError(null);
        valid=true;
        if (email.equals("")) {
            edt_email.setError("Bạn chưa nhập email");
            valid= false;
        }
        else if (pass.equals("")) {
    edt_pass.setError("Bạn chưa nhập mật khẩu");
    valid=false;
        }
        return valid;
    }

    public void findView(){
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_password);
        edt_confpass = findViewById(R.id.edt_confpass);
        btn_register = findViewById(R.id.btn_register);
        btn_login= findViewById(R.id.btn_login);

    }
    public void getText() {
        email = edt_email.getText().toString();
        pass= edt_pass.getText().toString();
    }
}
