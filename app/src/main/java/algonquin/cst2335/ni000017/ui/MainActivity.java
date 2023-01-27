package algonquin.cst2335.ni000017.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.ni000017.R;
import algonquin.cst2335.ni000017.data.MainViewModel;
import algonquin.cst2335.ni000017.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            variableBinding.textview.setText("Your edit text has: " + model.editString);
        });

        model.editString.observe(this, s-> {
            variableBinding.textview.setText("Your edit text has " + s);
        });
    }
}