package com.example.onlineresellingproject.microservicemsg.localservices;

import com.example.onlineresellingproject.microservicemsg.messages.MicroServiceMessage;

public interface MicroServiceInterface <MSG extends MicroServiceMessage> {

    void send(MSG msg);
}
