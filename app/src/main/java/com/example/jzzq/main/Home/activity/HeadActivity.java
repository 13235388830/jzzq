package com.example.jzzq.main.Home.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.jzzq.BaseActivity;
import com.example.jzzq.R;
import com.example.jzzq.main.Home.fragment.TabContentFragment;

import java.util.ArrayList;
import java.util.List;

public class HeadActivity extends BaseActivity {

    private Toolbar toolbar;
    private TextView toolText;

    private TabLayout mTabTl;
    private ViewPager mContentVp;

    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_head;
    }


    @Override
    protected void initView() {
        super.initView();
        toolbar = findViewById(R.id.tool_bar);
        toolText = findViewById(R.id.toolbar_text);
        toolText.setText("分享赚钱");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mTabTl = (TabLayout) findViewById(R.id.tl_tab);
        mContentVp = (ViewPager) findViewById(R.id.vp_content);

        initTab();
        initContent();
    }


    private void initTab() {
        mTabTl.setTabMode(TabLayout.MODE_FIXED);
        mTabTl.setTabTextColors(ContextCompat.getColor(this, R.color.gray), ContextCompat.getColor(this, R.color.colorPrimary));
        mTabTl.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));
        ViewCompat.setElevation(mTabTl, 4);
        mTabTl.setupWithViewPager(mContentVp);
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("精选");
        tabIndicators.add("热卖");
       // tabIndicators.add("日用");
        tabIndicators.add("零食");
        tabIndicators.add("服装");
        tabFragments = new ArrayList<>();
        for (String s : tabIndicators) {
            tabFragments.add(TabContentFragment.newInstance(s));
        }
        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }


    class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }

    }


}
