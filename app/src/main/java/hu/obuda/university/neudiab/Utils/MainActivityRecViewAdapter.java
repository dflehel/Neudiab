package hu.obuda.university.neudiab.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import hu.obuda.university.neudiab.R;

public class MainActivityRecViewAdapter extends RecyclerView.Adapter<MainActivityRecViewAdapter.ViewHolderCard> {


    private ArrayList<CarvViewItem> carvViewItems = new ArrayList<>();

    public MainActivityRecViewAdapter(ArrayList<CarvViewItem> carvViewItems) {
        this.carvViewItems = carvViewItems;
    }

    public ArrayList<CarvViewItem> getCarvViewItems() {
        return carvViewItems;
    }

    public void setCarvViewItems(ArrayList<CarvViewItem> carvViewItems) {
        this.carvViewItems = carvViewItems;
    }

    @NonNull
    @Override
    public ViewHolderCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_main_activity,parent,false);
        ViewHolderCard viewHolderCard = new ViewHolderCard(view,parent.getContext());
        return viewHolderCard;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCard holder, int position) {
            holder.date.setText(carvViewItems.get(position).getDate().toString());
            holder.setDecisionData(carvViewItems.get(position).getDecisionData());
            holder.setDieabetdata(carvViewItems.get(position).getDieabetdata());
            holder.setchartdecision();
            holder.setchartdiabet();

    }
    public void updateglukoz(double value){
        System.out.println(this.carvViewItems.get(0).getDieabetdata().size()*5);
        this.getCarvViewItems().get(0).addglukosvalue(this.carvViewItems.get(0).getDieabetdata().size()*5,value);
        this.notifyItemChanged(0);
        System.out.println("Sikeresen updeteltem");
        System.out.println(value);
    }

    @Override
    public int getItemCount() {
        return this.carvViewItems.size();
    }

    public class ViewHolderCard extends RecyclerView.ViewHolder {

        public TextView date;
        public LineChart chart;
        public LineChart chart_decisons;
        public ArrayList<DecisionData> decisionData;
        public ArrayList<Dieabetdata> dieabetdata;
        public LineData lineData;
        public LineData lineData2;
        public Context context;


        public ViewHolderCard(@NonNull View itemView, ArrayList<DecisionData> decisionData, ArrayList<Dieabetdata> dieabetdata) {
            super(itemView);
            this.decisionData = decisionData;
            this.dieabetdata = dieabetdata;
            this.chart = (LineChart) itemView.findViewById(R.id.card_view_data_chart);
            this.date = (TextView) itemView.findViewById(R.id.card_view_date_text);
            this.chart_decisons = (LineChart) itemView.findViewById(R.id.card_view_data_chart_decisions);
        }

        public ArrayList<DecisionData> getDecisionData() {
            return decisionData;
        }

        public void setDecisionData(ArrayList<DecisionData> decisionData) {
            this.decisionData = decisionData;
        }

        public ArrayList<Dieabetdata> getDieabetdata() {
            return dieabetdata;
        }

        public void setDieabetdata(ArrayList<Dieabetdata> dieabetdata) {
            this.dieabetdata = dieabetdata;
        }

        public ViewHolderCard(@NonNull View itemView,Context context) {
            super(itemView);
            this.chart = (LineChart) itemView.findViewById(R.id.card_view_data_chart);
            this.date = (TextView) itemView.findViewById(R.id.card_view_date_text);
            this.chart_decisons = (LineChart) itemView.findViewById(R.id.card_view_data_chart_decisions);
            this.context = context;
            this.lineData = new LineData();
            this.lineData2 = new LineData();


            // enable description text
            this.chart.getDescription().setEnabled(true);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(true);
            chart.setDrawGridBackground(false);

            // if disabled, scaling can be done on x- and y-axis separately
            chart.setPinchZoom(true);

            // set an alternative background color
            chart.setBackgroundColor(Color.LTGRAY);
            //chart_decisons.setOnChartValueSelectedListener(context);
            lineData.setValueTextColor(Color.RED);
            chart.setData(lineData);
            // enable description text
            chart_decisons.getDescription().setEnabled(true);

            // enable touch gestures
            chart_decisons.setTouchEnabled(true);

            // enable scaling and dragging
            chart_decisons.setDragEnabled(true);
            chart_decisons.setScaleEnabled(true);
            chart_decisons.setDrawGridBackground(false);

            // if disabled, scaling can be done on x- and y-axis separately
            chart_decisons.setPinchZoom(true);

            // set an alternative background color
            chart_decisons.setBackgroundColor(Color.LTGRAY);
           // lineData.setValueTextColor(Color.WHITE);
            // add empty data
            chart.setData(lineData);
            lineData2.setValueTextColor(Color.WHITE);
          //  chart.setVisibleXRangeMaximum(120);
            // add empty data

            chart_decisons.setData(lineData2);

        }

        public void setchartdiabet(){
            LineData data = chart.getData();
            ILineDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                LineDataSet set1;
                ArrayList<Entry> entries = new ArrayList<>();
                for (int i = 0; i < this.dieabetdata.size(); ++i) {
                    //  this.lineData2.addEntry(new Entry(,this.decisionData.get(i).getDecissionValue()),0);
                    Entry entry = new Entry();
                    entries.add(new Entry(i, this.dieabetdata.get(i).getMeasuredValue()));
                }
                set1 = new LineDataSet(entries, "DataSet 1");
                set1.setDrawIcons(false);

                // draw dashed line
                set1.enableDashedLine(10f, 0f, 0f);

                // black lines and points
                set1.setColor(Color.RED);
                set1.setCircleColor(Color.BLACK);

                // line thickness and point size
                set1.setLineWidth(2f);
                set1.setCircleRadius(0f);

                // draw points as solid circles
                set1.setDrawCircleHole(false);

                // customize legend entry
                // set1.setFormLineWidth(4f);
                // set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                // set1.setFormSize(15.f);

                // text size of values
                set1.setValueTextSize(9f);

                // draw selection line as dashed
                set1.enableDashedHighlightLine(10f, 5f, 0f);

                // set the filled area
                set1.setDrawFilled(true);
                set1.notifyDataSetChanged();
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1); // add the data sets
                data = new LineData(dataSets);
                chart.setData(data);
                chart.setData(data);
                // chart.set
                chart.getData().notifyDataChanged();
                chart.notifyDataSetChanged();
                chart.setVisibleXRangeMaximum(24);
                chart.moveViewToX(chart.getData().getEntryCount());
            }
            else {
                // create a data object with the data sets
                //data = new LineData(dataSets);
                //chart.setData(data);
                this.setentry(DATA_CONTANIER.getthelist().get(DATA_CONTANIER.getthelist().size()-1).getValue());
                // chart.set
                //chart.getData().notifyDataChanged();
                //chart.notifyDataSetChanged();
               // chart.setVisibleXRangeMaximum(24);
                //chart.moveViewToX(chart.getData().getEntryCount());
            }
        }

        public void setentry(double value){
            LineData data= this.chart.getLineData();
           // LineDataSet set = data.getDataSetCount();
            data.addEntry(new Entry(data.getEntryCount(),(int)value),0);
            data.notifyDataChanged();
            this.chart.notifyDataSetChanged();
            chart.setVisibleXRangeMaximum(24);
            chart.moveViewToX(chart.getData().getEntryCount());
        }

        public void setchartdecision(){

            LineDataSet set1;
            ArrayList<Entry> entries = new ArrayList<>();
            for (int i=0;i<this.decisionData.size();++i){
              //  this.lineData2.addEntry(new Entry(,this.decisionData.get(i).getDecissionValue()),0);
                Entry entry = new Entry();
                entries.add(new Entry(i,this.decisionData.get(i).getDecissionValue()));
            }
            set1 = new LineDataSet(entries, "DataSet 1");
            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.notifyDataSetChanged();
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);
            chart_decisons.getData().notifyDataChanged();
            chart_decisons.notifyDataSetChanged();
            chart_decisons.setData(data);
            chart_decisons.setVisibleXRangeMaximum(24);
            chart_decisons.moveViewToX(chart_decisons.getData().getEntryCount());
            //LineDataSet set = new LineDataSet(entries,"teszt");
         //   ArrayList<ILineDataSet> set1 = new ArrayList<>();
          //  set1.add(set);
          //  lineData2 = new LineData(set1);
          //  lineData2.notifyDataChanged();
            // let the chart know it's data has changed
         //   chart_decisons.notifyDataSetChanged();
         //   chart_decisons.invalidate();
            // limit the number of visible entries
         //   chart_decisons.setVisibleXRangeMaximum(288);
         //   chart_decisons.moveViewToX(lineData2.getEntryCount());

        }
    }
}
