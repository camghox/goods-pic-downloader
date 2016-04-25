import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ghox.tools.ExcelReader;
import com.ghox.tools.FileFinder;
import com.ghox.tools.ImageCompressor;
import com.ghox.tools.ImageInit;
import com.ghox.tools.PictureDownloader;

public class PictureDownloaderTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		System.out.println("ok");
		//PictureDownloader.download("http://image.9skg.com/SP_PIC/201509/19/201509101508279509290.jpg", "d:/temp.jpg");
		//ExcelReader er = new ExcelReader();
		//er.readExcel2(new File("C:\\Users\\jsb\\Documents\\zhaohuo\\webroot\\data\\lbz2.xls"), "D:/SPPIC/");
		
		//ExcelReader er = new ExcelReader();
		//er.readExcel2(new File("C:\\Users\\jsb\\Documents\\zhaohuo\\webroot\\data\\lbz1.xls"), "D:/SPPIC/");
		
		//ExcelReader er = new ExcelReader();
		//er.readExcel3(new File("C:/Users/jsb/Documents/zhaohuo/webroot/data/lbz1.xls"), "D:/SPPIC/");
		
		String dir = "d:/商品图片/商品图片";
		FileFinder fileFinder = new FileFinder();
		fileFinder.getFiles(dir, new FileFinder.FoundCallback() {
			
			public void process(String fullfile) {
				// TODO Auto-generated method stub
				try{
					FileInputStream fin = new FileInputStream(fullfile);
			        BufferedImage bi = ImageIO.read(fin);
			        ImageInit flt = new ImageInit(bi);
			        flt.changeGrey();
			        flt.getGrey();
			        flt.getBrighten();
			        bi = flt.getProcessedImg();
			        File file = new File(fullfile.replace(".png", "_0.jpg"));
			        ImageIO.write(bi, "jpg", file);
			        
					//ImageCompressor comp = new ImageCompressor();
					//comp.doCompress(fullfile, fullfile.replace(".png", "_640x640.jpg"));
					System.out.println("File=" + fullfile);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
			}
		});
		System.out.println("File Count=" + fileFinder.fileCount());
	}

}
