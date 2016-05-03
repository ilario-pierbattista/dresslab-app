package com.example.matteo.dresslap_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapterProduct extends BaseAdapter {

    private Context mContext;
    private List<Product> mProductList;

    public ListAdapterProduct (Context mContext, List<Product> mProductList){
        this.mContext=mContext;
        this.mProductList=mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.item_card,null);
        TextView tvId = (TextView)v.findViewById(R.id.tv_id_lv);
        TextView tvColore = (TextView)v.findViewById(R.id.tv_color_lv);
        TextView tvTaglia = (TextView)v.findViewById(R.id.tv_taglia_lv);
        TextView tvPrezzo = (TextView)v.findViewById(R.id.tv_prezzo_lv);
        tvId.setText(mProductList.get(position).getId());
        tvPrezzo.setText(mProductList.get(position).getPrezzo());
        tvColore.setText(mProductList.get(position).getColore());
        tvTaglia.setText(mProductList.get(position).getTaglia());
        v.setTag(mProductList.get(position).getId());
        return v;
    }
}
