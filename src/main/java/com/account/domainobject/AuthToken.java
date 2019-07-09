package com.account.domainobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AuthToken implements Serializable {
    private String access_token;
    private String token_type;
}