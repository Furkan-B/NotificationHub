package org.example;

import org.example.exception.HasCurrentPackageException;

import java.util.Calendar;

public class FixedSms implements Quota {

    private Company company;

    public FixedSms(Company company) {
        this.company = company;
        try {
            isHasCurrentPackage();
            save();
        } catch (HasCurrentPackageException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        company.setQuotaSms(1000);
        company.setSmsPackage(this);
        company.setDateSms(Calendar.getInstance().getTime());
        company.setDebtSms(20);
    }

    @Override
    public void isHasCurrentPackage() throws HasCurrentPackageException {
        if(company.getSmsPackage() != null)
            throw new HasCurrentPackageException(company.getName() + company.getLanguage().hasCurrentPackage());
    }

    @Override
    public void buy(Company company) {
        company.setQuotaSms(1000);
        company.setDebtSms(20);
    }
}
