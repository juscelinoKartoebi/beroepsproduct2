package sr.unasat.beroepsproduct2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import sr.unasat.beroepsproduct2.Fragment.MainActivityFragment;
import sr.unasat.beroepsproduct2.R;

public class MainActivity extends AppCompatActivity {

    String username;
    String password;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        extras = new Bundle();
        extras = getIntent().getExtras();

        username = extras.getString("username");
        password = extras.getString("password");


        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        loadfragment(mainActivityFragment);


    }

    public void loadfragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_update_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.password_update) {
            Intent intent = new Intent(this, UpdateUserActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("password", password);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}