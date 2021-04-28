package sr.unasat.beroepsproduct2.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sr.unasat.beroepsproduct2.Adapters.GebruikerModel;
import sr.unasat.beroepsproduct2.Database.DatabaseHelper;
import sr.unasat.beroepsproduct2.R;

public class UpdateUserActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextConfirmPassword;
    Button mButtonUpdate;
    DatabaseHelper db;
    Bundle bundle;
    GebruikerModel gebruikerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password_login);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

         db = new DatabaseHelper(this);
         gebruikerModel = new GebruikerModel();
         gebruikerModel = db.vindGebruiker(bundle.getString("username")
        );

        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextConfirmPassword = (EditText)findViewById(R.id.confirm_password);
        mButtonUpdate = (Button)findViewById(R.id.button_login);


        if (gebruikerModel != null){
            mTextUsername.setText(gebruikerModel.getUsername());
            mTextPassword.setText(gebruikerModel.getPassword());
            mTextConfirmPassword.setText(gebruikerModel.getPassword());
        }


        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  passwrd = mTextPassword.getText().toString();
                String passwordcnfirm = mTextConfirmPassword.getText().toString();


                if (mTextUsername.getText().toString().trim() == null || mTextUsername.getText().toString().trim() == "" ){
                    Toast.makeText(UpdateUserActivity.this,"Please enter a username",Toast.LENGTH_SHORT).show();
                    return;
                } else if(mTextPassword.getText().toString().trim() == null || mTextPassword.getText().toString().trim() == "") {
                    Toast.makeText(UpdateUserActivity.this,"Please enter a password",Toast.LENGTH_SHORT).show();
                    return;
                } else if(!passwrd.contentEquals(

                        passwordcnfirm
                )) {

                    Toast.makeText(UpdateUserActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                    return;
                } else{


                    try {
                        db.updateUser(mTextUsername.getText().toString().trim(), mTextPassword.getText().toString().trim(), gebruikerModel.getId());
                        Toast.makeText(UpdateUserActivity.this,"Update was successful",Toast.LENGTH_SHORT).show();
                        Intent HomePage = new Intent(getApplicationContext(), MainActivity.class);
                        HomePage.putExtra("username", mTextUsername.getText().toString());
                        HomePage.putExtra("password",mTextPassword.getText().toString());
                        startActivity(HomePage);
                    } catch (Exception e){
                        Toast.makeText(UpdateUserActivity.this,"An error has occurred",Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }
}