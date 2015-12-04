package edu.tfnrc.rtsp.message;

import android.util.Log;
import edu.tfnrc.rtsp.RtspConstants;
import edu.tfnrc.rtsp.api.EntityMessage;
import edu.tfnrc.rtsp.api.Message;
import edu.tfnrc.rtsp.header.CSeqHeader;
import edu.tfnrc.rtsp.header.RtspHeader;
import edu.tfnrc.rtsp.header.TransportHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Rtsp������Ϣ
 *
 * Created by leip on 2015/11/27.
 */
public abstract class RtspMessage implements Message{
    //Rtsp������Ϣ������4�������
    private String line;

    private List<RtspHeader> headers;

    private CSeqHeader cseq;

    private EntityMessage entity;

    public RtspMessage(){
        headers = new ArrayList<RtspHeader>();
    }

    public RtspHeader getHeader(final String name) throws Exception {
        int index = headers.indexOf(new Object() {

            public boolean equals(Object obj){
                return name.equalsIgnoreCase(((RtspHeader)obj).getName());
            }
        });


        if(index == -1) throw new Exception("[Missing Header] " + name);
        return headers.get(index);
    }

    //��ʵ��ת��Ϊ�ֽ����ݣ��Դ�����
    public byte[] getBytes() throws Exception {
        //δ�׳��쳣��˵������CSeqHeader
        getHeader(CSeqHeader.NAME);
        //���User-Agentͷ��Ϣ
        //TODO: �����жϣ��������δʶ��
        addHeader(new RtspHeader("User-Agent", "RtspClient"));
        //��ȡEntity֮ǰ����Ϣ����
        byte[] message = toString().getBytes();
        if(getEntityMessage() != null) {
            byte[] body = entity.getBytes();
            byte[] full = new byte[message.length + body.length];

            System.arraycopy(message, 0, full, 0, message.length);
            System.arraycopy(body, 0, full, message.length, body.length);

            message = full;
        }
        return message;
    }

    /*�洢��ListתΪArray*/
    public RtspHeader[] getHeaders() {
        return headers.toArray(new RtspHeader[headers.size()]);
    }

    public CSeqHeader getCSeq() {
        return cseq;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line){
        this.line = line;
    }

    public void addHeader(RtspHeader header){

        if(header == null) return;
        if(header instanceof CSeqHeader)
            cseq = (CSeqHeader) header;
        int index = headers.indexOf(header);

        //ͬ�����µ�ͷ��Ϣ
        if(index > -1)
            headers.remove(index);
        else
            index = headers.size();

        headers.add(index, header);
    }

    @Override
    public EntityMessage getEntityMessage() {
        return entity;
    }

    public Message setEntityMessage(EntityMessage entity){
        this.entity = entity;
        return this;
    }

    public String toString(){

        StringBuffer buffer = new StringBuffer();
        buffer.append(getLine()).append("\r\n");

        for(RtspHeader header : headers)
            buffer.append(header)   /*autouse header.toString()*/
                    .append("\r\n");

        buffer.append("\r\n");
        return buffer.toString();
    }
}
