package com.github.welshk.ukko.networking

import com.github.welshk.ukko.BuildConfig
import com.github.welshk.ukko.utils.MockUtil
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json

object KtorClient {
    fun getInstance(
        engine: HttpClientEngine = CIO.create(),
        baseUrl: String = BuildConfig.BASE_URL
    ): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
            defaultRequest {
                url(baseUrl)
            }
        }
    }

    fun getMockInstance(
        status: HttpStatusCode = HttpStatusCode.OK,
        json: String = BuildConfig.MOCK_CURRENT_WEATHER,
        headers: Headers = headersOf(HttpHeaders.ContentType, "application/json")
    ): HttpClient {
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    MockUtil.readJsonFile(
                        javaClass.classLoader,
                        json
                    )
                ),
                status = status,
                headers = headers
            )
        }
        return getInstance(mockEngine)
    }
}