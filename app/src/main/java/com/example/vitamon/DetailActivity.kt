package com.example.vitamon

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlin.random.Random

class DetailActivity : AppCompatActivity() {

    var heartRateDataList = ArrayList<Int>()
    var tempDataList = ArrayList<Int>()
    var o2ListDataList = ArrayList<Int>()
    var timeList = ArrayList<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

       val navbarTitle =  intent.getStringExtra("patient_First_Name")
        textView.text = navbarTitle

        for (i in 0..24) {
            heartRateDataList.add(Random.nextInt(60,150))
            tempDataList.add(Random.nextInt(25,32))
            o2ListDataList.add((Random.nextInt(500,600)))
        }
        for (i in 0..48) {
            timeList.add(i.toDouble() / 2.0)
        }
        Log.d("HR", heartRateDataList.toString())
        Log.d("TEMP", tempDataList.toString())
        Log.d("o2ListData", o2ListDataList.toString())
        Log.d("TIME", timeList.toString())

        setupBarChartData()
        setupLineChartData()

    }

    private fun setupBarChartData() {

        val barGrouping = ArrayList<BarEntry>()

        heartRateDataList.forEachIndexed { i, item ->
            barGrouping.add(BarEntry(i.toFloat(), item.toFloat()))
        }

        // creating dataset for Bar Group
        val barDataSet = BarDataSet(barGrouping, "")

        barDataSet.color = ContextCompat.getColor(this,R.color.amber)

        val data = BarData(barDataSet)
        barChart.setData(data)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.labelCount = 11
        barChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        barChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        barChart.description.isEnabled = false
        barChart.animateY(1000)
        barChart.legend.isEnabled = false
        barChart.setPinchZoom(true)
        barChart.data.setDrawValues(false)
    }

    private fun setupLineChartData() {

        val yHRVals = ArrayList<Entry>()

        heartRateDataList.forEachIndexed { i, item ->
            yHRVals.add(BarEntry(i.toFloat(), item.toFloat()))
        }
        val set1: LineDataSet
        set1 = LineDataSet(yHRVals, "DataSet 1")

        // set1.fillAlpha = 110
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        // set1.enableDashedLine(5f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        set1.color = Color.BLUE
        set1.setCircleColor(Color.BLUE)
        set1.lineWidth = 1f
        set1.circleRadius = 3f
        set1.setDrawCircleHole(false)
        set1.valueTextSize = 0f
        set1.setDrawFilled(false)

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data = LineData(dataSets)

        // set data
        HRLineChart.setData(data)
        HRLineChart.description.isEnabled = false
        HRLineChart.legend.isEnabled = false
        HRLineChart.setPinchZoom(true)
        HRLineChart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        HRLineChart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        HRLineChart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        //lineChart.setDrawGridBackground()
        HRLineChart.xAxis.labelCount = 11
        HRLineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
    }
}
