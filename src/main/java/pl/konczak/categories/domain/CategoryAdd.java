package pl.konczak.categories.domain;

import javax.validation.constraints.NotNull;

public class CategoryAdd {

    private Long parentId;

    @NotNull
    private String name;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
