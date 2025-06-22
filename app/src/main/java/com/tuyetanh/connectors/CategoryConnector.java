package com.tuyetanh.connectors;

import com.tuyetanh.model.Category;
import com.tuyetanh.model.ListCategory;

import java.util.ArrayList;

public class CategoryConnector {
    ListCategory listCategory;

    public CategoryConnector() {
        listCategory = new ListCategory();
        listCategory.generate_sample_dataset();
    }

    public ArrayList<Category> get_all_categories() {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generate_sample_dataset();
        }
        return listCategory.getCategories();
    }

    public ArrayList<Category> search_catalogs_by_name(String keyword) {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generate_sample_dataset();
        }

        ArrayList<Category> results = new ArrayList<>();
        for (Category c : listCategory.getCategories()) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(c);
            }
        }
        return results;
    }
}