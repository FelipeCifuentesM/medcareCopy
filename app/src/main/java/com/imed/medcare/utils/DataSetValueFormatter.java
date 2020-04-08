package com.imed.medcare.utils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class DataSetValueFormatter  implements IValueFormatter {


    public DataSetValueFormatter() { }


    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return String.format("%.1f", entry.getY());

    }
}
