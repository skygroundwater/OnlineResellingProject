package com.example.onlineresellingproject.microservicemsg.messages;

import java.io.Serializable;

public interface MicroServiceMessage extends Serializable {
    String getCommonInfo();
}
