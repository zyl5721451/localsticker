package com.henryford.sticker.util

import java.io.File
import java.io.FileInputStream
import java.text.DecimalFormat

class FileSizeUtil {

    val SIZETYPE_B = 1;//获取文件大小单位为B的double值
    val SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
    val SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
    val SIZETYPE_GB = 4;//获取文件大小单位为GB的double值

    /**
     * 获取指定文件或指定文件夹的的指定单位的大小
     * @param filePath 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    fun getFolderOrFileSize(filePath: String, sizeType: Int): Double {
        var file = File(filePath)
        var blockSize = 0L
        try {
            if (file.isDirectory()) {
                blockSize = getFolderSize(file)
            } else {
                blockSize = getFileSize(file)
            }
        } catch (e: Exception) {
            e.printStackTrace();
            LogUtil.e("获取文件大小", "获取失败!")
        }
        return FormetFileSize(blockSize, sizeType)
    }

    fun getsizeFolderOrFileSize(file: File, sizeType: Int): Double {
        var blockSize = 0L
        try {
            if (file.isDirectory()) {
                blockSize = getFolderSize(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (e: Exception) {
            e.printStackTrace();
            LogUtil.e("获取文件大小", "获取失败!");
        }
        return FormetFileSize(blockSize, sizeType);
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     * @param filePath 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    fun getAutoFolderOrFileSize(filePath: String): String {
        var file = File(filePath)
        var blockSize = 0L
        try {
            if (file.isDirectory()) {
                blockSize = getFolderSize(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (e: Exception) {
            e.printStackTrace();
            LogUtil.e("获取文件大小", "获取失败!")
        }
        return FormetFileSize(blockSize)
    }

    /**
     * 获取指定文件的大小
     * @param file
     * @return
     * @throws Exception
     */
    private fun getFileSize(file: File): Long {
        var size = 0L
        if (file.exists()) {
            var fis: FileInputStream? = null
            fis = FileInputStream(file)
            size = fis.available().toLong()
            fis.close()
        } else {
            file.createNewFile();
            LogUtil.e("获取文件大小", "文件不存在!");
        }

        return size;
    }

    /**
     * 获取指定文件夹的大小
     * @param file
     * @return
     * @throws Exception
     */
    private fun getFolderSize(file: File): Long {
        var size = 0L
        var flist = file.listFiles()
        for (i in flist.indices) {
            if (flist[i].isDirectory) {
                size += getFolderSize(flist[i])
            } else {
                size += getFileSize(flist[i])
            }
        }
        return size
    }

    /**
     * 转换文件大小
     * @param fileSize
     * @return
     */
    private fun FormetFileSize(fileSize: Long): String {
        var df = DecimalFormat("#.00")
        var fileSizeString = ""
        var wrongSize = "0B"
        if (fileSize == 0L) {
            return wrongSize
        }
        if (fileSize < 1024) {
            fileSizeString = df.format(fileSize.toDouble()) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format(fileSize.toDouble() / 1024) + "KB";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format(fileSize.toDouble() / 1048576) + "MB";
        } else {
            fileSizeString = df.format(fileSize.toDouble() / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     * @param fileSize
     * @param sizeType
     * @return
     */
    private fun FormetFileSize(fileSize: Long, sizeType: Int): Double {
        var df = DecimalFormat("#.00")
        var fileSizeLong: Double = 0.0
        when (sizeType) {
            SIZETYPE_B -> {
                fileSizeLong = df.format(fileSize.toDouble()).toDouble()
            }

            SIZETYPE_KB -> {
                fileSizeLong = df.format(fileSize.toDouble() / 1024).toDouble()
            }

            SIZETYPE_MB -> {
                fileSizeLong = df.format(fileSize.toDouble() / 1048576).toDouble()
            }

            SIZETYPE_GB -> {
                fileSizeLong = df.format(fileSize.toDouble() / 1073741824).toDouble()
            }

        }
        return fileSizeLong
    }


}