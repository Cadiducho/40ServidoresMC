package com.cadiducho.cservidoresmc.bukkit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.logging.Level;

/**
 * @author potame in Stackoverflow
 */
class CertificateUtil {

    static void setUpCertificates() {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            Path ksPath = Paths.get(System.getProperty("java.home"),
                    "lib", "security", "cacerts");
            keyStore.load(Files.newInputStream(ksPath), "changeit".toCharArray());

            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            boolean ok = setCertificate(keyStore, cf, "Let'sEncryptAuthorityX3");
            ok = ok && setCertificate(keyStore, cf, "DSTRootCAX3");
            if (!ok) {
                BukkitPlugin.get().log(Level.SEVERE, "Atención! Se está ejecutando una versión inferior a Java 8_101 y las conexiones con la web pueden no ser seguras!");
                BukkitPlugin.get().log(Level.SEVERE, "Debes instalar los certificados 'Let'sEncryptAuthorityX3.der' y 'DSTRootCAX3.der' en /plugins/40ServidoresMC/");
                BukkitPlugin.get().log(Level.SEVERE, "Estos archivos pueden ser descargados de ");
            }

            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean setCertificate(KeyStore keyStore, CertificateFactory cf, String name) throws Exception {
        File file = new File(BukkitPlugin.get().getDataFolder() + "/" + name + ".der");
        if (file.exists()) {
            try (InputStream caInput = new BufferedInputStream(new FileInputStream(file))) {
                Certificate crt = cf.generateCertificate(caInput);
                BukkitPlugin.get().debugLog("Agregado certificado para " + ((X509Certificate) crt).getSubjectDN());

                keyStore.setCertificateEntry(name, crt);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Java version as an int value.
     * Author: Open Street Map's JOSM contributors
     * @return the Java version as an int value (8, 9, etc.)
     * @since 12130
     */
    public static int getJavaVersion() {
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2);
        }
        // Allow these formats:
        // 1.8.0_72-ea
        // 9-ea
        // 9
        // 9.0.1
        int dotPos = version.indexOf('.');
        int dashPos = version.indexOf('-');
        return Integer.parseInt(version.substring(0,
                dotPos > -1 ? dotPos : dashPos > -1 ? dashPos : 1));
    }

    /**
     * Returns the Java update as an int value.
     * Author: Open Street Map's JOSM contributors
     * @return the Java update as an int value (121, 131, etc.)
     * @since 12217
     */
    public static int getJavaUpdate() {
        String version = System.getProperty("java.version");
        if (version.startsWith("1.")) {
            version = version.substring(2);
        }
        // Allow these formats:
        // 1.8.0_72-ea
        // 9-ea
        // 9
        // 9.0.1
        int undePos = version.indexOf('_');
        int dashPos = version.indexOf('-');
        if (undePos > -1) {
            return Integer.parseInt(version.substring(undePos + 1,
                    dashPos > -1 ? dashPos : version.length()));
        }
        int firstDotPos = version.indexOf('.');
        int lastDotPos = version.lastIndexOf('.');
        if (firstDotPos == lastDotPos) {
            return 0;
        }
        return firstDotPos > -1 ? Integer.parseInt(version.substring(firstDotPos + 1,
                lastDotPos > -1 ? lastDotPos : version.length())) : 0;
    }
}
