package com.example.bingo.Login;

import com.example.bingo.Login.LoginInterface;
import com.example.bingo.MainActivity;

public class LoginPresenter implements LoginInterface.Presenter, LoginInterface.TaskListener{
    private LoginInterface.View view;
    private LoginInterface.Model model;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        model=new LoginModel(this);
    }

    @Override
    public void onDestroy() {
        view=null;
    }

    @Override
    public void toLogin(String email, String password) {
        if (view!=null) {
            view.disableInput();
            view.showProgress();
        }
        model.doLogin(email, password);
    }

    @Override
    public void Onsucces() {
        if (view!=null) {
            view.enableInput();
            view.hideProgress();
            view.onLogin();
        }
    }

    @Override
    public void onError(String error) {
        if (view!=null) {
            view.onError(error);
            view.enableInput();
            view.hideProgress();
        }
    }
}
