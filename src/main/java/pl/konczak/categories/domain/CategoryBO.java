package pl.konczak.categories.domain;

import org.apache.log4j.Logger;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.GraphDatabase;
import org.springframework.stereotype.Service;

@Service
public class CategoryBO {

    private static final Logger LOGGER = Logger.getLogger(CategoryBO.class);

    private final CategoryRepository categoryRepository;

    private final GraphDatabase graphDatabase;

    @Autowired
    public CategoryBO(CategoryRepository categoryRepository,
            GraphDatabase graphDatabase) {
        this.categoryRepository = categoryRepository;
        this.graphDatabase = graphDatabase;
    }

    public pl.konczak.categories.domain.CategorySnapshot add(String name) {
        Category category = new Category(name);

        category = categoryRepository.save(category);

        return new CategorySnapshot(category);

    }

    public pl.konczak.categories.domain.CategorySnapshot add(Long parentId, String name) {
        Category category = new Category(name);

        try (Transaction transaction = graphDatabase.beginTx()) {
            category = categoryRepository.save(category);

            Category parent = categoryRepository.findOne(parentId);

            parent.add(category);

            categoryRepository.save(parent);

            category = categoryRepository.findOne(category.getId());

            transaction.success();
        } catch (Exception e) {
            LOGGER.error("Failed to add Category for parent", e);
        }

        return new CategorySnapshot(category);
    }

}
