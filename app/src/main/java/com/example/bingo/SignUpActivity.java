package com.example.bingo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bingo.SignUp.SignUpInterface;
import com.example.bingo.SignUp.SignUpPresenter;

import java.util.Objects;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements SignUpInterface.View {
    private EditText id_ConfirmPassword,id_passwordSignUp,id_mailSignUp,id_User;
    private Button btn_signUp;
    private AlertDialog dialogs;
    private SignUpInterface.Presenter presenter;
    private String contraseña,confirmarContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setView();
    }

    private void setView() {
        presenter=new SignUpPresenter(this);
        id_User=findViewById(R.id.id_User);
        id_ConfirmPassword=findViewById(R.id.id_ConfirmPassword);
        id_passwordSignUp=findViewById(R.id.id_passwordSignUp);
        id_mailSignUp=findViewById(R.id.id_mailSignUp);
        btn_signUp=findViewById(R.id.btn_signUp);

        confirmarContraseña=id_ConfirmPassword.getText().toString();
                contraseña=id_passwordSignUp.getText().toString();

        
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignUp();
            }
        });



    }



    @Override
    public void disableInput() {
        setEnable(false);
    }

    private void setEnable(boolean b) {
        btn_signUp.setEnabled(b);
        id_mailSignUp.setEnabled(b);
        id_passwordSignUp.setEnabled(b);
        id_ConfirmPassword.setEnabled(b);
        id_User.setEnabled(b);
    }

    @Override
    public void enableInput() {
        setEnable(true);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        //dialogs.dismiss();
    }

    @Override
    public void handleSignUp() {
        if (validateView()){
            presenter.doSignUp(id_User.getText().toString(),id_mailSignUp.getText().toString().trim(),
                   id_passwordSignUp.getText().toString() );
        }
    }

    @Override
    public boolean validateView() {
        boolean retVal = true;

        if (TextUtils.isEmpty(id_User.getText())) {
            id_User.setError("este campo es obligatorio");
            retVal = false;
        } else if (id_User.getText().toString().length() < 5) {
            id_User.setError("Debes ecribir al menos 5caracteres");
            retVal = false;
        }

        if (TextUtils.isEmpty(id_mailSignUp.getText().toString())) {
            id_mailSignUp.setError("este campo es obligatorio");
            retVal = false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(id_mailSignUp.getText().toString().trim()).matches()) {
            id_mailSignUp.setError("no es un e-mail valido");
            retVal = false;
        }

        if (TextUtils.isEmpty(id_passwordSignUp.getText().toString())) {
            id_passwordSignUp.setError("este campo es obligatorio");
            retVal = false;
        } else if (id_passwordSignUp.getText().toString().length() < 4) {
            id_passwordSignUp.setError("Debe ser al menos 4 caracteres");
            retVal = false;
        }

        if (TextUtils.isEmpty(id_ConfirmPassword.getText().toString())) {
            id_ConfirmPassword.setError("este campo es obligatorio");
            retVal = false;
        } else if (contraseña!=confirmarContraseña) {
            id_ConfirmPassword.setError("Las contraseñas no coinciden");
            retVal = false;


        }
        return retVal;
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLogin() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}