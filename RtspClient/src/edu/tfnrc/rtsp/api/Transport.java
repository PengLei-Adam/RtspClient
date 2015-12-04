package edu.tfnrc.rtsp.api;

import java.io.IOException;
import java.net.URI;

/**
 * ��������Э�飨TCP��UDP����
 * ʵ���������Ӧ�����ӷ�æ
 * Created by leip on 2015/11/26.
 */
public interface Transport {
    public void connect(URI to) throws IOException;

    public void disconnect();

    public void sendMessage(Message message) throws Exception;

    public void setTransportListener(TransportListener listener);

    public void setUserData(Object data);

    public boolean isConnected();
}
