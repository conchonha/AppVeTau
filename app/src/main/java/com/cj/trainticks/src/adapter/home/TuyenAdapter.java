package com.cj.trainticks.src.adapter.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.model.Tuyen;
import com.cj.trainticks.src.page.home.TuyenTauActivity;

import java.util.ArrayList;
import java.util.List;

public class TuyenAdapter extends RecyclerView.Adapter<TuyenAdapter.TuyenViewhodler>{
    private List<Tuyen>mList;
    private TuyenTauActivity mContext;
    private int mType;

    public TuyenAdapter(List<Tuyen>list,TuyenTauActivity mContext,int type) {
        this.mList = list;
        this.mContext = mContext;
        this.mType = type;
    }

    @NonNull
    @Override
    public TuyenViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.row_items_tuyen,null);
        return new TuyenViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TuyenViewhodler holder, int position) {
        Tuyen tuyen = mList.get(position);
        String []atr = tuyen.getTENGA().split(" ");
        holder.mTxtStt.setText(atr[0].substring(0,1)+atr[1].substring(0,1));
        holder.mTxtDescrip.setText(tuyen.getDIACHI());
        if(mType == 1){
            holder.mTxtName.setText(tuyen.getGADI());
        }else{
            holder.mTxtName.setText(tuyen.getGADEN());
        }
        holder.mCardView.setOnClickListener(v->{
            if(mType == 1){
                mContext.onclickItemAdapter1(tuyen.getGADI());
            }else{
                mContext.onclickItemAdapter1(tuyen.getGADEN());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class TuyenViewhodler extends RecyclerView.ViewHolder{
        private TextView mTxtStt,mTxtName,mTxtDescrip;
        private CardView mCardView;
        public TuyenViewhodler(@NonNull View itemView) {
            super(itemView);
            mTxtStt = itemView.findViewById(R.id.txtStt);
            mTxtName = itemView.findViewById(R.id.txtName);
            mTxtDescrip = itemView.findViewById(R.id.txtDesCription);
            mCardView = itemView.findViewById(R.id.card_item_tuyen);
        }
    }
}
