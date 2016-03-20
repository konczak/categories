package pl.konczak.categories.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@Configuration
@EnableNeo4jRepositories(value = {"pl.konczak.categories.domain"})
public class Neo4jConfig
        extends Neo4jConfiguration {

    public Neo4jConfig() {
        setBasePackage("pl.konczak.categories");
    }

    @Bean
    GraphDatabaseService graphDatabaseService() {
        return new GraphDatabaseFactory()
                .newEmbeddedDatabase("accessingdataneo4j.db");
    }

}
