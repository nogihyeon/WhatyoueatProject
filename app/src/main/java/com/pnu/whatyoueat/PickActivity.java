package com.pnu.whatyoueat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PickActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        final TextView text = (TextView)findViewById(R.id.textView6);

        Button pick_Button = (Button) findViewById(R.id.button3);
        pick_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PickActivity.this,Map.class);
                startActivity(intent);
            }
        });

        FoodTable food = new FoodTable();

        int i = getIntent().getExtras().getInt("index");
        text.setText(food.menu[i]);

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://whatyoueat-d9070.appspot.com");
        StorageReference storageReference = firebaseStorage.getReference().child(i + ".png");

        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    // Glide 이용하여 이미지뷰에 로딩
                    ImageView imageView = (ImageView)findViewById(R.id.imageView2);
                    Glide.with(PickActivity.this)
                            .load(task.getResult())
                            .into(imageView);
                } else {
                    // URL을 가져오지 못하면 토스트 메세지
                    Toast.makeText(PickActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
