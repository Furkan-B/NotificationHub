package org.example;

import org.example.language.Language;
import org.example.sender.SenderEmail;
import org.example.sender.SenderSms;
import org.example.exception.BlackListException;
import org.example.exception.NotFoundPackageException;

import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Company {
    private String name;
    private double money;
    private String email;
    private String number;
    private int quotaSms;
    private int quotaEmail;
    private double debtSms;
    private double debtEmail;
    private Language language;
    private Date dateEmail,dateSms;

    private Quota smsPackage, emailPackage;


    public Company() {
    }

    public Company(String name, int money, String email, String number, Language language) {
        this.name = name;
        this.money = money;
        this.email = email;
        this.number = number;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getQuotaSms() {
        return quotaSms;
    }

    public void setQuotaSms(int quotaSms) {
        this.quotaSms += quotaSms;
    }

    public int getQuotaEmail() {
        return quotaEmail;
    }

    public void setQuotaEmail(int quotaEmail) {
        this.quotaEmail += quotaEmail;
    }

    public double getDebtSms() {
        return debtSms;
    }

    public void setDebtSms(double debtSms) {
        this.debtSms += debtSms;
    }

    public double getDebtEmail() {
        return debtEmail;
    }

    public void setDebtEmail(double debtEmail) {
        this.debtEmail += debtEmail;
    }

    public Date getDateEmail() {
        return dateEmail;
    }

    public void setDateEmail(Date dateEmail) {
        this.dateEmail = dateEmail;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Quota getSmsPackage() {
        return smsPackage;
    }

    public void setSmsPackage(Quota smsPackage) {
        this.smsPackage = smsPackage;
    }

    public Quota getEmailPackage() {
        return emailPackage;
    }

    public void setEmailPackage(Quota emailPackage) {
        this.emailPackage = emailPackage;
    }

    public Date getDateSms() {
        return dateSms;
    }

    public void setDateSms(Date dateSms) {
        this.dateSms = dateSms;
    }




    private void smsValidator(int messageCount)  {
        if(quotaSms-messageCount < 0)
            smsPackage.buy(this);
    }

    private void isHasSms() throws NotFoundPackageException {
        if(smsPackage == null)
            throw new NotFoundPackageException(name+" sms "+language.notFoundPackage());
    }

    public void sendSms(SenderSms sms){
        try {
            isHasSms();
            isBlackListed(dateSms);
            smsValidator(sms.messageCount());
            sms.send();
            quotaSms -= sms.messageCount();
        } catch (NotFoundPackageException | BlackListException notFoundSmsPackage) {
            notFoundSmsPackage.printStackTrace();
        }


    }

    private void emailValidator(int messagaCount)  {
        if(quotaEmail-messagaCount < 0)
            emailPackage.buy(this);
    }

    private void isHasEmail() throws NotFoundPackageException {
        if(emailPackage == null)
            throw new NotFoundPackageException(name+" mail "+language.notFoundPackage());
    }

    public void sendEmail(SenderEmail email){
        try {
            isHasEmail();
            isBlackListed(dateEmail);
            emailValidator(email.messageCount());
            email.send();
            quotaEmail -= email.messageCount();
        } catch (NotFoundPackageException | BlackListException notFoundPackageException) {
            notFoundPackageException.printStackTrace();
        }
    }

    private void isBlackListed(Date boughtDate) throws BlackListException {

        Date dateNow = new Date();

        YearMonth m1 = YearMonth.from(boughtDate.toInstant().atZone(ZoneOffset.UTC));
        YearMonth m2 = YearMonth.from(dateNow.toInstant().atZone(ZoneOffset.UTC));

        if( (m1.until(m2, ChronoUnit.MONTHS) > 2 ) ){
            throw new BlackListException(name + language.blackList());
        }
    }

    public void paySmsDebt(){
        try {
            isHasSms();
            money -= debtSms;
            debtSms = 0;
            dateSms = new Date();
        } catch (NotFoundPackageException notFoundPackageException) {
            notFoundPackageException.printStackTrace();
        }

    }

    public void payEmailDebt(){

        try {
            isHasEmail();
            money -= debtEmail;
            debtEmail = 0;
            dateEmail = new Date();
        } catch (NotFoundPackageException notFoundPackageException) {
            notFoundPackageException.printStackTrace();
        }
    }


}
