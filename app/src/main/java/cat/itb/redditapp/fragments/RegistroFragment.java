package cat.itb.redditapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import cat.itb.redditapp.MainActivity;
import cat.itb.redditapp.R;

public class RegistroFragment extends Fragment {

    TextView logIn;
    MaterialButton continueButton;
    EditText editEmail;
    EditText editUsername;
    EditText editPassword;
    CheckBox editCheckBox;


    String username = "";
    String email = "";
    String password = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registro, container, false);
        logIn = v.findViewById(R.id.textViewLog);
        continueButton = v.findViewById(R.id.button6);
        editCheckBox = v.findViewById(R.id.checkBox);
        editEmail = v.findViewById(R.id.editTextTextEmail);
        editPassword = v.findViewById(R.id.editTextTextPassword2);
        editUsername = v.findViewById(R.id.editTextTextPersonName2);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new LoginFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUsername.getText().toString();
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()){

                }else {

                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(MainActivity.currentFragment);
                MainActivity.loginShow();
                transaction.commit();
            }
        });
        return v;
    }
}