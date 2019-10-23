package org.xi.quick.sys.vm;

import lombok.Data;

@Data
public class ResetPasswordModel {
    private String password;
    private String newPassword;
}
