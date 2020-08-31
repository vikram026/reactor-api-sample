package com.nisum.webflux.basicoperators;

import org.junit.Test;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.logging.Logger;

/**
 * link : https://www.reactiveprogramming.be/project-reactor-backpressure/
 * Understanding BackPressure concept
 * subscriber can tell the publisher that what amount of data it needs.
 * also subscriber can cancel request;
 *
 * 1.request
 * 2.cancel
 */
public class FluxAndMonoBackPressureTest {

    private final static Logger log =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * verifies the backPressure
     */
    @Test
    public void backPressureTest() {

        Flux<Integer> finiteFlux = Flux.range(1, 10)
                .log();

        StepVerifier.create(finiteFlux)
                .expectSubscription()
                .thenRequest(1)
                .expectNext(1)
                .thenRequest(1)
                .expectNext(2)
                .thenCancel()
                .verify();

    }

    /**
     * handling backbressure at the subscribe method itself as a forth argument
     *
     */
    @Test
    public void backPressure() {

        Flux<Integer> finiteFlux = Flux.range(1, 10)
                .log();

        finiteFlux.subscribe((element) -> log.info("Element is : " + element)
                , (e) -> log.info("Exception is : " + e)
                , () -> log.info("Done")
                , (subscription -> subscription.request(2)));

    }

    /**
     * cancel method used by subscriber to cancel the subscription;
     */
    @Test
    public void backPressure_cancel() {

        Flux<Integer> finiteFlux = Flux.range(1, 10)
                .log();

        finiteFlux.subscribe((element) -> log.info("Element is : " + element)
                , (e) -> log.info("Exception is : " + e)
                , () -> log.info("Done")
                , (subscription -> subscription.cancel()));

    }

    /**
     * implementation of customized backPressure overriding hookOnNext
     * method of BaseSubscriber abstract class
     */
    @Test
    public void customized_backPressure() {

        Flux<Integer> finiteFlux = Flux.range(1, 10)
                .log();

        finiteFlux.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnNext(Integer value) {
                request(1);
                log.info("Value received is : " + value);
                if(value == 4){
                    cancel();
                }

            }
        });

    }

}
