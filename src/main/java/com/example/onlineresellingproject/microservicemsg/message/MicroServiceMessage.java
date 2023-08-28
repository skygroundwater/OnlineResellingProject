package com.example.onlineresellingproject.microservicemsg.message;

import java.io.Serializable;

public interface MicroServiceMessage extends Serializable {
    String getCommonInfo();
}
