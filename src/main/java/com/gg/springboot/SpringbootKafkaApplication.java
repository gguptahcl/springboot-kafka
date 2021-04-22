package com.gg.springboot;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.gg.springboot.kafka.Greeting;
import com.gg.springboot.kafka.MessageListener;
import com.gg.springboot.kafka.MessageProducer;

@SpringBootApplication
public class SpringbootKafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(SpringbootKafkaApplication.class, args);
       

        MessageProducer producer = context.getBean(MessageProducer.class);
        MessageListener listener = context.getBean(MessageListener.class);
        
        /*
         * Sending a Hello World message to topic 'baeldung'. 
         * Must be received by both listeners with group foo
         * and bar with containerFactory fooKafkaListenerContainerFactory
         * and barKafkaListenerContainerFactory respectively.
         * It will also be received by the listener with
         * headersKafkaListenerContainerFactory as container factory.
         */
        
        producer.sendMessage("Hello, World!");
        // commented by Gaurav Start
       // listener.latch.await(10, TimeUnit.SECONDS);
        // commented by Gaurav End
        /*
         * Sending message to a topic with 5 partitions,
         * each message to a different partition. But as per
         * listener configuration, only the messages from
         * partition 0 and 3 will be consumed.
         */
  
   // commented by Gaurav Start     
      for (int i = 0; i < 5; i++) {
            producer.sendMessageToPartition("Hello To Partitioned Topic!", i);
        }
      /*      listener.partitionLatch.await(10, TimeUnit.SECONDS);

	*/
        // commented by Gaurav End
        
        /*
         * Sending message to 'filtered' topic. As per listener
         * configuration,  all messages with char sequence
         * 'World' will be discarded.
         */
        
        // commented by Gaurav Start
       
        producer.sendMessageToFiltered("Hello Baeldung!");
        producer.sendMessageToFiltered("Hello World!");
       /* listener.filterLatch.await(10, TimeUnit.SECONDS);
        */
        
        // commented by Gaurav End
        
        /*
         * Sending message to 'greeting' topic. This will send
         * and received a java object with the help of
         * greetingKafkaListenerContainerFactory.
         */
     // commented by Gaurav Start
        /*
        producer.sendGreetingMessage(new Greeting("Greetings", "World!"));
        listener.greetingLatch.await(10, TimeUnit.SECONDS);
		*/
       // commented by Gaurav End
       context.close();
    }

    /*
    @Bean
    public MessageProducer messageProducer() {
        return new MessageProducer();
    }

    @Bean
    public MessageListener messageListener() {
        return new MessageListener();
    }
    */

}