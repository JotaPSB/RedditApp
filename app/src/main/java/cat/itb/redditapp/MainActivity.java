package cat.itb.redditapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import cat.itb.redditapp.adapter.ViewPagerAdapter;
import cat.itb.redditapp.fragments.CardFragment;
import cat.itb.redditapp.fragments.ChatFragment;
import cat.itb.redditapp.fragments.LlegasteMuyLejosFragment;
import cat.itb.redditapp.fragments.LoginFragment;


public class MainActivity extends AppCompatActivity {

    private static TabLayout tabLayout;
    private static ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private DrawerLayout drawerLayout;
    private static MaterialToolbar topAppBar;
    private static AppBarLayout appBarLayout;
    private static BottomNavigationView bottomNavigationView;
    public static Fragment currentFragment;
    NavigationView navigationView;
    private ChatFragment chatFragment = new ChatFragment();
    private LlegasteMuyLejosFragment lejosFragment = new LlegasteMuyLejosFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);


        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        navigationView = findViewById(R.id.navigation_view_bottom);
        BottomSheetBehavior<NavigationView> bottomSheetBehavior = BottomSheetBehavior.from(navigationView);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        topAppBar = findViewById(R.id.top_app_bar);
        drawerLayout =findViewById(R.id.drawer_layout);
        appBarLayout = findViewById(R.id.app_bar);
        adapter.AddFragment(new CardFragment(),"Home");
        adapter.AddFragment(new CardFragment(),"Popular");
        topAppBar.setNavigationOnClickListener(new MaterialToolbar.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_1:
                        visibilidadOn();
                        return true;

                    case R.id.page_3:
                        visibilidadOn();
                        return true;

                    case R.id.page_2:
                        visibilidadOff();
                        changeFragment(lejosFragment);
                        return true;

                    case R.id.page_4:
                        visibilidadOff();
                        ChatFragment fragment = new ChatFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("foto", R.drawable.reddit_chat);
                        fragment.setArguments(bundle);
                        changeFragment(fragment);

                    case R.id.page_5:
                        visibilidadOff();
                        fragment = new ChatFragment();
                        bundle = new Bundle();
                        bundle.putInt("foto", R.drawable.inbox);
                        fragment.setArguments(bundle);
                        changeFragment(fragment);

                    default:
                        return true;
                }
            }
        });
        if(savedInstanceState == null) {
            loginHide();
            currentFragment = new LoginFragment();
            changeFragment(currentFragment);
        }
    }

    public static void visibilidadOn(){
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);
    }


    private void visibilidadOff(){
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
    }
    public void loginHide(){
        visibilidadOff();
        topAppBar.setVisibility(View.INVISIBLE);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        appBarLayout.setVisibility(View.INVISIBLE);

    }

    public static void loginShow(){
        visibilidadOn();
        topAppBar.setVisibility(View.VISIBLE);
        bottomNavigationView.setVisibility(View.VISIBLE);
        appBarLayout.setVisibility(View.VISIBLE);
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}