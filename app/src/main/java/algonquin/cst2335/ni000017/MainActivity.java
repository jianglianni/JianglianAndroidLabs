package algonquin.cst2335.ni000017;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.ni000017.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //Loads buttons /text on screen
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("LoginName", "");
        String password = prefs.getString("Password", "");
        binding.editTextEmail.setText(emailAddress);
        binding.editTextTextPassword.setText(password);
        binding.loginButton.setOnClickListener( btn->{
            // (1)where your are(2)Which activity do you want next
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            //actvaually makes the transationa
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName", binding.editTextEmail.getText().toString());
            editor.putString("Password", binding.editTextTextPassword.getText().toString());
            editor.apply();
            intent.putExtra( "EmailAddress", binding.editTextEmail.getText().toString());
            startActivity(intent);
        });

        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
        Log.d( TAG, "Message");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w( TAG, "The onStart()- the application is now visible on the secreen" );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w( TAG, "The onResume()- the application is now responding to user input" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( TAG, "onPause()- The application no longer responds to user input" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( TAG, "he application is no longer visible" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( TAG, "Any memory used by the application is freed" );

    }

}