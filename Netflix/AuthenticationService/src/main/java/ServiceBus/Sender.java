package ServiceBus;

import static java.nio.charset.StandardCharsets.UTF_8;

import Model.Account;
import com.google.gson.Gson;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Sender {

    static final Gson GSON = new Gson();

    public static String connectionString = "Endpoint=sb://stijntest.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=FKLUGyArIxX7d9lYb3KHRVPJfr8rKtnQR0kIZS+Z2CM=";
    public static void sendAccount(Account account) throws Exception {
        // TODO Auto-generated method stub

        TopicClient sendClient;

        sendClient = new TopicClient(new ConnectionStringBuilder(connectionString, "topictest"));
        sendMessagesAsync(sendClient, account).thenRunAsync(() -> sendClient.closeAsync());
    }

    static CompletableFuture<Void> sendMessagesAsync(TopicClient sendClient, Account account) {

        List<CompletableFuture> tasks = new ArrayList<>();
        Message message = new Message(GSON.toJson(account, Account.class).getBytes(UTF_8));
        message.setContentType("application/json");
        message.setLabel("Account");
        message.setTimeToLive(Duration.ofMinutes(2));
        System.out.printf("Sending account", message.getMessageId());
        tasks.add(
            sendClient.sendAsync(message).thenRunAsync(() -> {
                System.out.printf("Account sent!!!");
            }));
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture<?>[tasks.size()]));
    }

}
