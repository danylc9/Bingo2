package com.example.bingo.SignUp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpModel implements SignUpInterface.Model{
    private SignUpInterface.TaskListener listener;
    private FirebaseAuth auth;

    public SignUpModel(SignUpInterface.TaskListener listener) {
        this.listener = listener;
        auth=FirebaseAuth.getInstance();
    }

    @Override
    public void onSignUp(String name, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    UserProfileChangeRequest profile=new UserProfileChangeRequest.Builder()
                           .setDisplayName(name).build();
                    FirebaseUser user=auth.getCurrentUser();

                    if( user != null)

                    user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    listener.onSucces();
                                }
                                else if (task.getException()!=null){
                                    listener.onError(task.getException().getMessage());
                                }
                        }
                    });
                }else if (task.getException()!=null){
                    listener.onError(task.getException().getMessage());
                }
            }
        });
    }
}
