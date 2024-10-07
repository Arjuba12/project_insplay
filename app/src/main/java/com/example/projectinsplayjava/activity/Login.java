package com.example.projectinsplayjava.activity;

import static com.example.projectinsplayjava.R.layout.activity_login;

import android.content.Intent;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectinsplayjava.R;

public class Login extends AppCompatActivity {
    private EditText userEdit, passEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(activity_login);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
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
        Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(view -> {
            if (userEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()){
                Toast.makeText(Login.this, "Username dan Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            }else if(userEdit.getText().toString().equals("Test") && passEdit.getText().toString().equals("Test123")){
                Intent intent;
                intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }else{
                Toast.makeText(Login.this, "Username dan Password Salah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}