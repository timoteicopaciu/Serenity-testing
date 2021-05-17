package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.DictionaryPage;
import org.example.pages.HistoryPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EndUserSteps2 {

    HistoryPage historyPage;

    @Step
    public void enters(String keyword) {
        historyPage.enter_keywords(keyword);
    }

    @Step
    public void starts_search() {
        historyPage.lookup_terms();
    }

    @Step
    public void should_see_definition(String definition) {
        assertThat(historyPage.getDefinitions(), hasItem(containsString(definition)));
    }

    @Step
    public void should_not_see_definition() {
        assertThat(historyPage.getDefinitions().size(), is(0));
    }

    @Step
    public void is_the_home_page() {
        historyPage.open();
    }

    @Step
    public void looks_for(String term) {
        enters(term);
        starts_search();
    }
}