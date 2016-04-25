package com.ghox.tools;

import java.io.File;

public class FileFinder {

	private int count;
	
	public FileFinder(){}
	
	public void getFiles(String filePath, FoundCallback foundCallback) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				this.getFiles(file.getAbsolutePath(), foundCallback);
				//filelist.add(file.getAbsolutePath());
				//System.out.println("��ʾ" + filePath + "��������Ŀ¼�����ļ�" + file.getAbsolutePath());
			} else {
				//System.out.println("��ʾ" + filePath + "��������Ŀ¼" + file.getAbsolutePath());
				count++;
				foundCallback.process(file.getAbsolutePath());
			}
		}
	}
	public int fileCount(){
		return count;
	}
	public interface FoundCallback{
		void process(String fullfile);
	}
}

