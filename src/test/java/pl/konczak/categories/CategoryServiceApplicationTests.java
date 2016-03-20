package pl.konczak.categories;

import pl.konczak.categories.CategoryServiceApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CategoryServiceApplication.class)
@WebAppConfiguration
public class CategoryServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
