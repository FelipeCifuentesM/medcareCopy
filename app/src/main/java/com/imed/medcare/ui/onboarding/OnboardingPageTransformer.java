package com.imed.medcare.ui.onboarding;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.imed.medcare.R;

/**
 * Created by nmillward on 7/12/16.
 */
public class OnboardingPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(View page, float position) {

        // Get page index from tag
        int pagePosition = (int) page.getTag();

        int pageWidth = (int) page.getWidth();
        float pageWidthTimesPosition = pageWidth * position;
        float absPosition = Math.abs(position);

        if (position <= -1.0f || position >= 1.0f) {

            // Page is not visible -- stop any running animations

        } else if (position == 0.0f) {

            // Page is selected -- reset any views if necessary

        } else {

            // Page is currently being swiped -- perform animations here

            // Fragment 1 -- Medic Card
            final View tv_settings_title = page.findViewById(R.id.tv_text);
            //if (tv_settings_title != null) tv_settings_title.setAlpha(1.0f - absPosition * 2);

            final View tv_brightness = page.findViewById(R.id.ic_medic);
            if (tv_brightness != null) tv_brightness.setTranslationX(pageWidthTimesPosition * 0.6f);
            //if (tv_brightness != null) tv_brightness.setAlpha(1.0f - absPosition * 2);

            final View sb_brightness = page.findViewById(R.id.iv_phone);
            if (sb_brightness != null) sb_brightness.setTranslationX(pageWidthTimesPosition * 0.2f);
            //if (sb_brightness != null) sb_brightness.setAlpha(1.0f - absPosition * 2);



            // Fragment 2 -- Treatment Card
            final View tv_profile_title = page.findViewById(R.id.imageView10);
            //if (tv_profile_title != null) tv_profile_title.setAlpha(1.0f - absPosition * 2);

            final ImageView profile = (ImageView) page.findViewById(R.id.imageView11);
            if (profile != null) profile.setTranslationX(pageWidthTimesPosition * 1.2f);
            //if (profile != null) profile.setAlpha(1.0f - absPosition * 2);

            final View card = page.findViewById(R.id.textView24);
            if (card != null) card.setTranslationX(pageWidthTimesPosition * 0.7f);
            //if (card != null) card.setAlpha(1.0f - absPosition * 2);

            // Fragment 3 -- Stats Card
            final View text3 = page.findViewById(R.id.textView25);
            if (text3 != null) text3.setTranslationX(pageWidthTimesPosition * 0.2f);

            final View measurement1 = page.findViewById(R.id.imageView14);
            if (measurement1 != null) measurement1.setTranslationX(pageWidthTimesPosition * 0.7f);
            //if (measurement1 != null) measurement1.setAlpha(1.0f - absPosition * 2);

            final View measurement2 = page.findViewById(R.id.imageView15);
            if (measurement2 != null) measurement2.setTranslationX(pageWidthTimesPosition * 0.7f);
            //if (measurement2 != null) measurement2.setAlpha(1.0f - absPosition * 2);

            final View icUser2 = page.findViewById(R.id.imageView13);
            if (icUser2 != null) icUser2.setTranslationX(pageWidthTimesPosition * 0.2f);
            //if (icUser2 != null) icUser2.setAlpha(1.0f - absPosition * 2);

            final View bg = page.findViewById(R.id.imageView13);
            if (bg != null) bg.setTranslationX(pageWidthTimesPosition * 0.2f);
            if (bg != null) bg.setTranslationX(pageWidthTimesPosition * 1.2f);


        }
    }
}
