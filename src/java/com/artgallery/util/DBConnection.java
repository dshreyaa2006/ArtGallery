package com.artgallery.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        // Railway MySQL Public Network credentials (your exact values)
        String host = "shuttle.proxy.rlwy.net";
        String port = "39052";
        String db   = "railway";
        String user = "root";
        String pass = "kJECCuRzRdAtFCykSamdCjDODBLWQrfL";

        // JDBC URL
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db +
                     "?useSSL=false&serverTimezone=UTC";

        System.out.println("🔗 Connecting to: " + url);

        // Return connection
        return DriverManager.getConnection(url, user, pass);
    }
}
