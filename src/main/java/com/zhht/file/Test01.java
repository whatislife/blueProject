package com.zhht.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
 
import org.junit.Test;
 
import com.jcraft.jsch.ChannelSftp;
 
public class Test01 {
	//建立链接
	Util sf = new Util(); 
	String host = "123.57.173.185";
	int port = 22;
	String username = "root";
	String password = "Zhht2017";
	
	ChannelSftp sftp=sf.connect(host, port, username, password);
	
	@Test
	public void testList() throws Exception{
		String directory = "/home/songjian/";
		Vector v = sf.listFiles(directory, sftp);
		for(int i = 0;i < sf.listFiles(directory, sftp).size();i++){
			System.out.println(v.get(i));
		}
		System.out.println("============================");
		List<String> list = new ArrayList<String>();
		
		for(int i = 2;i < sf.listFiles(directory, sftp).size();i++){ 
			list.add(v.get(i).toString().substring(55, v.get(i).toString().length()));
			System.out.println(v.get(i).toString().substring(55, v.get(i).toString().length())); 
		}
		System.out.println(list.toString());
	}
	
	@Test
	public void testUpload(){
		String directory = "/home/songjian/testDir";
		sf.upload(directory, "D:/ftpfile/test.txt", sftp);
	}
	
	@Test
	public void testRenameFile(){
		String directory = "/home/songjian/";
		String oldname = "test.txt";
		String newname = "test2.txt";
		sf.renameFile(directory, oldname, newname, sftp);
	}
	
//	@Test
//	public void isExistFile(){
//		String directory = "/home/songjian/";
//		String oldname = "test.txt";
//		String newname = "test2.txt";
//		sf.renameFile(directory, oldname, newname, sftp);
//	}
	
	
	@Test
	public void testDelFile(){
		String directory = "/home/songjian/";
		String deleteFile = "test2.txt";
		sf.delete(directory, deleteFile, sftp);
	}
}
