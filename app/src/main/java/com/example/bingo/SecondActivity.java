package com.example.bingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bingo.Data.Jugador;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SecondActivity extends AppCompatActivity {
    ViewModelGeneral viewModel = new ViewModelGeneral();

    private boolean nombreEscrito = false;
    private boolean numeroEscrito = false;

    private int numCarts;

    private EditText nombre;
    private EditText numCartones;
    private Button btn_READY;

    private Jugador jugador;
    private ListJugadores lista;

    private String playerName = "";

    private FirebaseDatabase database;
    private DatabaseReference playerRef;
    private DatabaseReference cardboardRef;


// -------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setView();



        database = FirebaseDatabase.getInstance("https://bingo-proyecto-default-rtdb.europe-west1.firebasedatabase.app/");

        nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    nombre.setText("");
                    nombreEscrito = true;

                    if (nombreEscrito & numeroEscrito)
                        btn_READY.setEnabled(true);
                }
            }
        });



        numCartones.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

                    numeroEscrito = true;



                     if (nombreEscrito & numeroEscrito)
                        btn_READY.setEnabled(true);
                }
            }
        });


    }

    private void setView() {
        btn_READY = findViewById(R.id.btn_Log_In);
        nombre = findViewById(R.id.id_EnterName);
        numCartones = findViewById(R.id.id_cardBoard);


        btn_READY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recojo nombre y numcartones + Parseo de cartones
                playerName = nombre.getText().toString();
                numCarts = Integer.parseInt(numCartones.getText().toString());//guardo el numero de cartones

                if (numCarts>0&&numCarts<11) {



                //guardar jugador en el view model
                Jugador juga = new Jugador(playerName,numCarts);
                viewModel.setJugador(juga);

               startActivity(new Intent(getApplicationContext(), Lobby.class));
                }

                else{
                    mensajeCartonesMax();
                }


            }
        });
    }


    public void mensajeCartonesMax(){
        Toast.makeText(this,"Maximo 10",Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}