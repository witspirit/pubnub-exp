package be.witspirit.pubnub.exp;

import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Collections;

@RestController
public class Api {
    private static final Logger LOG = LoggerFactory.getLogger(Api.class);

    private PubNub pubNub;

    public Api(PubNub pubNub) {
        this.pubNub = pubNub;
    }

    @GetMapping("/subscribe/{channel}")
    public String subscribe(@PathVariable String channel) {

        pubNub.addListener(new LogListener());

        pubNub.subscribe().channels(Collections.singletonList(channel)).execute();

        return "Subscribed to "+channel;
    }

    @GetMapping("/publish/{channel}")
    public String publish(@PathVariable String channel) {

        ExpMessage demoMessage = new ExpMessage("demo", "Sample Message at " + Instant.now());

        pubNub.publish()
                .message(demoMessage)
                .channel(channel)
                .async(new PNCallback<>() {
                    @Override
                    public void onResponse(PNPublishResult result, PNStatus status)
                    {
                        LOG.debug(""+result+" - "+status);
                    }
                });

        return "Published '"+demoMessage+"' to "+channel;
    }



}
