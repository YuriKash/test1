package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {

    @FindBy(id="empsearch_id")
    public WebElement idTextField;

    @FindBy(xpath="//input[@id='empsearch_id']")
    public WebElement nameTextField;

    @FindBy(id="searchBtn")
    public WebElement searchButton;

    public EmployeeSearchPage(){
        PageFactory.initElements(driver,this);
    }
}
