package com.example.bingo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bingo.Login.LoginInterface;
import com.example.bingo.Login.LoginPresenter;
import com.example.bingo.SignUp.SignUpInterface;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MainActivity extends AppCompatActivity implements LoginInterface.View {
    private EditText email,password;
    private Button btn_log;
    private MaterialAlertDialogBuilder dialog;
    private LoginInterface.Presenter presenter;
    private TextView textViewRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setView();

        textViewRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
                //handleLogin();
            }
        });
    }


    private  void setView(){
     //presenter=new LoginPresenter(this);//-------------------------------------------NO SE CUADNO INICIALIZARLO CREO QUE DESPUES DE LOS BOTONES
        email=findViewById(R.id.id_mail);
        password=findViewById(R.id.id_password);
        btn_log=findViewById(R.id.btn_log);
        textViewRegistrar=findViewById(R.id.textViewRegistrar);

    }
    private void goToLogin(){
        Intent intentOnLogin = new Intent (this, Lobby.class);
        startActivity(intentOnLogin);
    }

  private void goToRegister(){
        Intent intennt = new Intent (this, SignUpActivity.class);
        startActivity(intennt);
    }

  private void setInputs(boolean enable){
        email.setEnabled(enable);
        password.setEnabled(enable);
        btn_log.setEnabled(enable);
  }

    @Override
    public void disableInput() {
        setInputs(false);
    }

    @Override
    public void enableInput() {
        setInputs(true);
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.setOnDismissListener(new AppCompatDialogFragment());
    }

    @Override
    public void handleLogin() {


        if (!isValidEmail()){
            Toast.makeText(this,"No es un email valido",Toast.LENGTH_SHORT).show();
        }
        else if (!isValidPassword()){
            Toast.makeText(this,"No es un password valido",Toast.LENGTH_SHORT).show();

        }
        else{
            presenter.toLogin(email.getText().toString().trim(),password.getText().toString().trim());

        }
    }

    @Override
    public boolean isValidEmail() {
        //if the email has body mail or not
        return Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches();
    }

    @Override
    public boolean isValidPassword() {
        if (TextUtils.isEmpty(password.getText().toString())&& password.getText().toString().length()<4){
            Toast.makeText(this,"No es un password valido",Toast.LENGTH_SHORT).show();
            password.setError("no es una password valida");
            return  false;
        }

        else{
            return true;
        }

    }

    @Override
    public void onLogin() {

        goToLogin();

        Toast.makeText(this,"Login correcto!",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,"error en el login",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void register(View view){
    }

}