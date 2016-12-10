package gameplayer.back_end.facebook;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FacebookInformation {
	
	private User myUser;
	private String myPictureUrl;
	private FacebookClient myFBClient;
	
	public String getUserName() {
		return myUser.getName();
	}
	
	public String getProfilePicture() {
		return myPictureUrl;
	}
	
	public void authenticatePlayer() {
		String domain = "http://google.com/";
		String appID = "204787326597008";
		String authenticateURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appID + 
				"&redirect_uri=" + domain + "&scope=user_about_me, user_photos, ads_management, manage_pages, " +
				"business_management, user_status, user_posts";
		
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
				
				myFBClient = new DefaultFacebookClient(accessToken);
				myUser = myFBClient.fetchObject("me", User.class);
				JsonObject picture = 
					      myFBClient.fetchObject("me/picture/data", 
						      JsonObject.class, Parameter.with("redirect","false"));
				//System.out.println(picture);
				//myPictureUrl = picture.getString("url");
				
				
				myPictureUrl = picture.getJsonObject("data").getString("url");
				
				break;
			}
			
		}
		driver.quit();
		return;
	}
	
	public void publishNews(String aTitle, String aMessage) {
		// Publishing a simple message.
		// FacebookType represents any Facebook Graph Object that has an ID property.
		if (myFBClient != null) {
			FacebookType publishMessageResponse =
				myFBClient.publish("me/feed", FacebookType.class,
				  Parameter.with(aTitle, aMessage));
		}
	}
	 
	
 }