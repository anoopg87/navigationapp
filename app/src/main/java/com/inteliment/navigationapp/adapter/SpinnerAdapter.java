package com.inteliment.navigationapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.inteliment.navigationapp.R;
import com.inteliment.navigationapp.model.SampleJsonModel;

import java.util.List;


public class SpinnerAdapter extends BaseAdapter {
    List<SampleJsonModel> mDataSet;
    View mView;

    public SpinnerAdapter(List<SampleJsonModel> dataSet) {
        mDataSet=dataSet;
    }

    @Override
    public int getCount() {
        return null!=mDataSet?mDataSet.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mView=convertView;
        viewHolder vh=null;
        if(null==mView){
            vh=new viewHolder();
            mView= LayoutInflater.from(parent.getContext()).inflate(R.layout.datarow,parent,false);
            vh.textView=((TextView)mView.findViewById(R.id.textView));
            mView.setTag(vh);
        }else {
           vh= (viewHolder) mView.getTag();
        }
        vh.textView.setText(mDataSet.get(position).getName());

        return mView;
    }

    public  class viewHolder{
        TextView textView;
    }
}
