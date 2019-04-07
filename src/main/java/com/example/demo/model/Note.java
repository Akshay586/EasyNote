package com.example.demo.model;

public class Note {
	private int noteID;
	private String noteUser;
	private String noteText;
	
	public int getNoteID() {
		return noteID;
	}
	public void setNoteID(int noteID) {
		this.noteID = noteID;
	}
	public String getNoteUser() {
		return noteUser;
	}
	public void setNoteUser(String noteUser) {
		this.noteUser = noteUser;
	}
	public String getNoteText() {
		return noteText;
	}
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
            return false;
        }
		Note note=(Note)o;
		return noteID==note.noteID &&
				noteUser==note.noteUser;
		
	}
	
	/*
	 * @Override public int hashCode() { int prime=31; int result=1; return
	 * prime*result+this.hashCode(); }
	 */
	
	public int hashCode() {
		int result=1;
		result=this.noteID;
		return 37*result+this.hashCode();
	}
}
