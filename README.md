## KtNAO

### Introduction

KtNAO is a SauceNAO API wrapper, for more api detail, plz refer to [SauceNAO API doc](https://saucenao.com/user.php?page=search-api).

### Usage

```kotlin
fun main() {
    val apiKey = "YOUR_API_KEY"
    
    val client = SauceNAOClient(apiKey)
    // request via image url
    client.request(imageUrl = "http://saucenao.com/images/static/banner.gif")
    // or send image directl
    client.request(imageBytes = Paths.get("image.png").readBytes())
}


```
