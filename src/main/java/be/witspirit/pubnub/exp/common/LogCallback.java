package be.witspirit.pubnub.exp.common;

import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogCallback<T> extends PNCallback<T> {
    private static final Logger LOG = LoggerFactory.getLogger(LogCallback.class);

    @Override
    public void onResponse(T result, PNStatus status) {
        LOG.debug("" + result + " - " + status);
    }
}