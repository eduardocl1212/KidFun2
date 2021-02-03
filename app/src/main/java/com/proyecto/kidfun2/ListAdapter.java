package com.proyecto.kidfun2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    public List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public ListAdapter(List<ListElement> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.listelements, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView actividad,desc;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconViewer);
            actividad = itemView.findViewById(R.id.nameActivity);
            desc = itemView.findViewById(R.id.DescActivity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //String classe = mData.get(position).getSelect();
                    Intent intent = new Intent(mContext, Actividades.class);
                    intent.putExtra("DescAct", mData.get(position).getSelect());
                    mContext.startActivity(intent);
                }
            });
        }

        void bindData(final  ListElement item){
            Glide.with(mContext).load(item.getPhotoURL()).into(iconImage);
            actividad.setText(item.getActividad());
            desc.setText(item.getDescripcion());

        }
    }
}
