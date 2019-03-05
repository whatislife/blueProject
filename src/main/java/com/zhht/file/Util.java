package com.zhht.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;
 
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
 
public class Util {
	/**
	 * 链接sftp
	 * 
	 * @param host
	 *            主机
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public ChannelSftp connect(String host, int port, String username,
			String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			System.out.println("Session创建成功");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session已连接");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("连接到主机" + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftp;
	}
 
	/**
	 * 文件重命名
	 * 
	 * @param directory 目录
	 * @param oldname 旧文件名	
	 * @param newname 新文件名
	 * @param sftp
	 */
	public void renameFile(String directory, String oldname, String newname,
			ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rename(oldname, newname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 文件上传
	 * 
	 * @param directory 目录
	 * @param uploadFile 要上传的文件名
	 * @param sftp
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(uploadFile);
			sftp.put(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 文件下载
	 * 
	 * @param directory 目录
	 * @param downloadFile 要下载文件名
	 * @param saveFile 保持的文件名
	 * @param sftp
	 */
	public void download(String directory, String downloadFile,
			String saveFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			File file = new File(saveFile);
			sftp.get(downloadFile, new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	/**
	 * 文件删除
	 * 
	 * @param directory 目录
	 * @param deleteFile 要删除的文件名
	 * @param sftp
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
			System.out.println("删除成功");
		} catch (Exception e) {
			System.out.println("删除失败");
			e.printStackTrace();
		}
	}
 
	/**
	 * 列出目录下的文件
	 * 
	 * @param directory 目录
	 * @param sftp
	 * @return
	 * @throws SftpException
	 */
	public Vector listFiles(String directory, ChannelSftp sftp)
			throws SftpException {
		return sftp.ls(directory);
	}
 
}
