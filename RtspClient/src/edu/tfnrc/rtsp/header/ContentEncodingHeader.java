package edu.tfnrc.rtsp.header;

/**
 * TODO:�������ʱ�޷�ʶ��
 *
 * Created by leip on 2015/11/26.
 */
public class ContentEncodingHeader extends RtspBaseStringHeader{

    public static final String NAME = "Content-Encoding";   //TODO: δ�ҵ����Ͷ���Ӧͷ�ؼ���

    public ContentEncodingHeader(){
        super(NAME);            //valueδ����
    }

    public ContentEncodingHeader(String header){
        super(NAME, header);
    }
}
