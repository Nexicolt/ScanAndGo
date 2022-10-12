package com.allsociety.mobilkiwsb.model;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.allsociety.mobilkiwsb.R;
import com.allsociety.mobilkiwsb.view.CartActivity;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class CartItemListAdapter extends BaseAdapter {
    private final CartActivity context;
    private final ArrayList<CartItemModel> cartItemList;
    LayoutInflater layoutInflater;
    private int times;

    public CartItemListAdapter(CartActivity context, ArrayList<CartItemModel> itemsArrayList) {

        this.context = context;
        cartItemList = itemsArrayList;
        layoutInflater=LayoutInflater.from(context);
        times=0;
    }

    @Override
    public int getCount() {
        return cartItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        if(times==0) {
            context.vm.itemFromListSelected(i);
            times++;
        }else if(times==1) {
            times--;
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.cart_item_single_row, null);
        TextView name=(TextView)convertView.findViewById(R.id.assortmentName);
        TextView ean=(TextView)convertView.findViewById(R.id.assortmentEan);
        TextView priceCountPrice=(TextView)convertView.findViewById(R.id.assortmentCountPrice);
        TextView priceBefore=(TextView)convertView.findViewById(R.id.assortmentTotalPriceBeforeDiscount);
        TextView priceAfter=(TextView)convertView.findViewById(R.id.assortmentTotalPriceAfterDiscount);
        CartItemModel tmp=cartItemList.get(position);
        if(tmp.getPriceDiscounted()!=tmp.getPrice()*tmp.getQuantity())
        {
            priceBefore.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            priceBefore.setText(formatToNonDecimalIfPosible(tmp.getPrice()*tmp.getQuantity()));
        }
        priceCountPrice.setText(formatToNonDecimalIfPosible(tmp.getQuantity())+"x"+formatToNonDecimalIfPosible(tmp.getPrice()));
        name.setText(tmp.getCode());
        ean.setText(tmp.getEanCode());
        priceAfter.setText(formatToNonDecimalIfPosible(tmp.getPriceDiscounted()));

        return convertView;
    }

    public static String formatToNonDecimalIfPosible(float d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
}
