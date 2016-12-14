package gameplayer.back_end.facebook;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

import gameplayer.back_end.exceptions.VoogaFacebookException;

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
		String domain = "https://google.com";
		String appID = "204787326597008";
		String authenticateURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appID + 
				"&redirect_uri=" + domain + "&scope=user_about_me, user_photos, ads_management, " +
				"business_management, user_status, user_posts, manage_pages, publish_actions";
		
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
				
				//String pageAccessToken = "EAAC6QMPbN5ABANZB0ihZBhoQBiFJjVg6EZCrZBLuJdtMZBs3HQIOgq2GnySXnrxTaN984EiulwXMsuFkkfhTf6FSvpdJPtimQtvsPlNZCgsJsAxJ2iEPfqk4dwJSIpyfDBFfs6B8Xxkgv60RpRHQPtLd8RZA8uvZAf2ZA1w96u33GtAZDZD";
				
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
	
	public void publishNews(String aTitle, String aMessage) throws VoogaFacebookException {
		// Publishing a simple message.
		// FacebookType represents any Facebook Graph Object that has an ID property.
		//String pageAccessToken = "EAAC6QMPbN5ABANZB0ihZBhoQBiFJjVg6EZCrZBLuJdtMZBs3HQIOgq2GnySXnrxTaN984EiulwXMsuFkkfhTf6FSvpdJPtimQtvsPlNZCgsJsAxJ2iEPfqk4dwJSIpyfDBFfs6B8Xxkgv60RpRHQPtLd8RZA8uvZAf2ZA1w96u33GtAZDZD";
		
		String appSecret = "f87efe0946d1584af720280c6e95036f";
		String appId = "204787326597008";
		
		//FacebookClient fb = new DefaultFacebookClient(pageAccessToken);
		
	    //Page page = myFBClient.fetchObject("me/feed", Page.class);

//	    myFBClient.publish("me/feed", FacebookType.class, Parameter.with("message", aMessage));

		if (myFBClient != null) {
			try {
				FacebookType publishMessageResponse =
						myFBClient.publish("/me/feed", FacebookType.class,
						  Parameter.with(aTitle, aMessage));
			} catch (FacebookException e) {
				throw new VoogaFacebookException(e.getMessage());
			}
		} else {
			throw new VoogaFacebookException("You are not logged in. Please log in to post");
		}
	}
	 
	
 }