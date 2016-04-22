package com.ghox.tools;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoodsSpider {

	private int count = 0;
	
	public void fetch(String barcode, String url, String basePath){
		try {
			
			Document doc = Jsoup.connect(url)
					  .userAgent("Mozilla")
					  .timeout(3000)
					  .get();
			if(url.contains("tmall")){
				Elements eles = doc.select("#J_UlThumb").select("img");
				Iterator<Element> eleiter = eles.iterator();
				int i = 0;
				while(eleiter.hasNext()){
					Element img = (Element)eleiter.next();
					if(img != null){
						String imgurl = img.attr("src");
						imgurl = imgurl.replace("60x60", "430x430").replace("_.webp", "");
						savetodisk(imgurl, basePath + "" + barcode + "-" + (i++) + ".jpg");
						//System.out.println(count + "£ºTmall=>" + imgurl);
					}
				}
				count++;
			}else if(url.contains("taobao")){
				Elements eles = doc.select("#J_UlThumb").select("img");
				Iterator<Element> eleiter = eles.iterator();
				int i = 0;
				while(eleiter.hasNext()){
					Element img = (Element)eleiter.next();
					if(img != null){
						String imgurl = img.attr("src");
						imgurl = imgurl.replace("50x50", "400x400").replace("_.webp", "");
						savetodisk(imgurl, basePath + "" + barcode + "-" + (i++) + ".jpg");
						//System.out.println(count + "£ºTaobao=>" + imgurl);
					}
				}
				count++;
			}
		} catch (IOException e) {
			System.out.println("find pictures error");
			e.printStackTrace();
		}
	}//
	private void savetodisk(String url, String filepath){
		if(!(new File(filepath)).exists()){
			PictureDownloader.download("http:" + url, filepath);
		}
	}
}
