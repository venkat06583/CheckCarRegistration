package com.registration.pageobject;

import com.registration.base.WebDriverBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchPage extends WebDriverBase {

    @FindBy(id="vrm-input")
    private WebElement searchtext;
    @FindBy(css ="form  button")
    private  WebElement submitbuttion;
    @FindBy(css =".container a")
    private  WebElement newCarSearch;

    public SearchPage(){
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,60),this);
    }

    public void searchOption()
    {
        newCarSearch.click();
    }

    public RegistraionDetailsPage sendRegistraionNum(String number) {
        searchtext.sendKeys(number);
        submitbuttion.click();
        return new RegistraionDetailsPage();
    }
}
