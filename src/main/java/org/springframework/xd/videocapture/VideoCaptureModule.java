package org.springframework.xd.videocapture;

import org.springframework.integration.endpoint.EventDrivenConsumer;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;

/**
 * Video capture consumer that connects the video capture message handler and
 * the XD input channel.
 * 
 * @author Ilayaperumal Gopinathan
 */
public class VideoCaptureModule extends EventDrivenConsumer {

	public VideoCaptureModule(SubscribableChannel inputChannel,
			MessageHandler handler) {
		super(inputChannel, handler);
	}
}
