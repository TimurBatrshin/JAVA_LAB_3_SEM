//package ru.itis.javalab.repositories;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class SimpleJdbcTemplate {
//    private DataSource dataSource;
//
//    public SimpleJdbcTemplate(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public <T>List<T> query(String sql, RowMapper<T> rowMapper, Object ... args){
//        Connection connection=null;
//        PreparedStatement statement=null;
//        ResultSet resultSet=null;
//        List<T> entities= new ArrayList<>();
//        try {
//            connection= dataSource.getConnection();
//            statement=connection.prepareStatement(sql);
//            int pos = 1;
//            if (args.length>0) {
//                for (Object arg : args) {
//                    statement.setObject(pos, arg);
//                    pos++;
//                }
//            }
//            resultSet=statement.executeQuery();
//
//            while (resultSet.next()){
//                entities.add(rowMapper.mapRow(resultSet));
//            }
//
//            return entities;
//        } catch (SQLException e) {
//            throw new IllegalStateException(e);
//        }finally {
//            if (resultSet!=null){
//                try{
//                    resultSet.close();
//                } catch (SQLException ignore){}
//            }
//            if (statement != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ignore) {
//                }
//            }
//            if (connection != null){
//                try {
//                    connection.close();
//                }catch (SQLException ignore){}
//            }
//        }
//    }
//}
