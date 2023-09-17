package com.rookienichezero.algorithm.leetcodetrain.tree.helper;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookienichezero.algorithm.leetcodetrain.tree.entity.CategoryEntity;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryHelperTest {


    private static final String CATEGORY_TREE = "{\n" +
            "    \"parentCategoryId\": null,\n" +
            "    \"categoryId\": -1,\n" +
            "    \"categoryName\": \"ROOT\",\n" +
            "    \"order\": 0,\n" +
            "    \"level\": \"0\",\n" +
            "    \"children\": [\n" +
            "        {\n" +
            "            \"parentCategoryId\": -1,\n" +
            "            \"categoryId\": 10,\n" +
            "            \"categoryName\": \"category 10\",\n" +
            "            \"order\": 10,\n" +
            "            \"level\": \"1\",\n" +
            "            \"children\": []\n" +
            "        },\n" +
            "        {\n" +
            "            \"parentCategoryId\": -1,\n" +
            "            \"categoryId\": 20,\n" +
            "            \"categoryName\": \"category 20\",\n" +
            "            \"order\": 10,\n" +
            "            \"level\": \"1\",\n" +
            "            \"children\": [\n" +
            "                {\n" +
            "                    \"parentCategoryId\": 20,\n" +
            "                    \"categoryId\": 100,\n" +
            "                    \"categoryName\": \"category 100\",\n" +
            "                    \"order\": 100,\n" +
            "                    \"level\": \"2\",\n" +
            "                    \"children\": [\n" +
            "                        {\n" +
            "                            \"parentCategoryId\": 100,\n" +
            "                            \"categoryId\": 1000,\n" +
            "                            \"categoryName\": \"category 1000\",\n" +
            "                            \"order\": 1000,\n" +
            "                            \"level\": \"3\",\n" +
            "                            \"children\": []\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";


    private static final String CATEGORY_LIST = "[\n" +
            "    {\n" +
            "        \"parentCategoryId\": null,\n" +
            "        \"categoryId\": -1,\n" +
            "        \"categoryName\": \"ROOT\",\n" +
            "        \"order\": 0,\n" +
            "        \"level\": \"0\",\n" +
            "        \"children\": []\n" +
            "    },\n" +
            "    {\n" +
            "        \"parentCategoryId\": -1,\n" +
            "        \"categoryId\": 10,\n" +
            "        \"categoryName\": \"category 10\",\n" +
            "        \"order\": 10,\n" +
            "        \"level\": \"1\",\n" +
            "        \"children\": []\n" +
            "    },\n" +
            "    {\n" +
            "        \"parentCategoryId\": -1,\n" +
            "        \"categoryId\": 20,\n" +
            "        \"categoryName\": \"category 20\",\n" +
            "        \"order\": 10,\n" +
            "        \"level\": \"1\",\n" +
            "        \"children\": []\n" +
            "    },\n" +
            "    {\n" +
            "        \"parentCategoryId\": 20,\n" +
            "        \"categoryId\": 100,\n" +
            "        \"categoryName\": \"category 100\",\n" +
            "        \"order\": 100,\n" +
            "        \"level\": \"2\",\n" +
            "        \"children\": []\n" +
            "    },\n" +
            "    {\n" +
            "        \"parentCategoryId\": 100,\n" +
            "        \"categoryId\": 1000,\n" +
            "        \"categoryName\": \"category 1000\",\n" +
            "        \"order\": 1000,\n" +
            "        \"level\": \"3\",\n" +
            "        \"children\": []\n" +
            "    }\n" +
            "]";

    @Test
    public void deserializeTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        CategoryEntity categoryEntity = mapper.readValue(CATEGORY_TREE, CategoryEntity.class);

        assertNotNull(categoryEntity);
        assertEquals(CategoryHelper.ROOT_CATEGORY_ID, categoryEntity.getCategoryId());
        assertNotNull(categoryEntity.getChildren());

        assertNotNull(categoryEntity.getChildren().get(0));
        assertNotNull(categoryEntity.getChildren().get(0).getChildren());
        assertNotNull(categoryEntity.getChildren().get(1).getChildren());
        assertNotNull(categoryEntity.getChildren().get(1).getChildren().get(0));
        assertNotNull(categoryEntity.getChildren().get(1).getChildren().get(0).getChildren());


        CategoryEntity[] categoryEntities = mapper.readValue(CATEGORY_LIST, CategoryEntity[].class);
        assertNotNull(categoryEntities);
        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntities);
        assertNotNull(categoryEntityList.get(0));
    }


    @Test
    public void buildCategoryTreeTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        CategoryEntity[] categoryEntities = mapper.readValue(CATEGORY_LIST, CategoryEntity[].class);
        assertNotNull(categoryEntities);
        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntities);
        assertNotNull(categoryEntityList.get(0));

        CategoryEntity rootCategory = CategoryHelper.buildCategoryTreeWithRoot(categoryEntityList);
        assertNotNull(rootCategory);
    }

    @Test
    public void getMaxDepthOfCategoryTreeTest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        CategoryEntity[] categoryEntities = mapper.readValue(CATEGORY_LIST, CategoryEntity[].class);
        assertNotNull(categoryEntities);
        List<CategoryEntity> categoryEntityList = Arrays.asList(categoryEntities);
        assertNotNull(categoryEntityList.get(0));

        CategoryEntity rootCategory = CategoryHelper.buildCategoryTreeWithRoot(categoryEntityList);
        assertNotNull(rootCategory);


        int maxDepthOfCategoryTree = CategoryHelper.getMaxDepthOfCategoryTree(rootCategory);
        assertEquals(3, maxDepthOfCategoryTree);
    }

}