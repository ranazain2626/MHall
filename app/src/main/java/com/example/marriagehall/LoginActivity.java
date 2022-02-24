package com.example.marriagehall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    public Button btnlogin;
    public TextView txsignup;
    public EditText email;
    public EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = (Button) findViewById(R.id.login);
        txsignup = (TextView) findViewById(R.id.sgin_up_txt);

        email = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError("Email  is required");
                }else if (TextUtils.isEmpty(password.getText().toString()))
                {
                    password.setError("Password is required");
                }else {
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String uid = task.getResult().getUser().getUid();

                                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                firebaseDatabase.getReference().child("User").child(uid).child("usertype").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int usertype =  snapshot.getValue(Integer.class);
                                        if (usertype==0){
                                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                            startActivity(intent);


                                        }
                                        else {
                                            Intent intent = new Intent(LoginActivity.this,AdminDashboard.class);
                                            startActivity(intent);

                                        }



                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                                Toast.makeText(LoginActivity.this,"logged in sucessfully",Toast.LENGTH_LONG).show();



                            }else {
                                Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_LONG).show();
                            }
                        }


                    });
                }



            }
        });
        txsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, SginUpActivity.class);
                startActivity(intent);
            }
        });



    }
}