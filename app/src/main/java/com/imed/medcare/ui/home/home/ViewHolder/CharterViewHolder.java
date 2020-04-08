package com.imed.medcare.ui.home.home.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.UserMeasurement;
import com.imed.medcare.model.UserTratmentMeasurement;
import com.imed.medcare.utils.AxisValueFormatter;
import com.imed.medcare.utils.DataSetValueFormatter;
import com.imed.medcare.utils.MedcareUtils;
import com.imed.medcare.utils.MyMarkerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

import static com.imed.medcare.utils.MedcareUtils.getDateChartStyle;

public class CharterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.chart1)
    LineChart mChart;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_measurement)
    TextView tvMeasurement;


    public CharterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(UserMeasurement userMeasurement) {

        String measurement;

        for(int i =0; i<userMeasurement.getUserTratmentMeasurementRealmList().size();i++) {
            Log.e(userMeasurement.getName()+" userMeasurement123", userMeasurement.getUserTratmentMeasurementRealmList().get(i).getValue());
        }
        if(userMeasurement !=null&& userMeasurement.getUserTratmentMeasurementRealmList() != null && userMeasurement.getUserTratmentMeasurementRealmList().size()>0) {

            String floatConvert = userMeasurement.getUserTratmentMeasurementRealmList().get(userMeasurement.getUserTratmentMeasurementRealmList().size()-1).getValue();

            Float measurementValue = Float.parseFloat(floatConvert);
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            String floatFormated  = df.format(measurementValue);
            measurement = floatFormated + " " + userMeasurement.getUnit();


        }else {
            measurement= "";
        }

        assert userMeasurement != null;
        if(userMeasurement.getUserTratmentMeasurementRealmList() !=null && userMeasurement.getUserTratmentMeasurementRealmList().size()>0 ) {
            tvName.setText(userMeasurement.getName());
            int lastValue = 0;
            if(userMeasurement.getUserTratmentMeasurementRealmList().size()>1){
                lastValue = userMeasurement.getUserTratmentMeasurementRealmList().size() -1;
            }
            tvTime.setText(getDateChartStyle(userMeasurement.getUserTratmentMeasurementRealmList().get(lastValue).getDate()));
            tvMeasurement.setText(measurement);
            mChart.setDragEnabled(false);
            mChart.setTouchEnabled(true);

            // enable scaling and dragging
            mChart.setScaleEnabled(false);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            //mChart.setPinchZoom(true);
            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(App.getContext(), R.layout.custom_marker_view);

            // Set the marker to the chart
            mv.setChartView(mChart);
            mChart.setMarker(mv);

            mChart.getXAxis().setDrawGridLines(false);
            mChart.getDescription().setEnabled(false);
            mChart.setExtraBottomOffset(8f);
            mChart.setExtraTopOffset(8f);
            mChart.setExtraRightOffset(18f);
            mChart.setExtraLeftOffset(8f);


            Legend l = mChart.getLegend();
            l.setEnabled(false);

            final ArrayList<String> xData = new ArrayList<>();
            ArrayList<Entry> yValues = new ArrayList<>();

            for(int i = 0; i<userMeasurement.getUserTratmentMeasurementRealmList().size();i++){
                xData.add(MedcareUtils.dateFormatMeasurement(userMeasurement.getUserTratmentMeasurementRealmList().get(i).getDate()));
                String floatConvert = userMeasurement.getUserTratmentMeasurementRealmList().get(i).getValue().replace(",","");
                Float measurementValue = Float.parseFloat(floatConvert);
                yValues.add(new Entry(i, measurementValue));
            }

//        LimitLine lowerLimit = new LimitLine(0.8f);
//        lowerLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        lowerLimit.setLineWidth(1f);
//        lowerLimit.setLineColor(App.getContext().getResources().getColor(R.color.green_blue));


            XAxis xAxis = mChart.getXAxis();
            YAxis yAxisRigth = mChart.getAxisRight();
            YAxis yAxisLeft = mChart.getAxisLeft();
            yAxisRigth.setEnabled(false);
            yAxisLeft.removeAllLimitLines();
//        yAxisRigth.addLimitLine(lowerLimit);
            yAxisRigth.setDrawAxisLine(false);
            yAxisLeft.setTextSize(13.5f);
            yAxisLeft.setGranularity(getGranularity(userMeasurement.getUserTratmentMeasurementRealmList()));
            yAxisLeft.setDrawLimitLinesBehindData(true);


            xAxis.setGranularity(1f);
            xAxis.setTextSize(13.5f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new AxisValueFormatter(xData));
            LineDataSet set1 = new LineDataSet(yValues, "Data Set 1");
            set1.setColor(App.getContext().getResources().getColor(R.color.subtitle_my_profile));
            set1.setFillAlpha(110);
            set1.setDrawCircleHole(false);
            set1.setCircleRadius(4.5f);
            set1.setCircleColor(App.getContext().getResources().getColor(R.color.subtitle_my_profile));
            set1.setLineWidth(1f);
            set1.setValueTextSize(13.5f);
            set1.setValueFormatter(new DataSetValueFormatter());
            set1.setDrawValues(false);

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData dataSet = new LineData(dataSets);


            //mChart.setExtraLeftOffset(getDp(16));
            //mChart.setExtraRightOffset(getDp(16f));
            //mChart.getXAxis().setAvoidFirstLastClipping(true);


            mChart.setData(dataSet);
        }


    }

    private float getGranularity(RealmList<UserTratmentMeasurement> userTratmentMeasurementRealmList) {
        float granularity;
        Float minValue = null;
        Float maxValue = null;
        for(UserTratmentMeasurement userTratmentMeasurement : userTratmentMeasurementRealmList){
            Float measurementValue = Float.parseFloat(userTratmentMeasurement.getValue());
            if(minValue == null){
                minValue = measurementValue;
            }else {
                if(minValue<measurementValue){
                    minValue = measurementValue;
                }
            }

            if(maxValue == null){
                maxValue = measurementValue;
            }else {
                if(maxValue>measurementValue){
                    maxValue = measurementValue;
                }
            }
        }
        if(maxValue != minValue) {
            granularity = (maxValue - minValue)/6;
            return granularity;
        }else {
            return 1f;
        }
    }
}
