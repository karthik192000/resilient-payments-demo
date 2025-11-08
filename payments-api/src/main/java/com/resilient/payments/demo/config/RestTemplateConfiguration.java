package com.resilient.payments.demo.config;

import static com.resilient.payments.demo.constants.PaymentConstants.TLS;

import java.io.InputStream;
import java.security.*;
import java.util.Objects;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/** * RestTemplate Configuration Class */
@Configuration
@Slf4j
public class RestTemplateConfiguration {

  @Value("${keystore.location}")
  String keyStorePath;

  @Value("${keystore.password}")
  String keyStorePassword;

  @Value("${truststore.location}")
  String trustStorePath;

  @Value("${truststore.password}")
  String trustStorePassword;

  @Value("${store.type}")
  String storeType;

  private static RestTemplate restTemplate;

  /**
   * Provides RestTemplate Instance
   *
   * @return RestTemplate
   */
  @Bean("defaultRestTemplate")
  public RestTemplate getRestTemplateInstance() {
    if (restTemplate != null) {
      return restTemplate;
    }
    return new RestTemplate();
  }

  /**
   * Creates and configures an HTTPS-enabled RestTemplate instance. This RestTemplate uses a custom
   * HttpComponentsClientHttpRequestFactory to support secure communication with self-signed
   * certificates.
   *
   * @return a configured RestTemplate instance for HTTPS communication
   */
  @Bean("httpsRestTemplate")
  public RestTemplate httpsRestTemplate() {
    HttpComponentsClientHttpRequestFactory requestFactory = buildRequestFactory();
    if (Objects.isNull(requestFactory)) {
      log.error("Failed to create HTTPS RestTemplate due to request factory creation error");
      return null;
    }

    return new RestTemplate(requestFactory);
  }

  private HttpComponentsClientHttpRequestFactory buildRequestFactory() {
    try {
      CloseableHttpClient httpClient = createHttpClient(createSslContext());
      return new HttpComponentsClientHttpRequestFactory(httpClient);
    } catch (Exception ex) {
      log.error("Error creating HTTPS RestTemplate request factory", ex);
    }
    return null;
  }

  private CloseableHttpClient createHttpClient(SSLContext sslContext) {
    // Build a TLS strategy (replaces old SSLConnectionSocketFactory)
    TlsSocketStrategy tlsStrategy =
        ClientTlsStrategyBuilder.create().setSslContext(sslContext).buildClassic();

    // Use the new API: setTlsSocketStrategy
    var connectionManager =
        PoolingHttpClientConnectionManagerBuilder.create()
            .setTlsSocketStrategy(tlsStrategy)
            .build();

    return HttpClients.custom().setConnectionManager(connectionManager).build();
  }

  private SSLContext createSslContext() throws Exception {
    // Load KeyStore from resources
    KeyStore keyStore = loadKeyStore(keyStorePath, keyStorePassword);

    // Load TrustStore from resources
    KeyStore trustStore = loadKeyStore(trustStorePath, trustStorePassword);

    // Initialize KeyManagerFactory with keystore
    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    kmf.init(keyStore, keyStorePassword.toCharArray()); // key password

    // Initialize TrustManagerFactory with truststore
    TrustManagerFactory tmf =
        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    tmf.init(trustStore);

    // Create and initialize SSLContext
    SSLContext sslContext = SSLContext.getInstance(TLS);
    sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

    return sslContext;
  }

  private KeyStore loadKeyStore(String path, String password) throws Exception {
    KeyStore keyStore = KeyStore.getInstance(storeType);
    try (InputStream stream = getClass().getResourceAsStream(path)) {
      if (stream == null) {
        throw new IllegalStateException("KeyStore not found at path: " + path);
      }
      keyStore.load(stream, password.toCharArray());
    }
    return keyStore;
  }
}
