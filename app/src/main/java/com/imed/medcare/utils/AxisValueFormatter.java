package com.imed.medcare.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import java.util.ArrayList;

public class AxisValueFormatter implements IAxisValueFormatter {

    private ArrayList<String> mValues;

    public AxisValueFormatter(ArrayList<String> mValues){
        this.mValues = mValues;
    }


    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        try {
            return mValues.get((int) value);
        } catch (Exception e) {
            return "";
        }
    }
}
