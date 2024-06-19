import android.content.res.AssetManager
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.BufferedReader
import java.io.InputStreamReader

class AssetFileInterceptor(private val assetManager: AssetManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        takeIf {
            chain.request().url.pathSegments.contains(ASSET_PATH)
        } ?: run {
            return chain.proceed(chain.request())
        }

//        val path = chain.request().url.encodedPath
        val assetFile = chain.request().url.pathSegments[chain.request().url.pathSegments.indexOf(ASSET_PATH).plus(1)]

        val responseBody =
            readAssetFile("api/$assetFile.json").toResponseBody("application/json;charset=UTF-8".toMediaTypeOrNull())

        return Response.Builder()
            .code(200)
            .message("OK")
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(responseBody)
            .build()
    }

    private fun readAssetFile(path: String): String {
        val reader = BufferedReader(InputStreamReader(assetManager.open(path)))
        val builder = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            builder.append(line)
            line = reader.readLine()
        }

        return builder.toString()
    }

    companion object {
        private const val ASSET_PATH: String = "assets"
    }
}