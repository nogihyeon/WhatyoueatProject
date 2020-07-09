package com.pnu.whatyoueat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

=======
>>>>>>> 859cb287d8fac9bbd22cc520de9dd8150ee47188
public class EnrollActivity extends AppCompatActivity implements View.OnClickListener {

    //define view objects
    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonSignup;
    TextView textviewSingin;
    TextView textviewMessage;
    ProgressDialog progressDialog;
    //define firebase object
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        //initializig firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //이미 로그인 되었다면 이 액티비티를 종료함
            finish();
            //그리고 profile 액티비티를 연다.
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class)); //추가해 줄 ProfileActivity
        }
        //initializing views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textviewSingin= findViewById(R.id.textViewSignin);
        textviewMessage = findViewById(R.id.textviewMessage);
        buttonSignup = findViewById(R.id.buttonSignup);
        progressDialog = new ProgressDialog(this);

        //button click event
        buttonSignup.setOnClickListener(this);
        textviewSingin.setOnClickListener(this);
    }

    //Firebse creating a new user
    private void registerUser(){
        //사용자가 입력하는 email, password를 가져온다.
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //email과 password가 비었는지 아닌지를 체크 한다.
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Email을 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password를 입력해 주세요.", Toast.LENGTH_SHORT).show();
        }

        //email과 password가 제대로 입력되어 있다면 계속 진행된다.
        progressDialog.setMessage("등록중입니다. 기다려 주세요...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
<<<<<<< HEAD

                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                            Map<String, Object> map = new HashMap<String, Object>();
                            Map<String, Object> map1 = new HashMap<String, Object>();
                            map.put(email.split("@")[0].toLowerCase(), email);
                            map1.put("vote state", "0");
                            mDatabase.child("users").updateChildren(map);

                            FoodTable Food = new FoodTable();

                            for(int i=0; i<Food.menu.length; ++i){
                                Map<String, Object> map2 = new HashMap<String, Object>();
                                map1.put(Food.menu[i], "0");
                                mDatabase.child("users").child(email.split("@")[0].toLowerCase()).updateChildren(map2);
                            }
                            mDatabase.child("users").child(email.split("@")[0].toLowerCase()).updateChildren(map1);

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

                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                            UserClass user = new UserClass(menu[0], "0");
                            mDatabase.child("users").setValue(user);
//                            for(int i=0; i<menu.length; ++i){
//                                UserClass user = new UserClass(menu[i], "0");
//                                mDatabase.child("users").child(email).setValue(menu[i]);
//                                mDatabase.child("users").child(email).child(menu[i]).setValue("0");
//                            }
>>>>>>> 859cb287d8fac9bbd22cc520de9dd8150ee47188
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                        }
                        else {
                            //에러발생시
                            textviewMessage.setText(" - 이미 등록된 이메일입니다  \n -암호는 최소 6자리 이상 \n - 서버에러");
                            Toast.makeText(EnrollActivity.this, "등록 에러!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    //button click event
    @Override
    public void onClick(View view) {
        if(view == buttonSignup) {
            //TODO
            registerUser();
        }

        if(view == textviewSingin) {
            //TODO
            startActivity(new Intent(this, LoginActivity.class)); //추가해 줄 로그인 액티비티
        }
    }
}