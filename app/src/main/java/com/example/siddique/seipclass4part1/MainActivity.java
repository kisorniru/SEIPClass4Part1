package com.example.siddique.seipclass4part1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //    Example of a listener
    private RadioGroup radioGroup;

    private EditText nameET, ageET, phoneET, emailET;
    private String gender = "Male";
    private List<String> languages = new ArrayList<>();

    private Spinner citySP;
    private String city;
    private Button dateBTN;
    private Calendar calendar;
    private int year, month, dayOfMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        ageET = findViewById(R.id.ageET);
        phoneET = findViewById(R.id.phoneET);
        emailET = findViewById(R.id.emailET);
        dateBTN = findViewById(R.id.dateBTN);
        calendar = Calendar.getInstance(Locale.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleDateFormatOnCreate = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormatOnCreate.format(new Date());
        dateBTN.setText(date);

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


        citySP = findViewById(R.id.citySP);
        /*
//        Default View
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getCities()
        );
        */
//        Custom View
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.city_spinner, getCities());

        citySP.setAdapter(arrayAdapter);


        citySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = getCities().get(position);
//                city = parent.getItemAtPosition(position).toString();
//                Toast.makeText(MainActivity.this, "city : "+city, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        citySP.setSelection(getCities().indexOf("MES"));

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
        String phone = phoneET.getText().toString();
        String email = emailET.getText().toString();
        String date = dateBTN.getText().toString();
        Employee employee = new Employee(name, age, phone, email, gender, languages, city, date);
        // Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        // Explicit Intent
        Intent intent = new Intent(this, registeredActivity.class);
        intent.putExtra("emp", employee);
        startActivity(intent);
    }

    private List<String> getCities(){
        List<String>cities = new ArrayList<>();
        cities.add("Abdullahpur");
        cities.add("House Building");
        cities.add("Azompur");
        cities.add("Rajlokhi");
        cities.add("Joshimuddin");
        cities.add("Airport");
        cities.add("Kawla");
        cities.add("Khilkhet");
        cities.add("Bissho Road");
        cities.add("Sewra");
        cities.add("MES");
        cities.add("Bonani");
        cities.add("Firmgate");
        cities.add("Kawran Bazar");
        cities.add("Bangla Motor");
        cities.add("Poribug");
        cities.add("Sahbug");
        cities.add("Motsho Vobon");
        cities.add("Kakrail");
        return cities;
    }

    public void selectDate(View view) {
        DatePickerDialog datePickerDialogOnSelect = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Calendar tempCalender = Calendar.getInstance();
                tempCalender.set(year, month, dayOfMonth);
                String date = simpleDateFormat.format(tempCalender.getTime());
                dateBTN.setText(date);
            }
        }, year, month, dayOfMonth);
        datePickerDialogOnSelect.show();
    }
}
