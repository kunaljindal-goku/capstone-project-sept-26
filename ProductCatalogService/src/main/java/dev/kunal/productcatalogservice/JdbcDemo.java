//package dev.kunal.productcatalogservice;
//
//import dev.kunal.productcatalogservice.model.Product;
//
//import java.sql.*;
//
//public class JdbcDemo {
//
//    public static void main(String[] args) throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/productcatalogservice";
//        try (Connection con = DriverManager.getConnection(url, "root", System.getenv("DB_PASSWORD"));
//             PreparedStatement ps = con.prepareStatement("SELECT id, name, price FROM demo_product WHERE id = ?")) {
//            ps.setLong(1, 2);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Product product = new Product();
//                product.setId(rs.getLong("id"));
//                product.setName(rs.getString("name"));
//                product.setPrice(rs.getDouble("price"));
//                System.out.println(product.getName() + " costs " + product.getPrice());
//            }
//        }
//    }
//}
