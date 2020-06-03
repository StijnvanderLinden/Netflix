package ServiceBus;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Sender {

    static final Gson GSON = new Gson();

    public static String connectionString = "Endpoint=sb://stijntest.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=FKLUGyArIxX7d9lYb3KHRVPJfr8rKtnQR0kIZS+Z2CM=";
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        TopicClient sendClient;

        sendClient = new TopicClient(new ConnectionStringBuilder(connectionString, "topictest"));
        sendMessagesAsync(sendClient).thenRunAsync(() -> sendClient.closeAsync());
    }

    static CompletableFuture<Void> sendMessagesAsync(TopicClient sendClient) {
        List<HashMap<String, String>> data =
            GSON.fromJson(
                "[" +
                    "{'name' = 'Einstein', 'firstName' = 'Albert'}," +
                    "{'name' = 'Heisenberg', 'firstName' = 'Werner'}," +
                    "{'name' = 'Curie', 'firstName' = 'Marie'}," +
                    "{'name' = 'Hawking', 'firstName' = 'Steven'}," +
                    "{'name' = 'Newton', 'firstName' = 'Isaac'}," +
                    "{'name' = 'Bohr', 'firstName' = 'Niels'}," +
                    "{'name' = 'Faraday', 'firstName' = 'Michael'}," +
                    "{'name' = 'Galilei', 'firstName' = 'Galileo'}," +
                    "{'name' = 'Kepler', 'firstName' = 'Johannes'}," +
                    "{'name' = 'Kopernikus', 'firstName' = 'Nikolaus'}" +
                    "]",
                new TypeToken<List<HashMap<String, String>>>() {
                }.getType());

        List<CompletableFuture> tasks = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            final String messageId = Integer.toString(i);
            Message message = new Message(GSON.toJson(data.get(i), Map.class).getBytes(UTF_8));
            message.setContentType("application/json");
            message.setLabel("Scientist");
            message.setMessageId(messageId);
            message.setTimeToLive(Duration.ofMinutes(2));
            System.out.printf("Message sending: Id = %s\n", message.getMessageId());
            tasks.add(
                sendClient.sendAsync(message).thenRunAsync(() -> {
                    System.out.printf("\tMessage acknowledged: Id = %s\n", message.getMessageId());
                }));
        }
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture<?>[tasks.size()]));
    }

}
