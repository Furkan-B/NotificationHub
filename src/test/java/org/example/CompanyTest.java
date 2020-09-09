package org.example;

import org.example.language.English;
import org.example.language.Turkish;
import org.example.sender.SenderEmail;
import org.example.sender.SenderSms;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CompanyTest {

    @Test
    public void it_should_has_quota(){
        //Given
        Company trendyol = new Company("trendyol",1000,"trendyol@info.com","4444444",new English());

        //When
        new FlexibleEmail(trendyol);
        new FixedSms(trendyol);

        //Then
        assertEquals(trendyol.getQuotaEmail(),2000);
        assertEquals(trendyol.getQuotaSms(),1000);
    }

    @Test
    public void it_should_decrease_quota_when_send_message(){
        //Given
        Company trendyol = new Company("trendyol",1000,"trendyol@info.com","4444444",new English());
        Company dolap = new Company("dolap",100,"dolap@info.com","4444444",new Turkish());
        new FlexibleEmail(dolap);
        new FixedSms(trendyol);

        //When
        trendyol.sendSms(new SenderSms("hi","5556665588"));
        dolap.sendEmail(new SenderEmail("hello","a@a.com","b@b.com","c@c.com"));

        String[] youngerUserNumbers = new String[]{"555","5524","545345"};
        trendyol.sendSms(new SenderSms("hi",youngerUserNumbers));


        //Then
        assertEquals(trendyol.getQuotaSms(),996);
        assertEquals(dolap.getQuotaEmail(),1997);

    }

    @Test
    public void it_should_be_flexible_package_flexible_price(){
        //Given
        Company trendyol = new Company("trendyol",1000,"trendyol@info.com","4444444",new English());
        new FlexibleEmail(trendyol);

        //When
        for (int i = 0; i < 2005; i++) {
            trendyol.sendEmail(new SenderEmail("this is a Email","gitti@gidiyor.com"));
        }

        //Then
        String debt = String.valueOf(trendyol.getDebtEmail());
        assertEquals(debt.substring(0,4), "7.65");
    }

    @Test
    public void it_should_be_fixed_package_fixed_price(){
        //Given
        Company trendyol = new Company("trendyol",1000,"trendyol@info.com","4444444",new English());
        new FixedSms(trendyol);

        //When
        for (int i = 0; i < 1005; i++) {
            trendyol.sendSms(new SenderSms("this is a Sms","5556665566"));
        }

        //Then
        assertEquals((int) trendyol.getDebtSms(), 40);
    }

    @Test
    public void it_should_not_send_message_if_has_not_package() {

        Company trendyol = new Company("Trendyol",1000,"trendyol@info.com","4444444",new English());
        new FlexibleSms(trendyol);

        trendyol.sendEmail(new SenderEmail("hi!","ali@ali.com"));
    }

    @Test
    public void it_should_not_have_like_or_same_package(){

        Company trendyol = new Company("Trendyol",1000,"trendyol@info.com","4444444",new English());
        new FlexibleEmail(trendyol);

        new FixedEmail(trendyol);

    }

    @Test
    public void it_should_not_send_message_if_on_blacklist() throws ParseException {

        //Given
        Company trendyol = new Company("Trendyol",1000,"trendyol@info.com","4444444",new English());
        new FixedEmail(trendyol);

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date tarih = df.parse("2020/07/01");

        //When
        trendyol.setDateEmail(tarih);

        trendyol.sendEmail(new SenderEmail("hi!","ali@ali.com"));
    }

    @Test
    public void it_should_send_message_if_paid_debt_and_decrease_money() throws ParseException {

        //Given
        Company trendyol = new Company("Trendyol",1000,"trendyol@info.com","4444444",new English());
        new FixedEmail(trendyol);

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Date tarih = df.parse("2020/07/01");
        trendyol.setDateEmail(tarih);

        //When
        trendyol.payEmailDebt();

        trendyol.sendEmail(new SenderEmail("hi!","ali@ali.com"));

        //Then
        assertEquals((int) trendyol.getMoney(),990);
    }

}