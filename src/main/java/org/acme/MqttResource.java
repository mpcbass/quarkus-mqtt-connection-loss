package org.acme;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.Cancellable;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class MqttResource {

    private Random random = new Random();


    @Outgoing("out-prices")
    public Multi<Double> generate() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .map(x -> random.nextDouble()).onFailure().retry().indefinitely();
    }
/*
    @Incoming("in-prices")
    public CompletionStage<Void> receive(Message<String> message) {
        return message.ack();
    }
     */
}
