package com.example.bingo.DashBoardInterfaces;

public interface DashBoard {
    interface VIew{
        void setInput(boolean enable);
        void enableInput();
        void disableInput();

        void fillEditTextNombre(String name);
        void fillEditTextCartones(int num);
        void onError(String error);
    }

    interface Presenter{
        void onSave(String name,int num);
        void onCharge();

        void onDestroy();

        void generate(int cardBoard);
    }

    interface Model{
        void chargeCardBoard();
        void setCardBoard(int num);
        void chargeName();
        void setName(String name);
    }

    interface TaskListener{
        void onSuccesSave();
        void onSuccesChargeName(String name);
        void onSuccesChargeCardBoard(int num);
        void onError(String error);
    }
}
