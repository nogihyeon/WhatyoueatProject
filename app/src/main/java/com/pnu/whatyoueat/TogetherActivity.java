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
<<<<<<< HEAD
=======
        Button Like_button = (Button) findViewById(R.id.Together_LikeButton);
>>>>>>> 859cb287d8fac9bbd22cc520de9dd8150ee47188

        Next_button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view)
            {
<<<<<<< HEAD
                FoodTable food = new FoodTable();
=======
                String[] menu = new String[37];
                menu[0]="비빔밥";
                menu[1]="국수";
                menu[2]="칼국수";
                menu[3]="찜닭";
                menu[4]="제육볶음";
                menu[5]="김치찌개";
                menu[6]="부대찌개";
                menu[7]="순두부찌개";
                menu[8]="보쌈/족발";
                menu[9]="밀면/냉면";
                menu[10]="분식";
                menu[11]="국밥";
                menu[12]="닭갈비";
                menu[13]="철판구이/볶음밥";
                menu[14]="삼계탕";
                menu[15]="불고기";
                menu[16]="닭볶음탕";
                menu[17]="갈비찜";
                menu[18]="김치찜";
                menu[19]="치킨";
                menu[20]="짜장/짬뽕";
                menu[21]="볶음밥";
                menu[22]="탄탄멘";
                menu[23]="파스타";
                menu[24]="햄버거";
                menu[25]="피자";
                menu[26]="샌드위치/토스트";
                menu[27]="초밥";
                menu[28]="덮밥";
                menu[29]="우동";
                menu[30]="라멘";
                menu[31]="돈가스";
                menu[32]="오므라이스";
                menu[33]="알밥";
                menu[34]="케밥";
                menu[35]="카레";
                menu[36]="태국음식";
>>>>>>> 859cb287d8fac9bbd22cc520de9dd8150ee47188

                Rand randClass = new Rand();
                int i = randClass.randnumber;
                randClass.savenumber = i;
<<<<<<< HEAD
                text.setText(food.menu[i]);
=======
                text.setText(menu[i]);
>>>>>>> 859cb287d8fac9bbd22cc520de9dd8150ee47188

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
