package be.witspirit.pubnub.exp.cli.publisher;

import be.witspirit.pubnub.exp.common.ExpMessage;
import be.witspirit.pubnub.exp.common.LogCallback;
import be.witspirit.pubnub.exp.common.PubNubConfig;
import com.pubnub.api.PubNub;
import com.pubnub.api.PubNubException;
import com.pubnub.api.endpoints.channel_groups.ListAllChannelGroup;
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsAllChannelsResult;
import com.pubnub.api.models.consumer.channel_group.PNChannelGroupsListAllResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.Instant;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PubNubPublisherApp.class, PubNubConfig.class})
public class PubNubPublisherApp implements CommandLineRunner {

    @Autowired
    private PubNub pubNub;

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "secrets");
        System.setProperty("spring.main.web-application-type", "NONE");
        SpringApplication.run(PubNubPublisherApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        Stream.of("partA", "partB", "partC", "PartD").forEach(channel -> {
            ExpMessage demoMessage = new ExpMessage("demo", "Sample Message on " + channel + " at " + Instant.now());

            try {
                pubNub.publish().channel(channel).message(demoMessage).sync();
            } catch (PubNubException e) {
                System.err.println("Failed to publish message on channel "+channel);
                e.printStackTrace();
            }
        });

        PNChannelGroupsListAllResult channelGroupsList = pubNub.listAllChannelGroups().sync();
        System.out.println("Channel Groups: " + channelGroupsList.getGroups());

        PNChannelGroupsAllChannelsResult partsChannels = pubNub.listChannelsForChannelGroup().channelGroup("parts").sync();
        System.out.println("Channels for 'parts' : "+partsChannels.getChannels());

        pubNub.forceDestroy();
    }
}
