package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;

    public AdminUser(ArrayList<String> securityIncidents) {
        this.securityIncidents = securityIncidents;
    }

    // TO DO: Implement a constructor that takes an ID and a role

    public AdminUser(Integer id, String role) {
        this.employeeID = id;
        this.role = role;
        this.securityIncidents = new ArrayList<>();
    }
    // TO DO: Implement HIPAACompliantUser!
    @Override
    public boolean assignPin(int pin) {
        if (String.valueOf(pin).length() >= 6) {
            this.pin = pin;
            return true;
        } else {
            return false;
        }
    }
//
    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
        if (confirmedAuthID.equals(this.employeeID)){
            return true;
        } else {
        authIncident();
        return false;
        }
    }
    // TO DO: Implement HIPAACompliantAdmin!
    @Override
    public ArrayList<String> reportSecurityIncidents() {
        return this.securityIncidents;
    }

    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n",
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n",
            new Date(), this.employeeID, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }



    // TO DO: Setters & Getters
    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getSecurityIncidents() {
        return securityIncidents;
    }

    public void setSecurityIncidents(ArrayList<String> securityIncidents) {
        this.securityIncidents = securityIncidents;
    }

    @Override
    public int getPin() {
        return super.getPin();
    }

    @Override
    public void setPin(int pin) {
        super.setPin(pin);
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }
}
