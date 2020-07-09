package com.pnu.whatyoueat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class TogetherActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private ListView listView;

    private ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private String name, chat_msg, chat_user;
    private DatabaseReference reference = FirebaseDatabase.getInstance()
            .getReference().child("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_together);

        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.Together_SendButton);
        editText = (EditText) findViewById(R.id.Together_EditText);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String[] s = user.getEmail().split("@");

        name = s[0];

        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                Map<String, Object> map = new HashMap<String, Object>();

                String key = reference.push().getKey();
                reference.updateChildren(map);

                DatabaseReference root = reference.child(key);

                Map<String, Object> objectMap = new HashMap<String, Object>();

                objectMap.put("name", name);
                objectMap.put("text", editText.getText().toString());

                root.updateChildren(objectMap);
                editText.setText("");
            }
        });

        reference.addChildEventListener(new ChildEventListener() {
            @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatConversation(dataSnapshot);
            }

            @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                chatConversation(dataSnapshot);
            }

            @Override public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override public void onCancelled(DatabaseError databaseError) {

            }
        });

        final TextView text = (TextView)findViewById(R.id.Together_TextView);
        Button Next_button = (Button) findViewById(R.id.Together_NextButton);
        Button Pick_button = (Button) findViewById(R.id.Together_PickButton);

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
                            ImageView imageView = (ImageView)findViewById(R.id.Together_randomImage);
                            Glide.with(TogetherActivity.this)
                                    .load(task.getResult())
                                    .into(imageView);
                        } else {
                            // URL을 가져오지 못하면 토스트 메세지
                            Toast.makeText(TogetherActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Pick_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Rand randClass = new Rand();
                Intent intent = new Intent(getApplicationContext(), PickActivity.class);
                intent.putExtra("index", randClass.savenumber);
                startActivity(intent);
            }
        });
    }

    private void chatConversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {
            chat_user = (String) ((DataSnapshot) i.next()).getValue();
            chat_msg = (String) ((DataSnapshot) i.next()).getValue();

            arrayAdapter.add(chat_user + " : " + chat_msg);
        }

        arrayAdapter.notifyDataSetChanged();
    }
}
