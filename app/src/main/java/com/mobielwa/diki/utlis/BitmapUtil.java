package com.mobielwa.diki.utlis;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class BitmapUtil {
	public static final int IMAGE_MAX_SIZE_LIMIT = 100;

	private static final int IMAGE_COMPRESSION_QUALITY = 90;
	private static final int NUMBER_OF_RESIZE_ATTEMPTS = 100;
	private static final int MINIMUM_IMAGE_COMPRESSION_QUALITY = 50;

	// private int mWidth;
	// private int mHeight;

	public static String compressImage(String path) {

		Options op = new Options();
		op.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, op);

		int outWidth = op.outWidth;
		int outHeight = op.outHeight;

		int widthLimit = op.outWidth;
		int heightLimit = op.outHeight;

		int byteLimit = IMAGE_MAX_SIZE_LIMIT * 1024;

		float scaleFactor = 1.F;
		while ((outWidth * scaleFactor > widthLimit)
				|| (outHeight * scaleFactor > heightLimit)) {
			scaleFactor *= .75F;
		}
		try {
			ByteArrayOutputStream os = null;
			int attempts = 1;
			int sampleSize = 1;
			Options options = new Options();
			int quality = IMAGE_COMPRESSION_QUALITY;
			Bitmap b = null;
			do {
				options.inSampleSize = sampleSize;
				try {
					b = BitmapFactory.decodeFile(path, options);
					if (b == null) {
						return null; // Couldn't decode and it wasn't because of
					}
				} catch (OutOfMemoryError e) {
					sampleSize *= 2; // works best as a power of two
					attempts++;
					continue;
				}
			} while (b == null && attempts < NUMBER_OF_RESIZE_ATTEMPTS);

			if (b == null) {
				return null;
			}
			boolean resultTooBig = true;
			attempts = 1; // reset count for second loop
			do {
				try {
					if (options.outWidth > widthLimit
							|| options.outHeight > heightLimit
							|| (os != null && os.size() > byteLimit)) {
						int scaledWidth = (int) (outWidth * scaleFactor);
						int scaledHeight = (int) (outHeight * scaleFactor);

						b = Bitmap.createScaledBitmap(b, scaledWidth,
								scaledHeight, false);
						if (b == null) {
							return null;
						}
					}
					os = new ByteArrayOutputStream();
					b.compress(CompressFormat.JPEG, quality, os);
					int jpgFileSize = os.size();
					if (jpgFileSize > byteLimit) {
						quality = (quality * byteLimit) / jpgFileSize; // watch
						if (quality < MINIMUM_IMAGE_COMPRESSION_QUALITY) {
							quality = MINIMUM_IMAGE_COMPRESSION_QUALITY;
						}
						os = new ByteArrayOutputStream();
						b.compress(CompressFormat.JPEG, quality, os);
					}
				} catch (OutOfMemoryError e) {
					// Log.w(TAG,
				}
				scaleFactor *= .75F;
				attempts++;
				resultTooBig = os == null || os.size() > byteLimit;
			} while (resultTooBig && attempts < NUMBER_OF_RESIZE_ATTEMPTS);
			b.recycle(); // done with the bitmap, release the memory
			if (resultTooBig) {
				return path;
			}
			FileOutputStream out = null;
			try {
				String p = URIUtil.getAppFIlePath()
						+ System.currentTimeMillis() + ".jpg";
				out = new FileOutputStream(p);
				out.write(os.toByteArray());
				out.close();
				return path;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return path;

		} catch (OutOfMemoryError e) {

			return path;
		}
	}

	public static Bitmap scaleBitmap(String path, Context context) {
		Bitmap result = null;
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);

		int imageHeight = opts.outHeight;
		int imageWidth = opts.outWidth;
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		Point size = new Point();
		wm.getDefaultDisplay().getSize(size);
		int windowHeight = size.y;
		int windowWidth = size.x;
		// 计算采样�?
		int scaleX = imageWidth / windowWidth;
		int scaleY = imageHeight / windowHeight;
		int scale = 1;
		// 采样率依照最大的方向为准
		if (scaleX > scaleY && scaleY >= 1) {
			scale = scaleX;
		}
		if (scaleX < scaleY && scaleX >= 1) {
			scale = scaleY;
		}

		opts.inJustDecodeBounds = false;
		opts.inSampleSize = (int) scale;
		result = BitmapFactory.decodeFile(path, opts);

		return result;
	}

	public static void getBitmapFromUrl(final Activity context, String url, final boolean protect,
                                        BitmapListener... listeners) {

		new AsyncTask<Object, Void, Bitmap>() {
			private BitmapListener[] mListeners;

			@Override
			protected Bitmap doInBackground(Object... params) {
				URL url = null;
				try {
					url = new URL((String) params[0]);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					// cancel(true);
				}
				this.mListeners = (BitmapListener[]) params[1];
				//MyLog.log("异步加载图片�?始！ url:" + url);
				Bitmap tmpBitmap = null;
				InputStream is = null;
				Options opt = new Options();
				try {
					is = url.openStream();
					if (protect) {
						int scale = 1;
						opt.inJustDecodeBounds = true;

						Display display = context.getWindowManager()
								.getDefaultDisplay();
						Point size = new Point();
						display.getSize(size);
						int width = size.x;
						int height = size.y;

						BitmapFactory.decodeStream(is, null, opt);
						int w = opt.outWidth;
						int h = opt.outHeight;
						while (true) {
							if ((width > 0 && w <= width)
									|| (height > 0 && h <= height)) {
								break;
							}
							w /= 2;
							h /= 2;
							scale *= 2;
						}
						opt.inSampleSize = scale;
						
						is.close();
						is = url.openStream();
					}
					
					opt.inJustDecodeBounds = false;
					opt.inPreferredConfig = Bitmap.Config.RGB_565;
					tmpBitmap = BitmapFactory.decodeStream(is, null, opt);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (is != null) {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				return tmpBitmap;
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				for (BitmapListener listener : mListeners) {
					listener.done(result);
				}
			}

		}.execute(url, listeners);
	}

	public interface BitmapListener {
		void done(Bitmap result);
	}

	public static int calculateInSampleSize(Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static Bitmap getSmallBitmap(String filePath, int w, int h) {
		Bitmap dest = null;
		final Options options = new Options();
		options.inJustDecodeBounds = true;
		File file = new File(filePath);
		if (file.exists()) {
			try {
				InputStream inputStream=file.toURL().openStream();
				BitmapFactory.decodeStream(inputStream, null,
						options);
				// Calculate inSampleSize
				options.inSampleSize = calculateInSampleSize(options, w, h);

				// Decode bitmap with inSampleSize set
				options.inJustDecodeBounds = false;
				//MyLog.log("get small bitmap success");
				dest = BitmapFactory.decodeFile(filePath, options);
				inputStream.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//MyLog.log("file isn't exists");
			return null;
		}

		return dest;
	}

	public static Bitmap getBitmap(String path) {
		if (path == null) {
			return null;
		}
		Bitmap dest = null;
		final Options options = new Options();
		options.inJustDecodeBounds = true;
		File file = new File(path);
		if (file.exists()) {
			try {
				BitmapFactory.decodeStream(file.toURL().openStream(), null,
						options);
				options.inJustDecodeBounds = false;
				dest = BitmapFactory.decodeFile(path, options);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return dest;
	}

	public static String saveBitmapFile(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		File f = new File(URIUtil.getAppFIlePath());
		if (!f.isDirectory()) {
			f.mkdirs();
		}

		File file = new File(URIUtil.getAppFIlePath() + System.currentTimeMillis() + ".jpg");
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			bitmap.compress(CompressFormat.JPEG, 100, bos);
			bos.flush();
			bos.close();
			return file.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String check(String path) {
		if (path.endsWith(".jpg") || path.endsWith(".jpeg")
				|| path.endsWith(".JPG") || path.endsWith(".JPEG")) {
			return path;
		}
		Bitmap bmp = getSmallBitmap(path, 50, 50);
		return saveBitmapFile(bmp);
	}

	public static boolean checkCanSend(String path) {
		if (path.endsWith(".jpg") || path.endsWith(".jpeg")
				|| path.endsWith(".JPG") || path.endsWith(".JPEG")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkJPG(String path){
		String content=path.toLowerCase();
		if(content.endsWith(".jpg") || content.endsWith(".jpeg"))
			return true;
		return false;
	}

	public static boolean checkPNG(String path){
		String content=path.toLowerCase();
		if(content.endsWith(".png"))
			return true;
		return false;
	}


	public static boolean isImage(String fileName) {
		// TODO Auto-generated method stub
		if (fileName.endsWith(".png") || fileName.endsWith(".PNG")
				|| fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
				|| fileName.endsWith(".JPG") || fileName.endsWith(".JPEG")) {
			return true;
		}
		return false;
	}

	public static String toJPG(File absoluteFile) {

		return null;
	}

	public static void imageZoom(Bitmap source) {
		double maxSize = 1000.00;
		// 将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		source.compress(CompressFormat.JPEG, 100, baos);
		byte[] b = baos.toByteArray();
		// 将字节换成KB
		double mid = b.length / 1024;
		if (mid > maxSize) {
			double i = mid / maxSize;
			source = zoomImage(source, source.getWidth() / Math.sqrt(i),
					source.getHeight() / Math.sqrt(i));
		}
	}

	public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
				(int) height, matrix, true);
		return bitmap;
	}

	// Bitmap转byte数组
	public static byte[] Bitmap2Bytes(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(CompressFormat.JPEG, 100, baos);// png类型
		return baos.toByteArray();
	}

	// 写到sdcard�?
	public static void write(byte[] bs, String path) throws IOException {
		FileOutputStream out = new FileOutputStream(new File(path));
		out.write(bs);
		out.flush();
		out.close();
	}

	public static Bitmap rotateToDegrees(Bitmap tmpBitmap, float degrees) {
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.setRotate(degrees);
		return tmpBitmap = Bitmap.createBitmap(tmpBitmap, 0, 0, tmpBitmap.getWidth(), tmpBitmap.getHeight(), matrix,true);
	}
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	public static String insertImageToSystem(Context context, String imagePath,String imageUri) {
		String url = "";
		try {
			url = MediaStore.Images.Media.insertImage(context.getContentResolver(), imagePath, imageUri, "NewsCatPicture");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String insertImageToSystem(Context context,Bitmap bitmap) {
		String url = "";
		try {
			url = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap,"Title","Picture");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}


	public static Bitmap clearBlank(Bitmap bp, int blank) {
		int HEIGHT = bp.getHeight();
		int WIDTH = bp.getWidth();
		int top = 0, left = 0, right = 0, bottom = 0;
		int[] pixs = new int[WIDTH];
		boolean isStop;
		for (int y = 0; y < HEIGHT; y++) {
			bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
			isStop = false;
			for (int pix : pixs) {
				if (pix != Color.TRANSPARENT) {
					top = y;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		for (int y = HEIGHT - 1; y >= 0; y--) {
			bp.getPixels(pixs, 0, WIDTH, 0, y, WIDTH, 1);
			isStop = false;
			for (int pix : pixs) {
				if (pix != Color.TRANSPARENT) {
					bottom = y;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		pixs = new int[HEIGHT];
		for (int x = 0; x < WIDTH; x++) {
			bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
			isStop = false;
			for (int pix : pixs) {
				if (pix != Color.TRANSPARENT) {
					left = x;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		for (int x = WIDTH - 1; x > 0; x--) {
			bp.getPixels(pixs, 0, 1, x, 0, 1, HEIGHT);
			isStop = false;
			for (int pix : pixs) {
				if (pix != Color.TRANSPARENT) {
					right = x;
					isStop = true;
					break;
				}
			}
			if (isStop) {
				break;
			}
		}
		if (blank < 0) {
			blank = 0;
		}
		left = left - blank > 0 ? left - blank : 0;
		top = top - blank > 0 ? top - blank : 0;
		right = right + blank > WIDTH - 1 ? WIDTH - 1 : right + blank;
		bottom = bottom + blank > HEIGHT - 1 ? HEIGHT - 1 : bottom + blank;
		return Bitmap.createBitmap(bp, left, top, right - left, bottom - top);
	}
	public static String save(String path, Bitmap bitmap) throws IOException {
		//bitmap = clearBlank(bitmap, 10);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.JPEG, 100, bos);
		String rsp="";
		byte[] buffer = bos.toByteArray();
		if (buffer != null) {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
			OutputStream outputStream = new FileOutputStream(file);
			outputStream.write(buffer);
			outputStream.close();
			rsp=file.getAbsolutePath();
		}
		return rsp;
	}

	public static String bitmapToBase64(Bitmap bitmap) {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Bitmap base64ToBitmap(String base64Data) {
		byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

}
