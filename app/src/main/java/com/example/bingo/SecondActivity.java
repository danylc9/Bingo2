package com.example.bingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bingo.DashBoardInterfaces.DashBoard;
import com.example.bingo.DashBoardInterfaces.DashBoardPresenter;
import com.example.bingo.Data.Jugador;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SecondActivity extends AppCompatActivity implements DashBoard.VIew {
    private boolean nombreEscrito=false;
    private boolean numeroEscrito=false;
    private String name;
    private String carton;
    private int cardBoard;

    private EditText nombre;
    private EditText numCartones;
    private Button btn_READY;

    private Jugador jugador;
    private   ListJugadores lista;

    private  String playerName="";

    private FirebaseDatabase database;
    private DatabaseReference playerRef;
    private DatabaseReference cardboardRef;
    private DashBoard.Presenter presenter;



// -------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setView();
        presenter=new DashBoardPresenter(this);


//-------------------------------------------------------------------------------

    database=FirebaseDatabase.getInstance("https://bingo-proyecto-default-rtdb.europe-west1.firebasedatabase.app/");

    // check if player exists and get reference
      //  SharedPreferences preferences=getSharedPreferences("PREFS",0);
        //playerName=preferences.getString("playerName","");
        //if(playerName.equals(""))
        //{
          //  playerRef=database.getReference("players/"+playerName);
            //cardboardRef=database.getReference("cardboard"+cardBoard);
            //addEventListener();
              //      playerRef.setValue("");
        //}




// -------------------------------------------------------------------------------


      nombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    nombre.setText("");
                    nombreEscrito =true;

                    if(nombreEscrito &numeroEscrito)
                        btn_READY.setEnabled(true);
                }
            }
        });


        numCartones.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    numCartones.setText("");
                    numeroEscrito=true;

                    if(nombreEscrito &numeroEscrito)
                        btn_READY.setEnabled(true);
                }
            }
        });



    }

    private void setView(){
        btn_READY=findViewById(R.id.btn_Log_In);
        nombre=findViewById(R.id.id_EnterName);
        numCartones=findViewById(R.id.id_cardBoard);


        btn_READY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recojo nombre y numcartones + Parseo de cartones
                playerName=nombre.getText().toString();
                carton=numCartones.getText().toString();
                cardBoard = Integer.parseInt(carton);//guardo el numero de cartones
                presenter.onCharge();

                presenter.onSave(playerName,cardBoard);

                startActivity(new Intent(getApplicationContext(),Lobby.class));

  //-----------------------------------------------------------
                //loggin the player in




/**
                nombre.setText("");
                if(!playerName.equals(""))
                {
                    btn_READY.setText("LOGGING IN");
                    btn_READY.setEnabled(false);
                    playerRef=database.getReference("players/"+playerName);
                    cardboardRef=database.getReference("cardboard/"+cardBoard);
                    addEventListener();
                    playerRef.setValue("");
                    cardboardRef.setValue("");
                }
                */
            }
        });
    }

/* private void addEventListener(){
        //read from database

        playerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //success- continue to the next screen after saving the player name
                if(!playerName.equals(""))
                {
                    SharedPreferences preferences =getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("playerName",playerName);
                    editor.apply();

                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error
                btn_READY.setText("LOG IN");
                btn_READY.setEnabled(true);
                Toast.makeText(SecondActivity.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });



        cardboardRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //success- continue to the next screen after saving the player name
                if(cardBoard>0&&cardBoard<3)
                {
                    SharedPreferences preferences =getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("cardboard",cardBoard);
                    editor.apply();


                    startActivity(new Intent(getApplicationContext(),Lobby.class));
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error
                btn_READY.setText("LOG IN");
                btn_READY.setEnabled(true);
                Toast.makeText(SecondActivity.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }*/




    @Override
    public void setInput(boolean enable) {
        btn_READY.setEnabled(enable);
        numCartones.setEnabled(enable);
        nombre.setEnabled(enable);
    }

    @Override
    public void enableInput() {
        setInput(true);
    }

    @Override
    public void disableInput() {
        setInput(false);
    }

    @Override
    public void fillEditTextNombre(String name) {
        nombre.setText(name);
    }

    @Override
    public void fillEditTextCartones(int num) {
        numCartones.setText(num);
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}