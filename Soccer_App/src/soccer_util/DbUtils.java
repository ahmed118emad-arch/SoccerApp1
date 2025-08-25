package soccer_util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DbUtils {

    // طباعة الـ ResultSet للـ debug بسهولة
    public static void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + ": " + rs.getObject(i) + "\t");
            }
            System.out.println();
        }
    }

    // فحص إذا كانت القيمة null
    public static boolean isNull(Object obj) {
        return obj == null;
    }
}
