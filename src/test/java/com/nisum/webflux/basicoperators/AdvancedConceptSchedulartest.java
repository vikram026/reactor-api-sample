package com.nisum.webflux.basicoperators;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.logging.Logger;

//link:http://www.vinsguru.com/reactive-programming-schedulers/

/**
 * Schedulers provides various Scheduler flavors usable by publishOn or subscribeOn :
 * •	parallel(): Optimized for fast Runnable non-blocking executions
 * •	single(): Optimized for low-latency Runnable one-off executions
 * •	elastic(): Optimized for longer executions, an alternative for blocking tasks where the number of active tasks (and threads) can grow indefinitely
 * •	boundedElastic(): Optimized for longer executions, an alternative for blocking tasks where the number of active tasks (and threads) is capped
 * •	immediate(): to immediately run submitted Runnable instead of scheduling them (somewhat of a no-op or "null object" Scheduler)
 */
@Service

public class AdvancedConceptSchedulartest {

    private final static Logger log =Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public static void main(String args[]) {


        simplePublisher();


        publisherOn_with_immidiateScheduler();


        publisherOn_with_singleScheduler();

        publisherOn_with_newSingleScheduler();


        publisherOn_with_boundedElastic_Scheduler();



        publisherOn_with_parallel_Scheduler();

         multiplePublishOnMethods();



        multiplePublishOnMethodsUsingSubscribeOn();

    }
    // SimplePublisher
    private static void simplePublisher() {
        Flux<Integer> flux = Flux.range(0, 2)
                .map(i -> {
                    log.info("Mapping for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                });

        //create a runnable with flux subscription
        Runnable r = getRunnable(flux);

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();
    }

    /**
     * If you notice the above output of simplePublisher carefully, for each subscription (cold subscription), our Publisher publishes values. The map and consumption operations are getting executed in the respective threads where subscription happens. This is the default behavior.
     *      *
     *      * However Reactor provides an easy way to switch the task execution in the reactive chain using below methods.
     *      *
     * publishOn
     * subscribeOn
     *
     * PublishOn:
     * PublishOn accepts a Scheduler which changes the task execution context for the operations in the downstream. (for all the operations or until another PublishOn switches the context in the chain). Lets take a look at the below examples.
     */
    //Scheulers.immediate():To keep the execution in the current thread.
    private static void publisherOn_with_immidiateScheduler() {
        Flux<Integer> flux = Flux.range(0, 2)
                .publishOn(Schedulers.immediate())
                .map(i -> {
                    log.info("Mapping for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                });

        //create a runnable with flux subscription
        Runnable r = getRunnable(flux);

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();
    }

    //Schedulers.single():
    //A single reusable thread. When we use this thread, all the operations of the reactive chain are executed using this thread by all the callers.
    private static void publisherOn_with_singleScheduler() {
        Flux<Integer> flux = Flux.range(0, 2)
                .publishOn(Schedulers.single())
                .map(i -> {
                    log.info("Mapping for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                });

        //create a runnable with flux subscription
        Runnable r = getRunnable(flux);

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();
    }

    /**
     * Schedulers.newSingle():
     * Same as above. But a dedicated single thread just for the caller.
     */
    private static void publisherOn_with_newSingleScheduler() {
        Flux<Integer> flux = Flux.range(0, 2)
                .publishOn(Schedulers.newSingle("newScheduler"));

        //create a runnable with flux subscription
        Runnable r = getRunnable(flux);

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();
    }

    /**
     * Schedulers.elastic():
     * This is a thread pool with unlimited threads which is no longer preferred. So DO NOT USE this option.
     */

    /**
     * Schedulers.boundedElastic():
     * This is a preferred one instead of above elastic. This thread pool contains 10 * number of CPU cores you have. Good choice for IO operations.
     */

    private static void publisherOn_with_boundedElastic_Scheduler() {
        Flux<Integer> flux = Flux.range(0, 2)
                .publishOn(Schedulers.boundedElastic());

        //create a runnable with flux subscription
        Runnable r = getRunnable(flux);

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();
    }

    /**
     * Schedulers.parallel():
     * A fixed pool of workers that is tuned for parallel work. It creates as many workers as you have CPU cores.
     */
    private static void publisherOn_with_parallel_Scheduler() {
        Flux<Integer> flux = Flux.range(0, 2)
                .publishOn(Schedulers.parallel());

        //create a runnable with flux subscription
        Runnable r = getRunnable(flux);

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();
    }
    /**
     * Multiple PublishOn Methods:
     * parallel/boundedElastic_Schedular
     */
    private static void multiplePublishOnMethods(){
        Flux<Integer> flux = Flux.range(0, 2)
                .map(i -> {
                    log.info("Mapping one for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.boundedElastic())
                .map(i -> {
                    log.info("Mapping two for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    log.info("Mapping three for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                });
        Runnable r=getRunnable(flux);
        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();

    }

    /**
     * SubscribeOn:
     * SubscribeOn method affects the context of the source emission. That is, as we had said earlier, nothing happens in the reactive chain until we subscribe! Once subscribed, the pipeline is getting executed by default on the thread which subscribed.
     * When the publishOn method is encountered, it switches the context for the downstream operations. But the source which is the Flux / Mono / or any publisher, is always executed on the current thread which subscribed.
     * This SubscribeOn method will change the behavior.
     *
     */

    private static void multiplePublishOnMethodsUsingSubscribeOn(){
        Flux<Integer> flux = Flux.range(0, 2)
                .map(i -> {
                    log.info("Mapping one for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.boundedElastic())
                .map(i -> {
                    log.info("Mapping two for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                })
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    log.info("Mapping three for " + i + " is done by thread " + Thread.currentThread().getName());
                    return i;
                });




        //creating runnable subscriber;


        Runnable r = () -> flux
                .subscribeOn(Schedulers.single())
                .subscribe(s -> {
                    log.info("Received " + s + " via " + Thread.currentThread().getName());
                });





        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");

        //lets start the threads. (this is when we are subscribing to the flux)
        log.info("Program thread :: " + Thread.currentThread().getName());
        t1.start();
        t2.start();

    }


    private static Runnable getRunnable(Flux<Integer> flux) {
        return () -> flux.subscribe(s -> {
            log.info("Received " + s + " via " + Thread.currentThread().getName());
        });

    }

}
