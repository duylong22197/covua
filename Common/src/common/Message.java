/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;


public class Message implements Serializable{
    private String title;
    private Object content;

    public Message(String title, Object content) {
        this.title = title;
        this.content = content;
    }

    public Message() {
        this.title = "";
        this.content = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
    
    
}
