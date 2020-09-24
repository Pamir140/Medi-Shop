package com.ekattorit.medishop.activities.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.ekattorit.medishop.R;
import com.ekattorit.medishop.models.ProductModel;
import com.ekattorit.medishop.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetalisActivity extends AppCompatActivity {

    @BindView(R.id.imgBackground)
    SimpleDraweeView imgBackground;
    @BindView(R.id.txtProductTitle)
    TextView txtProductTitle;
    @BindView(R.id.txtDescription)
    TextView txtDescription;
    @BindView(R.id.txtShopName)
    TextView txtShopName;
    @BindView(R.id.txtPrice)
    TextView txtPrice;
    @BindView(R.id.recyclerViewSliderImage)
    RecyclerView recyclerViewSliderImage;
    @BindView(R.id.fab_color_blue)
    FloatingActionButton fabColorBlue;
    @BindView(R.id.fab_color_pink)
    FloatingActionButton fabColorPink;
    @BindView(R.id.fab_color_grey)
    FloatingActionButton fabColorGrey;
    @BindView(R.id.fab_color_green)
    FloatingActionButton fabColorGreen;
    @BindView(R.id.btnSizeSmall)
    Button btnSizeSmall;
    @BindView(R.id.btnSizeMedium)
    Button btnSizeMedium;
    @BindView(R.id.btnSizeLarge)
    Button btnSizeLarge;
    @BindView(R.id.btnSizeExtraLarge)
    Button btnSizeExtraLarge;
    @BindView(R.id.btnSubstract)
    FloatingActionButton btnSubstract;
    @BindView(R.id.txtQuantity)
    TextView txtQuantity;
    @BindView(R.id.btnAdd)
    FloatingActionButton btnAdd;
    @BindView(R.id.btnAddToWishlist)
    AppCompatButton btnAddToWishlist;
    @BindView(R.id.btnAddToCart)
    AppCompatButton btnAddToCart;
    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.parent_view)
    RelativeLayout parentView;

    private ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detalis);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(Constants.MODEL_PRODUCT)) {
            productModel = getIntent().getParcelableExtra(Constants.MODEL_PRODUCT);

            imgBackground.setImageURI(Uri.parse(productModel.getProductImage()));
            txtProductTitle.setText(productModel.getProductName());
            txtShopName.setText(R.string.app_name);
            txtPrice.setText(productModel.getProductPrice());
            txtDescription.setText(productModel.getProductDetails());
            txtQuantity.setText("1");
        }
    }

    @OnClick({R.id.btnSizeSmall, R.id.btnSizeMedium, R.id.btnSizeLarge, R.id.btnSizeExtraLarge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSizeSmall:
                productModel.setProductSize(Constants.PRODUCT_SIZE_SMALL);
                changeSize(btnSizeSmall,btnSizeMedium,btnSizeLarge,btnSizeExtraLarge);
                break;
            case R.id.btnSizeMedium:
                productModel.setProductSize(Constants.PRODUCT_SIZE_MEDIUM);
                changeSize(btnSizeMedium,btnSizeSmall,btnSizeLarge,btnSizeExtraLarge);
                break;
            case R.id.btnSizeLarge:
                productModel.setProductSize(Constants.PRODUCT_SIZE_LAGRE);
                changeSize(btnSizeLarge,btnSizeMedium,btnSizeSmall,btnSizeExtraLarge);
                break;
            case R.id.btnSizeExtraLarge:
                productModel.setProductSize(Constants.PRODUCT_SIZE_EXTRA_LARGE);
                changeSize(btnSizeExtraLarge,btnSizeMedium,btnSizeLarge,btnSizeSmall);
                break;
        }
    }

    public void changeSize(View v1,View v2,View v3,View v4){
        v1.setEnabled(false);
        ((TextView)v1).setTextColor(ContextCompat.getColor(ProductDetalisActivity.this,R.color.colorWhite));
        v2.setEnabled(true);
        ((TextView)v2).setTextColor(ContextCompat.getColor(ProductDetalisActivity.this,R.color.colorBlack));
        v3.setEnabled(true);
        ((TextView)v3).setTextColor(ContextCompat.getColor(ProductDetalisActivity.this,R.color.colorBlack));
        v4.setEnabled(true);
        ((TextView)v4).setTextColor(ContextCompat.getColor(ProductDetalisActivity.this,R.color.colorBlack));
    }
}
