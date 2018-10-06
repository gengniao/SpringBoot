package cn.kongzi.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.junit.Test;

public class FastDFSTest {
	
	/**
	 * FastDFS文件上传测试
	 * @throws MyException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void uploadFile() throws FileNotFoundException, IOException, MyException{
		//1.加载FastDFS配置文件,获取配置文件的路径
		String conf_filename = this.getClass().getResource("/fastdfs-client.conf").getPath();
		//2.初始化客户端全局对象
		ClientGlobal.init(conf_filename);
		//3.创建储存客户端对象
		StorageClient storageClient = new StorageClient();
		//4.上传文件
		String[] arr = storageClient.upload_file("F:/8d5494eef01f3a29c80e4ed79a25bc315d607ce1.jpg", "jpg", null);
		System.out.println(Arrays.toString(arr));
	}
	/**
	 * FastDFS文件下载测试
	 * @throws MyException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void downloadFile() throws FileNotFoundException, IOException, MyException{
		//1.加载FastDFS配置文件,获取配置文件的路径
		String conf_filename = this.getClass().getResource("/fastdfs-client.conf").getPath();
		//2.初始化客户端全局对象
		ClientGlobal.init(conf_filename);
		//3.创建储存客户端对象
		StorageClient storageClient = new StorageClient();
		//4.上传下载
		byte[] data = storageClient.download_file("group1","M00/00/00/wKgMgVoOA_eAVmhJAAC9NkpPunk829.jpg");
		System.out.println("字节大小:"+data.length);
		FileOutputStream fos = new FileOutputStream(new File("E:\\Session1\\fastdfs-test\\1.jpg"));
		fos.write(data);
		fos.flush();
		fos.close();
	}
	/**
	 * FastDFS文件删除测试
	 * @throws MyException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void deleteFile() throws FileNotFoundException, IOException, MyException{
		//1.加载FastDFS配置文件,获取配置文件的路径
		String conf_filename = this.getClass().getResource("/fastdfs-client.conf").getPath();
		//2.初始化客户端全局对象
		ClientGlobal.init(conf_filename);
		//3.创建储存客户端对象
		StorageClient storageClient = new StorageClient();
		//4.上传下载
		int status = storageClient.delete_file("group1","M00/00/00/wKgMgVoOA_eAVmhJAAC9NkpPunk829.jpg");
		System.out.println("状态码:0:成功;1:失败                  "+status);
	}
}
