package org.example.onlineShop;

class Customer {
    private String fullName;
    private int age;
    private String phoneNumber;
    private Gender gender;

    public Customer(String fullName, int age, String phoneNumber, Gender gender) {
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
