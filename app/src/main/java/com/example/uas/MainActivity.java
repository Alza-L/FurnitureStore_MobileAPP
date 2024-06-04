package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.uas.FragmentBottomNav.CartFragment;
import com.example.uas.FragmentBottomNav.ProfileFragment;
import com.example.uas.FragmentBottomNav.HomeFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout layout_main;

    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_main = findViewById(R.id.layout_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        replace(new HomeFragment());
        bottomNavigation.show(1, true);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.icon_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.icon_cart));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.icon_person));

        meownavigator();
    }

    private void meownavigator() {
        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()) {

                    case 1 :
                        replace(new HomeFragment());
                        break;

                    case 2 :
                        replace(new CartFragment());
                        break;

                    case 3 :
                        replace(new ProfileFragment());
                        break;
                }

                return null;
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}