package pl.konczak.categories.domain;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CategoryRepository
        extends GraphRepository<Category> {
//Person.findBoss=start p=node({0}) match (p)<-[:BOSS]-(boss) return boss

    @Query(value = "MATCH category WHERE NOT (category)-[:parent]->() return category")
    List<Category> findAllByParentIsNull();
}
