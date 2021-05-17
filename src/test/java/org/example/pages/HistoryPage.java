package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("https://en.wiktionary.org/w/index.php?title=Wiktionary&action=history")
public class HistoryPage extends PageObject {

    @FindBy(className="oo-ui-labelElement-label")
    private WebElementFacade filterRevision;

    @FindBy(name="tagfilter")
    private WebElementFacade filterHistory;

    @FindBy(className = "oo-ui-buttonElement-button")
    private WebElementFacade filerButton;

    public void enter_keywords(String keyword) {
        filterRevision.click();
        filterHistory.type(keyword);
    }

    public void lookup_terms() {

        filerButton.click();
    }

    public List<String> getDefinitions() {
        WebElementFacade content = find(By.id("content"));
        if(!content.containsElements(By.tagName("ul")))
            return new ArrayList<>();
        WebElementFacade definitionList = content.find(By.tagName("ul"));

        List<String> res = definitionList.findElements(By.tagName("li")).stream()
                .map( element -> element.getText() )
                .collect(Collectors.toList());
        return res;
    }
}
