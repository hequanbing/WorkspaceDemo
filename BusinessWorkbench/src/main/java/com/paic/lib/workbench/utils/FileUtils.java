package com.paic.lib.workbench.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * 文件和文件夹操作工具类
 *
 * @date 2013-4-19
 */
public class FileUtils {
    private static final Object mLockObject = new Object();
    /**
     * 日志对象
     */
    private static final String TAG = FileUtils.class.getSimpleName();

    /**
     * ROOT_PATH
     */
    public static String ROOT_PATH = null;

    /**
     * 获取当前设备是否存在SDCard
     *
     * @return true 存在, false 不存在
     */
    public static boolean isExistsSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * [将文件保存到SDcard方法]<BR>
     * [功能详细描述]
     *
     * @param fileName fileName
     * @param inStream inStream
     * @return 成功与否
     */
    public static boolean saveFile2SDCard(String fileName, InputStream inStream) throws IOException {
        boolean flag = false;
        FileOutputStream fs = null;

        try {
            if (!TextUtils.isEmpty(fileName) && inStream != null) {

                File file = newFileWithPath(fileName.toString());
                if ((file != null) && file.exists()) {
                    file.delete();
                    Log.w(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 文件已存在 则先删除: " + fileName.toString());
                }

                fs = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = -1;

                while ((len = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, len);
                }

                flag = true;

                Log.i(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 保存成功: " + fileName.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                fs.close();
            }
        }

        return flag;
    }

    /**
     * [将文件保存到SDcard方法]<BR>
     * [功能详细描述]
     *
     * @param fileName  fileName
     * @param dataBytes dataBytes
     * @return 成功与否
     */
    public static boolean saveFile2SDCard(String fileName, byte[] dataBytes) throws IOException {
        boolean flag = false;
        FileOutputStream fs = null;
        try {
            if (!TextUtils.isEmpty(fileName)) {
                File file = newFileWithPath(fileName.toString());
                if ((file != null) && file.exists()) {
                    file.delete();
                    Log.w(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 文件已存在 则先删除: " + fileName.toString());
                }
                fs = new FileOutputStream(file);
                fs.write(dataBytes, 0, dataBytes.length);
                fs.flush();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                fs.close();
            }
        }

        return flag;
    }


    /**
     * [将文件流转换成字节]<BR>
     * [功能详细描述]
     *
     * @param inStream InputStream
     * @return byte[]
     */
    public static byte[] readStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outstream.write(buffer, 0, len);
        }

        return outstream.toByteArray();
    }

    /**
     * 将文件流读取到字符串中
     *
     * @param inStream inStream
     * @author EX-LIJINHUA001
     */
    public static String readStreamToString(InputStream inStream) throws IOException {
        InputStreamReader isr = new InputStreamReader(inStream);
        BufferedReader br = new BufferedReader(isr);

        StringBuffer sb = new StringBuffer();
        String s = null;
        while ((s = br.readLine()) != null) {
            sb.append(s);
            sb.append("\r\n");
        }
        br.close();
        return sb.toString();
    }

    /**
     * 创建一个文件，如果其所在目录不存在时，他的目录也会被跟着创建
     *
     * @return File
     * @author EX-LIJINHUA001
     */
    public static File newFileWithPath(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }

        int index = filePath.lastIndexOf(File.separator);

        String path = "";
        if (index != -1) {
            path = filePath.substring(0, index);
            if (!TextUtils.isEmpty(path)) {
                File file = new File(path.toString());
                // 如果文件夹不存在
                if (!file.exists() && !file.isDirectory()) {
                    boolean flag = file.mkdirs();
                    if (flag) {
                        Log.i(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 创建文件夹成功：" + file.getPath());
                    } else {
                        Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 创建文件夹失败：" + file.getPath());
                    }
                }
            }
        }
        return new File(filePath);
    }


    /**
     * 删除文件，可以是单个文件或文件夹
     *
     * @param fileName 待删除的文件名
     * @return 文件删除成功返回true, 否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 删除文件失败：" + fileName + "文件不存在");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String fileName) {
        return deleteFile(fileName, false);
    }

    /**
     * 删除单个文件
     *
     * @param fileName 被删除文件的文件名
     * @return 单个文件删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(String fileName, boolean isTemp) {
        if (fileName != null) {
            String logFile = "";
            if (isTemp) {
                logFile = "临时文件";
            } else {
                logFile = "文件";
            }
            File file = new File(fileName);
            if (file.isFile() && file.exists()) {
                file.delete();
                Log.i(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 删除单个" + logFile + ":" + fileName + "成功！");
                return true;
            } else {
                Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 删除单个" + logFile + ":" + fileName + "失败！");
                return false;
            }
        }
        return false;

    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param dir 被删除目录的文件路径
     * @return 目录删除成功返回true, 否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        if (dir == null || dir.length() == 0 || "null".equalsIgnoreCase(dir) || dir.isEmpty() || "".equals(dir)) {
            return false;
        }
        //如果dir不以文件分隔符结尾，自动添加文件分隔符   
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        //如果dir对应的文件不存在，或者不是一个目录，则退出   
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 删除目录失败" + dir + "目录不存在！");
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)   
        File[] files = dirFile.listFiles();
        for (File file : files) {
            //删除子文件   
            if (file.isFile()) {
                flag = deleteFile(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            //删除子目录   
            else {
                flag = deleteDirectory(file.getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 删除目录失败");
            return false;
        }

        //删除当前目录   
        if (dirFile.delete()) {
            Log.i(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 删除目录" + dir + "成功！");
            return true;
        } else {
            Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " 删除目录" + dir + "失败！");
            return false;
        }
    }


    /**
     * 获取SD卡剩余空间
     *
     * @return
     * @author EX-LIJINHUA001
     * @date 2013-6-7
     */
    public static long getSDFreeSize() {
        return getSDFreeSize(Environment.getExternalStorageDirectory().toString());
    }

    /**
     * 获取指定目录剩余空间
     *
     * @return
     * @author EX-LIJINHUA001
     * @date 2013-6-7
     */
    public static long getSDFreeSize(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }

            android.os.StatFs statfs = new android.os.StatFs(filePath);

            long nBlocSize = statfs.getBlockSize(); // 获取SDCard上每个block的SIZE

            long nAvailaBlock = statfs.getAvailableBlocks(); // 获取可供程序使用的Block的数量

            long nSDFreeSize = nAvailaBlock * nBlocSize; // 计算 SDCard
            // 剩余大小B
            return nSDFreeSize;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.i(TAG, "httpFrame threadName:" + Thread.currentThread().getName() + " getSDFreeSize  无法计算文件夹大小 folderPath:" + filePath);
        }

