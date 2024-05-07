package MainCase;

import CheckCase.Check;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class ProjectTest extends Login {
    By NextPage = By.xpath("//span[contains(text(),'下一頁')]");
    By CurPage = By.xpath("//span[@class='pageCurrent']");

    //mysql
    String url = "jdbc:mysql://localhost:3306/591houseprice";
    String username = "root";
    String password = "Binpro123";

    @Test(priority = 0)
    public void SignInPageExcel() throws Exception {
        WebUI.OpenURL("https://sale.591.com.tw/?category=1&shType=list&regionid=17&firstRow=5130&totalRows=23453");
        Check checkCase = new Check();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO houseprice (Shape, Layout, Area, MainArea, Age, Floor, TopSchool, Parking, Balcony, LowStruct, DisTrict, Road, Money) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            String tmp = "";
            while(tmp != "782") {
                WebUI.WaitForPageLoaded();
                tmp = WebUI.GetText(CurPage);
                System.out.println(tmp);
                WebUI.Sleep(1);
                for(int i = 1 ; i <= 32 ; i++){
                    if(Check.CheckVal(i) && Check.CheckAdd(i)){
                        checkCase.TakeIn4(statement ,i);
                    }
                }
                WebUI.ClickElementBy(NextPage);
            }

            // Close the connection and statement
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
