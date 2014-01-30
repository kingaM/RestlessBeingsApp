/**
 * @author Gemma Bartlett (C)2011+, Dept of Computer Science, UCL
 *
 * used some code from http://www.codeofaninja.com/2011/10/android-download-file-with-progress-bar.html
 */

package com.comp1008.MrugalaBartlett.FieldKnow.Web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.comp1008.MrugalaBartlett.FieldKnow.R;
import com.comp1008.MrugalaBartlett.FieldKnow.UserRelated.SettingsActivity;

public class DownloadActivity extends Activity {

	// this is our download file asynctask
	class DownloadFileAsync extends AsyncTask<String, String, String> {

		private void _dirChecker(String dir) {
			new File(rootDir + "/my_downloads/" + dir);

		}

		@Override
		protected String doInBackground(String... aurl) {

			try {
				// connecting to url
				URL u = new URL(fileURL);
				HttpURLConnection c = (HttpURLConnection) u.openConnection();
				c.setRequestMethod("GET");
				c.setDoOutput(true);
				c.connect();

				// lenghtOfFile is used for calculating download progress
				int lenghtOfFile = c.getContentLength();

				// this is where the file will be seen after the download
				FileOutputStream f = new FileOutputStream(new File(rootDir
						+ "/my_downloads/", fileName));
				// file input is from the url
				InputStream in = c.getInputStream();

				// here's the download code
				byte[] buffer = new byte[1024];
				int len1 = 0;
				long total = 0;

				while ((len1 = in.read(buffer)) > 0) {
					total += len1; // total = total + len1
					publishProgress("" + (int) ((total * 100) / lenghtOfFile));
					f.write(buffer, 0, len1);
				}
				f.close();

			} catch (Exception e) {
				Log.d("DownloadActivity", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(String unused) {
			// dismiss the dialog after the file was downloaded
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
			try {
				FileInputStream fin = new FileInputStream(rootDir
						+ "/my_downloads/" + fileName);
				ZipInputStream zin = new ZipInputStream(fin);
				ZipEntry ze = null;
				while ((ze = zin.getNextEntry()) != null) {
					Log.v("Decompress", "Unzipping " + ze.getName());

					if (ze.isDirectory()) {
						_dirChecker(ze.getName());
					} else {
						FileOutputStream fout = new FileOutputStream(rootDir
								+ "/my_downloads/" + ze.getName());
						for (int c = zin.read(); c != -1; c = zin.read()) {
							fout.write(c);
						}

						zin.closeEntry();
						fout.close();
						Intent it = new Intent(DownloadActivity.this,
								SettingsActivity.class);
						startActivity(it);
					}

				}
				zin.close();
			} catch (Exception e) {
				Log.e("Decompress", "unzip", e);
			}
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(DIALOG_DOWNLOAD_PROGRESS);
		}

		@Override
		protected void onProgressUpdate(String... progress) {
			Log.d("DownloadActivity", progress[0]);
			mProgressDialog.setProgress(Integer.parseInt(progress[0]));
		}

	}

	// initialize our progress dialog/bar
	private ProgressDialog mProgressDialog;

	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;

	// defining file name and url

	// initialize root directory
	File rootDir = Environment.getExternalStorageDirectory();
	public String fileName = "webzip.zip";

	public String fileURL;

	// function to verify if directory exists
	public void checkAndCreateDirectory(String dirName) {
		File new_dir = new File(rootDir + dirName);
		if (!new_dir.exists()) {
			new_dir.mkdirs();
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			fileURL = extras.getString("var1");
		}

		super.onCreate(savedInstanceState);
		// setting some display
		setContentView(R.layout.download);

		// making sure the download directory exists
		checkAndCreateDirectory("/my_downloads");

		// executing the asynctask
		new DownloadFileAsync().execute(fileURL);
	}

	// our progress bar settings
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS: // we set this to 0
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog
					.setMessage("Downloading & Unzipping file (may take a while)...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setMax(100);
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
			return mProgressDialog;
		default:
			return null;
		}
	}
}