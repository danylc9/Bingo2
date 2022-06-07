package com.example.bingo.Login;

public interface LoginInterface {
    interface View{
        void disableInput();
        void enableInput();
        void showProgress();
        void hideProgress();
        void handleLogin();
        boolean isValidEmail();
        boolean isValidPassword();
        void onLogin();
        void onError(String error);
    }

    interface Presenter{
        void onDestroy();
        void toLogin(String email,String password);
    }

    interface Model{
        void doLogin(String email,String password);
    }

    interface TaskListener{
        void Onsucces();
        void onError(String error);
    }
}
