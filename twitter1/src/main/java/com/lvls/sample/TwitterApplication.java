/**
 * 
 */
package com.lvls.sample;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterApplication {
    private final Logger logger = LoggerFactory.getLogger(TwitterApplication.class.getName());

    public static void main(String[] args) {
        new TwitterApplication().publish();
    }
  
    private void publish(){
    	logger.info("Starting");
       String message="Twitter application using Java http://www.java-tutorial.ch/architecture/twitter-with-java-tutorial";
       try {
           Twitter twitter = new TwitterFactory().getInstance();
           try {
               RequestToken requestToken = twitter.getOAuthRequestToken();
               AccessToken accessToken = null;
               while (null == accessToken) {
                   logger.info("Open the following URL and grant access to your account:");
                   logger.info(requestToken.getAuthorizationURL());
                   try {
                           accessToken = twitter.getOAuthAccessToken(requestToken);
                   } catch (TwitterException te) {
                       if (401 == te.getStatusCode()) {
                           logger.error("Unable to get the access token.");
                       } else {
                           te.printStackTrace();
                       }
                   }
               }
               logger.info("Got access token.");
               logger.info("Access token: " + accessToken.getToken());
               logger.info("Access token secret: " + accessToken.getTokenSecret());
           } catch (IllegalStateException ie) {
        	   logger.error("fail", ie);
               // access token is already available, or consumer key/secret is not set.
               if (!twitter.getAuthorization().isEnabled()) {
                   logger.error("OAuth consumer key/secret is not set.");
                   return;
               }
           }
//           Status status = twitter.updateStatus(message);
//           logger.info("Successfully updated the status to [" + status.getText() + "].");
           String queryString = "from:untiedt OR from:Tom_Adamski";
           Query query = new Query(queryString);
           query.setRpp(2);
           QueryResult result = twitter.search(query);
           List<Tweet> list = result.getTweets();
           for (Tweet tweet : list) {
        	   logger.info(tweet.getFromUser() + ": " + tweet.getText());
           }
//           Status status = twitter.showStatus(162616968845852672L);
//           logger.info("Status: " + status.getText());
       } catch (TwitterException te) {
           te.printStackTrace();
           logger.error("Failed to get timeline: " + te.getMessage());
       } 
    }
    

}

