package com.ilyastuit.http;

import com.intuit.karate.junit5.Karate;

class HttpRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }

}