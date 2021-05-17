package org.example.features.search;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;

import org.example.steps.serenity.EndUserSteps;
import org.example.steps.serenity.EndUserSteps2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;


@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/feature2.csv")
public class Feature_2{

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "https://en.wiktionary.org/w/index.php?title=Wiktionary&action=history")
    public Pages pages;

    public String name;
    public String definition;

    @Qualifier
    public String getQualifier() {
        return name;
    }

    @Steps
    public EndUserSteps2 endUser;

    //    @Issue("#WIKI-1")
    @Test
    public void filterTest() {
        endUser.is_the_home_page();
        endUser.looks_for(getName());
        if(name.equals("mobile edit")){
            endUser.should_see_definition(getDefinition());
        }
        else {
            endUser.should_not_see_definition();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

}
