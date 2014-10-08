package org.springframework.xd.videocapture;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.springframework.integration.handler.AbstractReplyProducingMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Message handler that grabs video images from the given camera url.
 *
 * @author Ilayaperumal Gopinathan
 * 
 */
public class VideoCaptureMessageHandler extends
		AbstractReplyProducingMessageHandler {

	private final FFmpegFrameGrabber frameGrabber;

	public VideoCaptureMessageHandler(String cameraUrl) {
		frameGrabber = new FFmpegFrameGrabber(cameraUrl);
		frameGrabber.setFormat("mjpeg");
	}

	@Override
	protected Object handleRequestMessage(Message<?> requestMessage) {
		IplImage img = null;
		byte[] data = null;
		try {
			img = frameGrabber.grab();
			data = new byte[img.imageSize()];
			img.getByteBuffer().get(data);
		} catch (Exception e) {
			// TODO log
			e.printStackTrace();
		}
		return MessageBuilder.withPayload(data).build();
	}

	@Override
	protected void doInit() {
		try {
			frameGrabber.start();
		} catch (Exception e) {
			// TODO log events
			e.printStackTrace();
		}
	}
}
