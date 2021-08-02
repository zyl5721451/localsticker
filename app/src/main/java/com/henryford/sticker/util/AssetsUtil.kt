package com.henryford.sticker.util

import android.content.Context
import java.io.*

object AssetsUtil {

    fun doCopy(context: Context, assetsPath:String, desPath:String) {
        var srcFiles = context.assets.list(assetsPath)
        srcFiles?.run {
            for (srcFileName in srcFiles) {
                var outFileName = desPath + File.separator + srcFileName
                var inFileName = assetsPath + File.separator + srcFileName
                if (assetsPath.equals("")) {// for first time
                    inFileName = srcFileName
                }
                LogUtil.e("tag","========= assets: "+ assetsPath+"  filename: "+srcFileName +" infile: "+inFileName+" outFile: "+outFileName);
                try {
                    var inputStream = context.assets.open(inFileName)
                    copyAndClose(inputStream,  FileOutputStream(outFileName))
                } catch (e:IOException ) {//if directory fails exception
                    e.printStackTrace()
                    File(outFileName).mkdir()
                    doCopy(context,inFileName, outFileName)
                }
            }
        }

    }


    private fun closeQuietly(outs: OutputStream){
        try{
            outs?.run {
                this.close()
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }
    private fun closeQuietly(ins: InputStream){
        try{
            ins?.run {
                this.close()
            }
        }catch (e:IOException){
            e.printStackTrace()
        }
    }

    private fun copyAndClose(ins: InputStream, outs: OutputStream){
        copy(ins,outs)
        closeQuietly(ins)
        closeQuietly(outs)
    }

    private fun copy(ins: InputStream, outs: OutputStream){
        var buffer = ByteArray(1024)
        var n = 0
        while (-1 != ins.read(buffer).also { n = it }) {
            outs.write(buffer, 0, n)
        }
    }



}