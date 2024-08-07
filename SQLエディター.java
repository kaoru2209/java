package sqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        while (true) {
            System.out.println("");
            System.out.println("SQLメニュー");
            System.out.println("======================");
            System.out.println("①値を追加");
            System.out.println("②データベースを表示");
            System.out.println("③値を削除");
            System.out.println("======================");
            System.out.println("① or ② or ③？");
            int choice = new java.util.Scanner(System.in).nextInt(); // ユーザーから選択を受け取る
            
            switch (choice) {
                case 1:
                    insert(); // insertメソッドを呼び出す
                    System.out.println("======================");
                    show(); // showメソッドを呼び出してデータベースの内容を表示
                    System.out.println("======================");
                    break;
                case 2:
                    System.out.println("======================");
                    show(); // showメソッドを呼び出してデータベースの内容を表示
                    System.out.println("======================");
                    break;
                case 3:
                    delete(); // deleteメソッドを呼び出す
                    System.out.println("======================");
                    show(); // showメソッドを呼び出してデータベースの内容を表示
                    System.out.println("======================");
                    break;
                default:
            }
        }
    }

    public static void insert() {
        final String URL = "jdbc:mysql://localhost/Jan"; // データベースのURL
        final String USER = "root"; // データベースのユーザー名
        final String PASS = ""; // データベースのパスワード
        final String SQL = "insert into test (id,name) VALUES (?,?)"; // データを挿入するSQL文
        
        System.out.println("IDを入力してください。");
        int a = new java.util.Scanner(System.in).nextInt(); // ユーザーからIDを入力
        System.out.println("NAMEを入力してください。");
        String b = new java.util.Scanner(System.in).nextLine(); // ユーザーから名前を入力

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) { // データベースに接続
            conn.setAutoCommit(false); // トランザクションを開始
            try (PreparedStatement ps = conn.prepareStatement(SQL)) { // SQL文を準備
                ps.setInt(1, a); // SQL文の1番目のパラメータにIDをセット
                ps.setString(2, b); // SQL文の2番目のパラメータに名前をセット
                ps.executeUpdate(); // SQL文を実行
                conn.commit(); // トランザクションをコミット
            } catch (Exception e) {
                conn.rollback(); // エラー発生時にロールバック
                System.out.println("rollback");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace(); // エラーを表示
        } finally {
            System.out.println("処理が完了しました");
            System.out.println("");
        }
    }

    public static void delete() {
        final String URL = "jdbc:mysql://localhost/Jan"; // データベースのURL
        final String USER = "root"; // データベースのユーザー名
        final String PASS = ""; // データベースのパスワード
        final String SQL = "delete from test where id = ?;"; // データを削除するSQL文
        
        System.out.println("IDを入力してください。");
        int a = new java.util.Scanner(System.in).nextInt(); // ユーザーからIDを入力

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) { // データベースに接続
            conn.setAutoCommit(false); // トランザクションを開始
            try (PreparedStatement ps = conn.prepareStatement(SQL)) { // SQL文を準備
                ps.setInt(1, a); // SQL文の1番目のパラメータにIDをセット
                ps.executeUpdate(); // SQL文を実行
                conn.commit(); // トランザクションをコミット
            } catch (Exception e) {
                conn.rollback(); // エラー発生時にロールバック
                System.out.println("rollback");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace(); // エラーを表示
        } finally {
            System.out.println("処理が完了しました");
            System.out.println("");
        }
    }

    public static void show() {
        // 変数の準備
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // SQL文の作成
        String sql = "SELECT * FROM test";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // JDBCドライバのロード
            con = DriverManager.getConnection("jdbc:mysql://localhost/Jan", "root", ""); // データベース接続
            stmt = con.prepareStatement(sql); // SQL実行準備
            rs = stmt.executeQuery(); // 実行結果取得
            // データがなくなるまで(rs.next()がfalseになるまで)繰り返す
            while (rs.next()) {
                String id1 = rs.getString("id");
                String name1 = rs.getString("name");
                System.out.println(id1 + ":" + name1); // 結果を表示
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバのロードでエラーが発生しました");
        } catch (SQLException e) {
            System.out.println("データベースへのアクセスでエラーが発生しました。");
        } finally {
            try {
                if (con != null) {
                    con.close(); // データベース接続を閉じる
                }
            } catch (SQLException e) {
                System.out.println("データベースへのアクセスでエラーが発生しました。");
            }
        }
    }
}
