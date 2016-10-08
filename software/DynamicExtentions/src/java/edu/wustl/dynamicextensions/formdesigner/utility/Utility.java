
package edu.wustl.dynamicextensions.formdesigner.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tika.parser.txt.CharsetDetector;
import org.apache.tika.parser.txt.CharsetMatch;

import edu.common.dynamicextensions.util.DirOperationsUtility;
import edu.common.dynamicextensions.util.ZipUtility;

public class Utility {

	public static void saveStreamToFileInTemp(InputStream uploadedInputStream, String uploadedFileLocation)
			throws IOException {
		// write code to save file using fileutils
		File pvFile = new File(uploadedFileLocation);
		pvFile.createNewFile();
		FileUtils.copyInputStreamToFile(uploadedInputStream, pvFile);
	}
	
	public static void downloadFile(InputStream inputStream, String tempDirName,
			String fileName, boolean unZip) throws IOException
	{
		BufferedInputStream reader = null;
		BufferedOutputStream fileWriter = null;
		DirOperationsUtility.getInstance().createNewTempDirectory(tempDirName);
		String completeFileName = tempDirName + File.separator + fileName;
		try
		{
			reader = new BufferedInputStream(inputStream);
			File file = new File(completeFileName);
			if (file.exists() && !file.delete())
			{
				System.out.println("Can not delete file : " + file);
			}
			fileWriter = new BufferedOutputStream(new FileOutputStream(file));

			byte[] buffer = new byte[1024];
			int len = reader.read(buffer);
			while (len >= 0)
			{
				fileWriter.write(buffer, 0, len);
				len = reader.read(buffer);
			}
			fileWriter.flush();

		}
		catch (IOException e)
		{
			throw new RuntimeException(
					"Exception occured while downloading the zip on server", e);

		}
		finally
		{
			if (fileWriter != null)
			{
				fileWriter.close();
			}
			if (reader != null)
			{
				reader.close();
			}
		}
		if(unZip){
			ZipUtility.extractZipToDestination(completeFileName, tempDirName);
		}
	}

	public static String detectFileCharset(String file) {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			return detectFileCharset(in);
		} catch (IOException ioe) {
			throw new RuntimeException("Error detecting charset", ioe);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	public static String detectFileCharset(InputStream in) {
		try {
			CharsetDetector detector = new CharsetDetector();
			detector.setText(in);

			CharsetMatch match = detector.detect();
			return match != null ? match.getName() : "UTF-8";
		} catch (IOException ioe) {
			throw new RuntimeException("Error detecting charset", ioe);
		}
	}
}
