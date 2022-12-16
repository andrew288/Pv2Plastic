package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myfirstapplication.fragments.AccountFragment;
import com.example.myfirstapplication.fragments.AwardsFragment;
import com.example.myfirstapplication.fragments.CreateElementFragment;
import com.example.myfirstapplication.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    CreateElementFragment createElementFragment = new CreateElementFragment();
    AwardsFragment awardsFragment = new AwardsFragment();
    AccountFragment accountFragment = new AccountFragment();
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(homeFragment);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(homeFragment);
                    return true;
                case R.id.secondFragment:
                    loadFragment(createElementFragment);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(awardsFragment);
                    return true;
                case R.id.fourthFragment:
                    loadFragment(accountFragment);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}