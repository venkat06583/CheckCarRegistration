package com.registration.stepdefinations;

import com.registration.base.WebDriverBase;
import com.registration.pageobject.RegistraionDetailsPage;
import com.registration.pageobject.SearchPage;
import com.registration.util.ReadFile;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class CheckRegistrationStepDef extends WebDriverBase {

    private List<String> registrationNumbers;
    private SearchPage searchpage;
    private RegistraionDetailsPage registraionDetailsPage;
    public String tempReg=null;
    private Map<String, List<String>> listMap;

    @Before
    public void setup(){
        WebDriverBase.initialBrowser();
    }

    @Given("Reads the input text file (.*)")
    public void readsTheInputTextFileINPUT_FILE(String inputFileName) throws IOException {
        registrationNumbers=ReadFile.getRegistrationNumbers(inputFileName);
    }


    @When("Navigate to website and perform free car check")
    public void navigateToWebsiteAndProfrmFreeCarCheck() {
        listMap= new HashMap<String, List<String>>();
        searchpage=new SearchPage();
        for(String carRegistrationNumber:registrationNumbers) {
        List<String> list = new ArrayList<String>();
        registraionDetailsPage = searchpage.sendRegistraionNum(carRegistrationNumber);
        list.add(registraionDetailsPage.getRegistrionNumber());
        list.add(registraionDetailsPage.getMake());
        list.add(registraionDetailsPage.getModel());
        list.add(registraionDetailsPage.getColour());
        list.add(registraionDetailsPage.getYear());
        listMap.put(carRegistrationNumber, list);
        searchpage.searchOption();
         }
    }

    @Then("Compare the details in output text file (.*)")
    public void compareTheDetailsInOutputTextFileOUTPUT_FILE(String outfileName) throws IOException {
        Map<String,List<String>> expectedMap=ReadFile.getExpectedDetails(outfileName);
        for (Map.Entry<String, List<String>> entry : expectedMap.entrySet()) {
            System.out.println("Expected Key : " + entry.getKey() + ", Value : " + entry.getValue());

        }
        for (Map.Entry<String, List<String>> entry : listMap.entrySet()) {
            System.out.println("Actual Key : " + entry.getKey() + ", Value : " + entry.getValue());

        }

        assertFalse(listMap.equals(expectedMap));
    }

    @After
    public void tearDown(){
        WebDriverBase.close();
    }

}

