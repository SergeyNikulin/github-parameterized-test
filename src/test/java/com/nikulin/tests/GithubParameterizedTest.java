package com.nikulin.tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GithubParameterizedTest {

    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = "https://github.com/";
    }

    @ValueSource(strings = {
            "SoftAssertions",
            "Home"
    })

    @ParameterizedTest(name = "Проверка в wiki страницы: {0}")
    public void valueParameterizedSearchTest(String searchQuery) {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue(searchQuery);
        $("#wiki-pages-box").shouldHave(text(searchQuery));
    }

    @CsvSource({
            "/selenide/selenide, SoftAssertions",
            "/selenide/selenide, Home"
    })

    @ParameterizedTest(name = "Проверка в wiki страницы: {1}")
    public void csvParameterizedSearchTest(String url, String searchQuery) {
        open(url);
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue(searchQuery);
        $("#wiki-pages-box").shouldHave(text(searchQuery));
    }

    @EnumSource(value = WikiSearchItem.class)
    @ParameterizedTest(name = "Проверка в wiki страницы: {0}")
    public void enumParameterizedSearchTest(WikiSearchItem wikiSearchItem) {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue(wikiSearchItem.getDesc());
        $("#wiki-pages-box").shouldHave(text(wikiSearchItem.getDesc()));
    }

    public static Stream<String> methodParameterizedSearchTest() {
        return Stream.of(
                "SoftAssertions",
                "Home"
        );
    }

    @ParameterizedTest(name = "Проверка в wiki страницы: {0}")
    @MethodSource
    public void methodParameterizedSearchTest(String searchQuery) {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue(searchQuery);
        $("#wiki-pages-box").shouldHave(text(searchQuery));
    }
}
