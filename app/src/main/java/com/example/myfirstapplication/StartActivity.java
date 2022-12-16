package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myfirstapplication.fragments.FootPrintCalculationFragment;
import com.example.myfirstapplication.fragments.LoginFragment;
import com.example.myfirstapplication.fragments.RegisterFragment;
import com.example.myfirstapplication.fragments.ResultCalculateFootprintFragment;
import com.example.myfirstapplication.fragments.WelcomeFragment;

public class StartActivity extends AppCompatActivity implements StartCallbacks {

    private FootPrintCalculationFragment footPrintCalculationFragment;
    private WelcomeFragment welcomeFragment = new WelcomeFragment();
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();
    private ResultCalculateFootprintFragment resultCalculateFootprintFragment;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        loadFragment(loginFragment);
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.start_framelayout, fragment);
        transaction.commit();
    }

    @Override
    public void onMsgFromFragmentToStart(String sender, String strValue) {
        if (sender.equals("register")){
            try {
                loadFragment(registerFragment);
            } catch (Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }

        if (sender.equals("login")){
            try {
                loadFragment(loginFragment);
            } catch (Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
        if (sender.equals("welcome")){
            try {
                loadFragment(welcomeFragment);
            }
            catch (Exception e){
                Log.e("ERROR", e.getMessage());
            }
        }

        if (sender.equals("nextFootprint")) {
            try {
                ft = getSupportFragmentManager().beginTransaction();
                footPrintCalculationFragment = FootPrintCalculationFragment.newInstance("","");
                ft.replace(R.id.start_framelayout, footPrintCalculationFragment);
                ft.commit();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }

        if (sender.equals("footprintCalculate")) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("TOTAL",Integer.parseInt(strValue));

                ft = getSupportFragmentManager().beginTransaction();
                resultCalculateFootprintFragment = ResultCalculateFootprintFragment.newInstance("","");
                resultCalculateFootprintFragment.setArguments(bundle);

                ft.replace(R.id.start_framelayout, resultCalculateFootprintFragment);
                ft.commit();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
        if (sender.equals("result")) {
            try {
                Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}