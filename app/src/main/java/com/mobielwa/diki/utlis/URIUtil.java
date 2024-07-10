package com.mobielwa.diki.utlis;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.File;

public class URIUtil {
	
	public static String getAppFIlePath(){
		  return Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"gotye.data"+ File.separator;
	  }
	
	public static String toPath(Context context, Uri selectedImage) {
		//string[] filePathColumn = { MediaStore.Images.Media.DATA };
		Cursor cursor = context.getContentResolver().query(selectedImage, null,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex("_data");
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			cursor = null;
			return picturePath;

		} else {
			File file = new File(selectedImage.getPath());
			if (file.exists()) {
				return file.getAbsolutePath();
			}
		}
		return null;
	}

	public static String uriToPath(Context context, Uri uri) {
		String scheme = uri.getScheme();
		if (scheme.equals("content")) {
			return initFromContentUri(context, uri);
		} else if (uri.getScheme().equals("file")) {
			return initFromFile(context, uri);
		}
		return null;
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	private static String initFromContentUri(Context context, Uri uri) {
		/*String fileName = null;
		if (uri != null) {
			if (uri.getScheme().toString().compareTo("content") == 0)
			{
				Cursor cursor = context.getContentResolver().query(uri, null,
						null, null, null);
				if (cursor != null && cursor.moveToFirst()) {
					int column_index = cursor.getColumnIndex("_data");
					fileName = cursor.getString(column_index);
					cursor.close();
				}
			} else if (uri.getScheme().compareTo("file") == 0)
			{
				fileName = uri.toString();
				fileName = uri.toString().replace("file://", "");

				if (!fileName.startsWith("/mnt")) {

					fileName += "/mnt";
				}
			}
		}
		return fileName;*/
		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		String rsp="";
		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					rsp= Environment.getExternalStorageDirectory() + "/" + split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

				rsp=getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				rsp=getDataColumn(context, contentUri, selection, selectionArgs);
			}
		}
		// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {
			rsp=getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			//return uri.getPath();
			String fileName = uri.toString();
			fileName = uri.toString().replace("file://", "");

			if (!fileName.startsWith("/mnt")) {

				fileName += "/mnt";
			}
			rsp=fileName;
		}
		return rsp;
	}

	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };
		String result="";
		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
					null);
			if (cursor != null && cursor.moveToFirst()) {
				final int column_index = cursor.getColumnIndexOrThrow(column);
				result=cursor.getString(column_index);
				//return tempStr;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (cursor != null)
				cursor.close();
		}
		return result;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}


	private static String initFromFile(Context context, Uri uri) {
		String mPath = uri.getPath();
		String extension = MimeTypeMap.getFileExtensionFromUrl(mPath);
		if (TextUtils.isEmpty(extension)) {
			// getMimeTypeFromExtension() doesn't handle spaces in filenames nor
			// can it handle
			// urlEncoded strings. Let's try one last time at finding the
			// extension.
			int dotPos = mPath.lastIndexOf('.');
			if (0 <= dotPos) {
				extension = mPath.substring(dotPos + 1);
			}
		}

		return mPath;
	}
}
