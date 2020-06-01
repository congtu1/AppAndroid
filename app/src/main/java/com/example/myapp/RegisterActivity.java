package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.model.user;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    final public static String TAG = "Activity";
    EditText edt_email;
    EditText edt_pass;
    EditText edt_confpass;
    Button btn_register;
    EditText edt_name;
    String email ;
    String pass;
    String name;
    String confpass;
    boolean valid = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        findView();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getText();
                validateForm();
                if (validateForm()==true) {
                    createAccount(email,pass);
                }
            }
        });
    }
    public void createAccount(final String email, final String password) {
        edt_email.setError(null);
        edt_pass.setError(null);

       validateForm();
           mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               Log.d(TAG, "createUserWithEmail:success");
                               Toast.makeText(RegisterActivity.this, "Đăng ký thành công .",
                                       Toast.LENGTH_SHORT).show();
                               FirebaseUser currentUser = mAuth.getCurrentUser();
                               FirebaseAuth.getInstance().signOut();
                               Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);



                           } else {
                               // If sign in fails, display a message to the user.
                               Log.w(TAG, "createUserWithEmail:failure", task.getException());
                               Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();

                           }

                           // ...
                       }
                   });




    }

    private boolean validateForm() {

        edt_email.setError(null);
        edt_pass.setError(null);
        edt_confpass.setError(null);
        valid=true;
        if (email.equals("")) {
            edt_email.setError("Bạn chưa nhập email");
            valid= false;
        }
        else if (pass.equals("")) {
            edt_pass.setError("Bạn chưa nhập mật khẩu");
            valid=false;
        }
        else if (!confpass.equals(pass)){
            edt_confpass.setError("Mật khẩu nhập lại không trùng khớp");
            valid=false;
        }
        return valid;

    }
    public void getText() {
         email = edt_email.getText().toString();
        pass= edt_pass.getText().toString();
        name = edt_name.getText().toString();
        confpass = edt_confpass.getText().toString();
    }
    public void findView(){
        edt_email = findViewById(R.id.edt_email);
        edt_pass = findViewById(R.id.edt_password);
        edt_confpass = findViewById(R.id.edt_confpass);
        btn_register = findViewById(R.id.btn_register);
        edt_name = findViewById(R.id.edt_name);
    }
}
