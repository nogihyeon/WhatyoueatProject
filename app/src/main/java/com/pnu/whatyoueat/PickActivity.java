package com.pnu.whatyoueat;

<<<<<<< HEAD
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
=======
import android.net.Uri;
import android.os.Bundle;
>>>>>>> 859cb287d8fac9bbd22cc520de9dd8150ee47188
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
<<<<<<< HEAD

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

        int i = getIntent().getExtras().getInt("index");
        text.setText(menu[i]);
>>>>>>> 859cb287d8fac9bbd22cc520de9dd8150ee47188

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
