package gameplayer.back_end.facebook;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.types.User;

public class FacebookInformation {
	
	//private static final String ACCESS_TOKEN = "EAACEdEose0cBAKqz91ZCys1MtLyDmwA5Urg1dTYiqpXc6EeAowOTnIAwAxfgZASsu77VhmJaof5vild7evkIjLrWE1ZBAc4j7ZAhGmS0BZASXZCPe93HnZBfmoEcq5py12e6dP54PhUwjNy54fhEncXaKhpp4izdMEaEpzheZAMJkQZDZD";
	
	private User myUser;
	
	public String getUserName() {
		return myUser.getName();
	}
	
	public String getProfilePicture() {
		return myUser.getPicture().getUrl();
	}
	
	public void authenticatePlayer() {
		String domain = "http://google.com/";
		String appID = "204787326597008";
		String authenticateURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appID + 
				"&redirect_uri=" + domain + "&scope=user_about_me, user_photos, ads_management, manage_pages, " +
				"business_management, user_status";
		
		File chromeDriverFile = new File("data/chromedriver");
		chromeDriverFile.setExecutable(true);
		
		System.setProperty("webdriver.chrome.driver", chromeDriverFile.toString());
		
		WebDriver driver = new ChromeDriver();
		driver.get(authenticateURL);
		
		String accessToken;
		while(true) {
			if (!driver.getCurrentUrl().contains("facebook.com")) {
				String url = driver.getCurrentUrl();
				accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
				driver.quit();
				
				DefaultFacebookClient fbClient = new DefaultFacebookClient(accessToken);
				myUser = fbClient.fetchObject("me", User.class);
				//System.out.println(myUser);
				
				break;
			}
			
		}
		driver.quit();
		System.out.println(myUser.getPicture());
		return;
	}
	 
	
 }