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

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.StartCallbacks;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.daos.DaoUser;
import com.example.myfirstapplication.db.User;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment {

    private Button btLogin;
    private Button btRegister;
    private TextInputEditText tieFullname;
    private TextInputEditText tieEmail;
    private TextInputEditText tieUsername;
    private TextInputEditText tiePassword;
    private TextInputEditText tieConfirmPassword;

    private StartCallbacks startCallbacks;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        btLogin = view.findViewById(R.id.register_bt_login);
        btRegister = view.findViewById(R.id.register_bt_register);
        tieFullname = view.findViewById(R.id.register_tie_fullname);
        tieEmail = view.findViewById(R.id.register_tie_email);
        tieUsername = view.findViewById(R.id.register_tie_username);
        tiePassword = view.findViewById(R.id.register_tie_password);
        tieConfirmPassword = view.findViewById(R.id.register_tie_confirm_password);
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoUser userDao = db.daoUser();


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("REGISTER","BACKLOGIN");
                startCallbacks.onMsgFromFragmentToStart("login","foo");
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("REGISTER","REGISTER");
                String fullname = tieFullname.getText().toString();
                String email = tieEmail.getText().toString();
                String username = tieUsername.getText().toString();
                String password = tiePassword.getText().toString();
                String confirmPassword = tieConfirmPassword.getText().toString();

                //VALIDATION

                if(password.equals(confirmPassword)){
                    //ADD DATABASE
                    User user = new User(fullname,email,username,password, "beginner");
                    userDao.insertUser(user);
                    Log.d("REGISTER","USER REGISTRADO");
                    startCallbacks.onMsgFromFragmentToStart("login","foo");

                }
                else{
                    Log.d("REGISTER","DATOS INCORRECTOS");
                }

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
}