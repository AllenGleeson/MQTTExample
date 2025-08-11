import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public class FitTrackSubscriber {

    // Setting a user ID for the example
    private static final String USER_ID = "1234";

    public static void main(String[] args) {
        // Setting the MQTT broker URL and client ID
        String broker = "tcp://localhost:1883";
		String clientId = "Subscriber";
        // Using MemoryPersistence for storing messages
		MemoryPersistence persistence = new MemoryPersistence();
        try {
            // Create an MQTT client instance and connect
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.setCallback(new FitnessSubscriber());
            client.connect();

            // Subscribe to all data for a specific user (heartrate, steps, temp)
            client.subscribe("fittrack/" + USER_ID + "/#");
            System.out.println("Subscribed to topic: " + userAllDataTopic);

            // Subscribe to heartrate data for all users
            client.subscribe("fittrack/+/heartrate");
            System.out.println("Subscribed to topic: " + allHeartrateTopic);

            // Keep subscriber running for 30 seconds to receive messages
            Thread.sleep(30000);
            // Disconnect the client
            client.disconnect();
            System.out.println("Subscriber disconnected.");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}