package com.driver;

public class Main {
    public static void main(String[] args) throws Exception {
           SavingsAccount k=new SavingsAccount("likesh",1000,10000,5);
        System.out.println(k.getCompoundInterest(4,4));
        CurrentAccount one=new CurrentAccount("Bharathi",8000,"Kaalyaanmk");
        one.validateLicenseId();
        BankAccount bb=new BankAccount("skure",6000,2000);
        System.out.println(bb.generateAccountNumber(7,23));

    }
}