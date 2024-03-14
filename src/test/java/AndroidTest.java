import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidTest {
    AndroidDriver<AndroidElement> driver = null;

    public void initialize(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Device1");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.calculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Step
    @Test
    public void calculatorTest(){
        initialize();
        AndroidElement number1button = driver.findElement(By.id("com.google.android.calculator:id/digit_1"));
        AndroidElement number2button = driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
        AndroidElement plusebutton = driver.findElement(By.id("com.google.android.calculator:id/op_add"));
        AndroidElement equalsbutton = driver.findElement(By.id("com.google.android.calculator:id/eq"));
        AndroidElement multiplybutton = driver.findElement(By.id("com.google.android.calculator:id/op_mul"));
        AndroidElement number4button = driver.findElement(By.id("com.google.android.calculator:id/digit_4"));
        AndroidElement dividebutton = driver.findElement(By.id("com.google.android.calculator:id/op_div"));
        AndroidElement minusbutton = driver.findElement(By.id("com.google.android.calculator:id/op_sub"));
        number1button.click();
        plusebutton.click();
        number2button.click();
        equalsbutton.click();
        AndroidElement result = driver.findElement(By.id("com.google.android.calculator:id/result_final"));
        Assert.assertEquals("3", result.getText());
        multiplybutton.click();
        number4button.click();
        equalsbutton.click();
        Assert.assertEquals("12", result.getText());
        dividebutton.click();
        number2button.click();
        equalsbutton.click();
        Assert.assertEquals("6", result.getText());
        minusbutton.click();
        number4button.click();
        equalsbutton.click();
        Assert.assertEquals("2", result.getText());
    }
}