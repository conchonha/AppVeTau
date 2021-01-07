package com.cj.trainticks.src.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.cores.reponse.GetToaTauReponse;
import com.cj.trainticks.model.Toa;
import com.cj.trainticks.src.page.home.ChonVeTauActivity;

import java.util.List;

public class ToaAdapter extends RecyclerView.Adapter<ToaAdapter.ToaViewhodler>{
    private List<Toa> mList;
    private ChonVeTauActivity mContext;

    public ToaAdapter(List<Toa> mList, ChonVeTauActivity mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ToaViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.rowitem_toa,null);
        return new ToaViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToaViewhodler holder, int position) {
        Toa reponse = mList.get(position);
        holder.mTextViewToa.setText(reponse.getTENTOA());
        holder.mCard.setOnClickListener(v->{
            mContext.onItemClickAdapter(reponse);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ToaViewhodler extends RecyclerView.ViewHolder{
        private CardView mCard;
        private TextView mTextViewToa;
        public ToaViewhodler(@NonNull View itemView) {
            super(itemView);
            mCard = itemView.findViewById(R.id.card);
            mTextViewToa = itemView.findViewById(R.id.txtTenToa);
        }
    }
}
