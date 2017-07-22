# retrofit2-html-converter

[ ![Download](https://api.bintray.com/packages/slashroot-v200/retrofit-html-converter/retrofit-html-converter/images/download.svg) ](https://bintray.com/slashroot-v200/retrofit-html-converter/retrofit-html-converter/_latestVersion)

## Download

### maven
```xml
<dependency>
  <groupId>com.github.slashrootv200</groupId>
  <artifactId>retrofit-html-converter</artifactId>
  <version>0.0.2</version>
  <type>pom</type>
</dependency>
```

### gradle
```groovy
compile 'com.github.slashrootv200:retrofit-html-converter:0.0.2'
```
## Usage
Service
```java
public interface CollectionsJavaDocWebPageService {
  @GET("javase/7/docs/api/java/util/Collections.html")
  Call<Document> fetch();
}
```
Add converter to the `RetrofitBuilder`
```java
Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
  .addConverterFactory(HtmlConverterFactory.create(baseUrl))
  .build();
CollectionsJavaDocWebPageService service
                      = retrofit.create(CollectionsJavaDocWebPageService.class);
Call<Document> call = service.fetch();
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
    // contains the html of https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html
  }

  @Override
  public void onFailure(Call<Document> call, Throwable t) {
    t.printStackTrace();
   }
});
```
