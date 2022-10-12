package com.allsociety.mobilkiwsb.view.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.allsociety.mobilkiwsb.R;

import java.util.List;

import API.ResponseObject.DiscountedAssortment;

public class PromotionUILIstAdapter extends ArrayAdapter<DiscountedAssortment> {

    public PromotionUILIstAdapter(Context context, List<DiscountedAssortment> assortmentList) {
        super(context, R.layout.list_item_discount_assortment, assortmentList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DiscountedAssortment assortment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_discount_assortment, parent, false);
        }

        TextView assortmentName = convertView.findViewById(R.id.itemList_AssortmentName);
        assortmentName.setText(assortment.code);

        TextView assortmentEAN = convertView.findViewById(R.id.itemList_AssortmentEAN);
        assortmentEAN.setText(assortment.eanCode);

        TextView assortmentOldPrice = convertView.findViewById(R.id.itemList_AssortmentOldPrice);
        TextView assortmentNewPrice = convertView.findViewById(R.id.itemList_AssortmentNewPrice);
        assortmentOldPrice.setText("Poprzednia cena:" + String.valueOf(assortment.price));
        assortmentNewPrice.setText("Nowa cena:" + String.valueOf(assortment.price - (assortment.price/100)*assortment.discount));

        TextView assortmentDiscount = convertView.findViewById(R.id.itemList_AssortmentDiscount);
        assortmentDiscount.setText(String.format("%s", (int)assortment.discount) + "%");


        return convertView;
    }
}
