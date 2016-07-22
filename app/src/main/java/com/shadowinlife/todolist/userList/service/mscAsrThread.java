package com.shadowinlife.todolist.userList.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.shadowinlife.todolist.common.FucUtil;
import com.shadowinlife.todolist.common.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shadowinlife on 16/7/10.
 */
public class mscAsrThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(mscAsrThread.class);

    // 语音识别对象
    private SpeechRecognizer mAsr;
    // 缓存
    private SharedPreferences mSharedPreferences;
    // 云端语法文件
    private String mCloudGrammar = null;
    private Context context;
    private static final String KEY_GRAMMAR_ABNF_ID = "grammar_abnf_id";
    private static final String GRAMMAR_TYPE_ABNF = "abnf";


    public mscAsrThread(Context context) {
        // 初始化识别对象
        this.context = context;
        mAsr = SpeechRecognizer.createRecognizer(context, mInitListener);
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            LOG.error("SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                LOG.error("初始化失败,错误码：" + code);
            }
            //指定引擎类型
            mCloudGrammar = FucUtil.readFile(context, "grammar_sample.abnf", "utf-8");
            mAsr.setParameter(SpeechConstant.ENGINE_TYPE, "cloud");
            mAsr.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
            mAsr.setParameter(SpeechConstant.SUBJECT, "asr");
            String mContent = new String(mCloudGrammar);
            int ret = mAsr.buildGrammar(GRAMMAR_TYPE_ABNF, mContent, mCloudGrammarListener);
            if (ret != ErrorCode.SUCCESS)
                LOG.error("语法构建失败,错误码：" + ret);
        }
    };

    /**
     * 云端构建语法监听器。
     */
    private GrammarListener mCloudGrammarListener = new GrammarListener() {
        @Override
        public void onBuildFinish(String grammarId, SpeechError error) {
            if (error == null) {
                String grammarID = new String(grammarId);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                if (!TextUtils.isEmpty(grammarId))
                    editor.putString(KEY_GRAMMAR_ABNF_ID, grammarID);
                editor.commit();
                LOG.error("语法构建成功：" + grammarId);
            } else {
                LOG.error("语法构建失败,错误码：" + error.getErrorCode());
            }
        }
    };

    /**
     * 识别监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            LOG.error("当前正在说话，音量大小：" + volume);
            LOG.error("返回音频数据：" + data.length);
        }

        @Override
        public void onBeginOfSpeech() {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onResult(final RecognizerResult result, boolean isLast) {
            if (null != result) {
                LOG.error("recognizer result：" + result.getResultString());
                String text;

                text = JsonParser.parseGrammarResult(result.getResultString());


                LOG.error("识别结果:" + text);
            } else {
                LOG.error("recognizer result : null");
            }
        }

        @Override
        public void onError(SpeechError speechError) {
            LOG.error(speechError.getErrorDescription());
        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };

    @Override
    public void run() {
        try {
            while (true) {
                int ret = mAsr.startListening(mRecognizerListener);
                if (ret != ErrorCode.SUCCESS) {
                    LOG.error("识别失败,错误码: " + ret);
                }
            }
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
    }
}


