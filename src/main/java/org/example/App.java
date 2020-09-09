package org.example;

import org.example.language.English;
import org.example.sender.SenderEmail;
import org.example.sender.SenderSms;
import org.example.language.Turkish;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        Company trendyol = new Company("trendyol",1000,"trendyol@info.com","4444444",new English());
        Company dolap = new Company("dolap",1000,"dolap@info.com","3333333",new Turkish());

        infoTrendyol(trendyol);
        infoDolap(dolap);

        new FlexibleSms(trendyol);
        new FixedEmail(trendyol);   //new FlexibleEmail(trendyol);

        trendyol.sendSms(new SenderSms("hi","1111","2222","3333"));
        trendyol.sendSms(new SenderSms("call me","753159"));

        String[] youngerUserNumbers = new String[]{"555","5524","545345"};
        trendyol.sendSms(new SenderSms("hello",youngerUserNumbers));

        infoTrendyol(trendyol);


        String[] goldMemberEmails = new String[]{"ali@ali.co","b@b.co","cenk@c.co","de@de.com"};
        trendyol.sendEmail(new SenderEmail("hello!",goldMemberEmails));

        infoTrendyol(trendyol);


        trendyol.payEmailDebt();
        trendyol.paySmsDebt();

        infoTrendyol(trendyol);


        for (int i = 0; i < 2005; i++) {
            trendyol.sendEmail(new SenderEmail("this is a Email","gitti@gidiyor.com"));
            trendyol.sendSms(new SenderSms("this is a Sms","4949"));
            infoTrendyol(trendyol);
        }


        dolap.payEmailDebt();
        dolap.setLanguage(new English());
        dolap.payEmailDebt();

        new FixedSms(dolap);

    }

    private static void infoDolap(Company dolap) {

        System.out.println("------>\nDolap Para: "+ dolap.getMoney() +
                "\nKullanılabilecek sms kotası: "+dolap.getQuotaSms()+"  -   SmsBorc: "+dolap.getDebtSms() +
                "\nKullanılabilecek Email kotası: "+dolap.getQuotaEmail()+"  -   EmailBorc: "+dolap.getDebtEmail() +
                "\n<---");



    }

    private static void infoTrendyol(Company trendyol) {

        System.out.println("------>\nTrendyol Para: "+ trendyol.getMoney() +
                "\nKullanılabilecek sms kotası: "+trendyol.getQuotaSms()+"  -   SmsBorc: "+trendyol.getDebtSms() +
                "\nKullanılabilecek Email kotası: "+trendyol.getQuotaEmail()+"  -   EmailBorc: "+trendyol.getDebtEmail() +
                "\n<---");

    }
}
