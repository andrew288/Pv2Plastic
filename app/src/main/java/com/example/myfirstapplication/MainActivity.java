package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myfirstapplication.fragments.AccountFragment;
import com.example.myfirstapplication.fragments.AwardsFragment;
import com.example.myfirstapplication.fragments.CategoryFragment;
import com.example.myfirstapplication.fragments.CreateElementFragment;
import com.example.myfirstapplication.fragments.HomeFragment;
import com.example.myfirstapplication.fragments.RecordItemsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements MainCallbacks {

    HomeFragment homeFragment = new HomeFragment();
    CreateElementFragment createElementFragment = new CreateElementFragment();
    RecordItemsFragment recordItemsFragment = new RecordItemsFragment();
    AwardsFragment awardsFragment = new AwardsFragment();
    AccountFragment accountFragment = new AccountFragment();
    CategoryFragment categoryFragment = new CategoryFragment();
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
                    loadFragment(recordItemsFragment);
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

    @Override
    public void onMsgFromFragmentToMain(String sender, int idCategory) {
        if (sender.equals("category")){
            try {
                Bundle bundle = new Bundle();
                bundle.putInt("ID_CATEGORY", idCategory);
                categoryFragment.setArguments(bundle);
                loadFragment(categoryFragment);
            } catch (Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
        if(sender.equals("create-element")){
            try{
                loadFragment(createElementFragment);
            } catch (Exception e){
                Log.e("ERROR", "onStrFromFragToMain " + e.getMessage());
            }
        }
    }
}