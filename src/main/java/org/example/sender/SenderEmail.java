package org.example.sender;

import java.util.Arrays;

public class SenderEmail implements Post {
    private String[] email;
    private String content;

    public SenderEmail(String content, String... email) {
        this.content = content;
        this.email = email;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String... email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public void send() {
        System.out.println("Alıcı Mailler: " + Arrays.toString(email) + " Mail iletildi: "+content);
    }

    @Override
    public int messageCount() {
        return email.length;
    }
}
