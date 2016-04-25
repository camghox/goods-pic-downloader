package com.ghox.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;

import com.alibaba.simpleimage.ImageRender;
import com.alibaba.simpleimage.SimpleImageException;
import com.alibaba.simpleimage.render.ReadRender;
import com.alibaba.simpleimage.render.ScaleParameter;
import com.alibaba.simpleimage.render.ScaleRender;
import com.alibaba.simpleimage.render.WriteRender;

public class ImageCompressor {

	public ImageCompressor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doCompress(String src, String dst){
		File in = new File(src);      //ԭͼƬ  
        File out = new File(dst);       //Ŀ��ͼƬ  
        ScaleParameter scaleParam = new ScaleParameter(640, 640);  //��ͼ�����Ե�1024x1024���ڣ�����1024x1024�����κδ���  
          
        FileInputStream inStream = null;  
        FileOutputStream outStream = null;  
        WriteRender wr = null;  
        try {  
            inStream = new FileInputStream(in);  
            outStream = new FileOutputStream(out);  
            ImageRender rr = new ReadRender(inStream);  
            ImageRender sr = new ScaleRender(rr, scaleParam);  
            wr = new WriteRender(sr, outStream);  
            wr.render();                            //����ͼ����  
        } catch(Exception e) {  
            e.printStackTrace();  
        } finally {  
            IOUtils.closeQuietly(inStream);         //ͼƬ�ļ��������������ǵùر�  
            IOUtils.closeQuietly(outStream);  
            if (wr != null) {  
                try {  
                    wr.dispose();                   //�ͷ�simpleImage���ڲ���Դ  
                } catch (SimpleImageException ignore) {  
                    // skip ...   
                }  
            }  
        } 
	}
	
}
