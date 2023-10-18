package extras;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class CollectionPage 
{
	@FindBy(how=How.LINK_TEXT,using="Gmail")
	public WebElement mylink1;
	
	@FindBy(how=How.LINK_TEXT,using="Gmail")
	@CacheLookup
	public WebElement mylink2;
	
	@FindBys( {
				@FindBy(className = "class1"),
				@FindBy(tagName = "tag1")
			})
	public List<WebElement> elementsWithBoth_class1ANDtag1;
	
	@FindAll({
		   @FindBy(className = "class1"),
		   @FindBy(className = "class2")
		})
	public List<WebElement> elementsWithEither_class1ORclass2;  	
}






