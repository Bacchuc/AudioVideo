package com.bacchus.audiovideo.audio_record

import android.media.AudioRecord
import android.util.Log

/**
 * 使用 AudioRecord 实现录音，并生成 wav
 * @time   : 2019/08/20
 * @author : Bacchus
 */
class AudioDemo private constructor() {

    companion object {
        val instance: AudioDemo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AudioDemo()
        }
    }

    // 缓冲区字节大小
    private var mBufferSizeInBytes = 0

    // AudioRecord 录音对象
    private lateinit var mAudioRecord: AudioRecord

    init {
        createDefaultAudio()
    }

    /**
     * 创建默认的录音对象
     */
    private fun createDefaultAudio() {
        // 获得缓冲区字节大小，AudioRecord 能接受的最小的 buffer 大小
        mBufferSizeInBytes = AudioRecord.getMinBufferSize(
            Constants.AUDIO_SAMPLE_RATE,
            Constants.AUDIO_CHANNEL,
            Constants.AUDIO_ENCODING
        )
        mAudioRecord = AudioRecord(
            Constants.AUDIO_INPUT,
            Constants.AUDIO_SAMPLE_RATE,
            Constants.AUDIO_CHANNEL,
            Constants.AUDIO_ENCODING,
            mBufferSizeInBytes
        )
    }

    fun startRecord() {
        Log.i("startRecord", "startRecord")

    }

    fun stopRecord() {
        Log.i("stopRecord", "stopRecord")
    }
}