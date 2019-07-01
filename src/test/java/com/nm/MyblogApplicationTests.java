package com.nm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nm.blog.es.EsBlog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyblogApplicationTests {

	@Test
	public void contextLoads() {
		System.setProperty("es.set.netty.runtime.available.processors", "false");

	}

}
