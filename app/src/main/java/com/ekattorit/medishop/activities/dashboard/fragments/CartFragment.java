package com.ekattorit.medishop.activities.dashboard.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ekattorit.medishop.R;
import com.ekattorit.medishop.adapters.CartAdapter;
import com.ekattorit.medishop.adapters.CartAdapterListener;
import com.ekattorit.medishop.models.CartModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends Fragment {

    public final String TAG = "CartFragment";

    @BindView(R.id.recyclerViewCart)
    RecyclerView recyclerViewCart;
    @BindView(R.id.txtTotalPrice)
    TextView txtTotalPrice;
    @BindView(R.id.btnCheckout)
    AppCompatButton btnCheckout;
    @BindView(R.id.layoutCheckout)
    LinearLayout layoutCheckout;

    private CartAdapter cartAdapter;
    private List<CartModel> cartModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);

        cartModels = new ArrayList<>();
        cartAdapter = new CartAdapter(getContext(), cartModels);

        recyclerViewCart.setLayoutManager(getLayoutManager());
        recyclerViewCart.setAdapter(cartAdapter);
        cartAdapter.setCartAdapterListener(new CartAdapterListener() {
            @Override
            public void onClick(int id) {

            }

            @Override
            public void onQantityChange(int position, int quantity) {
                cartModels.get(position).setProductQuantity(String.valueOf(quantity));
                updateCheckout();
            }
        });

        getCart();

        return view;
    }

    private void updateCheckout() {
        int totalAmount = 0;
        int price = 0;
        int quantity = 0;

        for(CartModel cartModel : cartModels){
            try {
                if(!TextUtils.isEmpty(cartModel.getProductQuantity()) && !TextUtils.isEmpty(cartModel.getProductPrice())) {
                    Log.d(TAG, "Quantity : " + cartModel.getProductQuantity() + " Price : " + cartModel.getProductPrice());
                    price = Integer.parseInt(cartModel.getProductPrice());
                    quantity = Integer.parseInt(cartModel.getProductQuantity());
                    totalAmount += price*quantity;
                }else {
                    Log.d(TAG, "Error : Quantity : " + cartModel.getProductQuantity() + " Price : " + cartModel.getProductPrice());
                }
            }catch (Exception e){
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        txtTotalPrice.setText("Tk : "+String.valueOf(totalAmount)+" /=");
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    private void getCart() {
        cartModels.add(new CartModel("1", "Product One", "", "1200", "", "", "", "01"));
        cartModels.add(new CartModel("2", "Product Two", "", "1400", "", "", "", "01"));
        cartModels.add(new CartModel("3", "Product Three", "", "1600", "", "", "", "01"));
        cartAdapter.setCartModels(cartModels);
    }

}
