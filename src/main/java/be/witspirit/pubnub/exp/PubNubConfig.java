package be.witspirit.pubnub.exp;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubNubConfig {

    @Value("${pubnub.publishKey}")
    private String publishKey;

    @Value("${pubnub.subscribeKey}")
    private String subscribeKey;

    @Bean
    public PubNub pubNub() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPublishKey(publishKey);
        pnConfiguration.setSubscribeKey(subscribeKey);

        return new PubNub(pnConfiguration);
    }
}
