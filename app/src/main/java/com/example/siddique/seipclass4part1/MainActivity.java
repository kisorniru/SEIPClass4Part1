package com.example.siddique.seipclass4part1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //    Example of a listener
    private RadioGroup radioGroup;

    private EditText nameET, ageET;
    private String gender = "Male";
    private List<String> languages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);

        radioGroup = findViewById(R.id.genderRG);
        // anonymous class implementation
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                gender = rb.getText().toString();
                // Toast.makeText(MainActivity.this, "Is Checked : "+gender, Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
//        Employee employee = (Employee) intent.getSerializableExtra("emp");
        Employee employee = (Employee) intent.getSerializableExtra("emp");
        if (employee != null){
            String name = employee.getName();
            nameET.setText(name);
            Toast.makeText(this, "Name : "+name, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Hello, I'm came back!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "On Resume!", Toast.LENGTH_SHORT).show();
    }

    public void selectLanguage(View view) {
        CheckBox cb = (CheckBox) view;
        boolean isChecked = cb.isChecked();
        String selectedLanguage = cb.getText().toString();
        if (isChecked)
        {
            languages.add(selectedLanguage);
            // Toast.makeText(this, "Is Checked : "+selectedLanguage, Toast.LENGTH_SHORT).show();
        }
        else
        {
            languages.remove(selectedLanguage);
            // Toast.makeText(this, "Is Unchecked : "+selectedLanguage, Toast.LENGTH_SHORT).show();
        }
        for (String s : languages)
        {
            Log.e("TAG : ", "selectLanguages: "+s);
        }
    }

    public void register(View view) {
        String name = nameET.getText().toString();
        String age = ageET.getText().toString();
        Employee employee = new Employee(name, age, gender, languages);
        // Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        // Explicit Intent
        Intent intent = new Intent(this, registeredActivity.class);
        intent.putExtra("msg", "Wellcome to new activity.");
        intent.putExtra("emp", employee);
        startActivity(intent);
    }
}
