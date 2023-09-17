package com.rookienichezero.algorithm.leetcodetrain.tree.helper;

import com.rookienichezero.algorithm.leetcodetrain.tree.entity.CategoryEntity;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryHelper {

    public static final long ROOT_CATEGORY_ID = -1;
    private CategoryHelper(){}

    public static CategoryEntity buildCategoryTreeWithRoot(List<CategoryEntity> categoryEntities) {
        Map<Long, CategoryEntity> id2entityMap = new HashMap<>();

        for (CategoryEntity category : categoryEntities) {
            id2entityMap.put(category.getCategoryId(), category);
        }

        List<CategoryEntity> tree = new ArrayList<>();

        for (CategoryEntity category : categoryEntities) {
            if(category.getCategoryId() == ROOT_CATEGORY_ID){
                continue;
            }

            Long parentCategoryId = category.getParentCategoryId();
            CategoryEntity parentCategory = id2entityMap.get(parentCategoryId);
            if (parentCategory != null) {
                parentCategory.getChildren().add(category);
            }
        }

        return id2entityMap.get(ROOT_CATEGORY_ID);
    }


    public static int getMaxDepthOfCategoryTree(CategoryEntity rootCategory) {
        if (rootCategory == null) {
            return 0;
        }
        int maxDepth = rootCategory.getLevel();
        List<CategoryEntity> children = rootCategory.getChildren();
        if (children != null) {
            for (CategoryEntity child : children) {
                int childMaxDepth = getMaxDepthOfCategoryTree(child);
                if (childMaxDepth > maxDepth) {
                    maxDepth = childMaxDepth;
                }
            }
        }
        return maxDepth;
    }
    

    public static CategoryEntity findCategoryFromTree(Long categoryId, CategoryEntity categoryTree){
        if(categoryId == null || categoryTree == null){
            return null;
        }

        if(categoryId.compareTo(Long.valueOf(categoryTree.getCategoryId())) == 0){
            return categoryTree;
        }

        List<CategoryEntity> childrenCategories = categoryTree.getChildren();
        if(CollectionUtils.isEmpty(childrenCategories)){
            return null;
        }

        for(CategoryEntity child : childrenCategories){
            CategoryEntity foundCategory = findCategoryFromTree(categoryId, child);
            if(foundCategory != null){
                return foundCategory;
            }
        }

        return null;
    }

    public static CategoryEntity findParentCategoryFromTree(Long categoryId, CategoryEntity categoryTree) {
        if(categoryId == null || categoryTree == null){
            return null;
        }

        List<CategoryEntity> childrenCategories = categoryTree.getChildren();
        if(CollectionUtils.isEmpty(childrenCategories)){
            return null;
        }

        for (CategoryEntity child : childrenCategories) {
            if(categoryId.compareTo(Long.valueOf(child.getCategoryId())) == 0) {
                return categoryTree;
            }

            CategoryEntity foundParentCategory = findParentCategoryFromTree(categoryId, child);
            if(foundParentCategory != null){
                return foundParentCategory;
            }
        }
        return null;
    }
}