package com.tech.dresshub;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class CustomerLogin extends AppCompatActivity {

    private Button reg;
    private EditText editText1;
    private EditText editText2;
    private Button log;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);


        editText1=(EditText)findViewById(R.id.Lemail);
        editText2=(EditText)findViewById(R.id.Lpass);
        log=(Button)findViewById(R.id.login);
        reg=(Button)findViewById(R.id.regi);

        firebaseAuth=FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerLogin.this, CustomerRegister.class);
                startActivity(intent);
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emaill = editText1.getText().toString();
                final String passwordd = editText2.getText().toString();

                if (emaill.isEmpty()) {
                    editText1.setError("Correct email required");
                } if (passwordd.isEmpty()) {
                    editText2.setError("Correct Password required");
                }else{

                    firebaseAuth.signInWithEmailAndPassword(emaill, passwordd)
                            .addOnCompleteListener(CustomerLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CustomerLogin.this, "Successfully sign in", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CustomerLogin.this, CustomerHome.class);
                                        startActivity(intent);
                                    }else {
                                        try{
                                            throw task.getException();
                                        }catch (FirebaseAuthInvalidUserException e){
                                            editText1.setError("User does not exists or is no longer valid. please register again");
                                            editText1.requestFocus();
                                        }catch (FirebaseAuthInvalidCredentialsException e){
                                            editText1.setError("Invalid credentials. kindly, check and re-enter ");
                                            editText1.requestFocus();
                                        }catch (Exception e){
                                            Log.e(TAG, e.getMessage());
                                            Toast.makeText(CustomerLogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                        // Toast.makeText(CustomerLogin.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
