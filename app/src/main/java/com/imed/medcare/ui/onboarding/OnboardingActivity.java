package com.imed.medcare.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.imed.medcare.R;
import com.imed.medcare.ui.onboarding.adapter.OnboardingAdapter;
import com.imed.medcare.ui.welcome.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnboardingActivity extends AppCompatActivity {
    @BindView(R.id.btn_next)
    Button nextBtn;
    @BindView(R.id.vp_tutorial)
    ViewPager viewPager;
    ImageView[] indicators;
    @BindView(R.id.dot1)
    ImageView indicator01;
    @BindView(R.id.dot2)
    ImageView indicator02;
    @BindView(R.id.dot3)
    ImageView indicator03;
    private int positionPage = 0;
    private long lastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        ButterKnife.bind(this);

        indicators = new ImageView[]{indicator01, indicator02, indicator03};
        viewPager.setAdapter(new OnboardingAdapter(getSupportFragmentManager()));
        viewPager.setPageTransformer(false, new OnboardingPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                positionPage= position;
                switch (position) {

                    case 0:
                        indicator01.setImageResource(R.drawable.ic_tutorial_actual_dot);
                        indicator02.setImageResource(R.drawable.ic_tutorial_dot);
                        indicator03.setImageResource(R.drawable.ic_tutorial_dot);
                        nextBtn.setText("Siguiente");
                        break;

                    case 1:
                        indicator01.setImageResource(R.drawable.ic_tutorial_dot);
                        indicator02.setImageResource(R.drawable.ic_tutorial_actual_dot);
                        indicator03.setImageResource(R.drawable.ic_tutorial_dot);
                        nextBtn.setText("Siguiente");
                        break;
                    case 2:
                        indicator01.setImageResource(R.drawable.ic_tutorial_dot);
                        indicator02.setImageResource(R.drawable.ic_tutorial_dot);
                        indicator03.setImageResource(R.drawable.ic_tutorial_actual_dot);
                        nextBtn.setText("Finalizar");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {


        if(positionPage == 2){
            if (SystemClock.elapsedRealtime() - lastClickTime < 1000){
                return;
            }
            lastClickTime = SystemClock.elapsedRealtime();

            Intent intent = new Intent(OnboardingActivity.this,WelcomeActivity.class);
            startActivity(intent);
        }else {
            viewPager.setCurrentItem(positionPage+1,true);
        }
    }
}
