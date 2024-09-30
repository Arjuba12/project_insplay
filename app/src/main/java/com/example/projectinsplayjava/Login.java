package com.example.projectinsplayjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    private EditText userEdit, passEdit;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initValue();

    }

    private void initValue() {
        userEdit = findViewById(R.id.email_form);
        passEdit = findViewById(R.id.pass_form);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Username dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else if(userEdit.getText().toString().equals("Test") && passEdit.getText().toString().equals("Test123")){
                    Intent intent;
                    intent = new Intent(Login.this, Intro.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "Username dan Password Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}