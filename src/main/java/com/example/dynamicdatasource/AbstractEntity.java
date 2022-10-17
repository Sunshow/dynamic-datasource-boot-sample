package com.example.dynamicdatasource;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners({DefaultEntityListener.class})
public abstract class AbstractEntity {

}
