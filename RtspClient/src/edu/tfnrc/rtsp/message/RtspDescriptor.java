package edu.tfnrc.rtsp.message;

import edu.tfnrc.rtsp.RtspConstants;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by leip on 2015/11/27.
 */
public class RtspDescriptor {

    public static String SEP = "\r\n";

    private ArrayList<RtspMedia> mediaList;

    public RtspDescriptor(String descriptor){

        mediaList = new ArrayList<RtspMedia>();

        RtspMedia mediaItem = null;

        try {
            //��descriptor�ָ�ָ���Ϊ"\r\n"
            StringTokenizer tokenizer = new StringTokenizer(descriptor, SEP);
            while(tokenizer.hasMoreTokens()){
                String token = tokenizer.nextToken();
                if(token.startsWith("m=")){
                    //�����µ�ý����   a new media item is detected
                    mediaItem = new RtspMedia(token);
                    mediaList.add(mediaItem);
                } else if(token.startsWith("a=")){
                    mediaItem.setAttribute(token);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<RtspMedia> getMediaList() {
        return mediaList;
    }
    //��ȡ�б����ǰ����Ƶ������Ƶ��
    public RtspMedia getFirstVideo(){
        RtspMedia video = null;
        for(RtspMedia mediaItem : this.mediaList){
            if(mediaItem.getMediaType().equals(RtspConstants.SDP_VIDEO_TYPE)){
                video = mediaItem;
                break;
            }
        }
        return video;
    }
}
