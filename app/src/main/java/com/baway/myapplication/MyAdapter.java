package com.baway.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 刘海峰.20:09
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    List<Data1> list;
    Context mContext;
    private  float tprice;

    public MyAdapter(List<Data1> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.item,null);
        ViewHolder mholder = new ViewHolder(view);
        return mholder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
          final Data1 bean = list.get(position);
        Glide.with(mContext).load(bean.getImage_url()).into(holder.image);
        holder.tv.setText(bean.getPrice() + "");
        holder.checkBox.setChecked(bean.ischecked());
holder.checkBox.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(holder.checkBox.isChecked()){
            tprice+=bean.getPrice();
          bean.setIschecked(true);

        }
        else {
            tprice-=bean.getPrice();
            bean.setIschecked(false);
            MainActivity activity=(MainActivity)mContext;
            activity.checked(false);

        }
        MainActivity activity=(MainActivity)mContext;
        activity.SetpPriceCount(tprice);

    }
});
    }




    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

      CheckBox checkBox;
        ImageView image;
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox= (CheckBox) itemView.findViewById(R.id.checkBox);
            image= (ImageView) itemView.findViewById(R.id.imageView);
            tv= (TextView) itemView.findViewById(R.id.textView);

        }


    }
    public void all() {
        for (int i = 0; i < list.size(); i++) {
            Data1 b = list.get(i);
            b.setIschecked(true);
            tprice+=b.getPrice();

        }
        MainActivity activity=(MainActivity)mContext;
        activity.SetpPriceCount(tprice);
        activity.checked(true);
        notifyDataSetChanged();
    }



}
