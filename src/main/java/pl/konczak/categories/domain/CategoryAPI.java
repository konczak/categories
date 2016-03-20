package pl.konczak.categories.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/category")
@RestController
public class CategoryAPI {

    private static final Logger LOGGER = Logger.getLogger(CategoryAPI.class);

    private final CategoryBO categoryBO;

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryAPI(CategoryBO categoryBO, CategoryRepository categoryRepository) {
        this.categoryBO = categoryBO;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<CategorySnapshot>> top() {
        LOGGER.info("Hello from top");

        List<pl.konczak.categories.domain.Category> categories = categoryRepository.findAllByParentIsNull();
        List<CategorySnapshot> list = categories.stream()
                .map(CategorySnapshot::new)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok(list);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/{categoryId}")
    public HttpEntity<CategorySnapshot> get(@PathVariable Long categoryId) {
        LOGGER.info("Hello from get");

        Category category = categoryRepository.findOne(categoryId);

        if (category == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity
                .ok(new CategorySnapshot(category));
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<CategorySnapshot> add(@Valid @RequestBody CategoryAdd categoryAdd) {
        CategorySnapshot categorySnapshot;
        if (categoryAdd.getParentId() == null) {
            categorySnapshot = categoryBO.add(categoryAdd.getName());
        } else {
            categorySnapshot = categoryBO.add(categoryAdd.getParentId(), categoryAdd.getName());
        }

        return new ResponseEntity(categorySnapshot, HttpStatus.CREATED);
    }
}
