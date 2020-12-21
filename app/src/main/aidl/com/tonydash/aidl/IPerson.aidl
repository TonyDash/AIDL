// IPerson.aidl
package com.tonydash.aidl.bean;
parcelable Person;

interface IPersonAidlInterface {
    void addPersonIn(in Person person);
}