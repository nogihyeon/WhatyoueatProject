package com.pnu.whatyoueat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StartActivity extends AppCompatActivity implements  OnTabItemSelectedListener{

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.tab1:
                        Toast.makeText(getApplicationContext(), "혼밥 추천 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                        return true;

                    case R.id.tab2 :
                        Toast.makeText(getApplicationContext(), "같이 추천 선택됨", Toast.LENGTH_SHORT).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                        startActivity(new Intent(getApplicationContext(), TogetherActivity.class)); //추가해 줄 ProfileActivity
                        return true;

                    case R.id.tab3 :
                        Toast.makeText(getApplicationContext(), "내 정보 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                        return true;
                }
                return false;
            }
        });

    }


public void onTabSelected(int position)
{
    if (position == 0) {
        bottomNavigation.setSelectedItemId(R.id.tab1);}
    else if (position == 1) {
        bottomNavigation.setSelectedItemId(R.id.tab2);}
    else if (position == 2) {
        bottomNavigation.setSelectedItemId(R.id.tab3); }

}
}
