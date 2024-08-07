import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static int a = 0; // 静的変数aを宣言
    public static int b = 0; // 静的変数bを宣言

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // アクティビティの初期化
        setContentView(R.layout.activity_main); // レイアウトを設定

        ArrayList<Integer> btnNum = new ArrayList<>(); // 数字ボタンのIDを保持するリストを作成
        Collections.addAll(btnNum, R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9); // リストに数字ボタンのIDを追加

        TextView txtFormula = findViewById(R.id.txtFormula); // 数式を表示するTextViewを取得
        TextView txtResult = findViewById(R.id.txtResult); // 計算結果を表示するTextViewを取得

        for (int i = 0; i < btnNum.size(); i++) { // 数字ボタンそれぞれにリスナーを設定
            Button btn = findViewById(btnNum.get(i)); // 各ボタンを取得
            btn.setOnClickListener(new View.OnClickListener() { // ボタンがクリックされたときの動作を設定
                @Override
                public void onClick(View view) {
                    txtFormula.setText(txtFormula.getText().toString() + btn.getText().toString()); // ボタンの数字を数式に追加
                }
            });
        }

        Button btnPlus = findViewById(R.id.btnPlus); // プラスボタンを取得
        btnPlus.setOnClickListener(new View.OnClickListener() { // プラスボタンがクリックされたときの動作を設定
            @Override
            public void onClick(View view) {
                try {
                    if (!txtFormula.getText().toString().equals("")) { // 数式が空でない場合
                        if (a != 0) { // 変数aが0でない場合
                            b = Integer.parseInt(txtFormula.getText().toString()); // 数式の値をbに代入
                            int c = a + b; // aとbを足した結果をcに代入
                            txtResult.setText(String.valueOf(c)); // 結果を表示
                            txtFormula.setText(""); // 数式をクリア
                            a = a + b; // aに新しい値を代入
                        } else {
                            a = Integer.parseInt(txtFormula.getText().toString()); // 数式の値をaに代入
                            txtFormula.setText(""); // 数式をクリア
                            txtResult.setText(String.valueOf(a)); // aの値を表示
                        }
                    }
                } catch (NumberFormatException e) {
                    txtResult.setText("値が多すぎます。"); // 数値変換エラー時のメッセージを表示
                }
            }
        });

        Button btnResult = findViewById(R.id.btnResult); // イコールボタンを取得
        btnResult.setOnClickListener(new View.OnClickListener() { // イコールボタンがクリックされたときの動作を設定
            @Override
            public void onClick(View view) {
                try {
                    b = Integer.parseInt(txtFormula.getText().toString()); // 数式の値をbに代入
                    int c = a + b; // aとbを足した結果をcに代入
                    txtResult.setText(String.valueOf(c)); // 結果を表示
                    txtFormula.setText(""); // 数式をクリア
                } catch (NumberFormatException e) {
                    txtResult.setText("値が多すぎます。"); // 数値変換エラー時のメッセージを表示
                }
            }
        });

        Button btnClear = findViewById(R.id.btnClear); // クリアボタンを取得
        btnClear.setOnClickListener(new View.OnClickListener() { // クリアボタンがクリックされたときの動作を設定
            @Override
            public void onClick(View view) {
                ArrayList<Character> text = new ArrayList<>(); // 数式の文字を保持するリストを作成
                String numbers = txtFormula.getText().toString(); // 数式の文字列を取得
                for (Character c : numbers.toCharArray()) {
                    text.add(c); // 数式の文字をリストに追加
                }
                if (!text.isEmpty()) {
                    text.remove(text.size() - 1); // リストの最後の文字を削除
                }
                StringBuilder sb = new StringBuilder(); // StringBuilderを作成
                for (char c : text) {
                    sb.append(c); // リストの文字を結合
                }
                txtFormula.setText(sb.toString()); // 数式を更新
            }
        });

        Button btnAllClear = findViewById(R.id.btnAllClear); // オールクリアボタンを取得
        btnAllClear.setOnClickListener(new View.OnClickListener() { // オールクリアボタンがクリックされたときの動作を設定
            @Override
            public void onClick(View view) {
                txtFormula.setText(""); // 数式をクリア
                txtResult.setText(""); // 結果をクリア
                a = 0; // 変数aをリセット
                b = 0; // 変数bをリセット
            }
        });
    }
}
