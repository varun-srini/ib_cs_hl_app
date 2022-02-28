package com.example.ibcshlapplication;

import android.os.Bundle;
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
import android.widget.Toast;

import com.example.ibcshlapplication.databinding.ActivityMainBinding;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private School school;
    private String curpos;
    private String finpos;
    private RadioGroup radioGroup;
    private Button button3;
    private RadioButton radioButton;
    private int w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curpos = "E1308";
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
        addListenerOnButton();
        try {
            school = new School();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addListenerOnButton() {
        View inflatedView = getLayoutInflater().inflate(R.layout.fragment_home, null);
        radioGroup = (RadioGroup) inflatedView.findViewById(R.id.radioGroup);
        button3 = (Button) inflatedView.findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                w = Integer.parseInt(radioButton.getText().toString());
                System.out.println(w);
                school.update_weights(curpos, w);
            }

        });

    }
}
