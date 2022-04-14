package com.example.ibcshlapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.Activity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import com.example.ibcshlapplication.databinding.ActivityMainBinding;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private School school;
    private String curpos;
    private String finpos;
    private RadioGroup radioGroup;
    private EditText editText2;
    private Button button3;
    private Button button4;
    private RadioButton radioButton;
    private TextView textView;
    private int w;
    private Button button5;
    private EditText et3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        InputStream fin = null;
        try {
            fin = getAssets().open("rooms2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        school = new School(fin);
        addListenerOnButton2();
        addListenerOnButton();

    }

    public void addListenerOnButton() {
        setContentView(R.layout.fragment_home);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        button3 = (Button) findViewById(R.id.button3);
        et3 = (EditText) findViewById(R.id.editTextTextPersonName3);

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                w = Integer.parseInt(radioButton.getText().toString());
                curpos = et3.getText().toString();
                school.update_weights(curpos, w);

                //go to next page
                addListenerOnButton2();
            }

        });
    }
    public void addListenerOnButton2() {
        setContentView(R.layout.fragment_dashboard);
        button4 = (Button) findViewById(R.id.button4);
        editText2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        textView = (TextView) findViewById(R.id.textView1);
        button5 = (Button) findViewById(R.id.button5);

        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                finpos = editText2.getText().toString();
                String path = school.pathfinder(curpos, finpos);

                textView.setText(path);
            }

        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //return to previous page
                addListenerOnButton();
            }
        });



    }


}
