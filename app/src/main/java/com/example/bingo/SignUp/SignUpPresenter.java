package com.example.bingo.SignUp;

public class SignUpPresenter implements SignUpInterface.Presenter, SignUpInterface.TaskListener{
    private SignUpInterface.View view;
    private SignUpInterface.Model model;

    public SignUpPresenter(SignUpInterface.View view) {
        this.view = view;
        model= new SignUpModel(this);
    }

    @Override
    public void onDestroy() {
        view=null;
    }

    @Override
    public void doSignUp(String name, String email, String password) {
        if (view!=null)
        {
            view.disableInput();
        }
        model.onSignUp(name, email, password);
    }

    @Override
    public void onSucces() {
        if (view!=null) {
            view.enableInput();
            view.hideProgress();
            view.onLogin();
            view.onLogin();
        }
    }

    @Override
    public void onError(String error) {
        if (view!=null){
            view.enableInput();
            view.hideProgress();
            view.onError(error);
        }
    }
}
