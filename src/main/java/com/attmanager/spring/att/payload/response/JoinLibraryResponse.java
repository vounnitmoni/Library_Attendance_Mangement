package com.attmanager.spring.att.payload.response;

public class JoinLibraryResponse {
    private String id;
    private String name;
    private String department;
    private byte[] profilePic;
    private String message;
    private Boolean success;

    public JoinLibraryResponse(String id, String name, String department, byte[] profilePic, String message, Boolean success) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.profilePic = profilePic;
        this.message = message;
        this.success = success;
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
    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    
}
