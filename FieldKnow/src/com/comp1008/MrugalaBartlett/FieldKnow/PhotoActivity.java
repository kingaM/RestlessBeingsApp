/**
 * @author Gemma Bartlett and Kinga Mrugala (C)2011+, Dept of Computer Science, UCL
 * 
 * based on: http://www.softwarepassion.com/android-series-taking-photos-with-andorid-built-in-camera/
 */

package com.comp1008.MrugalaBartlett.FieldKnow;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public abstract class PhotoActivity extends Activity implements OnClickListener {

	private static final int IMAGE_CAPTURE = 0;
	private Button startButton;
	private Button returnButton;
	private Uri imageUri;
	private ImageView imageView;

	public String getRealPathFromURI(Uri contentUri) {

		String[] proj = { MediaColumns.DATA };
		Cursor cursor = managedQuery(contentUri, proj, // Which columns to
														// return
				null, // WHERE clause; which rows to return (all rows)
				null, // WHERE clause selection arguments (none)
				null); // Order-by clause (ascending by name)
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();

		return cursor.getString(column_index);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == IMAGE_CAPTURE) {
			if (resultCode == RESULT_OK) {
				Log.d("ANDRO_CAMERA", "Picture taken!!!");
				imageView.setImageURI(imageUri);
				String realPath = getRealPathFromURI(imageUri);
				saveData(realPath);

			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startBtn:
			startCamera();
			break;
		case R.id.finish:
			returnIntent();
			break;
		}
	}

	/**
	 * Called when the activity is first created. sets the content and gets the
	 * references to the basic widgets on the screen like {@code Button} or
	 * {@link ImageView}
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		imageView = (ImageView) findViewById(R.id.img);
		startButton = (Button) findViewById(R.id.startBtn);
		returnButton = (Button) findViewById(R.id.finish);
		startButton.setOnClickListener(this);
		returnButton.setOnClickListener(this);
	}

	protected abstract void returnIntent();

	protected abstract void saveData(String realPath);

	public void startCamera() {
		Log.d("ANDRO_CAMERA", "Starting camera on the phone...");
		String fileName = "testphoto.jpg";
		ContentValues values = new ContentValues();
		values.put(MediaColumns.TITLE, fileName);
		values.put(ImageColumns.DESCRIPTION, "Image capture by camera");
		values.put(MediaColumns.MIME_TYPE, "image/jpeg");
		imageUri = getContentResolver().insert(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
		startActivityForResult(intent, IMAGE_CAPTURE);
	}
}