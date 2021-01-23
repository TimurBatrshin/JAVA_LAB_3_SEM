package homework;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EntityManager {
    private SimpleJdbcInsert simpleJdbcInsert;
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
    }

    //создание таблицы
    public <T> void createTable(String tableName, Class<T> entityClass) {
        Map<String, String> columns = getColumns(entityClass);
        StringBuilder str = new StringBuilder();
        str.append("create table")
                .append(tableName)
                .append("(")
                .append(createData(columns))
                .append(");");
        jdbcTemplate.execute(str.toString());
    }

    private StringBuilder createData(Map<String, String> map) {
        StringBuilder sqlCreate = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sqlCreate.append(entry.getKey()).append(" ");
            String value = entry.getValue();
            if (value.equals("boolean") || value.equals("Boolean")) {
                sqlCreate.append("boolean,");
            } else if (value.equals("char") || value.equals("Character")) {
                sqlCreate.append("char(20),");
            } else if (value.equals("Integer") || value.equals("int")) {
                sqlCreate.append("integer,");
            } else if (value.equals("Long") || value.equals("long")) {
                sqlCreate.append("bigint,");
            } else if (value.equals("double") || value.equals("Double")) {
                sqlCreate.append("DOUBLE PRECISION,");
            }
        }
        sqlCreate.deleteCharAt(sqlCreate.length() - 1);
        return sqlCreate;
    }


    private <T> HashMap<String, String> getColumns(Class<T> entityClass) {
        HashMap<String, String> columns = new HashMap<>();
        Class<?> aClass = entityClass;
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            String type = field.getType().getSimpleName();
            String[] val = type.split("\\.");
            columns.put(name, val[val.length - 1]);
        }
        return columns;
    }

    // сканируем его поля
    // сканируем значения этих полей
    // генерируем insert into
    public void saveData(String tableName, Object entity) throws IllegalAccessException {
        HashMap<String, Object> map = getColumnsAndValues(entity);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("insert into")
                .append(tableName + "(")
                .append(getColumns(map.keySet()))
                .append("VALUES (" + getValues(map.values().size()))
                .append(")");
        jdbcTemplate.execute(stringBuilder.toString());
    }

    private String getValues(Integer val) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < val; i++) {
            stringBuilder.append("?").append(",");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    private String getColumns(Set<String> set) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : set) {
            stringBuilder.append(str).append(",");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    private HashMap<String, Object> getColumnsAndValues(Object entity) throws IllegalAccessException {
        HashMap<String, Object> map = new HashMap<>();
        Class<?> classOfEntity = entity.getClass();
        Field[] fields = classOfEntity.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object val = field.get(entity);
            map.put(name, val);
        }
        return map;
    }

    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) throws IllegalAccessException, InstantiationException {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from")
                .append(tableName)
                .append("where id=")
                .append(idValue);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSet metaDataSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql.toString());
            resultSet = statement.executeQuery();
            DatabaseMetaData metaData = connection.getMetaData();
            //поля из таблицы
            metaDataSet = metaData.getColumns(null, null, tableName, "%");
            //поля из класса
            Map<String, String> map = getColumns(resultType);
            //создаем экземпляр класса
            T object = resultType.newInstance();

            while (metaDataSet.next()) {
                String column = metaDataSet.getString("COLUMN_NAME");
                String type = metaDataSet.getString("TYPE_NAME");
                if (map.containsKey(column)) {
                    Field field = resultType.getDeclaredField(column);
                    if (type.toLowerCase().equals("bigint") || type.toLowerCase().equals("numeric")) {
                        field.setInt(object, resultSet.getInt(column));
                    } else if (type.toLowerCase().equals("text") || type.toLowerCase().equals("char")
                            || type.toLowerCase().equals("varchar")) {
                        field.set(object, resultSet.getString(column));
                    } else if (type.toLowerCase().equals("boolean")) {
                        field.setBoolean(object, resultSet.getBoolean(column));
                    }
                }
            }
            return object;
        } catch (SQLException | NoSuchFieldException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignore) {
                }
            }
            if (statement != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                }
            }

        }
    }

}
