package com.junzhitian.basicapp.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 输入帮助类
 */
public class EditTextHelper {

    /**
     * 设置EditText内容并移动光标到尾部
     *
     * @param edittext
     * @param text
     */
    public static void setTextAndSelectEnd(EditText edittext, CharSequence text) {
        edittext.setText(text);
        edittext.setSelection(edittext.getText().length());
    }

    public static void addEmojiFilter(final EditText edittext) {
        edittext.addTextChangedListener(new TextWatcher() {
            // 是否重置了EditText的内容
            private boolean resetText;
            private int cursorPos;
            // 输入表情前EditText中的文本
            private String inputAfterText;

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int before, int count) {
                if (!resetText) {
                    cursorPos = edittext.getSelectionEnd();
                    // 这里用s.toString()而不直接用s是因为如果用s，
                    // 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
                    // inputAfterText也就改变了，那么表情过滤就失败了
                    inputAfterText = s.toString();
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (!resetText) {
                    if (count >= 2 && cursorPos
                            + count <= s.length()) {// 表情符号的字符长度最小为2
                        CharSequence input = s.subSequence(cursorPos, cursorPos
                                + count);
                        if (containsEmoji(input.toString())) {
                            resetText = true;
                            // Toast.makeText(mContext, "不支持输入Emoji表情符号",
                            // Toast.LENGTH_SHORT).show();
                            // 是表情符号就将文本还原为输入表情符号之前的内容
                            inputAfterText += filterEmoji(input.toString());
                            edittext.setText(inputAfterText);
                            CharSequence text = edittext.getText();
                            if (text instanceof Spannable) {
                                Spannable spanText = (Spannable) text;
                                Selection.setSelection(spanText, text.length());
                            }
                        }
                    }
                } else {
                    resetText = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 过滤emoji表情
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {
        StringBuilder sb = new StringBuilder();
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
                // return true;
                sb.append(codePoint);
            }
        }
        return sb.toString();
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }


    /**
     * 银行卡四位加空格
     *
     * @param mEditText
     */
    public static void bankCardNumAddSpace(final EditText mEditText) {
        mEditText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;

            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int konggeNumberB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                beforeTextLength = s.length();
                if (buffer.length() > 0) {
                    buffer.delete(0, buffer.length());
                }
                konggeNumberB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' ') {
                        konggeNumberB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3
                        || isChanged) {
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged) {
                    location = mEditText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()) {
                        if (buffer.charAt(index) == ' ') {
                            buffer.deleteCharAt(index);
                        } else {
                            index++;
                        }
                    }

                    index = 0;
                    int konggeNumberC = 0;
                    while (index < buffer.length()) {
                        if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                            buffer.insert(index, ' ');
                            konggeNumberC++;
                        }
                        index++;
                    }

                    if (konggeNumberC > konggeNumberB) {
                        location += (konggeNumberC - konggeNumberB);
                    }

                    tempChar = new char[buffer.length()];
                    buffer.getChars(0, buffer.length(), tempChar, 0);
                    String str = buffer.toString();
                    if (location > str.length()) {
                        location = str.length();
                    } else if (location < 0) {
                        location = 0;
                    }
                    mEditText.setText(str);
                    Editable etable = mEditText.getText();
                    Selection.setSelection(etable, location);
                    isChanged = false;
                }
            }
        });
    }

    /**
     * 输入框字数显示
     *
     * @param editText  输入框
     * @param tvShow    字数显示控件
     * @param maxNumber 最大值
     */
    public static void editContentNumber(final EditText editText, final TextView tvShow, final int maxNumber) {
//        editText. setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxNumber) });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvShow.setText(String.format("%d/%d", editText.getText().length(), maxNumber));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 过滤输入的第一位是0
     * @param editAccount
     */
    public static void FilterFirstZero(final EditText editAccount) {
        if (editAccount==null) {
            return;
        }
        editAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editAccount.getText().toString().matches("^0")) {//判断当前的输入第一个数是不是为0
                    editAccount.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
