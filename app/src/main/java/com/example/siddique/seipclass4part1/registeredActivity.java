package com.example.siddique.seipclass4part1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class registeredActivity extends AppCompatActivity {

    private TextView nameTV, ageTV, genderTV, languagesTV, cityTV, phoneTV, emailTV, dobValueTV;
    private ImageView callIV, smsIV, emailIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        nameTV = findViewById(R.id.nameValueTV);
        ageTV = findViewById(R.id.ageValueTV);
        genderTV = findViewById(R.id.genderValueTV);
        languagesTV = findViewById(R.id.languagesValueTV);
        cityTV = findViewById(R.id.cityValueTV);
        phoneTV = findViewById(R.id.phoneValueTV);
        emailTV = findViewById(R.id.emailValueTV);
        callIV = findViewById(R.id.callIV);
        emailIV = findViewById(R.id.emailIV);
        smsIV = findViewById(R.id.smsIV);
        dobValueTV = findViewById(R.id.dobValueTV);

        final Intent intent = getIntent();
        final Employee employee = (Employee) intent.getSerializableExtra("emp");

        try {
            String name = employee.getName();
            nameTV.setText(name);

            String age = employee.getAge();
            ageTV.setText(age);

            String gender = employee.getGender();
            genderTV.setText(gender);

            String languages = TextUtils.join(", ", employee.getLanguages());
            languagesTV.setText(languages);

            String city = employee.getCity();
            cityTV.setText(city);

            String phone = employee.getPhone();
            phoneTV.setText(phone);

            String email = employee.getEmail();
            emailTV.setText(email);

            String dob = employee.getDob();
            dobValueTV.setText(dob);

        } catch (NullPointerException e) {

            Toast.makeText(this, "Invalid Object", Toast.LENGTH_SHORT).show();

        }

        callIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + employee.getPhone()));
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    if (ActivityCompat.checkSelfPermission(registeredActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(registeredActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                        return;
                    }
                    startActivity(callIntent);
                }else{
                    Toast.makeText(registeredActivity.this, "No related application found for call.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        smsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse("smsto:"+employee.getPhone()));
                smsIntent.putExtra("sms_body", "This is a message.");
                if (smsIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(smsIntent);
                }else {
                    Toast.makeText(registeredActivity.this, "No related application found for sms.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        emailIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{employee.getEmail()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is a test Subject");

                if (emailIntent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(emailIntent);
                }else
                {
                    Toast.makeText(registeredActivity.this, "No related application found for email.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void update(View view) {
        Toast.makeText(this, "This is working", Toast.LENGTH_SHORT).show();
    }
}
