package com.example.robustelproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment
{
    private LineChart chart1, chart2;
    protected Typeface tfLight;
    private FloatingActionButton fab;
    private ArrayList<Entry> values1 = new ArrayList<>();
    private ArrayList<Entry> values2 = new ArrayList<>();

    public ChartFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        chart1 = (LineChart)view.findViewById(R.id.chart1);
        chart2 = (LineChart)view.findViewById(R.id.chart2);
        fab = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                chart1.animateX(1000);
                chart2.animateX(1000);
                Toast.makeText(getContext(),"刷新成功", Toast.LENGTH_SHORT).show();
            }
        });

        tfLight = Typeface.createFromAsset(getContext().getAssets(), "OpenSans-Light.ttf");

        // no description text
        chart1.getDescription().setEnabled(false);
        chart2.getDescription().setEnabled(false);

        // enable touch gestures
        chart1.setTouchEnabled(false);
        chart2.setTouchEnabled(false);

        chart1.setDragDecelerationFrictionCoef(0.9f);
        chart2.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        /*chart1.setDragEnabled(true);
        chart1.setScaleEnabled(true);
        chart1.setDrawGridBackground(false);
        chart1.setHighlightPerDragEnabled(true);*/

        // if disabled, scaling can be done on x- and y-axis separately
        /*chart1.setPinchZoom(true);*/

        // set an alternative background color
        chart1.setBackgroundColor(Color.WHITE);
        chart2.setBackgroundColor(Color.WHITE);

        setData(10, 50);

        chart1.invalidate();
        chart1.animateX(1000);
        chart2.animateX(1000);

        // get the legend (only possible after setting data)
        Legend l = chart1.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(tfLight);
        l.setTextSize(13f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        l = chart2.getLegend();

        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(tfLight);
        l.setTextSize(13f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis1 = chart1.getXAxis();
        xAxis1.setAxisMinimum(0);
        xAxis1.setAxisMaximum(11);
        xAxis1.setTypeface(tfLight);
        xAxis1.setTextSize(11f);
        xAxis1.setTextColor(Color.WHITE);
        xAxis1.setDrawGridLines(false);
        xAxis1.setDrawAxisLine(false);
        xAxis1.setEnabled(false);


        YAxis leftAxis1 = chart1.getAxisLeft();
        leftAxis1.setTypeface(tfLight);
        leftAxis1.setTextColor(Color.RED);
        leftAxis1.setAxisMaximum(getExtremum(values1, 1) + 1);
        leftAxis1.setAxisMinimum(getExtremum(values1, 0) - 1);
        leftAxis1.setDrawGridLines(true);
        leftAxis1.setGranularityEnabled(true);

        YAxis rightAxis1 = chart1.getAxisRight();
        rightAxis1.setEnabled(false);

        YAxis leftAxis2 = chart2.getAxisLeft();
        leftAxis2.setTypeface(tfLight);
        leftAxis2.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis2.setAxisMaximum(getExtremum(values2, 1) + 2);
        leftAxis2.setAxisMinimum(getExtremum(values2, 0) - 2);
        leftAxis2.setDrawGridLines(true);
//        leftAxis2.setDrawZeroLine(false);
        leftAxis2.setGranularityEnabled(true);

        YAxis rightAxis2 = chart2.getAxisRight();
        rightAxis2.setEnabled(false);

       XAxis xAxis2 = chart2.getXAxis();
       xAxis2.setAxisMinimum(0);
       xAxis2.setAxisMaximum(11);
       xAxis2.setTypeface(tfLight);
       xAxis2.setTextSize(11f);
       xAxis2.setTextColor(Color.WHITE);
       xAxis2.setDrawGridLines(false);
       xAxis2.setDrawAxisLine(false);
       xAxis2.setEnabled(false);

        return view;
    }

    private void setData(int count, float range)
    {


        for (int i = 1; i < count + 1; i++) {
            float val = (float) (Math.random() * 4f - 2f) + 30;
            values1.add(new Entry(i, val));
        }



        for (int i = 1; i < count + 1; i++) {
            float val = (float) (Math.random() * 10f - 5f) + 60;
            values2.add(new Entry(i, val));
        }

        LineDataSet set1, set2;

        if (chart1.getData() != null &&
                chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart1.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) chart2.getData().getDataSetByIndex(0);

            set1.setValues(values1);
            set2.setValues(values2);

            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values1, "温度 / °C");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.RED);
            set1.setCircleColor(Color.RED);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(true);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
            set2 = new LineDataSet(values2, "湿度 / %");
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(ColorTemplate.getHoloBlue());
            set2.setCircleColor(ColorTemplate.getHoloBlue());
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(true);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            //set2.setFillFormatter(new MyFillFormatter(900f));

//            set3 = new LineDataSet(values3, "DataSet 3");
//            set3.setAxisDependency(AxisDependency.RIGHT);
//            set3.setColor(Color.YELLOW);
//            set3.setCircleColor(Color.WHITE);
//            set3.setLineWidth(2f);
//            set3.setCircleRadius(3f);
//            set3.setFillAlpha(65);
//            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
//            set3.setDrawCircleHole(false);
//            set3.setHighLightColor(Color.rgb(244, 117, 117));

            // create a data object with the data sets
            LineData data1 = new LineData(set1);
            data1.setValueTextColor(Color.rgb(64,64,64));
            data1.setValueTextSize(12f);
            data1.setValueFormatter(new MyValueFormatter());

            LineData data2 = new LineData(set2);
            data2.setValueTextColor(Color.rgb(64,64,64));
            data2.setValueTextSize(12f);
            data2.setValueFormatter(new MyValueFormatter());

            // set data
            chart1.setData(data1);
            chart2.setData(data2);
        }
    }

    private float getExtremum(ArrayList<Entry> a, int mode)
    {
        ArrayList<Float> temp = new ArrayList<>();
        for (int i = 0; i < a.size(); i++)
        {
            temp.add(a.get(i).getY());
        }
        return (mode == 0 ? Collections.min(temp) : Collections.max(temp));
    }

    class MyValueFormatter implements IValueFormatter
    {
        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler)
        {
            return mFormat.format(value);
        }
    }

//    @Override
//    public void onAttach(Context context)
//    {
//        super.onAttach(context);
//        chart1.invalidate();
//        chart2.invalidate();
//        chart1.animateX(1000);
//        chart2.animateX(1000);
//    }
}
