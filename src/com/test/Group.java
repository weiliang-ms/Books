package com.test;

import java.io.Serializable;

public class Group implements Serializable{
        private static final long serialVersionUID = 1L;  
    	private String name;
    	private String type;
    	private String author;
    	private String have;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public String getHave() {
			return have;
		}
		public void setHave(String have) {
			this.have = have;
		}
		public Group(){
	    	  super();
	      }
		public Group(String name, String author, String type, String have){
	    	  super();
	    	  this.name = name;
	    	  this.author = author;
	    	  this.type = type;
	    	  this.have = have;
	      }
    }