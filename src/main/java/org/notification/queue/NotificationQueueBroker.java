package org.notification.queue;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class NotificationQueueBroker<NotificationMessage> extends MessageBroker implements Publisher<NotificationMessage>{
    final static Set<Subscriber> subscriberSet = new HashSet<>();

    @Override
    public void publish(Message<NotificationMessage> message) {
        queue.offer(message);
        System.out.println("Produced: " + message.getPayload());
        notifySubscribers();

    }

    @Override
    public void subscribe(Subscriber<NotificationMessage> subscriber) {
        subscriberSet.add(subscriber);
    }
    @Async
    private void notifySubscribers() {
        Iterator<Message<NotificationMessage>> iterator = queue.iterator();
        while(iterator.hasNext()){
            Message<NotificationMessage> message = iterator.next();
            subscriberSet.stream().parallel().forEach(s ->
                    queue.parallelStream().forEach(q ->s.onMessage(message))  );
            iterator.remove();
        }

    }
}
