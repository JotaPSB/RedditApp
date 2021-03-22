package cat.itb.redditapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.microedition.khronos.egl.EGLDisplay;

import cat.itb.redditapp.MainActivity;
import cat.itb.redditapp.R;

public class LoginFragment extends Fragment {

    TextView signUp;

    EditText editEmail;
    EditText editPassword;

    MaterialButton continueButton;

    private String email= "";
    private String password = "";

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        signUp = v.findViewById(R.id.textViewSignUp);
        editEmail = v.findViewById(R.id.editTextTextPersonName);
        editPassword = v.findViewById(R.id.editTextTextPassword);
        continueButton = v.findViewById(R.id.button3);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegistroFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                MainActivity.currentFragment = fragment;
                transaction.commit();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = editEmail.getText().toString();
                password = editPassword.getText().toString();
                
                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }else {
                    Toast.makeText(getContext(), "Rellena los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.remove(MainActivity.currentFragment);
                    MainActivity.loginShow();
                    transaction.commit();
                }else {
                    Toast.makeText(getContext(), "No se pudo iniciar sesión, comprueba los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}