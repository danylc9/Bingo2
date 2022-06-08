package com.example.bingo.DashBoardInterfaces;

import androidx.annotation.NonNull;

import com.example.bingo.Data.Jugador;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashBoardModel implements DashBoard.Model{
  static public Jugador jugador;
  private DashBoard.TaskListener listener;
  private FirebaseDatabase database;
  private DatabaseReference playerRef;
  private  DatabaseReference cardboardRef;



    public DashBoardModel(DashBoard.TaskListener listener) {
        this.listener = listener;
        database=FirebaseDatabase.getInstance();
        jugador= new Jugador();
        playerRef=database.getReference("players");
        cardboardRef=database.getReference("`players").child("carboard");

    }



  @Override
    public void chargeCardBoard() {
      /* cardboardRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
          DataSnapshot snapsho=snapshot.child("cardboard");

          if (snapsho.exists()){
              String number= (String) snapshot.child("cardboard").getValue();
              int carton=Integer.parseInt(number);
              listener.onSuccesChargeCardBoard(carton);
            }else {
              listener.onError("no hay numero asignado");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            listener.onError(error.getMessage());
        }
      });

       */
    }



    @Override
    public void chargeName() {
      playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
          if (snapshot.exists()){
            String name=(String) snapshot.child("players/").getValue();
            listener.onSuccesChargeName(name);
          }else {
            listener.onError("no hay nombre asignado");
          }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
          listener.onError(error.getMessage());
        }
      });
    }

  @Override
  public void setCardBoard(int num) {
    jugador.setNumCartones(num);
    cardboardRef.child("cardboard").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
      @Override
      public void onComplete(@NonNull Task<Void> task) {
        if (task.isSuccessful())
          listener.onSuccesSave();
        else{
          if (task.getException()!=null)
          listener.onError(task.getException().getMessage());
        }
      }
    });
  }

    @Override
    public void setName(String name) {
      jugador.setName(name);
      playerRef= database.getReference("players/"+name);
     }
}
