package test;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import page.ListAccountPage;
import page.LoginPage;
import page.NewAccountPage;
import page.SideNavigation;
import util.BrowserFactory;
import util.ExcelReader;

public class NewAccountTest {

   WebDriver driver;
   Random rnd = new Random();
   
	
	
ExcelReader reader = new ExcelReader("./data/data.xlsx");
String userName = reader.getCellData("Sheet1", "UserName", 2); 
String password = reader.getCellData("Sheet1", "Password", 2);
String accountName = reader.getCellData("Sheet1", "AccountTitle", 2) + rnd.nextInt(999);
String description = reader.getCellData("Sheet1", "Description", 2);
String amount = reader.getCellData("Sheet1", "InitialBalance", 2);

@Test
	public void addAnewContact() throws InterruptedException {
	driver = BrowserFactory.startBrowser();
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	loginPage.login(userName, password);
	SideNavigation sideNavigation = PageFactory.initElements(driver,  SideNavigation.class);
	sideNavigation.goToNewAccountPage();
	NewAccountPage newAccountPage = PageFactory.initElements(driver, NewAccountPage.class);
	
	newAccountPage.fillInTheNewAccountForm(accountName, description, amount);
	
	sideNavigation.goToListAccountPage();
	
	ListAccountPage listAccountPage = PageFactory.initElements(driver, ListAccountPage.class);
	
	List <String> accountList = listAccountPage.getListOf("Account");
	
	//Assert.assertTrue(isDataExist(accountName, accountList),"Account name was not found!");
	
	driver.close();
	driver.quit();
	
	}

    private boolean isDataExist(String expectedData, List <String> actualList) {
	for (int i=0; i<actualList.size();i++) {
	if (expectedData.equalsIgnoreCase(actualList.get(i))) {
	return true;
		
	}	
		}
	
	return false;
}
}










