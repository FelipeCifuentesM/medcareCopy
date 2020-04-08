package com.imed.medcare.model;

import java.util.Comparator;

public class CustomComparator implements Comparator<Pillbox> {
    @Override
    public int compare(Pillbox o1, Pillbox o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
