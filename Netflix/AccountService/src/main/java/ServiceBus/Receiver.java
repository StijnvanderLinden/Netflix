package ServiceBus;

import static ServiceBus.Sender.connectionString;
import static java.nio.charset.StandardCharsets.UTF_8;

import Model.Account;
import Service.AccountService;
import com.google.gson.Gson;
import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.SubscriptionClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import io.quarkus.runtime.StartupEvent;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class Receiver {

    static final Gson GSON = new Gson();

    @Inject
    private AccountService accountService;

    void onStart(@Observes StartupEvent event) throws Exception {
        SubscriptionClient subscription1Client = new SubscriptionClient(new ConnectionStringBuilder(connectionString, "topictest/subscriptions/S1"), ReceiveMode.PEEKLOCK);
        registerMessageHandlerOnClient(subscription1Client);
    }

    void registerMessageHandlerOnClient(SubscriptionClient receiveClient) throws Exception {
        // register the RegisterMessageHandler callback
        IMessageHandler messageHandler = new IMessageHandler() {
            // callback invoked when the message handler loop has obtained a message
            public CompletableFuture<Void> onMessageAsync(IMessage message) {

                // receives message is passed to callback
                if (message.getLabel().contentEquals("Account") &&
                    message.getContentType().contentEquals("application/json")) {

                    byte[] body = message.getBody();
                    Account account = GSON.fromJson(new String(body, UTF_8), Account.class);
                    account.accountId = account.id;
                    account.id = null;
                    accountService.persistAccount(account);
                }
                return receiveClient.completeAsync(message.getLockToken());
            }


            public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
                System.out.printf(exceptionPhase + "-" + throwable.getMessage());
            }
        };

        receiveClient.registerMessageHandler(
            messageHandler,
            // callback invoked when the message handler has an exception to report
            // 1 concurrent call, messages aren't auto-completed, auto-renew duration
            new MessageHandlerOptions(1, false, Duration.ofMinutes(1)));

    }

}
