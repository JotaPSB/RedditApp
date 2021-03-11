package cat.itb.redditapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import cat.itb.redditapp.R;

public class RegistroFragment extends Fragment {

    EditText editEmail;
    EditText editUsername;
    EditText editPassword;
    CheckBox editCheckBox;
    MaterialButton continueButton;


    String username = "";
    String email = "";
    String password = "";

    public RegistroFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registro, container, false);

        editCheckBox = v.findViewById(R.id.checkBox);
        editEmail = v.findViewById(R.id.editTextTextEmail);
        editPassword = v.findViewById(R.id.editTextTextPassword2);
        editUsername = v.findViewById(R.id.editTextTextPersonName2);
        continueButton = v.findViewById(R.id.button6);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUsername.getText().toString();
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();

                if (!username.isEmpty() && !password.isEmpty() && !email.isEmpty()){

                }else {

                }
            }
        });

        return v;
    }
}