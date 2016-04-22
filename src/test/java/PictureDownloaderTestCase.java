import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ghox.tools.ExcelReader;
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
		
		ExcelReader er = new ExcelReader();
		er.readExcel3(new File("C:/Users/jsb/Documents/zhaohuo/webroot/data/lbz1.xls"), "D:/SPPIC/");
	}

}
