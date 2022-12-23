package com.example.myfirstapplication.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaExtractor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myfirstapplication.MainCallbacks;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StartCallbacks;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.User;
import com.example.myfirstapplication.db.daos.DaoUser;


public class AccountFragment extends Fragment {

    private AppDatabase db;
    private DaoUser userDao;
    private User user;
    private View mainView;
    MainCallbacks mainCallbacks;

    public AccountFragment() {}

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // reference to database
        SharedPreferences pref = getContext().getSharedPreferences("Authentication",0);
        int username =  pref.getInt("ID_USER",0);
        db = AppDatabase.getInstance(this.getContext());
        userDao = db.daoUser();
        user = userDao.getUserById(username);

        mainView = inflater.inflate(R.layout.fragment_account, container, false);
        /**
         * TextView auxTextView, reference all elements.
         */
        updateScreen();

        ImageButton editButton = mainView.findViewById(R.id.setProfileButton);
        editButton.setOnClickListener(showProfile);

        AppCompatButton logoutButton =  mainView.findViewById(R.id.account_bt_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                mainCallbacks.onMsgFromFragmentToMain("return_start",-1);
            }
        });
        AppCompatButton termsButton = mainView.findViewById(R.id.terms_conditions);
        AppCompatButton devButton = mainView.findViewById(R.id.developers);

        termsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTermConditions();
            }
        });

        devButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDevelopers();
            }
        });

        AppCompatButton shareButton = mainView.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });

        return mainView;
    }

    private View.OnClickListener showProfile = view -> {
        showSetProfile();
    };


    private void showSetProfile () {
        Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_profile);

        ((EditText) dialog.findViewById(R.id.edit_profile_tie_username)).setText(user.getUsername());
        ((EditText) dialog.findViewById(R.id.edit_profile_tie_fullname)).setText(user.getFullName());
        ((EditText) dialog.findViewById(R.id.edit_profile_tie_password)).setText(user.getPassword());
        ((EditText) dialog.findViewById(R.id.edit_profile_tie_email)).setText(user.getEmail());

        ImageButton saveButton = dialog.findViewById(R.id.save_edit_profile);
        ImageButton discardButton = dialog.findViewById(R.id.discard_edit_profile);

        discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText aux = dialog.findViewById(R.id.edit_profile_tie_fullname);
                user.setFullName(aux.getText().toString());

                aux = dialog.findViewById(R.id.edit_profile_tie_email);
                user.setEmail(aux.getText().toString());

                aux = dialog.findViewById(R.id.edit_profile_tie_username);
                user.setUsername(aux.getText().toString());

                aux = dialog.findViewById(R.id.edit_profile_tie_password);
                user.setPassword(aux.getText().toString());

                userDao.updateUser(user);
                updateScreen();
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void updateScreen() {
        TextView auxTextView = mainView.findViewById(R.id.userFullName);
        auxTextView.setText(user.getFullName());

        auxTextView = mainView.findViewById(R.id.userNickName);
        auxTextView.setText(user.getUsername());

        auxTextView = mainView.findViewById(R.id.emailProfileText);
        auxTextView.setText(user.getEmail());

        auxTextView = mainView.findViewById(R.id.userPoints);
        auxTextView.setText(user.getLevel());

        auxTextView = mainView.findViewById(R.id.userDays);
        auxTextView.setText(String.valueOf(user.getScore()));

    }

    private void showDevelopers () {
        Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_shet_developers);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void showTermConditions() {
        Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_terms);
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainCallbacks){
            mainCallbacks = (MainCallbacks) context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement FragmentCallbacks");
        }
    }
}