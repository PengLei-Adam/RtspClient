package edu.tfnrc.rtsp.request;

import edu.tfnrc.rtsp.RtspClient;
import edu.tfnrc.rtsp.api.Response;

import java.net.URISyntaxException;

/**
 * Created by leip on 2015/11/30.
 */
public class RtspDescribeRequest extends RtspRequest {

    public RtspDescribeRequest(){
        super();
    }

    public RtspDescribeRequest(String messageLine )throws URISyntaxException{
        super(messageLine);
    }

    @Override
    public byte[] getBytes() throws Exception {

        //�ж��Ƿ���Accept�ؼ��֣�û�����׳��쳣
        getHeader("Accept");
        return super.getBytes();
    }

    @Override
    public void handleResponse(RtspClient client, Response response) {
        super.handleResponse(client, response);

        try{
            client.getRequestListener().onDescriptor(client, new String(response.getEntityMessage().getContent().getBytes()));
        }catch (Exception e){
            client.getRequestListener().onError(client, e);
        }
    }
}
