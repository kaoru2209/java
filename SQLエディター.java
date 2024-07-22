package sqltest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("");
			System.out.println("SQLメニュー");
			System.out.println("======================");
			System.out.println("①値を追加");
			System.out.println("②データベースを表示");
			System.out.println("③値を削除");
			System.out.println("======================");
			System.out.println("① or ② or ③？");
			int choice = new java.util.Scanner(System.in).nextInt();
			
			switch(choice){
				case 1:
					insert();
					System.out.println("======================");
					show();
					System.out.println("======================");
					break;
				case 2:
					System.out.println("======================");
					show();
					System.out.println("======================");
					break;
				case 3:
					delete();
					System.out.println("======================");
					show();
					System.out.println("======================");
					break;
				default:
			}
		}
		
    }
	public static void insert() {
		 final String URL 
	        = "jdbc:mysql://localhost/Jan";
	        final String USER = "root";
	        final String PASS = "";
	        final String SQL = "insert into test (id,name) VALUES (?,?)";
	        System.out.println("IDを入力してください。");
			int a = new java.util.Scanner(System.in).nextInt();
			System.out.println("NAMEを入力してください。");
			String b = new java.util.Scanner(System.in).nextLine();
	        try(Connection conn = 
	                DriverManager.getConnection(URL, USER, PASS)){
	            conn.setAutoCommit(false);
	            try(PreparedStatement ps = conn.prepareStatement(SQL)){
	                ps.setInt(1,a);
	                ps.setString(2,b);
	                ps.executeUpdate();
	                conn.commit();
	            } catch (Exception e) {
	                conn.rollback();
	                System.out.println("rollback");
	                throw e;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            System.out.println("処理が完了しました");
	            System.out.println("");
	        }
	}
	public static void delete() {
		final String URL 
        = "jdbc:mysql://localhost/Jan";
        final String USER = "root";
        final String PASS = "";
        final String SQL = "delete from test where id = ?;";
        System.out.println("IDを入力してください。");
		int a = new java.util.Scanner(System.in).nextInt();
        try(Connection conn = 
                DriverManager.getConnection(URL, USER, PASS)){
            conn.setAutoCommit(false);
            try(PreparedStatement ps = conn.prepareStatement(SQL)){
                ps.setInt(1,a);
                ps.executeUpdate();
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                System.out.println("rollback");
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
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
            // JDBCドライバのロード
            Class.forName("com.mysql.cj.jdbc.Driver");
            // データベース接続
            con = DriverManager.getConnection("jdbc:mysql://localhost/Jan", "root", "");
            // SQL実行準備
            stmt = con.prepareStatement(sql);
            // 実行結果取得
            rs = stmt.executeQuery();
            // データがなくなるまで(rs.next()がfalseになるまで)繰り返す
            while (rs.next()) {
                String id1 = rs.getString("id");
                String name1 = rs.getString("name");
                System.out.println(id1 + ":" + name1);
            }
        }catch (ClassNotFoundException e) {
            System.out.println("JDBCドライバのロードでエラーが発生しました");
        }catch (SQLException e) {
            System.out.println("データベースへのアクセスでエラーが発生しました。");
        }finally{
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("データベースへのアクセスでエラーが発生しました。");
            }
        }
	}
}
