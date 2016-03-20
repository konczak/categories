package pl.konczak.categories.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Category {

    @GraphId
    Long id;

    String name;

    @RelatedTo(direction = Direction.OUTGOING)
    Category parent;

    @RelatedTo(direction = Direction.INCOMING)
    Set<Category> subCategories;

    private Category() {
    }

    Category(String name) {
        this.name = name;
    }

    void add(Category child) {
        if (this.subCategories == null) {
            this.subCategories = new HashSet<>();
        }
        this.subCategories.add(child);
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    Category getParent() {
        return parent;
    }

    Set<Category> getSubCategories() {
        return Collections.unmodifiableSet(subCategories);
    }

}
