package com.example.onlineresellingproject.microservicemsg.localservice;

import com.example.onlineresellingproject.microservicemsg.message.MicroServiceMessage;

public interface MicroService<MSG extends MicroServiceMessage> {

    void send(MSG msg);
}
