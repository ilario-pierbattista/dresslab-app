package com.example.matteo.dresslap_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapterProduct extends BaseAdapter {

    private Context mContext;
    private List<Product> mProductList;
    private static final String MAGLIONCINO = "maglioncino AS";
    private static final String BORSA = "borsa AS";
    private static final String GIUBBOTTO = "giubbotto AS";
    private static final String GIUBBOTTO_DONNA = "giubbotto donna AS";
    private static final String JEANS = "jeans AS";
    private static final String SCARPE = "scarpe AS";
    private static final String TSHIRT = "tshirt AS";


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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.item_card,null);
        TextView tvNome = (TextView)v.findViewById(R.id.tv_id);
        ImageView ivColore = (ImageView)v.findViewById(R.id.iv_colore);
        TextView tvTaglia = (TextView)v.findViewById(R.id.tv_taglia);
        TextView tvPrezzo = (TextView)v.findViewById(R.id.tv_prezzo);
        ImageView ivCapo = (ImageView)v.findViewById(R.id.iv_capo);
        TextView tvCamerino = (TextView)v.findViewById(R.id.tv_camerino);

        Button btnArrivo = (Button)v.findViewById(R.id.btn_arrivo);
        btnArrivo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CustomModalActivity.class);
                intent.putExtra("task_id", ((Product) getItem(position)).getId());
                mContext.startActivity(intent);
            }
        });

        tvNome.setText(mProductList.get(position).getNome());
        tvTaglia.setText(mProductList.get(position).getTaglia());
        tvPrezzo.setText(mProductList.get(position).getPrezzo());
        tvCamerino.setText(mProductList.get(position).getCamerino());
        int color = (int)Long.parseLong(mProductList.get(position).getColore(),16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        ivColore.setBackgroundColor(Color.rgb(r, g, b));
        String img_title ="capo_" + String.valueOf(mProductList.get(position).getId());
        Context context =ivCapo.getContext();
        int id_img = context.getResources().getIdentifier(img_title,"drawable",context.getPackageName());
        ivCapo.setImageResource(id_img);
        v.setTag(mProductList.get(position).getNome());
        return v;
    }
}
