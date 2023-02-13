package algonquin.cst2335.ni000017;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import algonquin.cst2335.ni000017.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Intent fromPrevious = getIntent();
        String emailAddress = "Welcome back " + fromPrevious.getStringExtra("EmailAddress");
        Toast.makeText(getApplicationContext(), emailAddress, Toast.LENGTH_SHORT).show();

        binding.textView3.setText(emailAddress);

        binding.button.setOnClickListener( btn->{
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse(Uri.parse("tel:") + binding.editTextPhone.getText().toString()));
            startActivity(call);
        });

        binding.button2.setOnClickListener( btn->{

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(cameraIntent);
        });

        File file = new File( getFilesDir(), "Picture.png");
        Toast.makeText(getApplicationContext(), getFilesDir().toString(), Toast.LENGTH_SHORT).show();
        if(file.exists())
        {
            Toast.makeText(getApplicationContext(), "THIS IS Existed", Toast.LENGTH_SHORT).show();
            Bitmap theImage = (Bitmap)BitmapFactory.decodeFile(file.getPath());
            binding.imageView.setImageBitmap(theImage);
        }
    }

    ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK)
            {
                Intent data = result.getData();
                Bitmap thumnail = data.getParcelableExtra("data");
                FileOutputStream fOut = null;
                try
                {
                    fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                    thumnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    });

}