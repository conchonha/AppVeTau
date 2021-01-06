package com.cj.trainticks.src.adapter.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cj.trainticks.R;
import com.cj.trainticks.cores.reponse.TimChuyenTauReponse;
import com.cj.trainticks.src.page.home.ShowListChuyenTauActivity;

import java.util.Calendar;
import java.util.List;

public class ShowListChuyenTauAdapter extends RecyclerView.Adapter<ShowListChuyenTauAdapter.ShowListChuyenViewHodler> {
    private List<TimChuyenTauReponse> mList;
    private ShowListChuyenTauActivity mContext;

    public ShowListChuyenTauAdapter(List<TimChuyenTauReponse> mList, ShowListChuyenTauActivity mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ShowListChuyenViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.row_items_chon_chuyen_tau, null);
        return new ShowListChuyenViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowListChuyenViewHodler holder, int position) {
        TimChuyenTauReponse tauReponse = mList.get(position);
        holder.mTxtThoiGian1.setText(tauReponse.getGIODI());
        holder.mTxtNgay1.setText(tauReponse.getNGAYDI());
        holder.mTxtDiaDiem1.setText(tauReponse.getGADI());
        holder.mTxtThoiGian2.setText(tauReponse.getGIODEN());
        holder.mTxtNgay2.setText(tauReponse.getNGAYDEN());
        holder.mTxtDiaDiem2.setText(tauReponse.getGADEN());
        holder.mTxtTenTau.setText(tauReponse.getTENTAU());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.adapterItemClicked(tauReponse.getId());
            }
        });

        //so sanh t/g tau di
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR);
        int h1 = Integer.parseInt(tauReponse.getGIODI().substring(0,2));
        if(h1 < h){
            holder.mCardView.setCardBackgroundColor(R.color.xam);
            holder.mCardView.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ShowListChuyenViewHodler extends RecyclerView.ViewHolder {
        private TextView mTxtThoiGian1, mTxtNgay1, mTxtDiaDiem1, mTxtThoiGian2, mTxtNgay2, mTxtDiaDiem2,mTxtTenTau;
        private CardView mCardView;

        public ShowListChuyenViewHodler(@NonNull View itemView) {
            super(itemView);
            mTxtThoiGian1 = itemView.findViewById(R.id.txt_thoi_gian_1);
            mTxtNgay1 = itemView.findViewById(R.id.txt_ngay_1);
            mTxtDiaDiem1 = itemView.findViewById(R.id.txt_dia_diem_1);
            mTxtThoiGian2 = itemView.findViewById(R.id.txt_thoi_gian_2);
            mTxtNgay2 = itemView.findViewById(R.id.txt_ngay_2);
            mTxtDiaDiem2 = itemView.findViewById(R.id.txt_dia_diem_2);
            mTxtTenTau = itemView.findViewById(R.id.txt_ten_tau);
            mCardView = itemView.findViewById(R.id.card_chuyen_tau);
        }
    }
}
