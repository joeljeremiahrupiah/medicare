package com.moringaschool.medicare.ui.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.medicare.R;
import com.moringaschool.medicare.ui.fragments.AddressDialog;

public class CheckoutActivity extends AppCompatActivity implements AddressDialog.AddressDialogListener {
    private Button checkout;
    Dialog dialog;
    CardView cash;
    CardView card;
    CardView paypal;
    CardView mpesa;
    String deliveryAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        checkout = findViewById(R.id.chkButton);
        cash = findViewById(R.id.cash);
        paypal = findViewById(R.id.paypal);
        mpesa = findViewById(R.id.mpesa);
        card = findViewById(R.id.credit);

        cash.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                cash.setCardBackgroundColor(getColor(R.color.teal_50));
                openDialog();
            }
        });
        mpesa.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                mpesa.setCardBackgroundColor(getColor(R.color.teal_50));
            }
        });
        paypal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                paypal.setCardBackgroundColor(getColor(R.color.teal_50));
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                card.setCardBackgroundColor(getColor(R.color.teal_50));
            }
        });

        dialog = new Dialog(CheckoutActivity.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_background));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        Button ok = dialog.findViewById(R.id.btn_okay);
        Button cancel = dialog.findViewById(R.id.btn_cancel);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckoutActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             dialog.show();
                Toast.makeText(CheckoutActivity.this, "Delivery Address:"+deliveryAdd, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDialog() {
        AddressDialog addressDialog = new AddressDialog();
        addressDialog.show(getSupportFragmentManager(),"address dialog");
    }

    @Override
    public void applyText(String address) {
         deliveryAdd= address;
    }
}