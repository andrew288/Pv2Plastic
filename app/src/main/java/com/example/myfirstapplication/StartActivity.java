package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.myfirstapplication.fragments.FootPrintCalculationFragment;
import com.example.myfirstapplication.fragments.LoginFragment;
import com.example.myfirstapplication.fragments.RegisterFragment;
import com.example.myfirstapplication.fragments.ResultCalculateFootprintFragment;
import com.example.myfirstapplication.fragments.WelcomeFragment;

public class StartActivity extends AppCompatActivity implements StartCallbacks {

    private FootPrintCalculationFragment footPrintCalculationFragment = new FootPrintCalculationFragment();
    private WelcomeFragment welcomeFragment = new WelcomeFragment();
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();
    private ResultCalculateFootprintFragment resultCalculateFootprintFragment = new ResultCalculateFootprintFragment();
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Authentication",0);
        String username =  pref.getString("USERNAME",null);
        if(username!= null){
            Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else{
            setContentView(R.layout.activity_start);
            loadFragment(welcomeFragment);
        }
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
                Bundle bundle = new Bundle();
                bundle.putString("LEVEL", strValue);
                registerFragment.setArguments(bundle);
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
                loadFragment(footPrintCalculationFragment);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }

        if (sender.equals("footprintCalculate")) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("TOTAL", strValue);
                resultCalculateFootprintFragment.setArguments(bundle);
                loadFragment(resultCalculateFootprintFragment);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
        if (sender.equals("result")) {
            try {
                loadFragment(loginFragment);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}