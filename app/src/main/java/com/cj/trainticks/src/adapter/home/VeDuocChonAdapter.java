package com.cj.trainticks.src.adapter.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.model.Toa;
import com.cj.trainticks.model.VeTau;
import com.cj.trainticks.src.page.home.ChonVeTauActivity;

import java.util.List;

public class VeDuocChonAdapter extends RecyclerView.Adapter <VeDuocChonAdapter.VeDuocChonViewhodler>{
    private List<VeTau>mList;
    private ChonVeTauActivity mContext;

    public VeDuocChonAdapter(List<VeTau> list, ChonVeTauActivity mContext) {
        this.mList = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VeDuocChonViewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext,R.layout.row_item_luachon,null);
        return new VeDuocChonViewhodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeDuocChonViewhodler holder, int position) {
        VeTau veTau = mList.get(position);
        holder.mTxtToa.setText(veTau.getTenToa());
        holder.mTxtCho.setText(veTau.getSOGHE().toString());
        holder.mImgClose.setOnClickListener(v->{
            mContext.xoaVeDuocChon(veTau);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class VeDuocChonViewhodler extends RecyclerView.ViewHolder{
        private TextView mTxtCho,mTxtToa;
        private ImageView mImgClose;
        public VeDuocChonViewhodler(@NonNull View itemView) {
            super(itemView);
            mTxtCho  = itemView.findViewById(R.id.txt_cho);
            mTxtToa  = itemView.findViewById(R.id.txt_toa);
            mImgClose  = itemView.findViewById(R.id.img_close);
        }
    }
}
