package com.example.help;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    TextView loginhere;
    EditText fullname,email,password,number,city;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        databaseReference= FirebaseDatabase.getInstance().getReference("loginDatabase");
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        number=findViewById(R.id.number);
        register=findViewById(R.id.login);
        loginhere=findViewById(R.id.loginhere);
        city=findViewById(R.id.city);

        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                String name=fullname.getText().toString().trim();
                String no=number.getText().toString().trim();
                String c=city.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    fullname.setError("Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(Email)){
                    email.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(Password)){
                    password.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(no)){
                    number.setError("Number is Required");
                    return;
                }
                if (TextUtils.isEmpty(c)){
                    city.setError("City is Required");
                    return;
                }


                if (Password.length() < 6){
                    password.setError("password must be >=6 characters");
                    return;
                } else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Email address",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                //database
                String id =databaseReference.push().getKey();
                loginDatabase logindatabase= new loginDatabase(id, name,no,c);
                databaseReference.child(id).setValue(logindatabase);

                //register tha users in firebase

                fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(register.this,"User Created",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else {
                        Toast.makeText(register.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                    }
                });
            }
        });

        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
    }
}