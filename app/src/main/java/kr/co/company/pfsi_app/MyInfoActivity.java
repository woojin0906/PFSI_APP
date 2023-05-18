package kr.co.company.pfsi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

// 내정보 등록, 확인 Activity (2023-05-18 인범)
public class MyInfoActivity extends AppCompatActivity {

    private Button btnAddInfo, btnCancel, btnRegister;
    private LinearLayout layoutAddInfo;
    private EditText etIntro, etName, etBirth, etPhone, etAddress, etGardianPhone;
    private TextView tvIntro, tvName, tvBirth, tvContact, tvAddress, tvGuardianContact;

    private SQLiteDatabase db;
    private DatabaseOpenHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        // DatabaseOpenHelper 객체 생성
        dbHelper = new DatabaseOpenHelper(this, "mydatabase.db", null, 1);
        db = dbHelper.getWritableDatabase();

        btnAddInfo = findViewById(R.id.btnAddInfo);
        layoutAddInfo = findViewById(R.id.layoutAddInfo);
        btnCancel = findViewById(R.id.btnCancel);
        btnRegister = findViewById(R.id.btnRegister);

        tvIntro = findViewById(R.id.tvIntro);
        tvName = findViewById(R.id.tvName);
        tvBirth = findViewById(R.id.tvBirth);
        tvContact = findViewById(R.id.tvContact);
        tvAddress = findViewById(R.id.tvAddress);
        tvGuardianContact = findViewById(R.id.tvGuardianContact);

        etIntro = findViewById(R.id.etIntro);
        etName = findViewById(R.id.etName);
        etBirth = findViewById(R.id.etBirth);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        etGardianPhone = findViewById(R.id.etGardianPhone);

        // 내정보 검색 및 세팅
        Cursor cursor = dbHelper.selectInfo(db);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // 각 열의 데이터 추출
                @SuppressLint("Range") String intro = cursor.getString(cursor.getColumnIndex("intro"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String birth = cursor.getString(cursor.getColumnIndex("birth"));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex("phone"));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
                @SuppressLint("Range") String gardianPhone = cursor.getString(cursor.getColumnIndex("gardianPhone"));

                // 추출 데이터 세팅
                tvIntro.setText(intro);
                tvName.setText(name);
                tvBirth.setText(birth);
                tvContact.setText(phone);
                tvAddress.setText(address);
                tvGuardianContact.setText(gardianPhone);

            } while (cursor.moveToNext());
            cursor.close();
        }


        // 등록 창 띄우기
        btnAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutAddInfo.setVisibility(View.VISIBLE);
            }
        });

        // 등록 하기
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String intro = etIntro.getText().toString();
                String name = etName.getText().toString();
                String birth = etBirth.getText().toString();
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                String gardianPhone = etGardianPhone.getText().toString();

                // 이전 값 제거
                try {
                    String Contact = tvContact.getText().toString();
                    dbHelper.deleteInfo(db, Contact);
                } catch (Exception e){
                }

                // 현재 값 추가
                dbHelper.insertInfo(db, intro, name, birth, phone, address, gardianPhone);

                // 변경 값 세팅
                tvIntro.setText(intro);
                tvName.setText(name);
                tvBirth.setText(birth);
                tvContact.setText(phone);
                tvAddress.setText(address);
                tvGuardianContact.setText(gardianPhone);

                layoutAddInfo.setVisibility(View.GONE);
            }
        });

        // 등록 취소
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutAddInfo.setVisibility(View.GONE);
            }
        });


    }
}