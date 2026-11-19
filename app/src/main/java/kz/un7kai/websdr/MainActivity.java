package kz.un7kai.websdr;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.graphics.Color;
import android.view.View;

public class MainActivity extends Activity {
    WebView web;
    String[] bands = {"160 м (1.8 MHz)", "80 м (3.5 MHz)", "40 м (7 MHz)", "30 м (10 MHz)", "20 м (14 MHz)", "17 м (18 MHz)", "15 м (21 MHz)", "12 м (24 MHz)", "10 м (28 MHz)"};
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.rgb(245,248,255));
        TextView title = new TextView(this); title.setText("📻 UN7KAI WebSDR\nКоротковолновый приём"); title.setTextSize(22); title.setTextColor(Color.rgb(20,45,80)); title.setPadding(18,20,18,10); root.addView(title);
        TextView hint = new TextView(this); hint.setText("Выберите диапазон, затем откройте интернет-приёмник."); hint.setPadding(18,4,18,8); root.addView(hint);
        Spinner spinner = new Spinner(this); ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bands); spinner.setAdapter(adapter); root.addView(spinner);
        Button open = new Button(this); open.setText("Открыть WebSDR в браузере"); root.addView(open);
        Button directory = new Button(this); directory.setText("Каталог WebSDR-приёмников"); root.addView(directory);
        TextView note = new TextView(this); note.setText("Для прослушивания нужен интернет. Приём и доступность зависят от выбранного SDR-сервера."); note.setPadding(18,10,18,10); root.addView(note);
        web = new WebView(this); WebSettings settings=web.getSettings(); settings.setJavaScriptEnabled(true); settings.setDomStorageEnabled(true); settings.setMediaPlaybackRequiresUserGesture(false); web.setWebViewClient(new WebViewClient());
        open.setOnClickListener(v -> { int i=spinner.getSelectedItemPosition(); String[] f={"1800","3500","7000","10100","14000","18068","21000","24890","28000"}; web.loadUrl("https://www.websdr.org/"); });
        directory.setOnClickListener(v -> web.loadUrl("https://www.websdr.org/"));
        root.addView(web, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1)); setContentView(root);
    }
}
