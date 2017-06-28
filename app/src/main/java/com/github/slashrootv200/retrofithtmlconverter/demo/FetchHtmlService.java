package com.github.slashrootv200.retrofithtmlconverter.demo;

import com.github.slashrootv200.retrofithtmlconverter.HtmlConverterFactory;
import org.jsoup.nodes.Document;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface FetchHtmlService {
  @GET(".")
  Call<Document> fetch();

  class Instance {
    private FetchHtmlService service;

    public Instance(String baseUrl) {
      service = new Retrofit.Builder().baseUrl(baseUrl)
          .addConverterFactory(HtmlConverterFactory.create(baseUrl))
          .build()
          .create(FetchHtmlService.class);
    }

    public FetchHtmlService getService() {
      return service;
    }
  }
}
