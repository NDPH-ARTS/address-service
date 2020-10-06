package uk.ac.ox.ctsu.arts.addressservice.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://13.68.155.73:5432/arts"); // localhost:5433
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("root"); // orange
        return dataSourceBuilder.build();
    }
}
