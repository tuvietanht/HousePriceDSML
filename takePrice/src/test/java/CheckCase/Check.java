package CheckCase;

import MainCase.WebUI;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;

import java.sql.PreparedStatement;

public class Check {

    public static boolean CheckVal(int number) {
        By Shape = By.xpath("(//div[@class='houseList-item-main oh'])[" + number + "]//span[contains(@class , 'shape')]");
        By Layout = By.xpath("(//div[@class='houseList-item-main oh'])[" + number + "]//span[contains(@class , 'layout')]");
        By Area = By.xpath("(//div[@class='houseList-item-main oh'])[" + number + "]//span[contains(@class , 'area')][1]");
        By MainArea = By.xpath("(//div[@class='houseList-item-main oh'])[" + number + "]//span[contains(@class , 'mainarea')]");
        By Age = By.xpath("(//div[@class='houseList-item-main oh'])[" + number + "]//span[contains(@class , 'houseage')]");
        By Floor = By.xpath("(//div[@class='houseList-item-main oh'])[" + number + "]//span[contains(@class , 'floor')]");

        if (!WebUI.CheckElementExist(Shape)) return false;
        if (!WebUI.CheckElementExist(Layout)) return false;
        if (!WebUI.CheckElementExist(Area)) return false;
        if (!WebUI.CheckElementExist(MainArea)) return false;
        if (!WebUI.CheckElementExist(Age)) return false;
        if (!WebUI.CheckElementExist(Floor)) return false;
        return true;
    }

    public void TakeIn4(PreparedStatement statement, int Number) throws Exception {
        By Shape = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]//span[contains(@class , 'shape')]");
        By Layout = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]//span[contains(@class , 'layout')]");
        By Area = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]//span[contains(@class , 'area')][1]");
        By MainArea = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]//span[contains(@class , 'mainarea')]");
        By Age = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]//span[contains(@class , 'houseage')]");
        By Floor = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]//span[contains(@class , 'floor')]");

        By District = By.xpath("(//div[@class='houseList-item-address-row clearfix'])[" + Number + "]//span[2]");
        By Road = By.xpath("(//div[@class='houseList-item-address-row clearfix'])[" + Number + "]//span[3]");

        By Money = By.xpath("(//div[@class='houseList-item-price'])[" + Number + "]//em");

        //plus points
        By TopSchool = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]/descendant::div[contains(text(),'明星學區')]");
        By Parking = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]/descendant::div[contains(text(),'車位')]");
        By Balcony =By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]/descendant::div[contains(text(),'陽台')]");
        By LowStruct = By.xpath("(//div[@class='houseList-item-main oh'])[" + Number + "]/descendant::div[contains(text(),'低公設')]");

        //(Shape, Layout, Area, MainArea, Age, Floor, TopSchool, Parking, Balcony, LowStruct, DisTrict, Road, Money)
        statement.setString(1, WebUI.GetText(Shape));
        statement.setString(2, WebUI.GetText(Layout));
        statement.setString(3, WebUI.GetText(Area));
        statement.setString(4, WebUI.GetText(MainArea));
        statement.setString(5, WebUI.GetText(Age));
        statement.setString(6, WebUI.GetText(Floor));
        statement.setString(7, (WebUI.CheckElementExist(TopSchool) ? "1" : "0"));
        statement.setString(8, (WebUI.CheckElementExist(Parking) ? "1" : "0"));
        statement.setString(9, (WebUI.CheckElementExist(Balcony) ? "1" : "0"));
        statement.setString(10, (WebUI.CheckElementExist(LowStruct) ? "1" : "0"));
        statement.setString(11, WebUI.GetText(District));
        statement.setString(12, WebUI.GetText(Road));
        statement.setString(13, WebUI.GetText(Money));

        statement.executeUpdate();
    }


    public static boolean CheckAdd(int number) {
        By district = By.xpath("(//div[@class='houseList-item-address-row clearfix'])[" + number + "]//span[2]");
        By road = By.xpath("(//div[@class='houseList-item-address-row clearfix'])[" + number + "]//span[3]");


        if (!WebUI.CheckElementExist(district)) return false;
        if (!WebUI.CheckElementExist(road)) return false;
        return true;
    }

}
