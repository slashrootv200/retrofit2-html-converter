package com.github.slashrootv200.retrofithtmlconverter.demo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.jsoup.nodes.Document;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoActivity extends AppCompatActivity {
  private final String TAG = "htmlconverter";
  private EditText mEnterUrlEt;
  private TextView mResultTv;
  private ProgressBar mProgressBar;

  private void dLog(String message) {
    Log.d(TAG, message);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);
    mEnterUrlEt = (EditText) findViewById(R.id.enter_url);
    mResultTv = (TextView) findViewById(R.id.result);
    mProgressBar = (ProgressBar) findViewById(R.id.progress);
    mEnterUrlEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override
      public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if (actionId == EditorInfo.IME_ACTION_GO) {
          String url = mEnterUrlEt.getText().toString().trim();
          try {
            Uri uri = Uri.parse(url);
            if ("http".equals(uri.getScheme()) || "https".equals(uri.getScheme())) {
              // valid
              mResultTv.setVisibility(View.GONE);
              mProgressBar.setVisibility(View.VISIBLE);
              a(url);
            }
          } catch (Exception e) {
            Log.e(TAG, e.getMessage());
          }
          return true;
        }
        return false;
      }
    });
  }

  private void a(String url) {
    final String base = url;
    if (!url.endsWith("/")) {
      url = url + "/";
    }
    FetchHtmlService service = new FetchHtmlService.Instance(url).getService();
    Call<Document> call = service.fetch();
    call.enqueue(new Callback<Document>() {
      @Override
      public void onResponse(Call<Document> call, Response<Document> response) {
        Document document = response.body();
        document.setBaseUri(base);
        String html = document.html();
        dLog("document=" + html);
        mProgressBar.setVisibility(View.GONE);
        mResultTv.setVisibility(View.VISIBLE);
        mResultTv.setText(html);
      }

      @Override
      public void onFailure(Call<Document> call, Throwable t) {
        t.printStackTrace();
        mProgressBar.setVisibility(View.GONE);
        mResultTv.setVisibility(View.VISIBLE);
        mResultTv.setText("Error:" + t.getMessage());
        dLog("t=" + t.getMessage());
      }
    });
  }
}
