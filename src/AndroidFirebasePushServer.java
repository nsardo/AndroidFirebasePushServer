import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

/**
 * AndroidFirebasePushServer
 * 
 * A reference implementation for sending push messages to Android Phone's via
 * Firebase using their legacy api.
 * 
 * @author Nicholas Sardo
 *
 */
public class AndroidFirebasePushServer {

    /**
     * GENERATED BY FCM
     */
    private static String SERVER_KEY = "YOUR_SERVER_KEY";

    /**
     * ENTRY POINT
     */
    public static void main(String[] args) throws Exception {
	sendPushNotification();
    }

    /**
     * SEND NOTIFICATION TO ANDROID VIA FIREBASE
     * 
     * JSON Object is built up as substance of message A URL connection is opened to
     * the legacy server, and headers are created To send the object as a string, an
     * OutputStreamWriter is needed to write it out.
     * 
     * @author Nicholas Sardo
     * 
     */
    private static void sendPushNotification() throws Exception {

	JSONObject msg = new JSONObject();
	msg.put("to", "/topics/test");
	msg.put("priority", "High");

	JSONObject notification = new JSONObject();
	notification.put("sound", "default");
	notification.put("title", "Test Title");
	notification.put("body", "Test Message Body");

	JSONObject data = new JSONObject();
	data.put("key", "val");

	msg.put("notification", notification);
	msg.put("data", data);

	System.out.println(msg.toString());

	// Create connection to send FCM Message request.
	URL url = new URL("https://fcm.googleapis.com/fcm/send");

	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
	conn.setRequestProperty("Content-Type", "application/json");
	conn.setRequestMethod("POST");
	conn.setDoOutput(true);

	// Send FCM message content.
	OutputStream outputStream = conn.getOutputStream();
	OutputStreamWriter osw = new OutputStreamWriter(outputStream, "UTF-8");

	osw.write(msg.toString());
	osw.flush();
	osw.close();

	System.out.println(conn.getResponseCode());
	System.out.println(conn.getResponseMessage());
    }
}
