package org.example;

import org.example.exception.HasCurrentPackageException;

import java.util.Calendar;

public class FlexibleEmail implements Quota {

    private Company company;

    public FlexibleEmail(Company company) {
        this.company = company;
        try {
            isHasCurrentPackage();
            save();
        } catch (HasCurrentPackageException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        company.setQuotaEmail(2000);
        company.setEmailPackage(this);
        company.setDateEmail(Calendar.getInstance().getTime());
        company.setDebtEmail(7.5);
    }

    @Override
    public void isHasCurrentPackage() throws HasCurrentPackageException {
            if(company.getEmailPackage() != null)
                throw new HasCurrentPackageException(company.getName() + company.getLanguage().hasCurrentPackage());
        }

    @Override
    public void buy(Company company) {
        company.setQuotaEmail(1);
        company.setDebtEmail(0.03);
    }
}
