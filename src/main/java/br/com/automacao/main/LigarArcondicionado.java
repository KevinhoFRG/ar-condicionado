package br.com.automacao.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LigarArcondicionado {

	private AndroidDriver<AndroidElement> driver;
	private final DesiredCapabilities capabilities;

	public LigarArcondicionado() {
		this.capabilities = new DesiredCapabilities();
		this.capabilities.setCapability("appPackage", "com.lgeha.nuts");
		this.capabilities.setCapability("appActivity", "com.lgeha.nuts.MainActivity");
	}

	public void run() throws Exception {
		Home();
		VerificaTemperatura();
	}

	private void Home() throws Exception {
		try {
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			throw new MalformedURLException("Falha ao abrir o aplicativo!");
		}
	}

	private String PegaTemperatura() throws Exception {
		try {
			return new WebDriverWait(driver, 25)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("card_data"))).getText();

		} catch (Exception e) {
			throw new Exception("Falha ao verificar temperatura!");
		}
	}

	private void VerificaTemperatura() throws Exception {
		String temperaturaCru = PegaTemperatura();
		temperaturaCru = temperaturaCru.replace("°C", "");
		double temperatura = Double.parseDouble(temperaturaCru);
		
		if (temperatura > 20) {
			new WebDriverWait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.id("card_action_button"))).click();
			JOptionPane.showMessageDialog(null, "Ar-condicionado ligado \nTemperatura atual: " + temperatura + "°C");
			
		}else {
			JOptionPane.showMessageDialog(null, "Temperatura abaixo de 20°C\nTemperatura atual: " + temperatura + "°C");
		}

	}

}
