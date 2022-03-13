package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTrainingMaterialsNew {
    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true; // на период отладки тестов
        open("https://grinfer.com/");
    }

    @AfterEach
    public void tearDown() {
    }

    @ValueSource(strings = {"Java", "Python", "Selenium"})
    @ParameterizedTest(name = "Регистрируемся и запрашиваем поиск учебных материалов по языку - \"{0}\"")
    void singInTest(String subject) {
        String email = "test01@yandex.ru";
        String password = "Password01";
        openAccount(email, password);
        searchDataAndControl(subject);
        closeAccount();
    }

    //    пользователи в зависимости от своих интересов заняты поиском учебных материалов
    //    по языкам программирования
    @CsvSource(value = {
            "Python| test01| Password01",
            "Java| test02| Password02 "
    }, delimiter = '|')
    @ParameterizedTest(name = "регистрация на сайте, поиск учебных материалов по языку программирования \"{0}\"")
    public void searchToLanguage(String language, String name, String password) {
        String email = name + "@yandex.ru";
        openAccount(email, password);
        searchDataAndControl(language);
        closeAccount();
    }

    static Stream<Arguments> TestDataProvider() {
        return Stream.of(
                Arguments.of("Angular", "test01", "Password01"),
                Arguments.of("Java", "test02", "Password02"));
    }
    @MethodSource(value = "TestDataProvider")
    @DisplayName(" control result of work with @MethodSource")
    @ParameterizedTest(name = "Студент {1}  с паролем {2}  в поиске данных по {0} ")
    public void searchToLanguage2(String language, String name, String password) {
        String email = name + "@yandex.ru";
        openAccount(email, password);
        searchDataAndControl(language);
        closeAccount();
    }

    void openAccount(String email, String password) {
        $(byLinkText("Log In")).click();
        $("#email").setValue(email);
        $("#password").setValue(password).click();
        $x("//button[@type='submit']").click();
        sleep(2000);
    }

    void searchDataAndControl(String subject) {
        $("input").setValue(subject).pressEnter();
        $x("//h1[contains(text(),'Search Results')]").shouldBe(visible);
    }

    void closeAccount() {
        $("strong.sc-nst8f5-2").click();
        $x("//button[text()='Log out']").click();
    }
}
