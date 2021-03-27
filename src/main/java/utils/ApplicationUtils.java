package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import pages.PageBase;

public class ApplicationUtils extends PageBase {
	
	boolean flag;
	public ApplicationUtils(WebDriver driver)
	{
		super(driver);

	}

	public boolean setValue(WebElement lookUp,WebElement lookUpSearchBtn, String value,List<WebElement> searchListItem, By searchList) throws InterruptedException
 	{
	
		seleniumutils.mouseHover(lookUp);
 		Thread.sleep(2000);
 		seleniumutils.updateFieldValue(lookUp, value);
        javascriptutils.clickByJs(lookUpSearchBtn);
        seleniumutils.waitForStaleElement(searchList);
        flag=seleniumutils.selectAnOptionFromDropDwon(searchListItem,value,searchList);
        return flag;
 	}
	
	public boolean setFirstValue(WebElement lookUp,WebElement lookUpSearchBtn,List<WebElement> searchListItem, By searchList) throws InterruptedException
 	{
		Thread.sleep(2000);
		javascriptutils.clickByJs(lookUpSearchBtn);
        seleniumutils.waitForStaleElement(searchList);
        flag=seleniumutils.selectFirstValueFromDropDwon(searchListItem, searchList);

        return flag;		
 	}

	public String setDropDownValue(WebElement dropDown, String value)  {
    	seleniumutils.waitForElement(dropDown).click();
        Select casetype = new Select(dropDown);
        casetype.selectByVisibleText(value);
      //  log.info("select the value");
        return value;
        }
	
	public void getDropDownValues(WebElement dropdown, WebElement clickDropDown, List <WebElement> dropDownValues)
	{
		seleniumutils.mouseHover(dropdown);
		seleniumutils.waitForElement(clickDropDown).click();
		
		for(WebElement element:dropDownValues )
		{
			//seleniumutils.waitForAnElement(element).click();
		//	logger.log(Status.INFO,"Following country name is selected" + element.getText());
			
		}
	}
	
	public String[] getListOptions(WebElement mouseHoverTxtbox, WebElement clickSearchBtn, List <WebElement> searchResults) 
	{
		seleniumutils.mouseHover(mouseHoverTxtbox);
		seleniumutils.waitForElement(clickSearchBtn).click();
		String[] searchList= new String[seleniumutils.waitForListElements(searchResults).size()];
				
		int i=0;	
		for(WebElement searchResult:searchResults )	
		{
			searchList[i]= searchResult.getText();	
		}
		return searchList;
	}
	
	public String selectAnFirstOptionFromDropdown(WebElement mouseHoverTxtbox, WebElement clickSearchBtn, List <WebElement> searchResults) 
	{
		seleniumutils.mouseHover(mouseHoverTxtbox);
		seleniumutils.waitForElement(clickSearchBtn).click();
		String selectOptionName = null;
		for(WebElement searchResult:searchResults )	
		{
			selectOptionName= searchResult.getText();
			seleniumutils.waitForElement(searchResult).click();
			break;
		}	
		return selectOptionName;		 
	}
	
	public void removeValueFromLookUp(List<WebElement> lookUpType, WebElement removeIcon) throws InterruptedException
 	{
 		if(!(lookUpType.size()==0))
 		{
 			for(WebElement lookUP: lookUpType)
 			{
 				seleniumutils.mouseHover(lookUP);
 				Thread.sleep(2000);
 				seleniumutils.waitForElementClickable(removeIcon).click();
 				Thread.sleep(2000);
 				//log.info("Item is removed");
 				break;
 			}
 		}
 	}
	


	
	
}
