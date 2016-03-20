package pl.konczak.categories.domain;

public class CategorySnapshot {

    private final Long categoryId;

    private final String name;

    public CategorySnapshot(Category category) {
        this.categoryId = category.id;
        this.name = category.name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

}
