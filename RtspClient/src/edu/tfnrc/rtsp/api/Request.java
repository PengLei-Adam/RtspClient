package edu.tfnrc.rtsp.api;

import edu.tfnrc.rtsp.RtspClient;

import java.net.URISyntaxException;

/**
 * C->S���͵�������Ϣ
 * Created by leip on 2015/11/26.
 */
public interface Request extends Message {

    //���ݷ���˶�������λ����ͬ
    enum Method{
        DESCRIBE, ANNOUNCE, GETPARAMETERS, OPTIONS, PAUSE, PLAY,
        RECORD, REDIRECT, SETUP, SERPARAMETERS, TEARDOWN
    };      //default��Ȩ�ޣ�

    public void setLine(Method method, String uri) throws URISyntaxException;

    public Method getMethod();

    public String getURI();

    public void handleResponse(RtspClient client, Response response);

}
