package kr.co.company.pfsi_app;
// 메인 액티비티 클래스 (2023-05-10 우진)
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button ChatVoiceBtn, MyInfoBtn, UrgentContactInfoBtn, SupportProgramInfoBtn, WelfareMapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatVoiceBtn = findViewById(R.id.ChatVoiceBtn);
        MyInfoBtn = findViewById(R.id.MyInfoBtn);
        UrgentContactInfoBtn = findViewById(R.id.UrgentContactInfoBtn);
        SupportProgramInfoBtn = findViewById(R.id.SupportProgramInfoBtn);
        WelfareMapBtn = findViewById(R.id.WelfareMapBtn);

        ChatVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ChatVoiceActivity.class);
                startActivity(intent);
            }
        });

        MyInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MyInfoActivity.class);
                startActivity(intent);
            }
        });

        UrgentContactInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, UrgentContactInfoActivity.class);
                startActivity(intent);
            }
        });

        SupportProgramInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, SupportProgramInfoActivity.class);
                startActivity(intent);
            }
        });

        WelfareMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, WelfareMapActivity.class);
                startActivity(intent);
            }
        });

    }
}