package AutoTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.interactions.Actions;

public class AddToCategory {
    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void beforeClass() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://cms.anhtester.com/login");
//		Login
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@example.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

//		Tìm đến category để thực hiện add
        driver.findElement(By.xpath("//span[text()='Products']")).click();
        driver.findElement(By.xpath("//span[text()='Category']")).click();
        driver.findElement(By.xpath("//span[text()='Add New category']")).click();
    }

    @Test
    public void Case_01_Empty_Data() throws InterruptedException {
//		Click button save
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5 * 1000);
//		Verify vẫn đang ở màn Category Information => chưa add thành công
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Category Information']")).isDisplayed());
    }

//    @Test
//    public void Case_02_Empty_Name() throws InterruptedException  {
	//	chọn file ảnh
//        driver.findElement(By.xpath("(//label[text()='Banner ']//parent::div//following-sibling::div)[1]//div[text()='Choose File']")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("(//div[@title='430333983_914461580223005_5432766109724515617_n.jpg'])[1]")).click();
//        Thread.sleep(1000);
//        driver.findElement(By.xpath("//button[text()='Add Files']")).click();
//        Thread.sleep(4 * 1000);
//		Click button save
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//		Verify vẫn đang ở màn Category Information => chưa add thành công
//        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Category Information']")).isDisplayed());
//    }


    @Test
    public void Case_03_Create_Success() throws InterruptedException  {
//		Điền Name
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("GHuong");
//      Chọn Parent Category
        driver.findElement(By.xpath("//div[text()='No Parent']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li//a//span[text()='Sport shoes']")).click();
//      Select Type
        driver.findElement(By.xpath("//div[text()='Physical']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li//a//span[text()='Digital']")).click();
//      Chọn Ordering Number
        driver.findElement(By.xpath("//input[@id='order_level']")).sendKeys("2");
        Thread.sleep(1000);
//		Chọn file ảnh trong Banner
        driver.findElement(By.xpath("(//label[text()='Banner ']//parent::div//following-sibling::div)[1]//div[text()='Choose File']")).click();
        Thread.sleep(2 * 1000);
        driver.findElement(By.xpath("(//div[@title='430333983_914461580223005_5432766109724515617_n.jpg'])[1]")).click();
        Thread.sleep(2 * 1000);
        driver.findElement(By.xpath("//button[text()='Add Files']")).click();
        Thread.sleep(4 * 1000);
//      Chọn file ảnh trong Icon
        driver.findElement(By.xpath("(//label[text()='Icon ']//parent::div//following-sibling::div)[1]//div[text()='Choose File']")).click();
        Thread.sleep(2 * 1000);
        driver.findElement(By.xpath("//input[@placeholder='Search your files']")).sendKeys("cooking");
        Thread.sleep(4 * 1000);
        driver.findElement(By.xpath("(//div[@title='cookingrice.png'])[1]")).click();
        Thread.sleep(4 * 1000);
        driver.findElement(By.xpath("//button[text()='Add Files']")).click();
        Thread.sleep(5 * 1000);
//      Nhập Meta Titile
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Xin chào");
        Thread.sleep(1000);
//      Nhập Meta description
        driver.findElement(By.xpath("//textarea[@name='meta_description']")).sendKeys("Đây là bài kiểm tra giữa kỳ");
        Thread.sleep(1000);
//      Chọn Filtering Attributes
        driver.findElement(By.xpath("//div[text()='Nothing selected']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li//a//span[text()='Capacity']")).click();
        Thread.sleep(1000);
//		Click button save
        driver.findElement(By.xpath("//button[@type='submit']")).click();
//		Verify hiển thị message thông báo thành công
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message']")).getText(), "Category has been inserted successfully");
        Thread.sleep(2 * 1000);
//		Tìm kiếm category vừa tạo thành công
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("GHuong");
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(5 * 1000);
//		Verify hiển thị category vừa tạo
        Assert.assertTrue(driver.findElement(By.xpath("(//*[text()='GHuong'])[1]")).isDisplayed());
        driver.quit();

    }

}
