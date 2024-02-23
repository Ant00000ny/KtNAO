## KtNAO

### Introduction

KtNAO is a SauceNAO API wrapper, for more api detail, plz refer to [SauceNAO API doc](https://saucenao.com/user.php?page=search-api).

### Usage

```kotlin
fun main() {
    val apiKey = "YOUR_API_KEY"

    val client = SaucenaoClient(apiKey)
    
    // request via image url
    val result = client.request(imageUrl = "http://saucenao.com/images/static/banner.gif")
    
    // or send image directly
    val result = client.request(imageBytes = Paths.get("image.png").readBytes())
}

```

The return value above is a list of SaucenaoResult.

```text
[SaucenaoResult(similarity=73.80, thumbnail=https://img1.saucenao.com/res/pixiv/9420/94203441_p0_master1200.jpg?auth=o3sEuCO6wQnpeY2X1JdRUg&exp=1709064000, urls=[https://www.pixiv.net/member_illust.php?mode=medium&illust_id=94203441], title=【宣伝】雨とカプチーノ～ビターカプチーノ～, memberName=まごつき, memberId=5472279)]
```

## TODO

- [ ] publish maven package
