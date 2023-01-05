package com.attmanager.spring.att.payload.response;

public class JoinLibraryResponse {
    private String id;
    private String name;
    private byte[] profilePic;
    private String message;

    public JoinLibraryResponse(String id, String name, byte[] profilePic, String message) {
        this.id = id;
        this.name = name;
        this.profilePic = profilePic;
        this.message = message;
    } 
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public byte[] getProfilePic() {
        return profilePic;
    }
    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}
