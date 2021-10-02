package com.example.starBooks;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.databinding.DialogCustomBinding;

public class CustomDialog extends Dialog implements View.OnClickListener {

    private DialogCustomBinding binding;
    private Context context;
    private CustomDialogListener customDialogListener;

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    private int starRate = 0;

    public CustomDialog(@NonNull Context context, CustomDialogListener customDialogListener) {
        super(context);
        this.context = context;
        this.customDialogListener= customDialogListener;
    }

    public interface CustomDialogListener {
        void onStar1Click(int starRate);
        void onStar2Click(int starRate);
        void onStar3Click(int starRate);
        void onStar4Click(int starRate);
        void onStar5Click(int starRate);
        void onRegisterClick(String comment);
        void onNegativeClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_custom, null, false);
        binding.setOnClick(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.star1:
                binding.star1.setBackgroundResource(R.drawable.ic_star_small_solid);
                starRate = 1;
                this.customDialogListener.onStar1Click(starRate);
                break;

            case R.id.star2:
                binding.star1.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star2.setBackgroundResource(R.drawable.ic_star_small_solid);
                starRate=2;
                this.customDialogListener.onStar2Click(starRate);
                break;

            case R.id.star3:
                binding.star1.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star2.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star3.setBackgroundResource(R.drawable.ic_star_small_solid);
                starRate=3;
                this.customDialogListener.onStar3Click(starRate);
                break;

            case R.id.star4:
                binding.star1.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star2.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star3.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star4.setBackgroundResource(R.drawable.ic_star_small_solid);
                starRate=4;
                this.customDialogListener.onStar4Click(starRate);

                break;

            case R.id.star5:
                binding.star1.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star2.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star3.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star4.setBackgroundResource(R.drawable.ic_star_small_solid);
                binding.star5.setBackgroundResource(R.drawable.ic_star_small_solid);
                starRate=5;
                this.customDialogListener.onStar5Click(starRate);

                break;

            case R.id.button_cancel:
                this.customDialogListener.onNegativeClick();
                dismiss();
                break;

            case R.id.button_register:
                String comment = binding.tvWriteReview.getText().toString();
                this.customDialogListener.onRegisterClick(comment);
                dismiss();
                break;
        }
    }
}
