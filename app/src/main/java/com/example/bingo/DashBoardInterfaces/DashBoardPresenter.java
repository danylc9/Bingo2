package com.example.bingo.DashBoardInterfaces;

import com.example.bingo.Jugador;
import com.example.bingo.Game;

public class DashBoardPresenter implements DashBoard.Presenter,DashBoard.TaskListener{
    private DashBoard.VIew view;
    private  DashBoard.Model model;
    private Jugador jugador;
    private  Game game;

    public DashBoardPresenter(DashBoard.VIew view){
        this.view=view;
        model=new DashBoardModel(this);
        game=new Game();
    }

    @Override
    public void onSave(String name, int num) {
           if(view!=null){
                view.disableInput();
                jugador=new Jugador(name,num);
                model.setCardBoard(jugador.getNumCartones());
                model.setName(jugador.getName());
            }


    }

    @Override
    public void onCharge() {
        if(view!=null){
           // view.disableInput();
            model.chargeCardBoard();
            model.chargeName();
        }
    }

    @Override
    public void onDestroy() {

        view=null;
    }
//AQUI SE SACA LA GENERACION DE MATRICES
    @Override
    public void generate(int cardBoard) {
        game.generarMatrices(cardBoard);
    }


    @Override
    public void onSuccesSave() {
        if (view!=null){
          //  view.enableInput();
            view.onError("Guardado correctamente. ");
        }

    }

    @Override
    public void onSuccesChargeCardBoard( int num) {
        if (view!=null){
          //  view.enableInput();
            view.fillEditTextCartones(num);

        }
    }

    @Override
    public void onSuccesChargeName( String name) {
        if (view!=null){
            view.enableInput();

            view.fillEditTextNombre(name);
        }
    }


    @Override
    public void onError(String error) {
        if (view!=null){
            view.enableInput();
            view.onError(error);
        }
    }
}
