package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class searchTrainingMaterials {
    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true; // на период отладки тестов
    }

    @AfterEach
    public void tearDown() {
    }


    @Test
    @DisplayName(" Тест на проверку поиска учебных видео материалов по языку Python")
    void singInTest() {
        String email = "test01@yandex.ru";
        open("https://grinfer.com/");
        $(byLinkText("Log In")).click();
        $("#email").setValue(email);
        $("#password").setValue("Password01").click();
        $x("//button[@type='submit']").click();
        sleep(2000);
        $("input").setValue("Python").pressEnter();
        $x("//h1[contains(text(),'Search Results')]").shouldBe(visible);
        $("strong.sc-nst8f5-2").click();
        $x("//button[text()='Log out']").click();
    }

    @Test
    @DisplayName(" Тест на создания аккаунта, тест однократный, при повторе небходимо менять входные данные")
    public void createdAccount() {
        String name = "tester124",
                surname = "newcomer",
                password = "Password124",
                email = name + "@yandex.ru";
        open("https://grinfer.com/");
        $(byLinkText("Create account")).click();
        $("#email").val(email);
        $("#firstName").setValue(name).click();
        $("#lastName").setValue(surname).click();
        $("#password").setValue(password).click();
        $x("//button[@type='submit']").click();
//        $(byLinkText(name)).click();
//        $(byLinkText("Log out")).click();
    }

//    пользователи в зависимости от своих интересов заняты поиском учебных материалов
//    по языкам программирования
    @CsvSource(value = {
            " Python| test01| Password01",
            "Java| test02| Password02 "
    }, delimiter = '|')
    @ParameterizedTest(name = "регистрация на сайте, поиск учебных материалов по языку программирования \"{0}\"")
    public void searchToLanguage(String language, String name, String password) {

        open("https://grinfer.com/");
        $(byLinkText("Log In")).click();
        $("#email").setValue(name + "@yandex.ru");
        $("#password").setValue(password).click();
        $x("//button[@type='submit']").click();
        sleep(2000);
        $("input").setValue(language).pressEnter();
        $x("//h1[contains(text(),'Search Results')]").shouldBe(visible);
        sleep(2000);
        $("strong.sc-nst8f5-2").click();
        $x("//button[text()='Log out']").click();
    }
}
