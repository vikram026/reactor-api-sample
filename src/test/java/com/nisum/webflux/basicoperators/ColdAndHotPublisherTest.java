package com.nisum.webflux.basicoperators;
import org.junit.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * Examples on Cold and hot publisher
 * reference:::: http://www.vinsguru.com/reactive-programming-publisher-types-cold-vs-hot/
 */
public class ColdAndHotPublisherTest {

    private final static Logger log =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * cold publishers generates new data every time a new subscriber subscribes to them.
     * Publishers by default do not produce any value unless at least 1 observer subscribes to it.
     * Publishers create new data producers for each new subscription.
     *
     * creating the cold publisher
     * @throws InterruptedException
     */

    @Test
    public void coldPublisherTest() throws InterruptedException {

        Flux<String> stringFlux = Flux.just("A","B","C","D","E","F")
                .delayElements(Duration.ofSeconds(1));

        stringFlux.subscribe(s -> log.info("Subscriber 1 : " + s)); //emits the value from beginning

        Thread.sleep(2000);

        stringFlux.subscribe(s -> log.info("Subscriber 2 : " + s));//emits the value from beginning


        Thread.sleep(4000);
    }

    /**
     * creating hotpubisher
     * Hot Publishers do not create new data producer for each new subscription (as the Cold Publisher does).
     * Instead there will be only one data producer and all the observers listen to the data produced by the single data producer.
     * So all the observers get the same data.
     * subscriber 2 is missing some of the content as hot publisher is not waiting for the subscriber
     * @throws InterruptedException
     */
    @Test
    public void hotPublisherTest() throws InterruptedException {

        Flux<String> stringFlux = Flux.just("A","B","C","D","E","F")
                .delayElements(Duration.ofSeconds(1));

        ConnectableFlux<String> connectableFlux = stringFlux.publish();
        connectableFlux.connect();
        connectableFlux.subscribe(s -> log.info("Subscriber 1 : " + s));
        Thread.sleep(3000);

        connectableFlux.subscribe(s -> log.info("Subscriber 2 : " + s)); // does not emit the values from beginning
        Thread.sleep(4000);

    }
}
