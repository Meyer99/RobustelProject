package com.example.robustelproject;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineChartFragment extends SimpleFragment
{

    public LineChartFragment()
    {
        // Required empty public constructor
    }

    @NonNull
    public static Fragment newInstance()
    {
        return new LineChartFragment();
    }

    @SuppressWarnings("FieldCanBeLocal")
    private LineChart chart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_line_chart, container, false);

        chart = v.findViewById(R.id.lineChart1);

        chart.getDescription().setEnabled(false);

        chart.setDrawGridBackground(false);

        chart.setData(getComplexity());
        chart.animateX(3000);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");

        Legend l = chart.getLegend();
        l.setTypeface(tf);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tf);

        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        return v;
    }
}


