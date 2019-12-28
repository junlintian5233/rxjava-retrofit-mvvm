package com.juntian.basicapp.event;

/**
 * @作者:TJ
 * @时间:2018/8/20 14:36
 * @描述:EventBus事件
 */
public class MessageEvent {

    private String  message;
    private String  content;
    private int     arg;
    private boolean b;
    private Object  object;



    public MessageEvent(String message, int arg) {
        this.message = message;
        this.arg = arg;
    }

    public MessageEvent(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public MessageEvent(String message, String content) {
        this.message = message;
        this.content = content;
    }

    public MessageEvent(String message, int arg, Object object) {
        this.message = message;
        this.arg = arg;
        this.object = object;
    }

    public MessageEvent(String message, int arg, Object object, boolean b) {
        this.message = message;
        this.arg = arg;
        this.object = object;
        this.b = b;
    }


    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public MessageEvent() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


}
