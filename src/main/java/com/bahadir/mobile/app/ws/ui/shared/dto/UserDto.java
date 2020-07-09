package com.bahadir.mobile.app.ws.ui.shared.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus;

}
