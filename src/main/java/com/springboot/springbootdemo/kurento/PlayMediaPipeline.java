package com.springboot.springbootdemo.kurento;

/*import static org.kurento.tutorial.one2onecallrec.CallMediaPipeline.RECORDING_EXT;
import static org.kurento.tutorial.one2onecallrec.CallMediaPipeline.RECORDING_PATH;*/

import com.google.gson.JsonObject;
import org.kurento.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static com.springboot.springbootdemo.kurento.CallMediaPipeline.RECORDING_EXT;
import static com.springboot.springbootdemo.kurento.CallMediaPipeline.RECORDING_PATH;

public class PlayMediaPipeline {

    private static final Logger log = LoggerFactory.getLogger(PlayMediaPipeline.class);

    private final MediaPipeline pipeline;
    private WebRtcEndpoint webRtc;
    private final PlayerEndpoint player;

    public PlayMediaPipeline(KurentoClient kurento, String user, final WebSocketSession session) {
        // Media pipeline
        pipeline = kurento.createMediaPipeline();

        // Media Elements (WebRtcEndpoint, PlayerEndpoint)
        webRtc = new WebRtcEndpoint.Builder(pipeline).build();
        player = new PlayerEndpoint.Builder(pipeline, RECORDING_PATH + user + RECORDING_EXT).build();

        // Connection
        player.connect(webRtc);

        // Player listeners
        player.addErrorListener(new EventListener<ErrorEvent>() {
            @Override
            public void onEvent(ErrorEvent event) {
                log.info("ErrorEvent: {}", event.getDescription());
                sendPlayEnd(session);
            }
        });
    }

    public void sendPlayEnd(WebSocketSession session) {
        try {
            JsonObject response = new JsonObject();
            response.addProperty("id", "playEnd");
            session.sendMessage(new TextMessage(response.toString()));
        } catch (IOException e) {
            log.error("Error sending playEndOfStream message", e);
        }

        // Release pipeline
        pipeline.release();
        this.webRtc = null;
    }

    public void play() {
        player.play();
    }

    public String generateSdpAnswer(String sdpOffer) {
        return webRtc.processOffer(sdpOffer);
    }

    public MediaPipeline getPipeline() {
        return pipeline;
    }

    public WebRtcEndpoint getWebRtc() {
        return webRtc;
    }

    public PlayerEndpoint getPlayer() {
        return player;
    }

}