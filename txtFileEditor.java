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
	static String pas = "C:\\\\pleiades\\\\2024-03\\\\workspace\\\\to-do list\\\\src\\\\txt_to_do\\\\text.txt";
    public static void main(String[] args) throws IOException {
    	while (true) {
			System.out.println("何番を実行しますか？");
			System.out.println("1.タスクを追加");
			System.out.println("2.タスクを確認");
			System.out.println("3.タスクの全消");
			System.out.println("");

			int choice = new java.util.Scanner(System.in).nextInt();

			switch (choice) {
			case 1:
				add();
				show();
				break;
			case 2:
				show();
				break;
			case 3:
				del();
			default:
			}
    	}
    }
    public static void show() {
    	Path path = Paths.get(pas);
        try {
            List<String> lines = Files.readAllLines(path);
            for(String line : lines){
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void add() throws IOException{
    	try {
    		FileWriter file = new FileWriter(pas,true);
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            System.out.println("内容を記入");
    		String list = new java.util.Scanner(System.in).nextLine();
            pw.println(list);
            pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public static void del() throws IOException{
    	try {
    		FileWriter file = new FileWriter(pas);
            PrintWriter pw = new PrintWriter(new BufferedWriter(file));
            pw.print("");
            pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}