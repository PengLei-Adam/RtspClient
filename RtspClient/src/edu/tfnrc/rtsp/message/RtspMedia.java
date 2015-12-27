package edu.tfnrc.rtsp.message;

import java.security.spec.ECField;

/**
 * ý���������
 * Created by leip on 2015/11/27.
 */
public class RtspMedia {

    private String mediaType;
    private String mediaFormat;

    private String transportPort;
    private String transportProtocol;

    private String encoding;
    private String clockrate;

    private String framerate;

    private static String SDP_CONTROL   = "a=control:";
    private static String SDP_RANGE     = "a=range:";
    private static String SDP_LENGTH    = "a=length:";
    private static String SDP_RTPMAP    = "a=rtpmap:";     //TODO:ԭ��:SDP_RTMAP
    private static String SDP_FRAMERATE = "a=framerate:";
//    private static String SDP_RECVONLY  = "a=recvonly";

    public RtspMedia(String line){      //ע��line��ʽ��˳��
        String[] tokens = line.substring(2).split(" ");

        mediaType = tokens[0];
        mediaFormat = tokens[3];

        transportPort = tokens[1];
        transportProtocol = tokens[2];
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getFrameRate() {
        return framerate;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getTransportPort() {
        return transportPort;
    }

    public String getClockrate() {
        return clockrate;
    }

    //����line��Ϣ����ý�������
    public void setAttribute(String line) throws Exception {

        /*if(line.startsWith(SDP_RECVONLY)){
            return;
        }else*/
        if(line.startsWith(SDP_CONTROL)){
                return;
        }else if(line.startsWith(SDP_RANGE)){
                return;
        }else if(line.startsWith(SDP_LENGTH)){
                return;
        }else if(line.startsWith(SDP_FRAMERATE)){
            framerate = line.substring(SDP_FRAMERATE.length());
        }else if(line.startsWith(SDP_RTPMAP)){
            String[] tokens = line.substring(SDP_RTPMAP.length()).split(" ");

            String payloadType = tokens[0];
            if(payloadType.equals(mediaFormat) == false)
                throw new Exception("Corrupted Session Description - Payload Type");

            if(tokens[1].contains("/")){
                String[] subtokens = tokens[1].split("/");
                encoding = subtokens[0];
                clockrate = subtokens[1];
            }else {
                encoding = tokens[1];
            }
            //TODO: ����"a=fmtp"
        }else   /*����ȷ��SDP����*/
            throw new Exception("Uncorrect SDP Description");
    }

    public String toString(){
        return mediaType + " " + transportPort + " " + mediaFormat + " " +
                encoding + "/" + clockrate;
    }
}
