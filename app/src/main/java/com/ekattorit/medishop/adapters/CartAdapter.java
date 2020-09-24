package com.ekattorit.medishop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekattorit.medishop.R;
import com.ekattorit.medishop.models.CartModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartModel> cartModels = new ArrayList<>();
    private Context mContext;
    private CartAdapterListener cartAdapterListener;

    public CartAdapter(Context context, List<CartModel> cartModels) {
        this.mContext = context;
        this.cartModels = cartModels;
    }

    public void setCartAdapterListener(CartAdapterListener cartAdapterListener) {
        this.cartAdapterListener = cartAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.row_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CartModel cartModel = cartModels.get(position);

        holder.productPrice.setText(cartModel.getProductPrice());

        holder.cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cartAdapterListener != null){
                    cartAdapterListener.onClick(position);
                }
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtQuantity.setText(String.valueOf(Integer.parseInt(holder.txtQuantity.getText().toString())+1));
                cartAdapterListener.onQantityChange(position,Integer.parseInt(holder.txtQuantity.getText().toString()));
            }
        });

        holder.btnSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.txtQuantity.setText(String.valueOf(Integer.parseInt(holder.txtQuantity.getText().toString())>0 ? Integer.parseInt(holder.txtQuantity.getText().toString())-1:0));
                cartAdapterListener.onQantityChange(position,Integer.parseInt(holder.txtQuantity.getText().toString()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }

    public void setCartModels(List<CartModel> cartModels) {
        this.cartModels = cartModels;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.productPrice)
        TextView productPrice;
        @BindView(R.id.btnSubstract)
        ImageButton btnSubstract;
        @BindView(R.id.txtQuantity)
        TextView txtQuantity;
        @BindView(R.id.btnAdd)
        ImageButton btnAdd;
        @BindView(R.id.btnImage)
        ImageView btnImage;
        @BindView(R.id.cell)
        LinearLayout cell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
