package com.ekattorit.medishop.database;

import com.ekattorit.medishop.models.ProductModel;

import java.util.List;

public interface FirebaseDatabaseManagerInterface {

    public void getAllProducts(List<ProductModel> allProducts);

}
