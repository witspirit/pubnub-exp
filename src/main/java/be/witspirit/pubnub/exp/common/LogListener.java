package be.witspirit.pubnub.exp.common;

import com.google.gson.JsonElement;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogListener extends SubscribeCallback {
    private static final Logger LOG = LoggerFactory.getLogger(LogListener.class);

    @Override
    public void status(PubNub pubNub, PNStatus status) {
        LOG.debug(status.toString());
        if (status.getCategory() == PNStatusCategory.PNUnknownCategory) {
            LOG.error(status.getErrorData().toString());
        }
    }

    @Override
    public void message(PubNub pubNub, PNMessageResult message) {
        JsonElement msg = message.getMessage();
        LOG.info(msg.toString()+"@"+message.getChannel()+" from "+message.getPublisher());
    }

    @Override
    public void presence(PubNub pubNub, PNPresenceEventResult presenceEvent) {
        LOG.debug(presenceEvent.toString());
    }
}
