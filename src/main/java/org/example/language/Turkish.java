package org.example.language;

public class Turkish implements Language{
    @Override
    public String notFoundPackage() {
        return "Paket bulunmadı";
    }

    @Override
    public String blackList() {
        return " Kara Listedesin. Borcunu Ödemelisin";
    }

    @Override
    public String hasCurrentPackage() {
        return " Zaten böyle bir pakete sahipsin, tekrar alamazsın";
    }
}
