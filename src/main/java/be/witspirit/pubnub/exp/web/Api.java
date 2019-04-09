//package be.witspirit.pubnub.exp.web;
//
//import be.witspirit.pubnub.exp.common.ExpMessage;
//import be.witspirit.pubnub.exp.common.LogListener;
//import com.pubnub.api.PubNub;
//import com.pubnub.api.callbacks.PNCallback;
//import com.pubnub.api.models.consumer.PNStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.Instant;
//import java.util.Collections;
//
//@RestController
//public class Api {
//    private static final Logger LOG = LoggerFactory.getLogger(Api.class);
//
//    private PubNub pubNub;
//
//    public Api(PubNub pubNub) {
//        this.pubNub = pubNub;
//    }
//
//    @GetMapping("/channel/{channel}/subscribe")
//    public String channelSubscribe(@PathVariable String channel) {
//
//        pubNub.addListener(new LogListener());
//
//        pubNub.subscribe().channels(Collections.singletonList(channel)).execute();
//
//        return "Subscribed to " + channel;
//    }
//
//    @GetMapping("/channel/{channel}/publish")
//    public String channelPublish(@PathVariable String channel) {
//
//        ExpMessage demoMessage = new ExpMessage("demo", "Sample Message on " + channel + " at " + Instant.now());
//
//        pubNub.publish()
//                .message(demoMessage)
//                .channel(channel)
//                .async(new LogCallback<>());
//
//        return "Published '" + demoMessage + "' to " + channel;
//    }
//
//    @GetMapping("/group/{group}/subscribe")
//    public String groupSubscribe(@PathVariable String group) {
//
//        pubNub.subscribe().channelGroups(Collections.singletonList(group)).execute();
//
//        return "Subscribed to ChannelGroup " + group;
//    }
//
//    @GetMapping("/group/{group}/channel/{channel}/add")
//    public String addChannelToGroup(@PathVariable String group, @PathVariable String channel) {
//
//        pubNub.addChannelsToChannelGroup().channelGroup(group).channels(Collections.singletonList(channel)).async(new LogCallback<>());
//
//        return "Added Channel " + channel + " to ChannelGroup " + group;
//    }
//
//    @GetMapping("/group/{group}/channel/{channel}/remove")
//    public String removeChannelFromGroup(@PathVariable String group, @PathVariable String channel) {
//
//        pubNub.removeChannelsFromChannelGroup().channelGroup(group).channels(Collections.singletonList(channel)).async(new LogCallback<>());
//
//        return "Removed Channel " + channel + " from ChannelGroup " + group;
//    }
//
//
//    private static class LogCallback<T> extends PNCallback<T> {
//        @Override
//        public void onResponse(T result, PNStatus status) {
//            LOG.debug("" + result + " - " + status);
//        }
//    }
//}