        return -1;
    }

    /**
     * 拼接路径
     *
     * @param prePath      http://www.demo.com/wwwroot 或 http://www.demo.com/wwwroot/
     * @param fileNamePath index.html 或 /index.html
     * @return http://www.demo.com/wwwroot/index.html
     * @author EX-LIJINHUA001
     * @date 2013-8-12
     */
    public static String splitJointPath(String prePath, String fileNamePath) {
        if (prePath == null || fileNamePath == null) {
            return "";
        }
        String path = "";
        if (prePath.endsWith("/")) {
            if (fileNamePath.startsWith("/")) {
                path = prePath + fileNamePath.substring(1);
            } else {
                path = prePath + fileNamePath;
            }
        } else {
            if (fileNamePath.startsWith("/")) {
                path = prePath + fileNamePath;
            } else {
                path = prePath + "/" + fileNamePath;
            }
        }
        return path;
    }

    public static long getBitmapLength(Bitmap bitmap) {
        if (null == bitmap) {
            return 0;
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * 将图片输出在应用缓存当中
     *
     * @param bm     bm
     * @param format format
     * @return String
     * @author 罗春
     * 2011-7-20
     */
    public static String saveBitmap(String fileRootPath, Bitmap bm, CompressFormat format, String extension) {

        if (createFolder(fileRootPath)) {
            long bitmapSize = getBitmapLength(bm);

            FileOutputStream fos = null;
            if (getSDFreeSize(fileRootPath) <= bitmapSize) {
                Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " saveBitmap  没有足够的空间 " + fileRootPath);
                return null;
            }

            try {
                String filePath = fileRootPath + File.separator + getTimeMillisFileName();
                if (extension != null && extension.length() > 0) {
                    filePath = filePath + "." + extension;
                }

                File file = new File(filePath);
                file.createNewFile();
                fos = new FileOutputStream(file);
                bm.compress(format, 100, fos);
                return filePath;

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " saveBitmap  保存图片出错 " + e.getMessage());
            } finally {
                try {
                    if (fos != null) {
                        fos.flush();
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " saveBitmap  创建文件夹失败");
        }

        return null;
    }


    public static String saveFile(String fileRootPath, String extension, byte[] bytes) {
        return saveFile(fileRootPath, extension, bytes, false);
    }

    public static String saveFile(String fileRootPath, String extension, byte[] bytes, boolean isTemp) {
        String logFile = "";
        if (isTemp) {
            logFile = "临时文件";
        } else {
            logFile = "文件";
        }

        if (createFolder(fileRootPath)) {
            long bitmapSize = bytes.length;

            FileOutputStream fos = null;
            if (getSDFreeSize(fileRootPath) <= bitmapSize) {
                Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " saveFile  保存" + logFile + "  没有足够的空间 " + fileRootPath);
                return null;
            }

            try {
                String filePath = fileRootPath + File.separator + getTimeMillisFileName() + "." + extension;
                File file = new File(filePath);
                file.createNewFile();

                fos = new FileOutputStream(file);
                fos.write(bytes, 0, bytes.length);
                Log.i(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " saveFile  保存" + logFile + "  成功： " + filePath);
                return filePath;

            } catch (Exception e) {
                Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " saveFile  保存 " + logFile + "错误" + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.flush();
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Log.e(TAG, "httpFrame  threadName:" + Thread.currentThread().getName() + " saveFile  创建文件夹失败");
        }

        return null;
    }

    /**
     * 创建文件夹
     *
     * @param folderPath 路径
     * @return 成功与否
     */
    public static boolean createFolder(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 截取文件名
     *
     * @param filePath 路径
     * @return 路径
     */
    public static String getFileName(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {  //--截取文件名
            int i = filePath.lastIndexOf("/");
            return filePath.substring(i + 1, filePath.length());
        }
        return null;
    }

    /**
     * 获取文件长度
     *
     * @param filePath 路径
     * @return 长度
     */
    public static long getFileLength(String filePath) {
        if (filePath != null) {
            File file = new File(filePath);
            if (file.isFile()) {
                return file.length();
            }
        }
        return -1;
    }

    /**
     * 获取文件的扩展名
     *
     * @param filePath 路径
     * @return 扩展名
     */
    public static String getExtensionName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }

        int indexSeparator = filePath.lastIndexOf(File.separator);
        if (filePath.indexOf(".", indexSeparator) < 0) {
            return null;
        }

        int i = filePath.lastIndexOf(".");
        if (i >= 0) {
            return filePath.substring(i + 1, filePath.length());
        }
        return null;
    }

    /**
     * 获取文件路径字符串
     *
     * @param filePath 路径
     * @return 文件路径字符串
     */
    public static String getFolderPath(String filePath) {
        if (!TextUtils.isEmpty(filePath)) {  //--截取文件名
            int i = filePath.lastIndexOf("/");
            return filePath.substring(0, i);
        }
        return null;
    }

    /**
     * 复制文件
     *
     * @param srcPath  旧目录
     * @param destPath 新目录
     * @return 成功与否
     */
    public static boolean copyFile(File srcPath, File destPath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcPath);
            out = new FileOutputStream(destPath);

            byte[] buffer = new byte[1024];

            int length;

            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 获取毫秒数当成文件名
     *
     * @return String
     */
    public static String getTimeMillisFileName() {
        synchronized (mLockObject) {
            long curTime = System.currentTimeMillis();
            while (true) {
                long time = System.currentTimeMillis();
                if (time - curTime > 0) {
                    return "" + time;
                }
            }
        }
    }

    /**
     * initTempRootPath
     *
     * @param context context
     */
    public static void initTempRootPath(Context context) {
        ROOT_PATH = getAppStorageDir(context);
    }

    /**
     * 获取APP 存储的路径
     *
     * @param context context
     * @return String
     */
    public static String getAppStorageDir(Context context) {
        // 获取Android程序在Sd上的保存目录约定 当程序卸载时，系统会自动删除。
        File f = context.getExternalFilesDir(null);
        // 如果约定目录不存在
        if (f == null) {
            // 获取外部存储目录即 SDCard
            String storageDirectory = Environment.getExternalStorageDirectory().toString();
            File fDir = new File(storageDirectory);
            // 如果sdcard目录不可用
            if (!fDir.canWrite()) {
                // 获取可用
                storageDirectory = getSDCardDir();
                if (storageDirectory != null) {
                    storageDirectory = storageDirectory + File.separator + "pingan_upload";
                    new File(storageDirectory).mkdir();
                    Log.i(TAG, "获取临时保存目录  storageDirectory:" + storageDirectory);
                    return storageDirectory;
                }
            } else {
                storageDirectory = storageDirectory + File.separator + "pingan_upload";
                new File(storageDirectory).mkdir();
                Log.i(TAG, "获取临时保存目录  storageDirectory:" + storageDirectory);
                return storageDirectory;
            }
        } else {
            String storageDirectory = f.getAbsolutePath() + File.separator + "pingan_upload";
            new File(storageDirectory).mkdir();
            Log.i(TAG, "获取临时保存目录  storageDirectory:" + storageDirectory);
            return storageDirectory;
        }
        Log.w(TAG, "获取临时保存目录  失败");
        return null;
    }

    /**
     * 获取一个可用的存储路径（可能是内置的存储路径）
     *
     * @return 可用的存储路径
     */
    public static String getSDCardDir() {
        String pathDir = null;
        // 先获取内置sdcard路径
        File sdfile = Environment.getExternalStorageDirectory();
        // 获取内置sdcard的父路径
        File parentFile = sdfile.getParentFile();
        // 列出该父目录下的所有路径
        File[] listFiles = parentFile.listFiles();
        // 如果子路径可以写 就是拓展卡（包含内置的和外置的）

        long freeSizeMax = 0L;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].canWrite()) {
                // listFiles[i]就是SD卡路径
                String tempPathDir = listFiles[i].getAbsolutePath();
                long tempSize = getSDFreeSize(tempPathDir);
                if (tempSize > freeSizeMax) {
                    freeSizeMax = tempSize;
                    pathDir = tempPathDir;
                }
            }
        }
        return pathDir;
    }

    /**
     * 判断是否是文件
     *
     * @param filePath 路径
     * @return 是否
     */
    public static boolean isFile(String filePath) {
        return filePath != null && !"".equals(filePath.trim()) && new File(filePath).isFile();
    }

    /**
     * 文件是否存在
     *
     * @param filePath 路径
     * @return 是否
     */
    public static boolean isExists(String filePath) {
        if (filePath != null && !"".equals(filePath.trim())) {
            return new File(filePath).exists();
        }
        return false;
    }

    /**
     * 清空已有的文件内容，以便下次重新写入新的内容
     *
     * @param fileName 文件
     */
    public static void clearInfoForFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取assets资源管理器
     *
     * @param context  context
     * @param fileName fileName
     * @return String
     */
    public static String getJsonFromAssets(Context context, String fileName) throws IOException {
        //获取assets资源管理器
        AssetManager assetManager = context.getAssets();

        return getJsonFromStream(new InputStreamReader(assetManager.open(fileName)));
    }

    /**
     * getJsonFromFile
     *
     * @param file fileName
     * @return String
     */
    public static String getJsonFromFile(String file) throws IOException {
        return getJsonFromStream(new InputStreamReader(new FileInputStream(file)));
    }

    /**
     * getJsonFromStream
     *
     * @param stream fileName
     * @return String
     */
    private static String getJsonFromStream(final InputStreamReader stream) throws IOException {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        //通过管理器打开文件并读取
        BufferedReader bf = new BufferedReader(stream);
        String line;
        while ((line = bf.readLine()) != null) {
            stringBuilder.append(line);
        }

        return stringBuilder.toString();
    }

    /**
     * 检测文件是否可用
     */
    public static boolean checkFile(File f) {
        return f != null && f.exists() && f.canRead() && (f.isDirectory() || (f.isFile() && f.length() > 0));
    }

    /**
     * 删除缓存文件
     *
     * @param f f
     */
    public static void deleteCacheFile(String f) {
        if (f != null && f.length() > 0) {
            File files = new File(f);
            if (files.exists() && files.isDirectory()) {
                for (File file : files.listFiles()) {
                    if (!file.isDirectory() && (file.getName().contains(".ts") || file.getName().contains("temp"))) {
                        file.delete();
                    }
                }
            }
        }
    }

    /**
     * 删除ts缓存文件
     *
     * @param f f
     */
    public static void deleteCacheFile2TS(String f) {
        if (f != null && f.length() > 0) {
            File files = new File(f);
            if (files.exists() && files.isDirectory()) {
                for (File file : files.listFiles()) {
                    if (!file.isDirectory() && (file.getName().contains(".ts"))) {
                        file.delete();
                    }
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param f f
     */
    public static boolean deleteFile(File f) {
        return f != null && f.exists() && !f.isDirectory() && f.delete();
    }

    /**
     * 删除文件夹
     *
     * @param f f
     */
    public static void deleteDir(File f) {
        if (f != null && f.exists() && f.isDirectory()) {
            for (File file : f.listFiles()) {
                if (file.isDirectory())
                    deleteDir(file);
                file.delete();
            }
            f.delete();
        }
    }

    /**
     * File转换uri
     *
     * @param context context
     * @param uriFile uriFile
     * @return Uri
     */
    public static Uri file2Uri(Context context, File uriFile) {
        String authority = context.getPackageName() + ".fileProvider";
        Uri uri = FileProvider.getUriForFile(context, authority, uriFile);
        return uri;
    }
}
