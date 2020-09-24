package com.ekattorit.medishop.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekattorit.medishop.R;
import com.ekattorit.medishop.activities.dashboard.ProductDetalisActivity;
import com.ekattorit.medishop.models.ProductModel;
import com.ekattorit.medishop.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewReleaseProductAdapter extends RecyclerView.Adapter<NewReleaseProductAdapter.ViewHolder> {

    private Context context;
    private List<ProductModel> productModels;
    private NewReleaseProductAdapterListener newReleaseProductAdapterListener;

    public NewReleaseProductAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    public void setNewReleaseProductAdapterListener(NewReleaseProductAdapterListener newReleaseProductAdapterListener) {
        this.newReleaseProductAdapterListener = newReleaseProductAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_product_item, parent, false);
        return new ViewHolder(view);
    }

    public void setProductModels(List<ProductModel> productModels){
        this.productModels = productModels;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final ProductModel productModel = productModels.get(position);
        Uri uri = Uri.parse(productModel.getProductImage());

        holder.imgProduct.setImageURI(uri);
        holder.txtTitle.setText(productModel.getProductName());
        holder.txtPrice.setText(productModel.getProductPrice());

        holder.layoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newReleaseProductAdapterListener != null){
                    newReleaseProductAdapterListener.onItemClicked(productModel.getProductId());
                }
                Intent intent = new Intent(context, ProductDetalisActivity.class);
                intent.putExtra(Constants.MODEL_PRODUCT,productModel);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgProduct)
        SimpleDraweeView imgProduct;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtPrice)
        TextView txtPrice;
        @BindView(R.id.btnMore)
        ImageButton btnMore;
        @BindView(R.id.layoutProduct)
        LinearLayout layoutProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
