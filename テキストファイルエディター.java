package txt_to_do;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Sample {
    static String pas = "C:\\\\pleiades\\\\2024-03\\\\workspace\\\\to-do list\\\\src\\\\txt_to_do\\\\text.txt"; // テキストファイルのパスを定義

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("何番を実行しますか？");
            System.out.println("1.タスクを追加");
            System.out.println("2.タスクを確認");
            System.out.println("3.タスクの全消");
            System.out.println("");

            int choice = new java.util.Scanner(System.in).nextInt(); // ユーザーから選択を受け取る

            switch (choice) {
                case 1:
                    add(); // addメソッドを呼び出してタスクを追加
                    show(); // showメソッドを呼び出してタスクを表示
                    break;
                case 2:
                    show(); // showメソッドを呼び出してタスクを表示
                    break;
                case 3:
                    del(); // delメソッドを呼び出して全タスクを削除
                    break;
                default:
            }
        }
    }

    public static void show() {
        Path path = Paths.get(pas); // ファイルのパスを取得
        try {
            List<String> lines = Files.readAllLines(path); // ファイルの全行を読み込む
            for (String line : lines) {
                System.out.println(line); // 各行を表示
            }
        } catch (IOException e) {
            e.printStackTrace(); // エラーを表示
        }
    }

    public static void add() throws IOException {
        try {
            FileWriter file = new FileWriter(pas, true); // 追記モードでファイルを開く
            PrintWriter pw = new PrintWriter(new BufferedWriter(file)); // BufferedWriterを使ってPrintWriterを作成
            System.out.println("内容を記入");
            String list = new java.util.Scanner(System.in).nextLine(); // ユーザーからタスクを入力
            pw.println(list); // ファイルにタスクを追加
            pw.close(); // PrintWriterを閉じる
        } catch (IOException e) {
            e.printStackTrace(); // エラーを表示
        }
    }

    public static void del() throws IOException {
        try {
            FileWriter file = new FileWriter(pas); // 上書きモードでファイルを開く
            PrintWriter pw = new PrintWriter(new BufferedWriter(file)); // BufferedWriterを使ってPrintWriterを作成
            pw.print(""); // ファイルの内容を空にする
            pw.close(); // PrintWriterを閉じる
        } catch (IOException e) {
            e.printStackTrace(); // エラーを表示
        }
    }
}
