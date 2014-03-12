package jp.mixi.sample.res.value;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // boolean の取得
        boolean bool = getResources().getBoolean(R.bool.isCompany);

        // integer の取得
        int integer = getResources().getInteger(R.integer.DefaultPickaxe);

        // integer-array の取得
        int[] integers = getResources().getIntArray(R.array.Periods);

        // color の取得
        int color = getResources().getColor(R.color.Blue);

        // dimension の取得
        float titleDim = getResources().getDimension(R.dimen.TitleSize);

        // typed-array の取得
        TypedArray colors = getResources().obtainTypedArray(R.array.Colors);
        int color2 = colors.getColor(0, Color.BLACK);
        TypedArray drawables = getResources().obtainTypedArray(R.array.Drawables);
        Drawable drawable = drawables.getDrawable(0);
        TypedArray mixed = getResources().obtainTypedArray(R.array.MixedArray);
        Drawable drawable2 = mixed.getDrawable(0); // ここで違う型のものを取り出そうとすると実行時例外でクラッシュ
        int color3 = mixed.getColor(1, Color.BLACK);
    }
}