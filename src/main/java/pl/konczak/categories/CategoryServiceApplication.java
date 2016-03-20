package pl.konczak.categories;

import java.io.File;

import org.neo4j.io.fs.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CategoryServiceApplication {

    public static void main(String[] args)
            throws Exception {
        FileUtils.deleteRecursively(new File("accessingdataneo4j.db"));

        SpringApplication.run(CategoryServiceApplication.class, args);
    }
}
