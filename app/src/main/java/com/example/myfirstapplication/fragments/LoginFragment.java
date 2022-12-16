package com.example.myfirstapplication.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myfirstapplication.FragmentStartCallbacks;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StartCallbacks;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.daos.DaoUser;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment implements FragmentStartCallbacks {

    private Button btLogin;
    private Button btRegister;
    private TextInputEditText tieUsername;
    private TextInputEditText tiePassword;
    private StartCallbacks startCallbacks;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btLogin = view.findViewById(R.id.login_bt_login);
        btRegister = view.findViewById(R.id.login_b_register);
        tieUsername = view.findViewById(R.id.login_tie_username);
        tiePassword = view.findViewById(R.id.login_tie_password);

        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoUser userDao = db.daoUser();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = tieUsername.getText().toString();
                String password = tiePassword.getText().toString();
                if(userDao.getUserByUsername(username,password)!= null){
                    Log.d("LOGIN","NEXTHOME");
                    startCallbacks.onMsgFromFragmentToStart("welcome","foo");
                }
                else{
                    Log.d("LOGIN", "NOT USER REGISTERED");
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("REGISTER","NEXTREGISTER");
                startCallbacks.onMsgFromFragmentToStart("register","foo");
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof StartCallbacks){
            startCallbacks = (StartCallbacks) context;
        }
        else{
            throw new RuntimeException(context.toString()+"must implement FragmentCallbacks");
        }
    }

    @Override
    public void onMsgFromStartToFragment(String value) {

    }
}