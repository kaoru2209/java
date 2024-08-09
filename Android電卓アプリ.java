package com.example.calcapp;

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

    private double operand1 = 0;
    private double operand2 = 0;
    private String currentOperation = ""; // "+", "-", "*", "/"
    private TextView txtFormula, txtResult;

    private String num = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) { // アクティビティが作成されるときに呼び出されるメソッド
        super.onCreate(savedInstanceState); // スーパークラスのonCreateメソッドを呼び出す
        setContentView(R.layout.activity_main); // レイアウトファイルactivity_mainを画面に設定する

        txtFormula = findViewById(R.id.txtFormula); // 数式を表示するテキストビューを取得
        txtResult = findViewById(R.id.txtResult); // 結果を表示するテキストビューを取得

        setupNumberButtons(); // 数字ボタンの設定を行うメソッドを呼び出す
        setupOperationButtons(); // 演算ボタンの設定を行うメソッドを呼び出す
        setupFunctionButtons(); // 機能ボタンの設定を行うメソッドを呼び出す
    }

    private void setupNumberButtons() { // 数字ボタンの設定を行うメソッド
        ArrayList<Integer> btnNum = new ArrayList<>(); // ボタンのIDを格納するリストを作成
        Collections.addAll(btnNum, R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9); // 数字ボタンのIDをリストに追加

        for (int id : btnNum) { // 各ボタンIDについてループ処理
            Button btn = findViewById(id); // ボタンをIDから取得
            btn.setOnClickListener(v -> { // ボタンがクリックされたときの処理を設定
                txtFormula.append(btn.getText().toString()); // 数式表示にボタンのテキストを追加
            });
        }
    }

    private void setupOperationButtons() { // 演算ボタンの設定を行うメソッド
        Button btnPlus = findViewById(R.id.btnPlus); // 足し算ボタンを取得
        Button btnMinus = findViewById(R.id.btnMinus); // 引き算ボタンを取得
        Button btnMulti = findViewById(R.id.btnMulti); // 掛け算ボタンを取得
        Button btnDivision = findViewById(R.id.btnDivision); // 割り算ボタンを取得


        btnPlus.setOnClickListener(v -> setOperation("+")); // 足し算ボタンがクリックされたときの処理を設定
        btnMinus.setOnClickListener(v -> setOperation("-")); // 引き算ボタンがクリックされたときの処理を設定
        btnMulti.setOnClickListener(v -> setOperation("*")); // 掛け算ボタンがクリックされたときの処理を設定
        btnDivision.setOnClickListener(v -> setOperation("/")); // 割り算ボタンがクリックされたときの処理を設定

        TextView savenum = findViewById(R.id.saveNum); //保持のview取得
        Button btnResult = findViewById(R.id.btnResult); // 結果ボタンを取得
        btnResult.setOnClickListener(v ->{
            num+=txtResult.getText().toString()+"\n"; //保持の変数に追加
            savenum.setText(num); //保持に結果の数字を保持
            txtFormula.setText(""); // 数式表示をクリア
            txtResult.setText(""); // 結果表示をクリア
            operand1 = 0; // operand1をリセット
            operand2 = 0; // operand2をリセット
            currentOperation = ""; // 現在の演算をリセット
        }); // 結果ボタンがクリックされたときの処理を設定
        Button resultClea = findViewById(R.id.btnResultClea);
        resultClea.setOnClickListener(v ->{
            savenum.setText("");
            num = "";
        });
    }

    private void setOperation(String operation) { // 演算を設定するメソッド
        if (!txtFormula.getText().toString().isEmpty()) { // 数式表示が空でない場合
            currentOperation = operation; // 現在の演算を設定
            calculateResult(); //計算関数へ
        }
    }

    private void calculateResult() { // 結果を計算するメソッド
        try {
            if (txtResult.getText().toString().isEmpty()) { // 数式表示が空の場合（1回目）
                operand1 = Integer.parseInt(txtFormula.getText().toString()); //数値を変数にセット
                txtResult.setText(txtFormula.getText().toString()); //入力の数値を結果にセット
                txtFormula.setText(""); // 数式表示をクリア
            }else{ //　２回目以降
                double result = 0; // 結果を格納する変数を初期化
                operand2  = Integer.parseInt(txtFormula.getText().toString()); //　数値を変数２にセット
                switch (currentOperation) { // 現在の演算に応じて処理を分岐
                    case "+":
                        result = operand1 + operand2; // 足し算の結果を計算
                        break;
                    case "-":
                        result = operand1 - operand2; // 引き算の結果を計算
                        break;
                    case "*":
                        result = operand1 * operand2; // 掛け算の結果を計算
                        break;
                    case "/":
                        if (operand2 != 0) { // 割り算の除数がゼロでないことを確認
                            result = operand1 / operand2; // 割り算の結果を計算
                        } else {
                            txtResult.setText("ゼロ除算エラー"); // 除数がゼロの場合のエラーメッセージを表示
                            return; // メソッドを終了
                        }
                        break;
                    default:
                        result = operand2; // デフォルトではoperand2を結果として設定
                        break;
                }
                operand1 = result; // 結果をoperand1に格納
                txtResult.setText(String.valueOf(result)); // 結果をテキストビューに表示
                txtFormula.setText(""); // 数式表示をクリア
            }
        } catch (NumberFormatException e) { // 数値変換に失敗した場合の例外処理

        }
    }

    private void setupFunctionButtons() { // 機能ボタンの設定を行うメソッド
        Button btnClear = findViewById(R.id.btnClear); // クリアボタンを取得
        Button btnAllClear = findViewById(R.id.btnAllClear); // 全クリアボタンを取得

        btnClear.setOnClickListener(v -> { // クリアボタンがクリックされたときの処理を設定
            String text = txtFormula.getText().toString(); // 数式表示のテキストを取得
            if (text.length() > 0) { // テキストが空でない場合
                txtFormula.setText(text.substring(0, text.length() - 1)); // 最後の文字を削除
            }
        });

        btnAllClear.setOnClickListener(v -> { // 全クリアボタンがクリックされたときの処理を設定
            txtFormula.setText(""); // 数式表示をクリア
            txtResult.setText(""); // 結果表示をクリア
            operand1 = 0; // operand1をリセット
            operand2 = 0; // operand2をリセット
            currentOperation = ""; // 現在の演算をリセット
        });
    }
}
