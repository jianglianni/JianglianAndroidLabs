package algonquin.cst2335.ni000017.ui;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        model.choosedValue.observe(this, selected -> {
            variableBinding.CheckBox.setChecked(selected);
            variableBinding.radioButton1.setChecked(selected);
            variableBinding.radioButton2.setChecked(!selected);
            variableBinding.Switch1.setChecked(selected);
            Context context = getApplicationContext();
            CharSequence text = "The value is now:" + selected;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        variableBinding.CheckBox.setOnCheckedChangeListener((btn,isChecked) ->{
            model.choosedValue.postValue(variableBinding.CheckBox.isChecked());
        });

        variableBinding.Switch1.setOnCheckedChangeListener((btn,isChecked) ->{
            model.choosedValue.postValue(variableBinding.Switch1.isChecked());
        });

        variableBinding.radioButton1.setOnCheckedChangeListener((btn,isChecked) ->{
            model.choosedValue.postValue(variableBinding.radioButton1.isChecked());
        });

        variableBinding.radioButton2.setOnCheckedChangeListener((btn,isChecked) ->{
            model.choosedValue.postValue(!variableBinding.radioButton2.isChecked());
        });

        variableBinding.myimagebutton.setOnClickListener(click ->{
            Context context = getApplicationContext();
            CharSequence text = "The width =" +variableBinding.myimagebutton.getWidth() + " and height =" + variableBinding.myimagebutton.getHeight();
            int duration = Toast.LENGTH_SHORT;

            Toast t = Toast.makeText(context, text, duration);
            t.show();
        });
    }
}