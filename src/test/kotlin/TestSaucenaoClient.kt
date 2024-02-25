import com.isekaiofficial.ktnao.SaucenaoClient
import com.isekaiofficial.ktnao.enums.SaucenaoDbEnum
import com.isekaiofficial.ktnao.util.client
import com.isekaiofficial.ktnao.util.objectMapper
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import java.io.File
import kotlin.test.Test

class TestSaucenaoClient {
    private val apiKey = object {}
        .javaClass
        .classLoader
        .getResource("properties.json")
        ?.toURI()
        ?.let { File(it) }
        ?.readText(Charsets.UTF_8)
        ?.let { objectMapper.readTree(it) }
        ?.at("/api_key")
        ?.asText()
        ?: error("api_key not found")

    private val saucenaoClient = SaucenaoClient(apiKey = apiKey, db = SaucenaoDbEnum.ALL_DB)

    @Test
    fun testSearchUrl() = runBlocking {
        val results = saucenaoClient.request(imageUrl = "https://avatars.githubusercontent.com/u/20972206?v=4")

        println(results)
    }

    @Test
    fun testSearchBytes() = runBlocking {
        val response = client.get("https://avatars.githubusercontent.com/u/20972206?v=4")
        val image = response.body<ByteArray>()

        val results = saucenaoClient.request(imageBytes = image)

        println(results)
    }
}
