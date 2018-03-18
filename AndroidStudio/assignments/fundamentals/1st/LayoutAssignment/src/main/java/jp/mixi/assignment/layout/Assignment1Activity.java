package jp.mixi.assignment.layout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import jp.mixi.assignment.layout.CalcModel.OPERATOR;

@SuppressLint("UseSparseArrays")
public class Assignment1Activity extends AppCompatActivity {

    /**
     * 結果表示用フォーマット
     */
    private static final NumberFormat DISPLAY_FORMAT = new DecimalFormat("0.########");
    private static final Map<Integer, Integer> mNumberMap;
    private static final Map<Integer, OPERATOR> mOperatorMap;

    static {
        mNumberMap = new HashMap<>();
        mNumberMap.put(R.id.button0, 0);
        mNumberMap.put(R.id.button1, 1);
        mNumberMap.put(R.id.button2, 2);
        mNumberMap.put(R.id.button3, 3);
        mNumberMap.put(R.id.button4, 4);
        mNumberMap.put(R.id.button5, 5);
        mNumberMap.put(R.id.button6, 6);
        mNumberMap.put(R.id.button7, 7);
        mNumberMap.put(R.id.button8, 8);
        mNumberMap.put(R.id.button9, 9);

        mOperatorMap = new HashMap<>();
        mOperatorMap.put(R.id.plus, OPERATOR.PLUS);
        mOperatorMap.put(R.id.minus, OPERATOR.MINUS);
        mOperatorMap.put(R.id.clear, OPERATOR.CLEAR);
        mOperatorMap.put(R.id.times, OPERATOR.TIMES);
        mOperatorMap.put(R.id.divide, OPERATOR.DIVIDE);
        mOperatorMap.put(R.id.calc, OPERATOR.EQUAL);
    }

    private CalcModel calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment1);
        calc = new CalcModel();
        setCalcResult(calc.getNowValue());
        setupOnClickListener();
    }

    // 各ボタンに対して押された時の動作をリンクさせる
    private void setupOnClickListener() {
        for (Integer key : mNumberMap.keySet()) {
            findViewById(key).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    onClickNumber(v);
                }
            });
        }
        for (Integer key : mOperatorMap.keySet()) {
            findViewById(key).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    onClickOperator(v);
                }
            });
        }
        findViewById(R.id.dot).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                calc.putDot();
            }
        });
    }

    private void onClickNumber(View v) {
        calc.putNumber(mNumberMap.get(v.getId()));
        setCalcResult(calc.getNowValue());
    }

    private void onClickOperator(View v) {
        calc.putOperator(mOperatorMap.get(v.getId()));
        setCalcResult(calc.getNowValue());
    }

    // 結果を画面表示
    private void setCalcResult(double result) {
        TextView v = (TextView) findViewById(R.id.result);
        v.setText(DISPLAY_FORMAT.format(result));
    }
}
