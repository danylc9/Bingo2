package com.example.bingo.SignUp;

public interface SignUpInterface {
    interface View{
        void disableInput();
        void enableInput();
        void showProgress();
        void hideProgress();
        void handleSignUp();
        boolean validateView();
        void onError(String error);
        void onLogin();
    }

    interface Model{
        void onSignUp(String name,String email,String password);
    }

    interface Presenter{
        void onDestroy();
        void doSignUp(String name,String email,String password);
    }

    interface  TaskListener{
        void onSucces();
        void onError(String error);
    }
}
