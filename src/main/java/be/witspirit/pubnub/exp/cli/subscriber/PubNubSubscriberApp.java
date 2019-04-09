package be.witspirit.pubnub.exp.cli.subscriber;

import be.witspirit.pubnub.exp.common.LogListener;
import be.witspirit.pubnub.exp.common.PubNubConfig;
import com.pubnub.api.PubNub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Collections;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PubNubSubscriberApp.class, PubNubConfig.class})
public class PubNubSubscriberApp implements CommandLineRunner {

    @Autowired
    private PubNub pubNub;

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "secrets");
        System.setProperty("spring.main.web-application-type", "NONE");
        SpringApplication.run(PubNubSubscriberApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        pubNub.addListener(new LogListener());

        pubNub.subscribe().channelGroups(Collections.singletonList("parts")).execute();
    }
}
