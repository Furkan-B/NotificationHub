package org.example;

import org.example.exception.HasCurrentPackageException;

public interface Quota {
    void buy(Company company);
    void isHasCurrentPackage() throws HasCurrentPackageException;
}
