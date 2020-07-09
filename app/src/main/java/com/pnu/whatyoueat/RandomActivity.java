package com.pnu.whatyoueat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RandomActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_random);

        final TextView text = (TextView)findViewById(R.id.Solo_TextView);
        Button Next_button = (Button) findViewById(R.id.Solo_NextButton);
        Button Pick_button = (Button) findViewById(R.id.Solo_PickButton);

        Next_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FoodTable food = new FoodTable();

                Rand randClass = new Rand();
                int i = randClass.randnumber;
                randClass.savenumber = i;
                text.setText(food.menu[i]);

                FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://whatyoueat-d9070.appspot.com");
                StorageReference storageReference = firebaseStorage.getReference().child(i + ".png");

                storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            // Glide 이용하여 이미지뷰에 로딩
                            ImageView imageView = (ImageView)findViewById(R.id.Solo_RandomImage);
                            Glide.with(RandomActivity.this)
                                    .load(task.getResult())
                                    .into(imageView);
                        } else {
                            // URL을 가져오지 못하면 토스트 메세지
                            Toast.makeText(RandomActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Pick_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                final FirebaseUser user = mAuth.getCurrentUser();
                final String email = user.getEmail();
                final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                final FoodTable food= new FoodTable();
                //Toast.makeText(RandomActivity.this, email, Toast.LENGTH_SHORT).show();

                mDatabase.child("users").child(email.split("@")[0]).child(food.menu[Rand.savenumber]).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Get Post object and use the values to update the UI
                            int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                            value = value+1;
                            mDatabase.child("users").child(email.split("@")[0]).child(food.menu[Rand.savenumber]).setValue(Integer.toString(value));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
                    }
                });

                Intent intent = new Intent(getApplicationContext(), PickActivity.class);
                intent.putExtra("index", Rand.savenumber);
                startActivity(intent);
            }
        });
    }

}
