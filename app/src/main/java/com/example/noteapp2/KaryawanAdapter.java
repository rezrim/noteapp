package com.example.noteapp2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KaryawanAdapter extends RecyclerView.Adapter<KaryawanAdapter.ViewHolderK> {
    private Context context;
    private List<KaryawanModel> karyawan;

    public KaryawanAdapter(Context context) {
        this.context = context;
        this.karyawan = new ArrayList<>();
    }
    public void setKaryawan(List<KaryawanModel> karyawan) {
        this.karyawan = karyawan;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolderK onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.karyawan_item, viewGroup, false);
        return new ViewHolderK(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderK viewHolderK, int i) {
        final KaryawanModel karyawanModel = karyawan.get(i);

        viewHolderK.tvId.setText(karyawanModel.getK_Id());
        viewHolderK.tvNama.setText(karyawanModel.getK_Nama());
        viewHolderK.tvEmail.setText(karyawanModel.getK_Email());
        viewHolderK.cvItemK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, addKaryawan.class);
                intent.putExtra("isEditK", true);
                intent.putExtra("karyawanNO", karyawanModel.getK_No());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return karyawan.size();
    }
    public class ViewHolderK extends RecyclerView.ViewHolder {
        private CardView cvItemK;
        private TextView tvId, tvNama, tvEmail;

        public ViewHolderK(@NonNull View itemView) {
            super(itemView);
            cvItemK = itemView.findViewById(R.id.cv_itemK);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }
    }
}