package com.base.kiap.https.retrofit;//package com.nokess.apphelp.https.retrofit;
//
//
//import com.nokess.apphelp.R;
//import com.nokess.apphelp.config.AppConfig;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.GeneralSecurityException;
//import java.security.KeyStore;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateException;
//import java.security.cert.CertificateFactory;
//import java.security.cert.X509Certificate;
//import java.util.Arrays;
//import java.util.Collection;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//import javax.net.ssl.X509TrustManager;
//
//public class SSLSocketClient {
//
//    private static InputStream trustedCertificatesInputStream() {
//        return AppConfig.INSTANCE.getApplication().getResources().openRawResource(R.raw.hipai);
//    }
//    private static KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
//        try {
//            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            InputStream in = null; // By convention, 'null' creates an empty key store.
//            keyStore.load(in, password);
//            return keyStore;
//        } catch (IOException e) {
//            throw new AssertionError(e);
//        }
//    }
//    private static SSLContext trustManagerForCertificates(InputStream in)throws GeneralSecurityException, IOException {
//        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
//        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
//        if (certificates.isEmpty()) {
//            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
//        }
//
//        // Put the certificates a key store.
//        char[] password = "214825242330908".toCharArray(); // Any password will work.
//        KeyStore keyStore = newEmptyKeyStore(password);
//        int index = 0;
//        for (Certificate certificate : certificates) {
//            String certificateAlias = Integer.toString(index++);
//            keyStore.setCertificateEntry(certificateAlias, certificate);
//        }
//
//        //  keyStore.load(in,CLIENT_KET_PASSWORD.toCharArray());
//        // Use it to build an X509 trust manager.
//        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
//                KeyManagerFactory.getDefaultAlgorithm());
//        keyManagerFactory.init(keyStore, password);
//        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
//                TrustManagerFactory.getDefaultAlgorithm());
//        trustManagerFactory.init(keyStore);
//        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
//        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
//            throw new IllegalStateException("Unexpected default trust managers:"
//                    + Arrays.toString(trustManagers));
//        }
//
//        SSLContext ssContext = SSLContext.getInstance("SSL");
//        ssContext.init(keyManagerFactory.getKeyManagers(),trustManagers,null);
//        return  ssContext;
//    }
//
//
//    public static SSLSocketFactory getSSLSocketFactory() {
//        try {
//           // SSLContext sslContext = SSLContext.getInstance("SSL");//TLS是SSL前身
//            //sslContext.init(null, new TrustManager[]{getTrustManager()}, null);
//            SSLContext sslContext = trustManagerForCertificates(trustedCertificatesInputStream()); //SSLContext.getInstance("TLS");
//           return sslContext.getSocketFactory();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static X509TrustManager getTrustManager() {
//        TrustManager trustManagers = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
//
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//        };
//        return (X509TrustManager) trustManagers;
//    }
//
//    public static HostnameVerifier getHostnameVerifier(){
//        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
//            @Override
//            public boolean verify(String s, SSLSession sslSession) {
//                return true;
//            }
//        };
//        return hostnameVerifier;
//    }
//
//}
