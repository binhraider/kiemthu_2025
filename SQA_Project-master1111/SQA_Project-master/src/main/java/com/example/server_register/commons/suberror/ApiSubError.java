package com.example.server_register.commons.suberror;

import java.io.Serializable;


public interface ApiSubError extends Serializable {
    String getErrorMessage();
}
