package homework;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Main {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/java_lab_1");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("Timur007");
        hikariConfig.setMaximumPoolSize(20);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        EntityManager entityManager = new EntityManager(dataSource);
    }
}
