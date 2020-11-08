package hu.obuda.university.neudiab.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import hu.obuda.university.neudiab.R;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        final Context context = this;
        ProgressBar progressBar = new ProgressBar(this);
        final EditText email = (EditText) findViewById(R.id.sig_in_activity_email);
        final EditText name = (EditText) findViewById(R.id.sing_in_activity_name);
        final EditText password= (EditText) findViewById(R.id.sign_in_activity_password);
        final EditText passwodconfirme = (EditText) findViewById(R.id.sign_in_activity_confirme_password);
        Button signupbutton = (Button) findViewById(R.id.sign_in_activity_button);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!password.getText().toString().equalsIgnoreCase(passwodconfirme.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Password are not match",Toast.LENGTH_LONG).show();
                    //showToastMessage("Password");
                }
                else{
                    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                               // Toast.makeText(, "Sing up is succesfull", Toast.LENGTH_LONG);
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                Map<String, Object> user = new HashMap<>();
                                Map<String,Object> csomagolo = new HashMap<>();

                                user.put("name", name.getText().toString());
                                user.put("email", email.getText().toString());
                              //  user.put(mAuth.getUid(),csomagolo);
                                System.out.println("regiszrtracio vegee");
                                db.collection("users").document(mAuth.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(), "Sing up is succesfull", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"Sing up is unsuccesfull",Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                             /*   db.collection("users").document(mAuth.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        Toast.makeText(context, "Sing up is succesfull", Toast.LENGTH_LONG);
                                        System.out.println("adabazisbejegyzesvege");
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context,"Sing up is unsuccesfull",Toast.LENGTH_LONG);
                                    }
                                });*/
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Sing up is unsuccesfull",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

    }


}
