package org.example.sender;

import java.util.Arrays;

public class SenderSms implements Post{
    private String[] number;
    private String content;

    public SenderSms(String content, String... number) {
        this.number = number;
        this.content = content;
    }

    public String[] getNumber() {
        return number;
    }

    public void setNumber(String... number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public void send() {
        System.out.println("Alıcı Numaralar: "+ Arrays.toString(number)+"\t\tSMS İletildi: "+content);
    }

    @Override
    public int messageCount() {
        return number.length;
    }
}
