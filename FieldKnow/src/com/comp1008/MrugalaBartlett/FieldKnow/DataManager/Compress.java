/**
 * 
 * @author Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 *
 * taken from http://www.jondev.net/articles/Zipping_Files_with_Android_%28Programmatically%29
 * used to compress files into a zip
 * it has been modified to use ArrayList instead of an Array
 */
package com.comp1008.MrugalaBartlett.FieldKnow.DataManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import android.util.Log;

public class Compress {

	private static final int BUFFER = 2048;

	private ArrayList<String> files = new ArrayList<String>();
	private String zipFile;

	public Compress(ArrayList<String> files, String zipFile) {
		this.files = files;
		this.zipFile = zipFile;
	}

	public void zip() {
		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(zipFile);

			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					dest));

			byte data[] = new byte[BUFFER];

			for (String file : files) {
				Log.v("Compress", "Adding: " + file);
				FileInputStream fi = new FileInputStream(file);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(file.substring(file
						.lastIndexOf("/") + 1));
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}