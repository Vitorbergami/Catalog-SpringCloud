package one.digitalinnovation.experts.productcatalog.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackageClasses = "one.digitalinnovation.experts.productcatalog.repository")
@Configuration


public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient(){
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectTo ("localhost:8080", "localhost:9300")
                .build();
                return RestClients.create(clientConfiguration).rest();

    }

    @Bean
    @Override

    public EntityMapper entityMapper(){
        ElasticsearchEntityMapper entityMapper = new ElastitcsearchEntityMapper(elasticsearchMappingContext(),
                new defaultConversionService());
        entityMapper.setConversions(elasticsearchCustomConversions());
        return entityMapper;
    }

}
