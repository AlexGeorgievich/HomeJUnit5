package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class CreatAccount {
    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true; // на период отладки тестов
        open("https://grinfer.com/");
    }

    // в целях не злоупотреблять терпением владельцев сайта по учебным материалам,
    // использовать даннй тест с ограничениями
    @Test
    @DisplayName(" Тест на создания аккаунта, тест однократный, при повторе небходимо менять входные данные")
    public void createdAccount() {
        String name = "tester126",
                surname = "newcomer",
                password = "Password125",
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
}
