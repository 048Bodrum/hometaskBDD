package netology.ru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static final ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard]");
    private final SelenideElement reloadButton = $("[data-test-id=action-reload]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text  = cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId())).getText();
        return extractBalance(text);
    }

    public static int getCardBalance(int index) {
        var text = cards.get(index).getText();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
      cards.findBy(Condition.attribute("data-test-id", cardInfo.getTestId())).$("button").click();
      return new TransferPage();
    }

    public void reloadDashboardPage() {
        reloadButton.click();
        heading.shouldBe(visible);
    }

    private static int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}