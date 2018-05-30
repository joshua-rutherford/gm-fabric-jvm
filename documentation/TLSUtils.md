# Overview
General information on using the provided TLS utilities.

## TLS
There are two helper objects, `TlsConfigUtil` and `TlsServerUtil`. The `TlsServerUtil` is geared to creating server side `SSLEngine`. The `TlsConfigUtil` would be mostly leveraged to create client side `SSLContext`, it does have the ability to create server side as well if one where to provide it their own `SSLContext`. These utilities all use the standard Java components in `javax.net.ssl` and are located in the `core` module at `com.deciphernow.server.tls`.

### javax.net.ssl

- [How to create an SSLContext](https://docs.oracle.com/javase/7/docs/api/javax/net/ssl/SSLContext.html)
- [How to create an SSLEngine](https://docs.oracle.com/javase/7/docs/api/javax/net/ssl/SSLEngine.html)

### Creating Keystores && Truststores

- [Walk thourgh 1](https://docs.oracle.com/cd/E19509-01/820-3503/6nf1il6er/index.html)
- [Walk through 2](https://blogs.oracle.com/jtc/installing-trusted-certificates-into-a-java-keystore)
- [Walk through 3](https://www.digitalocean.com/community/tutorials/java-keytool-essentials-working-with-java-keystores)

### Example

The following example shows how to write a Two-Way SSL HTTP client.
    
     protected CloseableHttpClient createClient() throws URISyntaxException {
 
         ClassLoader classloader = Thread.currentThread().getContextClassLoader();
         URL privateKeyURL = ClassLoader.getSystemResource("keystore.jks");
         URL publickKeyURL = classloader.getSystemResource("truststore.jks");
         TrustStrategy trustStrategy = new TrustStrategy() {
             @Override
             public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                 return true;
             }
         };
         final SSLContext sslContext = TlsConfigUtil.createSslContext(
                 new File(privateKeyURL.toURI()),
                 "password",
                 new File(publickKeyURL.toURI()),
                 "password"
         );
         SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                 sslContext,
                 new String[] { "TLSv1.2", "SSLv3" },
                 null,
                 SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
 
         return HttpClients.custom().setSSLSocketFactory(sslsf).build();
     }