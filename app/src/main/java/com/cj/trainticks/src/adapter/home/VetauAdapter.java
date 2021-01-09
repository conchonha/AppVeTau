package com.cj.trainticks.src.adapter.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.model.VeTau;
import com.cj.trainticks.src.page.home.ChonVeTauActivity;

import java.util.List;

public class VetauAdapter extends RecyclerView.Adapter<VetauAdapter.GheViewhodler> {
    private List<VeTau>mList;
    private ChonVeTauActivity mContext;

    public VetauAdapter(List<VeTau> mList, ChonVeTauActivity mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public void thayList(List<VeTau>list){
        this.mList = list;
    }

    @NonNull
    @Override
    public GheViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.row_items_vetau,null);
        return new GheViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GheViewhodler holder, int position) {
        VeTau veTau = mList.get(position);
        holder.mTxtGia.setText(veTau.getGIAVE().toString()+"k");
        holder.mTxtMaGhe.setText(veTau.getSOGHE().toString());
        if(veTau.getStatus() == 1){
            holder.mLinerLayout.setBackgroundResource(R.color.mau_do);
            holder.mTxtGia.setTextColor(R.color.white);
            holder.mTxtMaGhe.setTextColor(R.color.white);
            holder.mCardVe.setEnabled(false);
        }
        if(veTau.getmCheck()){
            holder.mLinerLayout.setBackgroundResource(R.color.colorPrimary);
        }

        holder.mCardVe.setOnClickListener(v->{
//            veTau.setmCheck(true);
//            notifyItemChanged(position);
            mContext.onclicedItemVeTauAdapter(veTau);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GheViewhodler extends RecyclerView.ViewHolder{
        private TextView mTxtMaGhe,mTxtGia;
        private LinearLayout mLinerLayout;
        private CardView mCardVe;
        public GheViewhodler(@NonNull View itemView) {
            super(itemView);
            mTxtMaGhe = itemView.findViewById(R.id.txt_ma_ghe);
            mTxtGia = itemView.findViewById(R.id.txt_gia_tien);
            mLinerLayout = itemView.findViewById(R.id.liner_3);
            mCardVe = itemView.findViewById(R.id.card_ve);
        }
    }
}
