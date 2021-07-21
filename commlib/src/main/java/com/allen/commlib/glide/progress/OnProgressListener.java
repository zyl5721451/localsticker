package com.allen.commlib.glide.progress;

/**
 * 进度监听类
 */
public interface OnProgressListener {
    void onProgress(boolean isComplete, int percentage, long bytesRead, long totalBytes);
}
