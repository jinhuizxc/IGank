package cn.edu.ustc.igank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import cn.edu.ustc.igank.R;
import cn.edu.ustc.igank.support.StatusBarUtil;
import cn.edu.ustc.igank.ui.girl.GirlFragment;
import cn.edu.ustc.igank.ui.settings.SettingsActivity;
import cn.edu.ustc.igank.ui.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private ViewPager mViewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(MainActivity.this, ContextCompat.getColor(this, R.color.colorAccent));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Girl");
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        setBottomBar(savedInstanceState);
    }

    private void setBottomBar(Bundle savedInstanceState) {
        //固定显示
        //mBottomBar = BottomBar.attach(this, savedInstanceState);
        //滑动时自动隐藏
        mBottomBar = BottomBar.attachShy((CoordinatorLayout) findViewById(R.id.main_content),
                findViewById(R.id.container), savedInstanceState);

        //mBottomBar.useFixedMode();
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bb_menu_recents) {
                    toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                    StatusBarUtil.setColor(MainActivity.this, ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                } else if (menuItemId == R.id.bb_menu_favorites) {
                    toolbar.setBackgroundColor(0xFF5D4037);
                    StatusBarUtil.setColor(MainActivity.this, 0xFF5D4037);
                } else if (menuItemId == R.id.bb_menu_food) {
                    toolbar.setBackgroundColor(0xFF9800);
                    StatusBarUtil.setColor(MainActivity.this, 0xFF9800);
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(1, 0xFF5D4037);
        mBottomBar.mapColorForTab(2, "#7B1FA2");
        mBottomBar.mapColorForTab(3, "#FF5252");
        mBottomBar.mapColorForTab(4, "#FF9800");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_settings){
            Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
//            if (position == 0) {
//                return new GirlFragment();
//            }else if (position==4){
//                return new GirlFragment();
//            }
//            else {
//                return null;
            return new GirlFragment();
 //           }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}