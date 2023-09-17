package com.rookienichezero.algorithm.leetcodetrain.tree.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL )
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryEntity {

    private static final long serialVersionUID = 1L;

    @JsonProperty("parentCategoryId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long parentCategoryId = null;

    @JsonProperty("categoryId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long categoryId = null;

    @JsonProperty("categoryName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String categoryName = null;

    @JsonProperty("order")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer order = null;

    @JsonProperty("level")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer level = null;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CategoryEntity> children = null;

    public List<CategoryEntity> getChildren() {
        if(children == null){
            children = new ArrayList<>();
        }
        return children;
    }
}