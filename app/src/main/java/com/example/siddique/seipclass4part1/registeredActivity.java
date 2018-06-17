package com.example.siddique.seipclass4part1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class registeredActivity extends AppCompatActivity {

    private TextView nameTV, ageTV, genderTV, languagesTV;
    private String name;
    private String age;
    private String gender;
    private List<String>languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        nameTV = findViewById(R.id.nameValueTV);
        ageTV = findViewById(R.id.ageValueTV);
        genderTV = findViewById(R.id.genderValueTV);
        languagesTV = findViewById(R.id.languagesValueTV);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        Employee employee = (Employee) intent.getSerializableExtra("emp");

        try{
            String name = employee.getName();
            nameTV.setText(name);

            String age = employee.getAge();
            ageTV.setText(age);

            String gender = employee.getGender();
            genderTV.setText(gender);

            languages = employee.getLanguages();

            String languages = TextUtils.join(", ", employee.getLanguages());
            languagesTV.setText(languages);

        }catch (NullPointerException e){

            Toast.makeText(this, "Invalid Object", Toast.LENGTH_SHORT).show();

        }
    }

    public void update(View view) {
        Toast.makeText(this, "This is working", Toast.LENGTH_SHORT).show();
        Employee employee = new Employee(name, age, gender, languages);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("emp", employee);
        startActivity(intent);
    }
}
