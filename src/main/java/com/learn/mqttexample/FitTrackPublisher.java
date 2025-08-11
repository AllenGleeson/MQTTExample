import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class FitTrackPublisher {
    // Setting a user ID for the example
    private static final String USER_ID = "1234";

    public static void main(String[] args) {
        // Setting the MQTT broker URL and client ID
        String broker = "tcp://localhost:1883";
		String clientId = "Publisher";
        // Using MemoryPersistence for storing messages
		MemoryPersistence persistence = new MemoryPersistence();
        try {
            // Create an MQTT client instance and connect
            MqttClient client = new MqttClient(broker, clientId, persistence);
            client.connect();

            // Publish heartrate data
            String heartrateTopic = "fittrack/" + USER_ID + "/heartrate";
            String heartratePayload = "{\"bpm\": 85}";
            publish(client, heartrateTopic, heartratePayload, 1, true);

            // Publish steps data
            String stepsTopic = "fittrack/" + USER_ID + "/steps";
            String stepsPayload = "{\"count\": 4200}";
            publish(client, stepsTopic, stepsPayload, 0, false);

            // Disconnect the client
            client.disconnect();
            System.out.println("Publisher disconnected.");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    // Method to publish messages to the MQTT broker
    private static void publish(MqttClient client, String topic, String payload, int qos, boolean retained) throws MqttException {
        System.out.println("Publishing message: " + payload + " on topic "+ topic ); 
        MqttMessage message = new MqttMessage(payload.getBytes());
        message.setRetained(retained);
		message.setQos(qos);
        // Publish the message to the specified topic 
        client.publish(topic, message);
        System.out.println("Published message to topic: " + topic + " Payload: " + payload);
    }
}
