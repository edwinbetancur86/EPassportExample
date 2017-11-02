package com.example.edwinb.epassportexample.root;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Edwin B on 10/20/2017.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AppScope {
}
