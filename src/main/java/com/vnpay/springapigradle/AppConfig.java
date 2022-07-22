package com.vnpay.springapigradle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Value("${config.snowflake}")
    private String configSnow;

//    @Bean
//    public Snowflake genIds() {
//        long datacenter = Long.valueOf(configSnow.split(",")[0]);
//        long workerId = Long.valueOf(configSnow.split(",")[1]);
//        final Snowflake sf = new Snowflake(datacenter, workerId);
////        final Snowflake sf = new Snowflake(3, 4);
//        return sf;
//    }
    @Value("${app.db.schema}")
    private String db_schema;
    @Value("${app.db.url}")
    private String url;
    @Value("${app.db.username}")
    private String username;
    @Value("${app.db.password}")
    private String password;
    @Value("${app.db.type}")
    private String db_type;

    @Value("${app.db.driver}")
    private String driver;
    @Value("${app.db.minpool}")
    private int minpool;
    @Value("${app.db.maxpool}")
    private int maxpool;

    @Bean
    public DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMinimumIdle(minpool);
        ds.setMaximumPoolSize(maxpool);
        ds.setDataSourceClassName(db_type);
        ds.addDataSourceProperty("url", url);
        ds.addDataSourceProperty("user", username);
        ds.addDataSourceProperty("password", password);
        return ds;
    }
}
