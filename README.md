# retrofit2-html-converter

[ ![Download](https://api.bintray.com/packages/slashroot-v200/retrofit-html-converter/retrofit-html-converter/images/download.svg) ](https://bintray.com/slashroot-v200/retrofit-html-converter/retrofit-html-converter/_latestVersion)

## Download

### maven
```xml
<dependency>
  <groupId>com.github.slashrootv200</groupId>
  <artifactId>retrofit-html-converter</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
```

### gradle
```groovy
compile 'com.github.slashrootv200:retrofit-html-converter:0.0.1'
```
## Usage
Add converter to the `RetrofitBuilder`
```java
Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
  .addConverterFactory(HtmlConverterFactory.create(baseUrl))
  .build();
```

Get `Document` as a response object
```java
import org.jsoup.nodes.Document;

call.enqueue(new Callback<Document>() {
  @Override
  public void onResponse(Call<Document> call, Response<Document> response) {
    Document document = response.body();
    document.setBaseUri(base);
    String html = document.html();
  }

  @Override
  public void onFailure(Call<Document> call, Throwable t) {
    t.printStackTrace();
   }
});
```
